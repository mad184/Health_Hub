package Client.ClientView.resources;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class clientMainViewController {

    public void onExerciseButtonPushed(ActionEvent event) throws IOException {
        Parent exerciseViewParent = FXMLLoader.load(getClass().getResource("clientExerciseView.fxml"));
        Scene exerciseViewScene = new Scene(exerciseViewParent);

        //Gets stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(exerciseViewScene);
        window.show();
    }
}
