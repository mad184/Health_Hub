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

import org.json.JSONObject;
import staff.StaffToDB;
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
        StaffToDB db = new StaffToDB();
        String userType = (String) userTypeComboBox.getValue();
        boolean ownerToMangerFlag = false;


        //hack part 1: because owners are being stored in the DB as managers
        if (userType.equals("Owner")) {
            userType = "Manager";
            ownerToMangerFlag = true;
        }

        //send the heathHub Controller the login text
        int loginSuccessCodeOrUniqueId = HealthHubController.LogIn(this.email.getText(), this.passWord.getText(), userType);


        //hack part 2:need to change it back to go to the proper view
        if (ownerToMangerFlag) {
            userType = "Owner";
        }

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
            }
            //check to see if the owner is trying to log in as a manager
            else if (userType.equals("Manager")) {
                JSONObject ownerManger = db.getManager(loginSuccessCodeOrUniqueId);
                if (!ownerManger.has("Managers")) {
                    View.goToViewWithUniqueID("../../staff/ManagerViews/managerMainView.fxml", event, loginSuccessCodeOrUniqueId, "Manager");
                } else {
                    View.showAlertMessage("You have ownership permission, please log in as a owner");
                }
            }
            //check to see if it is a manager logging in as a owner
            else if (userType.equals("Owner")) {
                //check for this key to make sure that it has the proper permission to be a owner
                JSONObject ownerManger = db.getManager(loginSuccessCodeOrUniqueId);
                if (ownerManger.has("Managers")) {
                    View.goToViewWithUniqueID("../../staff/OwnerViews/ownerMainView.fxml", event, loginSuccessCodeOrUniqueId, "Owner");
                } else {
                    View.showAlertMessage("You do not have owner permissions");
                }
            } else {
                View.showAlertMessage("Unknown login user type");
                View.goToView("LoginView.fxml", event);
            }
        }
    }
}

