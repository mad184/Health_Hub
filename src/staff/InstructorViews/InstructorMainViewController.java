package staff.InstructorViews;

import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

import org.json.JSONObject;
import staff.Controllers.InstructorController;
import staff.Models.InstructorModel;
import staff.StaffToDB;

public class InstructorMainViewController {

  InstructorController controller;

  StaffToDB db;

  // Label for Instructor name
  @FXML private Label nameLabel = new Label();

  // Label for most recent client recommendations
  @FXML private Label recommendationLabel = new Label();

  // Label for most recent client comments
  @FXML private Label commentLabel = new Label();

  public void setupScene(int _id) throws EmptyQueryException {
    this.db = new StaffToDB();
    // Sets client to client controller for scene
    //controller.model.DB.getInstructor(instructorID);
    InstructorModel newInstructor =
            new InstructorModel(
                    "",
                    "",
                    0,
                    "",
                    "",
                    0,
                    0,
                    "none",
                    1,
                    null,
                    "test-user",
                    "healthhub1",
                    "test-user",
                    "Test-General-Database");

    InstructorController Instructorcontroller = new InstructorController(newInstructor);

    JSONObject InstructorFromDb = db.getInstructor(_id);

    Instructorcontroller.fromJson(InstructorFromDb);

    this.controller = Instructorcontroller;
    // Changes name label to clients name
    nameLabel.setText(this.controller.getName());

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
    FXMLLoader loader = new FXMLLoader(getClass().getResource("instructorExerciseView.fxml"));
    Parent root = loader.load();

    // Gets exercise view controller and passes client to it
    InstructorExerciseViewController ViewController = loader.getController();
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
    FXMLLoader loader = new FXMLLoader(getClass().getResource("instructorProgressView.fxml"));
    Parent root = loader.load();

    // Gets progress view controller and passes client to it
    InstructorProgressViewController ViewController = loader.getController();
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
    FXMLLoader loader = new FXMLLoader(getClass().getResource("instructorNutrientView.fxml"));
    Parent root = loader.load();

    // Gets nutrient view controller and passes client to it
    InstructorNutrientController ViewController = loader.getController();
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
    FXMLLoader loader = new FXMLLoader(getClass().getResource("instructorProfileView.fxml"));
    Parent root = loader.load();

    // Gets profile view controller and passes client to it
    InstructorProfileViewController ViewController = loader.getController();
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
    FXMLLoader loader = new FXMLLoader(getClass().getResource("instructorSettingsView.fxml"));
    Parent root = loader.load();

    // Gets Setting view controller and passes client to it
    InstructorSettingViewController ViewController = loader.getController();
    ViewController.setupScene(controller);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }
}
