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
    private TextField userName;
    @FXML
    private TextField passWord;

    @FXML
    private ComboBox userTypeComboBox;

    ObservableList<String> userTypeList = FXCollections.observableArrayList("Client", "Instructor", "Manager", "Owner");

    public HealthHubController healthHubController = new HealthHubController(null);


    @FXML
    public void initialize(){
        this.userTypeComboBox.setValue("");
        this.userTypeComboBox.setItems(userTypeList);
    }

    public void gotoView(String fxmlFileName, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    @FXML
    public void onSignUpButtonPushed(ActionEvent event) throws IOException {
        gotoView("SignUpOptionsPageView.fxml", event);
    }

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
                case "Client" -> gotoView("ClientView.fxml", event);
                case "Instructor" -> gotoView("InstructorView.fxml", event);
                case "Manager" -> gotoView("ManagerView.fxml", event);
                case "Owner" -> gotoView("OwnerView.fxml", event);
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

