package healthhub.ViewControllers;

import healthhub.Views.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SignUpOptionsPageController {

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

    /**
     * Switches view to the corresponding view based on an action event
     *
     * @param event: ActionEvent when the button is clicked
     * @throws IOException: for gotoView()
     */
    @FXML
    public void onManagerButtonPushed(ActionEvent event) throws IOException {
        View.goToView("ManagerSignUpView.fxml", event);
    }
}
