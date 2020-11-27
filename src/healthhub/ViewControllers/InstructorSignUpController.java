package healthhub.ViewControllers;

import Client.ClientView.ClientMainViewController;
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


import javax.swing.JOptionPane;
import java.io.IOException;

public class InstructorSignUpController {
    @FXML
    private TextField Name, age, Email, Password, organziation, phoneNumber;


    /**
     * Goes to the back to the previous defined page SignUpOptionsPageView.fxml
     *
     * @param event: the ActionEvent that occured
     * @throws IOException: for View.gotoView()
     */
    @FXML
    public void backButtonPushed(ActionEvent event) throws IOException {
        View.goToView("LoginView.fxml", event);
    }


    /**
     * Gathers the information entered into the textboxes and
     *
     * @param event: the ActionEvent that occured
     * @throws IOException: For the FXMLLoader .load() function
     */
    @FXML
    public void onSignUpButtonPushed(ActionEvent event) throws IOException {
        // check that our inputs were properly entered
        String name = this.Name.getText();
        String ageString = this.age.getText();
        String email = this.Email.getText();
        String organization = this.organziation.getText();
        String passWord = this.Password.getText();
        String phoneNumber = this.phoneNumber.getText();
        int age = 0;

        try {
            age = Integer.parseInt(ageString);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "age could not be read");
        }
        //regex looks for any number of white space
        if (!(name.length() > 0) || name.matches("^ *$")) {
            JOptionPane.showMessageDialog(null, "A Name is required");
        } else if (!(age > 0) || !(age < 150)) {
            JOptionPane.showMessageDialog(null, "Right now only ages 1 - 149 are accepted");
        }

        //regex looks for empty spaces entered
        else if (!(email.length() > 0) || email.matches("^ *$")) {
            JOptionPane.showMessageDialog(null, "A Email is required");
        }

        //regex looks for empty spaces entered
        else if (!(organization.length() > 0) || organization.matches("^ *$")) {
            JOptionPane.showMessageDialog(null, "A userName is required");
        }

        //regex looks for empty spaces entered
        else if (!(passWord.length() > 0) || passWord.matches("^ *$")) {
            JOptionPane.showMessageDialog(null, "A Password is required");
        }
        //regex looks for empty spaces entered
        else if (!(phoneNumber.length() > 0) || phoneNumber.matches("^ *$")) {
            JOptionPane.showMessageDialog(null, "A phone number is required");
        } else if ((!HealthHubController.organizationExists())) {
            JOptionPane.showMessageDialog(null, "A valid organization must be given");
        }


////        //For testing the outputs manually
//        System.out.println("Start Manual output Test for Instructor Sign Up input");
//        System.out.println("name: " + name);
//        System.out.println("age: " + age);
//        System.out.println("phoneNumber: " + phoneNumber);
//        System.out.println("organziation: " + organization);
//        System.out.println("email: " + email);
//        System.out.println("passWord: " + passWord);
//        System.out.println("finsished manual input testing");


        JSONObject instructroJsonObject = new JSONObject();
        instructroJsonObject.put("name", name);
        instructroJsonObject.put("email", email);
        instructroJsonObject.put("password", passWord);
        instructroJsonObject.put("age", age);
        instructroJsonObject.put("phoneNumber", phoneNumber);
        instructroJsonObject.put("organization", organization);


        int errorOrUniqueID = HealthHubController.addInstructor(instructroJsonObject);

        if (errorOrUniqueID == 403) {
            JOptionPane.showMessageDialog(null, "ERROR: Email " + email + " has already been used");
        } else if (errorOrUniqueID == 500) {
            JOptionPane.showMessageDialog(null, "ERROR: Server Error");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Instructor/InstructorView/instructorMainView.fxml"));
            Parent root = loader.load();

            // Gets main view controller and passes client to it
            ClientMainViewController viewController = loader.getController();
//                viewController.setupScene(errorOrUniqueID);

            Scene viewScene = new Scene(root);
            // Gets stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(viewScene);
            window.show();
        }
    }
}
