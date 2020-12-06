package staff.ManagerViews;

import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import staff.Controllers.InstructorController;
import staff.Controllers.ManagerController;

import java.io.IOException;

public class ManagerProfileViewController {

  public ManagerController controller = new ManagerController(null);
  private Label nameLabel = new Label();
  private Label ageLabel = new Label();

  public void setupScene(ManagerController manager) {
    controller = manager;
  }

  // Goes to main view scene when back button is pushed
  public void onBackButtonPressed(ActionEvent event) throws IOException, EmptyQueryException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("managerMainView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    ManagerMainViewController viewController = loader.getController();
    viewController.setupScene(controller.getId());

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }
}
