package healthhub.ViewControllers;


import Client.Client;
import Client.ClientView.ClientMainViewController;
import database.EmptyQueryException;
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

import javax.swing.*;
import java.io.IOException;

public class ClientSignUpViewController {

    @FXML
    private TextField Name, age, Email, phoneNumber, Password;


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
     * This method take the text box entry from the ClientSignUpView, verifys it and then sends it to the healthHub
     * Controller to be added to the database, Then switches the view to the clinets view
     *
     * @param event: The ActionEvent associated with the button clicked action
     * @throws IOException: For the FXMLLoader .load() function
     */
    @FXML
    public void onSignUpButtonPushed(ActionEvent event) throws IOException, EmptyQueryException {
        String name = this.Name.getText();
        String ageString = this.age.getText();
        String email = this.Email.getText();
        String phoneNumber = this.phoneNumber.getText();
        String passWord = this.Password.getText();
        int age = 0;

        try {
            age = Integer.parseInt(ageString);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "age could not be read");
            View.goToView("ClientSignUpView.fxml", event);
        }

        //regex looks for a string space string, meaning users must enter a first and last name
        if (!(name.length() > 0) || !name.matches("^([a-z]|[A-Z])+\\s([a-z]|[A-Z])+$")) {
            JOptionPane.showMessageDialog(null, "A first and last name are requried");
        } else if (!(age > 0) || !(age < 150)) {
            JOptionPane.showMessageDialog(null, "Right now only ages 1 - 149 are accepted");
            View.goToView("ClientSignUpView.fxml", event);
        }

        //regex looks for a email in the format of anything@anything.ca or anything@anything.com
        else if (!(email.length() > 0) || !email.matches("^.*[@]{1}.*(.ca|.com)$")) {
            JOptionPane.showMessageDialog(null, "A Email is required in the format example@js.com or example@js.ca");
            View.goToView("ClientSignUpView.fxml", event);
        }

        //regex looks anyting of the format #-###-###-####
        else if (!(phoneNumber.length() > 0) || !phoneNumber.matches("^[0-9][-]{1}[0-9]{3}[-]{1}[0-9]{3}[-]{1}[0-9]{4}$")) {
            JOptionPane.showMessageDialog(null, "A phone number the example format 1-306-220-5665 is required");
            View.goToView("ClientSignUpView.fxml", event);
        }

        // min length of 6, Regex looks for any spaces in the password that is one string, no spaces w/ special characters,characters,numbers
        else if (!(passWord.length() > 5) || passWord.matches("^ *$") || !passWord.matches("^(\\w|\\D|\\d|\\W)*$")) {
            JOptionPane.showMessageDialog(null, "A password of at at least 6 characters without spaces is required");
            View.goToView("ClientSignUpView.fxml", event);
        }

//        //For testing the outputs manually
//        System.out.println("Start Manual output Test for clinet sign up");
//        System.out.println("name: " + name);
//        System.out.println("age: " + age);
//        System.out.println("phoneNumber: " + phoneNumber);
//        System.out.println("email: " + email);
//        System.out.println("passWord: " + passWord);
//        System.out.println("finished manual output tesing");

        else {
            int clientUniqueID = HealthHubController.getUniqueID();

            Client newClient = new Client(name,
                    email,
                    passWord,
                    "none",
                    "none",
                    clientUniqueID,
                    age,
                    0,
                    0,
                    phoneNumber,
                    0,
                    0,
                    0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);

            //Add client to the database, we will either get back a error code or a uniuqe id
            int signUpSuccessCode = HealthHubController.addClient(clientUniqueID, newClient.toJSON());

            if (signUpSuccessCode == 403) {
                JOptionPane.showMessageDialog(null, "ERROR: Email " + email + " has already been used");
            } else if (signUpSuccessCode == 500) {
                JOptionPane.showMessageDialog(null, "ERROR: Server Error");
            } else if (signUpSuccessCode == 200) {
                View.goToViewWithUniqueID("../../Client/ClientView/clientMainView.fxml", event, clientUniqueID, "Client");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: Sorry, a unknown error occurred");
            }
        }
    }
}
