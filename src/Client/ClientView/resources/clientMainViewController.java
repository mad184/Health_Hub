package Client.ClientView.resources;

import Client.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class clientMainViewController implements Initializable {

    @FXML
    private Label nameLabel = new Label();

    Client client1 = new Client(
            "Justyn Pollard",
            "Justynpollard12@hotmail.com",
            "Pete",
            "Gym1",
            1,
            20,
            180,
            180,
            "3068500727",
            170,
            1000,
            null,
            null);

    public void changeSceneButtonAction(ActionEvent event, String viewFXML) throws IOException{
        Parent viewParent = FXMLLoader.load(getClass().getResource(viewFXML));
        Scene viewScene = new Scene(viewParent);

        //Gets stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
        window.show();
    }

    public void onExerciseButtonPushed(ActionEvent event) throws IOException {
        changeSceneButtonAction(event, "clientExerciseView.fxml");
    }

    public void onProgressButtonPushed(ActionEvent event) throws IOException {
        changeSceneButtonAction(event, "clientProgressView.fxml");
    }

    public void onNutrientButtonPushed(ActionEvent event) throws IOException {
        changeSceneButtonAction(event, "clientNutrientView.fxml");
    }

    public void onProfileButtonPushed(ActionEvent event) throws IOException {
        changeSceneButtonAction(event, "clientProfileView.fxml");
    }

    public void onSettingsButtonPushed(ActionEvent event) throws IOException {
        changeSceneButtonAction(event, "clientSettingsView.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.setText(client1.getName());
    }
}
