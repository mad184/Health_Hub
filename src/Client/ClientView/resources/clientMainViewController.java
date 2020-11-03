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

    @FXML
    private Label nameLabel = new Label();
    

    public void onExerciseButtonPushed(ActionEvent event) throws IOException {
        Parent exerciseViewParent = FXMLLoader.load(getClass().getResource("clientExerciseView.fxml"));
        Scene exerciseViewScene = new Scene(exerciseViewParent);

        //Gets stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(exerciseViewScene);
        window.show();
    }

    public void onProgressButtonPushed(ActionEvent event) throws IOException {
        Parent progressViewParent = FXMLLoader.load(getClass().getResource("clientProgressView.fxml"));
        Scene progressViewScene = new Scene(progressViewParent);

        //Gets stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(progressViewScene);
        window.show();
    }

    public void onNutrientButtonPushed(ActionEvent event) throws IOException {
        Parent nutrientViewParent = FXMLLoader.load(getClass().getResource("clientNutrientView.fxml"));
        Scene nutrientViewScene = new Scene(nutrientViewParent);

        //Gets stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(nutrientViewScene);
        window.show();
    }

    public void onProfileButtonPushed(ActionEvent event) throws IOException {
        Parent profileViewParent = FXMLLoader.load(getClass().getResource("clientProfileView.fxml"));
        Scene profileViewScene = new Scene(profileViewParent);

        //Gets stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(profileViewScene);
        window.show();
    }

    public void onSettingsButtonPushed(ActionEvent event) throws IOException {
        Parent settingsViewParent = FXMLLoader.load(getClass().getResource("clientSettingsView.fxml"));
        Scene settingsViewScene = new Scene(settingsViewParent);

        //Gets stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(settingsViewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.setText(client1.getName());
    }
}
