package staff.OwnerViews;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import staff.Controllers.OwnerController;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javafx.scene.Node;

public class OwnerExerciseViewController {

    private OwnerController controller;

    public void setupScene(OwnerController controller) {
        this.controller = controller;
    }

    public void onBackButtonPressed(ActionEvent event) throws IOException {
        // Loads Scene for main view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("clientMainView.fxml"));
        Parent root = loader.load();

        // Gets main view controller and passes client to it
        OwnerMainViewController MainViewController = loader.getController();
        MainViewController.setupScene(controller);

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }
}