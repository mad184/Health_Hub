package staff.OwnerViews;

import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.json.JSONObject;
import staff.Controllers.InstructorController;
import staff.Controllers.OwnerController;
import staff.Models.OwnerModel;
import staff.StaffToDB;

import java.io.IOException;

public class OwnerMainViewController {

  OwnerController controller;
  final StaffToDB db = new StaffToDB();

  // Label for Instructor name
  @FXML
  private Label nameLabel = new Label();

  // Label for most recent client recommendations
  @FXML
  private Label recommendationLabel = new Label();

  // Label for most recent client comments
  @FXML
  private Label commentLabel = new Label();

  public void setupScene(int uniqueIDfromHealthHub) throws EmptyQueryException {
    OwnerModel newOwnerModel = new OwnerModel(
            "",
            "",
            0,
            "",
            "",
            0,
            0,
            "",
            uniqueIDfromHealthHub,
            null,
            null,
            "test-user",
            "healthhub1",
            "Test-General-Database",
            "Instructor-Table");

    JSONObject ownerFromDB = db.getManager(uniqueIDfromHealthHub);

    // Sets controller for the view
    this.controller = new OwnerController(newOwnerModel);

    // Changes name label to clients name
    nameLabel.setText(controller.getName());

    // Changes recommendation label to client's recommendations
    recommendationLabel.setText("None");

    // Sets clients comments label to none if there are no comments, else sets to first comment

  }

  /**
   * Goes to exercise page when exercise button is pressed
   *
   * @param event Event of when exercise button is pressed
   * @throws IOException throws exception
   */
  public void onExerciseButtonPushed(ActionEvent event) throws IOException {
    // Loads Scene for exercise view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ownerExerciseView.fxml"));
    Parent root = loader.load();

    // Gets exercise view controller and passes client to it
    OwnerExerciseViewController ViewController = loader.getController();
    ViewController.setupScene(controller);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }

  /**
   * Goes to progress page when progress button is pressed
   *
   * @param event Event of when progress button pressed
   * @throws IOException throws exception
   */
  public void onProgressButtonPushed(ActionEvent event) throws IOException {
    // Loads Scene for progress view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ownerProgressView.fxml"));
    Parent root = loader.load();

    // Gets progress view controller and passes client to it
    OwnerProgressViewController ViewController = loader.getController();
    ViewController.setupScene(controller);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }

  /**
   * Goes to nutrient page when nutrient button is pressed
   *
   * @param event Event of nutrient button pressed
   * @throws IOException throws exception
   */
  public void onNutrientButtonPushed(ActionEvent event) throws IOException {
    // Loads Scene for nutrient view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ownerNutrientView.fxml"));
    Parent root = loader.load();

    // Gets nutrient view controller and passes client to it
    OwnerNutrientViewController ViewController = loader.getController();
    ViewController.setupScene(controller);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }

  /**
   * Goes to profile page when profile button is pressed
   *
   * @param event Event of profile button pressed
   * @throws IOException throws exception
   */
  public void onProfileButtonPushed(ActionEvent event) throws IOException {
    // Loads Scene for profile view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ownerProfileView.fxml"));
    Parent root = loader.load();

    // Gets profile view controller and passes client to it
    OwnerProfileViewController ViewController = loader.getController();
    ViewController.setupScene(controller);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }

  /**
   * Goes to settings page when settings button is pressed
   *
   * @param event Event of setting button pressed
   * @throws IOException throws exception
   */
  public void onSettingsButtonPushed(ActionEvent event) throws IOException {
    // Loads Scene for settings view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ownerSettingsView.fxml"));
    Parent root = loader.load();

    // Gets Setting view controller and passes client to it
    OwnerSettingViewController ViewController = loader.getController();
    ViewController.setupScene(controller);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }
}
