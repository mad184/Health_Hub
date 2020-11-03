package Client.ClientView.resources;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class clientMainViewController implements Initializable {

    public void onExerciseButtonPushed(ActionEvent event) throws IOException {
        Parent exerciseViewParent = FXMLLoader.load(getClass().getResource("clientExerciseView.fxml"));
        Scene exerciseViewScene = new Scene(exerciseViewParent);

        //Gets stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(exerciseViewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
