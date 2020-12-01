package Client.ClientView.ExerciseSearch;

import API.ExerciseItem;
import Client.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class ExerciseRepsAndSetsViewController {

    //Exercise Item
    ExerciseItem exerciseItem;

    //Reps and sets input fields
    @FXML TextField setsInput = new TextField();
    @FXML TextField repsInput = new TextField();

    // Controller to hold client information
    private ClientController clientController = new ClientController(null);

    public void setupScene(ClientController controller, ExerciseItem exerciseItem) {
        clientController = controller;
        this.exerciseItem = exerciseItem;
    }

    public void addExerciseButtonPushed(ActionEvent event) throws IOException {
        this.exerciseItem.setSets(Integer.parseInt(setsInput.getText()));
        this.exerciseItem.setReps(Integer.parseInt(repsInput.getText()));
        clientController.addExercise(this.exerciseItem);
        goBack(event);
    }

    public void goBack(ActionEvent event) throws IOException {
        // Load food search scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("exerciseSearchView.fxml"));
        Parent root = loader.load();

        // Get controller for search scene
        ExerciseSearchViewController viewController = loader.getController();
        // setup scene
        viewController.setupScene(clientController);

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }
}
