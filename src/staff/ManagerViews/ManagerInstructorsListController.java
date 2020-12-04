package staff.ManagerViews;

import database.EmptyQueryException;
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
import staff.Controllers.ManagerController;
import staff.UserID;

import java.io.IOException;
import java.util.ArrayList;

public class ManagerInstructorsListController {

  @FXML ComboBox<String> instructorSelectComboBox = new ComboBox<>();
  @FXML ComboBox<String> instructorDisplayComboBox = new ComboBox<>();


  private ObservableList<String> instructorList;
  private ObservableList<String> displayInstructorList;
  private ArrayList<UserID> instructor;
  private ManagerController controller;

  public void setupScene(ManagerController controller) {
    this.controller = controller;
    onLoadInstructorList();
  }

  /** Method called by the setupScene that will load the instructors in the view if there is any */
  public void onLoadInstructorList() {
    if (controller.getInstructors() != null && !controller.getInstructors().isEmpty()){

        instructor = (ArrayList<UserID>) controller.getInstructors();
        ArrayList<String> instructorNames = new ArrayList<>();
        ArrayList<String> instructorDisplayNames = new ArrayList<>();
        if (instructor != null) {
            for (UserID instructorUser : instructor) {
                instructorNames.add(instructorUser.getName());
                instructorDisplayNames.add(instructorUser.getName() + " -- ID: " +instructorUser.getId());

            }
        }
        //Show the Instructors in the system to remove it
        instructorList = FXCollections.observableList(instructorNames);
        this.instructorSelectComboBox.setValue("");
        this.instructorSelectComboBox.setItems(instructorList);
        //Show the Instructors in the system
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

  public void onRemoveButtonPressed(ActionEvent event) throws IOException {
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
