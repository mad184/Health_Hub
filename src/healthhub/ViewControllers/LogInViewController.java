package healthhub.ViewControllers;

//for switching windows


import database.EmptyQueryException;
import healthhub.HealthHubController;
import healthhub.Views.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

// for alerting the user of invalid or valid password
import javax.swing.JOptionPane;

// for ActionEvent methods
import java.io.IOException;

public class LogInViewController {
    @FXML
    private TextField email, passWord;

    @FXML
    private ComboBox userTypeComboBox;

    ObservableList<String> userTypeList = FXCollections.observableArrayList("Client", "Instructor", "Manager", "Owner");


    /**
     * Function sets the values of the drop down menu (ComboBox) to the values of userTypeList
     */
    @FXML
    public void initialize() {
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
    public void onLoginButtonPushed(ActionEvent event) throws IOException, EmptyQueryException {
        String userType = (String) userTypeComboBox.getValue();

        //send the heathHub Controller the login text
        int loginSuccessCodeOrUniqueId = HealthHubController.LogIn(this.email.getText(), this.passWord.getText(), userType);


        // send account not found to LogInView
        if (loginSuccessCodeOrUniqueId == 404) {
            View.showAlertMessage("Account Not Found");
        }
        // send account exists, password is not correct to LogInView
        else if (loginSuccessCodeOrUniqueId == 401) {
            View.showAlertMessage("Account Exists, password was incorrect");
        }
        // send server error to LogInView
        else if (loginSuccessCodeOrUniqueId == 500) {
            View.showAlertMessage("Server Error Occurred");
        }

        else {
            if (userType.equals("Client")) {
                View.goToViewWithUniqueID("../../Client/ClientView/clientMainView.fxml", event, loginSuccessCodeOrUniqueId, "Client");
            } else if (userType.equals("Instructor")) {
                View.goToViewWithUniqueID("../../staff/InstructorViews/instructorMainView.fxml", event, loginSuccessCodeOrUniqueId, "Instructor");
            } else if (userType.equals("Manager")) {
                View.goToViewWithUniqueID("../../staff/ManagerViews/managerMainView.fxml", event, loginSuccessCodeOrUniqueId, "Manager");

            } else if (userType.equals("Owner")) {
                View.goToViewWithUniqueID("../../staff/OwnerViews/ownerMainView.fxml", event, loginSuccessCodeOrUniqueId, "Owner");
            } else {
                JOptionPane.showMessageDialog(null, "Unknown login user type");
                View.goToView("LoginView.fxml", event);
            }
        }
    }
}

