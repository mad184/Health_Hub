package staff.InstructorViews;

import API.ExerciseItem;
import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import staff.Controllers.InstructorController;
import staff.InstructorViews.ExerciseSearch.ExerciseSearchViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InstructorExerciseViewController implements Initializable {

  public InstructorController controller = new InstructorController(null);
  @FXML
  VBox exerciseNameVBox = new VBox();
  @FXML
  VBox exerciseSetsVBox = new VBox();
  @FXML
  VBox exerciseRepsVBox = new VBox();

  /**
   * Setups of controller for scene
   *
   * @param controller controller for client info
   */
  public void setupScene(InstructorController controller) {
    this.controller = controller;
    updateVBox();
  }

  /**
   * Goes back to main view
   *
   * @param event of back button being pushed
   */
  public void onBackButtonPressed(ActionEvent event) throws IOException, EmptyQueryException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("instructorMainView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    InstructorMainViewController viewController = loader.getController();
    viewController.setupScene(controller.getId());

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }

  /**
   * Goes to exerciseSearchView
   *
   * @param event of add exercise button being pushed
   */
  public void exerciseSearch(ActionEvent event) throws IOException {
    // Load food search scene
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ExerciseSearch/exerciseSearchView.fxml"));
    Parent root = loader.load();

    // Get controller for search scene
    ExerciseSearchViewController viewController = loader.getController();
    // setup scene
    viewController.setupScene(controller);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }

  /**
   * Updates exercise names in v box
   */
  public void updateVBox() {
    if (controller.getExercises() != null) {
      ArrayList<ExerciseItem> exercisesList = controller.getExercises();
      for (int i = 0; i < exercisesList.size(); i++) {
        Label exerciseName = new Label(exercisesList.get(i).getExerciseName());
        Label exerciseSets = new Label(Integer.toString(exercisesList.get(i).getSets()));
        Label exerciseReps = new Label(Integer.toString(exercisesList.get(i).getReps()));
        exerciseName.setTextFill(Color.web("#ddd9d9"));
        exerciseSets.setTextFill(Color.web("#ddd9d9"));
        exerciseReps.setTextFill(Color.web("#ddd9d9"));
        exerciseNameVBox.getChildren().add(exerciseName);
        exerciseSetsVBox.getChildren().add(exerciseSets);
        exerciseRepsVBox.getChildren().add(exerciseReps);
      }
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }
}
