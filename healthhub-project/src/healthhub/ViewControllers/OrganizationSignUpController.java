package healthhub.ViewControllers;

import healthhub.HealthHubAccessSingleton;
import healthhub.HealthHubController;
import healthhub.Views.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import javax.swing.JOptionPane;
import java.io.IOException;

public class OrganizationSignUpController {

    @FXML
    private TextField organizationName, ownerName, age, email, phoneNumber, Password;


    /**
     * Goes to the back to the previous defined page SignUpOptionsPageView.fxml
     *
     * @param event: the ActionEvent that occured
     * @throws IOException: for View.gotoView()
     */
    @FXML
    public void backButtonPushed(ActionEvent event) throws IOException {
        View.goToView("SignUpOptionsPageView.fxml", event);
    }

    /**
     * When the Create Organization button is pressed,
     * - if the organization has already been created display a message and go back to the LoginView.fxml
     * - otherwise gather/check all the information from the textbox input, create the organization and go to the
     * owners view
     *
     * @param event: the ActionEvent that occured
     * @throws IOException: for View.gotoView()
     */
    @FXML
    public void onCreateOrganizationButtonPushed(ActionEvent event) throws IOException {

        if (HealthHubAccessSingleton.isOrganizationCreated()) {
            JOptionPane.showMessageDialog(null, "Organization has already been created");
            View.goToView("LoginView.fxml", event);
        } else {
            // check that our inputs were properly entered
            String organizationName = this.organizationName.getText();
            String ownerName = this.ownerName.getText();
            String ageString = this.age.getText();
            String email = this.email.getText();
            String phoneNumber = this.phoneNumber.getText();
            String passWord = this.Password.getText();
            int age = 0;

            //in case a error occurs while changing age from string to int
            try {
                age = Integer.parseInt(ageString);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "age could not be read");
            }

            //regex looks for any number of white space
            if (!(ownerName.length() > 0) || ownerName.matches("^ *$")) {
                JOptionPane.showMessageDialog(null, "A Name is required");
            } else if (!(organizationName.length() > 0) || organizationName.matches("^ *$")) {
                JOptionPane.showMessageDialog(null, "A Name is required");
            } else if (HealthHubController.organizationExists()) {
                JOptionPane.showMessageDialog(null, "Organization Is already Created/Name is taken");
            } else if (!(age > 0) || !(age < 150)) {
                JOptionPane.showMessageDialog(null, "Right now only ages 1 - 149 are accepted");
            }

            //regex looks for empty spaces entered
            else if (!(email.length() > 0) || email.matches("^ *$")) {
                JOptionPane.showMessageDialog(null, "A Email is required");
            }

            //regex looks for empty spaces entered
            else if (!(phoneNumber.length() > 0) || phoneNumber.matches("^ *$")) {
                JOptionPane.showMessageDialog(null, "A userName is required");
            }

            //regex looks for empty spaces entered
            else if (!(passWord.length() > 0) || passWord.matches("^ *$")) {
                JOptionPane.showMessageDialog(null, "A Password is required");
            }
//            //For testing the outputs manually
//            System.out.println("Start Manual output Test for Organization Sign Up input");
//            System.out.println("Organization Name: " + organizationName);
//            System.out.println("Owner Name: " + ownerName);
//            System.out.println("Owner Age: " + age);
//            System.out.println("phoneNumber: " + phoneNumber);
//            System.out.println("email: " + email);
//            System.out.println("passWord: " + passWord);
//            System.out.println("finished manual input testing");

           /*
          ToDO:
           - ensure we are passing the owner as a manager and storing as such
           - get controller name to pass the unique id of the owner
           - ensure file names are correct
            */


            //create barebones json to add to database
            JSONObject ownerJson = new JSONObject();
            ownerJson.put("name", ownerName);
            ownerJson.put("email", email);
            ownerJson.put("password", passWord);
            ownerJson.put("age", age);
            ownerJson.put("phoneNumber", phoneNumber);
            ownerJson.put("organization", organizationName);

            //create organization json to add to database
            JSONObject organizationJson = new JSONObject();
            organizationJson.put("organizationName", organizationName);
            organizationJson.put("ownerName", ownerName);

            int errorOrUniqueId = HealthHubController.addManager(ownerJson);

            switch (errorOrUniqueId) {
                case 403 -> JOptionPane.showMessageDialog(null, "ERROR: Email " + email + " has already been used");

                case 500 -> JOptionPane.showMessageDialog(null, "ERROR: Server Error");

                default -> {
                    int successCode = HealthHubController.createOrganization(organizationName, organizationJson);
                    switch (successCode) {
                        case 500:
                            JOptionPane.showMessageDialog(null, "ERROR: Server Error");
                            break;
                        case -1:
                            JOptionPane.showMessageDialog(null, "ERROR: Unknown Error");
                            break;
                        case 200:
                            //TODO: Uncomment after merge, ensure file names/controller names are correct
//                          InstructorMainViewController viewController = loader.getController();
//                           viewController.setupScene(instructorController.instructorID);
//                           View.goToView("OwnerView.fxml", event);
                    }
                }
            }
        }
    }
}