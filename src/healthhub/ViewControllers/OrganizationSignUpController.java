package healthhub.ViewControllers;

import Client.ClientView.ClientMainViewController;
import database.EmptyQueryException;
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
    public void onCreateOrganizationButtonPushed(ActionEvent event) throws IOException, EmptyQueryException {

        if (HealthHubController.organizationExists()) {
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
                View.goToView("OrganizationSignUpView.fxml", event);
            }

            //regex looks for any number of white space
            if (!(ownerName.length() > 0) || ownerName.matches("^ *$")) {
                JOptionPane.showMessageDialog(null, "A Name is required");
                View.goToView("OrganizationSignUpView.fxml", event);
            } else if (!(organizationName.length() > 0) || organizationName.matches("^ *$")) {
                JOptionPane.showMessageDialog(null, "A Name is required");
                View.goToView("OrganizationSignUpView.fxml", event);
            } else if (HealthHubController.organizationExists()) {
                JOptionPane.showMessageDialog(null, "Organization Is already Created/Name is taken");
                View.goToView("OrganizationSignUpView.fxml", event);
            } else if (!(age > 0) || !(age < 150)) {
                JOptionPane.showMessageDialog(null, "Right now only ages 1 - 149 are accepted");
                View.goToView("OrganizationSignUpView.fxml", event);
            }

            //regex looks for empty spaces entered
            else if (!(email.length() > 0) || email.matches("^ *$")) {
                JOptionPane.showMessageDialog(null, "A Email is required");
                View.goToView("OrganizationSignUpView.fxml", event);
            }

            //regex looks for empty spaces entered
            else if (!(phoneNumber.length() > 0) || phoneNumber.matches("^ *$")) {
                JOptionPane.showMessageDialog(null, "A userName is required");
                View.goToView("OrganizationSignUpView.fxml", event);
            }

            //regex looks for empty spaces entered
            else if (!(passWord.length() > 0) || passWord.matches("^ *$")) {
                JOptionPane.showMessageDialog(null, "A Password is required");
                View.goToView("OrganizationSignUpView.fxml", event);
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


            int uniqeID = HealthHubController.getUniqueID();
            OwnerModel newOwner = new OwnerModel(ownerName,
                    age,
                    email,
                    phoneNumber,
                    0,
                    0,
                    organizationName,
                    uniqeID,
                    null,
                    null,
                    "test-user",
                    "healthhub1",
                    "test-user",
                    "Test-General-Database");

            //add owner
            int successCodeAddOwner = HealthHubController.addManager(uniqeID, newOwner.toJson());

            //add orgianziaiton
            JSONObject jsonOrganization = new JSONObject();
            jsonOrganization.put("name", organizationName);
            int successCodeAddOrganization = HealthHubController.createOrganization(organizationName, jsonOrganization);

            //check returned success codes
            if (successCodeAddOwner == 403) {
                JOptionPane.showMessageDialog(null, "ERROR: Email " + email);
                View.goToView("OrganizationSignUpView.fxml", event);

            } else if (successCodeAddOwner == 500 || successCodeAddOrganization == 500) {
                JOptionPane.showMessageDialog(null, "ERROR: Server Error, check terminal for error codes");
                System.out.println("Error code add owner: " + successCodeAddOwner);
                System.out.println("Error code add Organziation " + successCodeAddOrganization);
                View.goToView("OrganizationSignUpView.fxml", event);

            } else if (successCodeAddOwner == 200 && successCodeAddOrganization == 200) {
                System.out.println("Added Owner + org successfully");
                View.goToViewWithUniqueID("../../Staff/OwnerViews/OwnerMainView.fxml", event, uniqeID, "Owner");

            } else {
                JOptionPane.showMessageDialog(null, "ERROR: Sorry a unknown error occurred, check terminal for error codes");
                System.out.println("Error code add owner: " + successCodeAddOwner);
                System.out.println("Error code add Organziation " + successCodeAddOrganization);
                View.goToView("OrganizationSignUpView.fxml", event);
            }
        }
    }
}