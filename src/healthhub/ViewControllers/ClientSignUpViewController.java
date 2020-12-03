package healthhub.ViewControllers;


import Client.Client;
import database.EmptyQueryException;
import healthhub.HealthHubController;
import healthhub.Views.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
     * This method take the text box entry from the ClientSignUpView, verify it and then sends it to the healthHub
     * Controller to be added to the database, Then switches the view to the clients view
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
            View.showAlertMessage("age could not be read");
        }

        //regex looks for a string space string, meaning users must enter a first and last name
        if (!(name.length() > 0) || name.matches("^\\s+$")) {
            View.showAlertMessage("Name could not be read");
        } else if (!(age > 0) || !(age < 150)) {
            View.showAlertMessage("Right now only ages 1 - 149 are accepted");
        }

        //regex looks for a email in the format of anything@anything.anything
        else if (!(email.length() > 0) || !email.matches("^.+[@].+[.].+$")) {
            View.showAlertMessage("A Email is required in the format example@js.com or example@js.ca");
        } else if (!(phoneNumber.length() > 0)) {
            View.showAlertMessage("A phone number is requried");
        }

        // min length of 6, Regex looks for any spaces in the password that is one string, no spaces w/ special characters,characters,numbers
        else if (!(passWord.length() > 5) || !passWord.matches("^(\\w|\\D|\\d|\\W)*$") || passWord.matches("^\\s*$")) {
            View.showAlertMessage("A password of at at least 6 characters without spaces is required");
        } else {
            int clientUniqueID = HealthHubController.getUniqueID();

            Client newClient = new Client(
                    name,
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
                    null,
                    null);

            //Add client to the database, we will either get back a error code or a uniuqe id
            int signUpSuccessCode = HealthHubController.addClient(clientUniqueID, newClient.toJSON());

            if (signUpSuccessCode == 403) {
                View.showAlertMessage("ERROR: Email " + email + " has already been used");
            } else if (signUpSuccessCode == 500) {
                View.showAlertMessage("ERROR: Server Error");
            } else if (signUpSuccessCode == 200) {
                View.goToViewWithUniqueID("../../Client/ClientView/clientMainView.fxml", event, clientUniqueID, "Client");
            } else {
                View.showAlertMessage("ERROR: Sorry, a unknown error occurred");
            }
        }
    }
}
