package healthhub.ViewControllers;


import healthhub.HealthHubController;
import healthhub.Views.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import javax.swing.JOptionPane;
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
    public void onSignUpButtonPushed(ActionEvent event) throws IOException {
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
        }

        //regex looks for any number of white space
        if (!(name.length() > 0) || name.matches("^ *$")) {
            JOptionPane.showMessageDialog(null, "A Name is required");
        }

        else if (!(age > 0) || !(age < 150) ) {
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

//        //For testing the outputs manually
//        System.out.println("Start Manual output Test for clinet sign up");
//        System.out.println("name: " + name);
//        System.out.println("age: " + age);
//        System.out.println("phoneNumber: " + phoneNumber);
//        System.out.println("email: " + email);
//        System.out.println("passWord: " + passWord);
//        System.out.println("finished manual output tesing");


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("email", email);
        jsonObject.put("password", passWord);
        jsonObject.put("age", age);
        jsonObject.put("phoneNumber", phoneNumber);

        //Add clinet to the database, we will either get back a error code or a uniuqe id
        int errorOrUniqueID = HealthHubController.addClient(jsonObject);

        switch (errorOrUniqueID) {
            case 403 -> JOptionPane.showMessageDialog(null, "ERROR: Email " + email + " has already been used");
            case 500 -> JOptionPane.showMessageDialog(null, "ERROR: Server Error");
            default -> {
//                ClientMainViewController viewController = loader.getController();
//                viewController.setupScene(clientController.getClientID());
//                View.goToView("ClientMainView.fxml", event);
            }
        }
    }
}
