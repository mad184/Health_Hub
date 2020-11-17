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
     * Switches view to the corresponding view based on an action event
     *
     * @param event: ActionEvent when the button is clicked
     * @throws IOException: for gotoView()
     */
    @FXML
    public void onClientButtonPushed(ActionEvent event) throws IOException {
        View.goToView("ClientSignUpView.fxml", event);
    }


    /**
     * Switches view to the corresponding view based on an action event
     *
     * @param event: ActionEvent when the button is clicked
     * @throws IOException: for gotoView()
     */
    @FXML
    public void onInstructorButtonPushed(ActionEvent event) throws IOException {
        View.goToView("InstructorSignUpView.fxml", event);
    }


    /**
     * Switches view to the corresponding view based on an action event
     *
     * @param event: ActionEvent when the button is clicked
     * @throws IOException: for gotoView()
     */
    @FXML
    public void onOrganizationButtonPushed(ActionEvent event) throws IOException {
        View.goToView("OrganizationSignUpView.fxml", event);
    }
}
