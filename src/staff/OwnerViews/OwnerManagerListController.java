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

public class OwnerManagerListController {

  @FXML ComboBox<String> managerSelectComboBox = new ComboBox<>();
  @FXML ComboBox<String> managerDisplayComboBox = new ComboBox<>();

  private ObservableList<String> managerList;
  private ObservableList<String> displayManagerList;
  private ArrayList<UserID> instructor;
  private OwnerController controller;
  private StaffToDB db;

  public void setupScene(OwnerController controller) {
    this.controller = controller;
    this.db = new StaffToDB();
    onLoadInstructorList();
  }

  public void onLoadInstructorList() {
    if (controller.getManagers() != null) {

      instructor = (ArrayList<UserID>) controller.getManagers();
      ArrayList<String> managersNames = new ArrayList<>();
      ArrayList<String> managerDisplayNames = new ArrayList<>();
      if (instructor != null) {
        for (UserID managerUser : instructor) {
          managersNames.add(managerUser.getName());
          managerDisplayNames.add(managerUser.getName() + " -- ID: " + managerUser.getId());
        }
      }
      // Show the Instructors in the system to remove it
      managerList = FXCollections.observableList(managersNames);
      this.managerSelectComboBox.setValue("");
      this.managerSelectComboBox.setItems(managerList);
      // Show the Instructors in the system
      displayManagerList = FXCollections.observableList(managerDisplayNames);
      this.managerDisplayComboBox.setValue("");
      this.managerDisplayComboBox.setItems(displayManagerList);
    }
  }

  /**
   * Button that will redirect to a page to add a new Instructor
   *
   * @param event The "+" button being pushed
   */
  public void onAddInstructorButtonPressed(ActionEvent event) throws IOException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ownerAddManagerView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    OwnerAddManagerController ownerAddManagerController = loader.getController();
    ownerAddManagerController.setupScene(controller);

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
    String managerToBeRemoved = managerSelectComboBox.getValue();

    if (managerToBeRemoved != null && !managerToBeRemoved.isEmpty()) {
      for (UserID managerID : controller.getManagers()) {
        if (managerID.getName().equals(managerToBeRemoved)) {
          controller.removeManager(managerID);
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
