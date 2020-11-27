package healthhub.ViewControllers;

//for switching windows

import Client.ClientView.ClientMainViewController;
import database.EmptyQueryException;
import healthhub.HealthHubController;
import healthhub.Views.View;
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
import staff.Controllers.OwnerController;
import staff.InstructorViews.InstructorMainViewController;
import staff.ManagerViews.ManagerMainViewController;
import staff.OwnerViews.OwnerMainViewController;

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
        }
        // send account exists, password is not correct to LogInView
        else if (loginSuccessCodeOrUniqueId == 401) {
            JOptionPane.showMessageDialog(null, "Account Exists, password was incorrect");
        }
        // send server error to LogInView
        else if (loginSuccessCodeOrUniqueId == 500) {
            JOptionPane.showMessageDialog(null, "Server Error Occurred");
        }

        else {

            if (userType.equals("Client")) {
                // Loads Scene for main view
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Client/ClientView/clientMainView.fxml"));
                Parent root = loader.load();

                // Gets main view controller and passes client to it
                ClientMainViewController viewController = loader.getController();
                viewController.setupScene(loginSuccessCodeOrUniqueId);

                Scene viewScene = new Scene(root);
                // Gets stage information
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(viewScene);
                window.show();
            }
            else if (userType.equals("Instructor")) {
                // Loads Scene for main view
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Staff/InstructorViews/instructorMainView.fxml"));
                Parent root = loader.load();

                // Gets main view controller and passes client to it
                InstructorMainViewController viewController = loader.getController();
//                viewController.setupScene(loginSuccessCodeOrUniqueId);

                Scene viewScene = new Scene(root);
                // Gets stage information
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(viewScene);
                window.show();
            } else if (userType.equals("Manager")) {
                // Loads Scene for main view
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Staff/ManagerViews/managerMainView.fxml"));
                Parent root = loader.load();

                // Gets main view controller and passes client to it
                ManagerMainViewController viewController = loader.getController();
//                viewController.setupScene(loginSuccessCodeOrUniqueId);

                Scene viewScene = new Scene(root);
                // Gets stage information
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(viewScene);
                window.show();
            } else if (userType.equals("Owner")) {
                // Loads Scene for main view
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Staff/OwnerViews/ownerMainView.fxml"));
                Parent root = loader.load();

                // Gets main view controller and passes client to it
                OwnerMainViewController viewController = loader.getController();
//                viewController.setupScene(loginSuccessCodeOrUniqueId);

                Scene viewScene = new Scene(root);
                // Gets stage information
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(viewScene);
                window.show();
            }
        }
    }
}

