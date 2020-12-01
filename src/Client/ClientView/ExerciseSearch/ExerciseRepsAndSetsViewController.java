package Client.ClientView.ExerciseSearch;

import API.ExerciseItem;
import Client.ClientController;
import Client.ClientToDB;
import Client.ClientView.ClientExerciseViewController;
import database.EmptyQueryException;
import database.JsonObjectException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ExerciseRepsAndSetsViewController {

    //Database connection
    ClientToDB DB = new ClientToDB();

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

    public void addExerciseButtonPushed(ActionEvent event) throws IOException, JsonObjectException, EmptyQueryException {
        this.exerciseItem.setSets(Integer.parseInt(setsInput.getText()));
        this.exerciseItem.setReps(Integer.parseInt(repsInput.getText()));
        clientController.addExercise(exerciseItem);
        DB.updateClient(clientController.getClientID(), clientController.clientToJson());
        goBack(event);
    }

    public void goBack(ActionEvent event) throws IOException {
        // Load food search scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../clientExerciseView.fxml"));
        Parent root = loader.load();

        // Get controller for search scene
        ClientExerciseViewController viewController = loader.getController();
        // setup scene
        viewController.setupScene(clientController);

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }
}
