package healthhub;

//for switching windows
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

// for alerting the user of invalid or valid password
import javax.swing.JOptionPane;

// for ActionEvent methods
import java.io.IOException;

public class LogInViewController {
    @FXML
    private TextField userName, passWord;

    @FXML
    private ComboBox userTypeComboBox;

    ObservableList<String> userTypeList = FXCollections.observableArrayList("Client", "Instructor", "Manager", "Owner");

    public HealthHubController healthHubController = new HealthHubController(null);


    /**
     * Function sets the values of the drop down menu (ComboBox) to the values of userTypeList
     */
    @FXML
    public void initialize(){
        this.userTypeComboBox.setValue("");
        this.userTypeComboBox.setItems(userTypeList);
    }


    /**
     * Function switches to the gui window upon the ActionEvent
     *
     * @param event: the ActionEvent that occured
     * @throws IOException: For the FXMLLoader .load() function
     */
    @FXML
    public void onSignUpButtonPushed(ActionEvent event) throws IOException {
        View.goToView("SignUpOptionsPageView.fxml", event);
    }

    /**
     * Function gathers the username and password from the textboxes and, selection from the type of login, passes it
     * to the controller and displays the proper message upon return or switches to the proper gui
     *
     * @param event: the ActionEvent that occured
     * @throws IOException: For the FXMLLoader .load() function
     */
    @FXML
    public void onLoginButtonPushed(ActionEvent event) throws IOException {
        String userType = (String) userTypeComboBox.getValue();

        //send the heathHub Controller the login text
        int loginCode = healthHubController.LogIn(this.userName.getText(),this.passWord.getText(), userType);

        //go to the different view depending on which user we are
        if (loginCode == 200){
            /*
            ToDO:
                change .fxml file names to proper file names
             */
            switch (userType) {
                case "Client" -> View.goToView("ClientView.fxml", event);
                case "Instructor" -> View.goToView("InstructorView.fxml", event);
                case "Manager" -> View.goToView("ManagerView.fxml", event);
                case "Owner" -> View.goToView("OwnerView.fxml", event);
            }
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

