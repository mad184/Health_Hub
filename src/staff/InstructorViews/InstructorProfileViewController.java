package staff.InstructorViews;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import staff.Controllers.InstructorController;

import java.io.IOException;

public class InstructorProfileViewController {

  public InstructorController instructorController = new InstructorController(null);
  private Label nameLabel = new Label();
  private Label ageLabel = new Label();

  public void setupScene(InstructorController instructor) {
    instructorController = instructor;
  }

  // Goes to main view scene when back button is pushed
  public void onBackButtonPressed(ActionEvent event) throws IOException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("instructorMainView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    InstructorMainViewController viewController = loader.getController();
    viewController.setupScene(instructorController);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }
}
