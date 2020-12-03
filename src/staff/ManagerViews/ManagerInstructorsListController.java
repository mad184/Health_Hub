package staff.ManagerViews;

import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import staff.Controllers.ManagerController;

import java.io.IOException;

public class ManagerInstructorsListController {

  @FXML Label firstInstructor = new Label();
  @FXML Label secondInstructor = new Label();
  @FXML Label thirdInstructor = new Label();
  @FXML Label fourthInstructor = new Label();

  private ManagerController controller;

  public void setupScene(ManagerController controller) {
    this.controller = controller;
  }

  /** Method called by the setupScene that will load the instructors in the view if there is any */
  public void onLoadInstructorList() {
    if (!controller.getInstructors().isEmpty()) {
      // TODO: add instructors to the labels so far we can do a fix number, fox Example 4
    }
  }

  /**
   * Button that will redirect to a page to add a new Instructor
   *
   * @param event The "+" button being pushed
   */
  public void onAddInstructorButtonPressed(ActionEvent event) throws IOException {
      // Loads Scene for main view
      FXMLLoader loader = new FXMLLoader(getClass().getResource("managerAddInstructorView.fxml"));
      Parent root = loader.load();

      // Gets main view controller and passes client to it
      ManagerAddInstructorController managerAddInstructorController = loader.getController();
      managerAddInstructorController.setupScene(controller);

      Scene viewScene = new Scene(root);
      // Gets stage information
      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
      window.setScene(viewScene);
      window.show();
  }

  public void onBackButtonPressed(ActionEvent event) throws IOException, EmptyQueryException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("managerMainView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    ManagerMainViewController MainViewController = loader.getController();
    MainViewController.setupScene(controller.getId());

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }
}
