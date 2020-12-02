package staff.InstructorViews.ExerciseSearch;

import API.ExerciseItem;
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
import staff.Controllers.InstructorController;
import staff.InstructorViews.InstructorExerciseViewController;

import java.io.IOException;

public class ExerciseRepsAndSetsViewController {

    //Exercise Item
    ExerciseItem exerciseItem;

    //Reps and sets input fields
    @FXML
    TextField setsInput = new TextField();
    @FXML
    TextField repsInput = new TextField();

    // Controller to hold client information
    private InstructorController controller = new InstructorController(null);

    /**
     * Setsup scene for actions
     *
     * @param controller   client info
     * @param exerciseItem exercise being added to client
     */
    public void setupScene(InstructorController controller, ExerciseItem exerciseItem) {
        this.controller = controller;
        this.exerciseItem = exerciseItem;
    }

    /**
     * Add exercise to client, updates client in database, then goes back to ClientExercise view
     *
     * @param event of add exercise button being pushed
     * @throws EmptyQueryException throws when client is not found in database
     */
    public void addExerciseButtonPushed(ActionEvent event) throws IOException, JsonObjectException, EmptyQueryException {
        this.exerciseItem.setSets(Integer.parseInt(setsInput.getText()));
        this.exerciseItem.setReps(Integer.parseInt(repsInput.getText()));
        controller.addExercises(exerciseItem);
        controller.toJson();
        goBack(event);
    }

    /**
     * Goes back to ClientExerciseView
     *
     * @param event event of add exercise button being pushed
     */
    public void goBack(ActionEvent event) throws IOException {
        // Load food search scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../instructorExerciseView.fxml"));
        Parent root = loader.load();

        // Get controller for search scene
        InstructorExerciseViewController viewController = loader.getController();
        // setup scene
        viewController.setupScene(controller);

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }
}
