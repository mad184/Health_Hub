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


    public void gotoView(String fxmlFileName, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    @FXML
    public void onClientButtonPushed(ActionEvent event) throws IOException {
        gotoView("ClientSignUpView.fxml", event);
    }


    @FXML
    public void onInstructorButtonPushed(ActionEvent event) throws IOException {
        gotoView("InstructorSignUpView.fxml", event);
    }


    @FXML
    public void onOrganizationButtonPushed(ActionEvent event) throws IOException {
        gotoView("OrganizationSignUpView.fxml", event);
    }
}
