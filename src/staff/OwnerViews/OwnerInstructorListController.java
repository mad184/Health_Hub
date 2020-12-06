package staff.OwnerViews;

import database.EmptyQueryException;
import database.JsonObjectException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import staff.Controllers.OwnerController;
import staff.StaffToDB;
import staff.UserID;

import java.io.IOException;
import java.util.ArrayList;

public class OwnerInstructorListController {

  @FXML ComboBox<String> instructorSelectComboBox = new ComboBox<>();
  @FXML ComboBox<String> instructorDisplayComboBox = new ComboBox<>();

  private ObservableList<String> instructorList;
  private ObservableList<String> displayInstructorList;
  private ArrayList<UserID> instructor;
  private OwnerController controller;
  private StaffToDB db;

  public void setupScene(OwnerController controller) {
    this.controller = controller;
    this.db = new StaffToDB();
    onLoadInstructorList();
  }

  public void onLoadInstructorList() {
    if (controller.getInstructors() != null) {

      instructor = (ArrayList<UserID>) controller.getInstructors();
      ArrayList<String> instructorNames = new ArrayList<>();
      ArrayList<String> instructorDisplayNames = new ArrayList<>();
      if (instructor != null) {
        for (UserID instructorUser : instructor) {
          instructorNames.add(instructorUser.getName());
          instructorDisplayNames.add(
              instructorUser.getName() + " -- ID: " + instructorUser.getId());
        }
      }
      // Show the Instructors in the system to remove it
      instructorList = FXCollections.observableList(instructorNames);
      this.instructorSelectComboBox.setValue("");
      this.instructorSelectComboBox.setItems(instructorList);
      // Show the Instructors in the system
      displayInstructorList = FXCollections.observableList(instructorDisplayNames);
      this.instructorDisplayComboBox.setValue("");
      this.instructorDisplayComboBox.setItems(displayInstructorList);
    }
  }

  /**
   * Button that will redirect to a page to add a new Instructor
   *
   * @param event The "+" button being pushed
   */
  public void onAddInstructorButtonPressed(ActionEvent event) throws IOException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ownerAddInstructorView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    OwnerAddInstructorController ownerAddInstructorController = loader.getController();
    ownerAddInstructorController.setupScene(controller);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }

  /**
   * Function will remove the instructor selected in the combo box
   *
   * @param event
   */
  public void onRemoveButtonPressed(ActionEvent event)
      throws IOException, JsonObjectException, EmptyQueryException {
    String instructorToBeRemoved = instructorSelectComboBox.getValue();

    if (instructorToBeRemoved != null && !instructorToBeRemoved.isEmpty()) {
      for (UserID instructorID : controller.getInstructors()) {
        if (instructorID.getName().equals(instructorToBeRemoved)) {
          controller.removeInstructor(instructorID);
          db.updateManager(controller.getId(), controller.toJson());
          onLoadInstructorList();
          return;
        }
      }
    }
  }

  /**
   * Will navigate to the back page - the main view
   *
   * @param event the button back pressed
   */
  public void onBackButtonPressed(ActionEvent event) throws IOException, EmptyQueryException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("OwnerMainView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    OwnerMainViewController ownerMainViewController = loader.getController();
    ownerMainViewController.setupScene(controller.getId());

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }
}
