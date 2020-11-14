package Health_Hub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpOptionsPageController {

    @FXML
    public void onClientButtonPushed(ActionEvent event) throws IOException {
        //go to client sign up page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientSignUpView.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
    @FXML
    public void onInstructorButtonPushed(ActionEvent event) throws IOException {
        //go to instructor sign up page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InstructorSignUpView.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
    @FXML
    public void onOrganizationButtonPushed(ActionEvent event) throws IOException {
        //go to instructor sign up page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrganizationView.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
}
