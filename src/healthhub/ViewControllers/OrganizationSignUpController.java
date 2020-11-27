package healthhub.ViewControllers;

import Client.ClientView.ClientMainViewController;
import healthhub.HealthHubAccessSingleton;
import healthhub.HealthHubController;
import healthhub.Views.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;
import staff.InstructorViews.InstructorMainViewController;
import staff.Models.InstructorModel;
import staff.Models.OwnerModel;
import staff.OwnerViews.OwnerMainViewController;

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


            OwnerModel newInstructor = new OwnerModel(ownerName,
                    age,
                    email,
                    phoneNumber,
                    0,
                    0,
                    organizationName,
                    0,
                    null,
                    null,
                    "test-user",
                    "healthhub1",
                    "test-user",
                    "Test-General-Database");


            int errorOrUniqueID = HealthHubController.addInstructor(newInstructor.toJson());

            if (errorOrUniqueID == 403) {
                JOptionPane.showMessageDialog(null, "ERROR: Email " + email + " has already been used");
            } else if (errorOrUniqueID == 500){
                JOptionPane.showMessageDialog(null, "ERROR: Server Error");
            } else {
                // Loads Scene for main view
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Staff/OwnerViews/OwnerMainView.fxml"));
                Parent root = loader.load();

                // Gets main view controller and passes client to it
                OwnerMainViewController viewController = loader.getController();
                //viewController.setupScene(errorOrUniqueID);

                Scene viewScene = new Scene(root);
                // Gets stage information
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(viewScene);
                window.show();
            }
        }
    }
}