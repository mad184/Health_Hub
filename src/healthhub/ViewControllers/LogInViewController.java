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
            JOptionPane.showMessageDialog(null, "Account Not Found");
            View.goToView("LoginView.fxml", event);
        }
        // send account exists, password is not correct to LogInView
        else if (loginSuccessCodeOrUniqueId == 401) {
            JOptionPane.showMessageDialog(null, "Account Exists, password was incorrect");
            View.goToView("LoginView.fxml", event);
        }
        // send server error to LogInView
        else if (loginSuccessCodeOrUniqueId == 500) {
            JOptionPane.showMessageDialog(null, "Server Error Occurred");
            View.goToView("LoginView.fxml", event);
        }

        else {
            if (userType.equals("Client")) {
                View.goToViewWithUniqueID("../../Client/ClientView/clientMainView.fxml", event, loginSuccessCodeOrUniqueId, "Client");
            } else if (userType.equals("Instructor")) {
                View.goToViewWithUniqueID("../../Staff/InstructorViews/instructorMainView.fxml", event, loginSuccessCodeOrUniqueId, "Client");
            } else if (userType.equals("Manager")) {
                View.goToViewWithUniqueID("../../Staff/ManagerViews/managerMainView.fxml", event, loginSuccessCodeOrUniqueId, "Client");

            } else if (userType.equals("Owner")) {
                View.goToViewWithUniqueID("../../Staff/OwnerViews/ownerMainView.fxml", event, loginSuccessCodeOrUniqueId, "Client");
            } else {
                JOptionPane.showMessageDialog(null, "Unknown login user type");
                View.goToView("LoginView.fxml", event);
            }
        }
    }
}

