package staff.OwnerViews;

import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import staff.Controllers.OwnerController;

import java.io.IOException;

public class OwnerExerciseViewController {

  private OwnerController controller;

  public void setupScene(OwnerController controller) {
    this.controller = controller;
  }

  public void onBackButtonPressed(ActionEvent event) throws IOException, EmptyQueryException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ownerMainView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    OwnerMainViewController MainViewController = loader.getController();
    MainViewController.setupScene(controller.getId());

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }
}
