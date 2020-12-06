package staff.InstructorViews;

import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import staff.Controllers.InstructorController;

import java.io.IOException;

public class InstructorProgressViewController {

  private InstructorController controller;

  // Label with percentage calories out of goal calories eaten so far
  @FXML private Label progressLabel = new Label();

  // Label for calorie intake
  @FXML private Label calorieLabel = new Label();

  // Label for calorie goal
  @FXML private Label calorieGoalLabel = new Label();

  // Progress bar for daily calorie intake progress
  @FXML private ProgressBar pBar = new ProgressBar();

  public void setupScene(InstructorController controller) {

    this.controller = controller;

    // Set calorie labels to current values in client
    calorieLabel.setText(String.valueOf(this.controller.getCalories()));
    calorieGoalLabel.setText(String.valueOf(this.controller.getCaloriesGoal()));

    // Set progress label to clients current progress of daily calories
    progressLabel.setText(String.format("%.4f", calculateDailyProgress()));

    // Set progress bar value
    pBar.setProgress(calculateDailyProgress());
  }

  public double calculateDailyProgress() {
    return (double) controller.getCalories() / (double) controller.getCaloriesGoal();
  }

  // Goes to main view scene when back button is pushed
  public void onBackButtonPressed(ActionEvent event) throws IOException, EmptyQueryException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("instructorMainView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    InstructorMainViewController MainViewController = loader.getController();
    MainViewController.setupScene(controller.getId());

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }
}
