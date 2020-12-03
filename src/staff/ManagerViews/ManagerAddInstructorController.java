package staff.ManagerViews;

import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import staff.Controllers.ManagerController;

import java.io.IOException;

public class ManagerAddInstructorController {

    private ManagerController controller;

    public void setupScene(ManagerController controller) {
        this.controller = controller;
    }

    public void onBackButtonPressed(ActionEvent event) throws IOException, EmptyQueryException {
        // Loads Scene for main view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("managerInstructorsListView.fxml"));
        Parent root = loader.load();

        // Gets main view controller and passes client to it
        ManagerInstructorsListController managerInstructorsListController = loader.getController();
        managerInstructorsListController.setupScene(controller);

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }
}
