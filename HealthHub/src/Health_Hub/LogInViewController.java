package Health_Hub;



import Health_Hub.HealthHubController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.JOptionPane;
import java.io.IOException;

public class LogInViewController{
    @FXML
    private TextField userName;
    @FXML
    private TextField passWord;

    @FXML
    public void onNewClientButtonPushed(ActionEvent event) throws IOException {
        System.out.println("new client pressed");
    }
    @FXML
    public void onNewInstructorButtonPushed(ActionEvent event) throws IOException {
        System.out.println("new Instructor pressed");
    }
    @FXML
    public void onNewManagerButtonPushed(ActionEvent event) throws IOException {
        System.out.println("new Manager pressed");
    }

    public HealthHubController healthHubController = new HealthHubController(null);
    @FXML
    public void onLoginButtonPushed(ActionEvent event) throws IOException {

        //send the heathHub Controller the login text
        int loginCode = healthHubController.LogIn(this.userName.getText(),this.passWord.getText());


        //display different messages to view depending on code we get back
        if (loginCode == 200){
            //start up client || instructor || manager views
            ////???????????????????????????????????????????????????????????????????????????///////////////
            ////???????????????????????????????????????????????????????????????????????????///////////////
        }
        // send account not found to LogInView
        else if(loginCode == 404){
            JOptionPane.showMessageDialog(null, "Account Not Found");
        }
        // send account exists, password is not correct to LogInView
        else if (loginCode == 401){
            JOptionPane.showMessageDialog(null, "Account Exists, password was incorrect");
        }
        // send server error to LogInView
        else if (loginCode == 500){
            JOptionPane.showMessageDialog(null, "Server Error Occurred");
        }
        //unknown error
        else{
            JOptionPane.showMessageDialog(null, "Sorry, an unknown error occurred");
        }
    }

}

