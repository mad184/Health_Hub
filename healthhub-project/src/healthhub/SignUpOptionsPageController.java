package healthhub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpOptionsPageController {

    /**
     * Function switches to the gui window
     *
     * @param fxmlFileName: the .fxml file wanting to switch to
     * @param event: the ActionEvent that occured
     * @throws IOException: For the FXMLLoader .load() function
     */
    public void gotoView(String fxmlFileName, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }


    /**
     * Switches view to the corresponding view based on an action event
     *
     * @param event: ActionEvent when the button is clicked
     * @throws IOException: for gotoView()
     */
    @FXML
    public void onClientButtonPushed(ActionEvent event) throws IOException {
        gotoView("ClientSignUpView.fxml", event);
    }


    /**
     * Switches view to the corresponding view based on an action event
     *
     * @param event: ActionEvent when the button is clicked
     * @throws IOException: for gotoView()
     */
    @FXML
    public void onInstructorButtonPushed(ActionEvent event) throws IOException {
        gotoView("InstructorSignUpView.fxml", event);
    }


    /**
     * Switches view to the corresponding view based on an action event
     *
     * @param event: ActionEvent when the button is clicked
     * @throws IOException: for gotoView()
     */
    @FXML
    public void onOrganizationButtonPushed(ActionEvent event) throws IOException {
        gotoView("OrganizationSignUpView.fxml", event);
    }
}
