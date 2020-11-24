package staff.ManagerViews;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import staff.Controllers.ManagerController;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javafx.scene.Node;

public class ManagerExerciseViewController {

    private ManagerController controller;

    public void setupScene(ManagerController controller) {
        this.controller = controller;
    }

    public void onBackButtonPressed(ActionEvent event) throws IOException {
        // Loads Scene for main view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("clientMainView.fxml"));
        Parent root = loader.load();

        // Gets main view controller and passes client to it
        ManagerMainViewController MainViewController = loader.getController();
        MainViewController.setupScene(controller);

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }
}