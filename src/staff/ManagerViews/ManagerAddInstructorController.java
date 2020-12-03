package staff.ManagerViews;


import API.FoodItem;
import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import staff.Controllers.ManagerController;
import staff.StaffToDB;

import java.io.IOException;
import java.util.ArrayList;

public class ManagerAddInstructorController {

    StaffToDB db;

    // Array list initialized for results
    ArrayList<FoodItem> results = new ArrayList<>();

    private ManagerController controller;

    public void setupScene(ManagerController controller) {
        this.controller = controller;
        db = new StaffToDB();
    }

    public void onSearchButtonPressed(ActionEvent event) throws IOException{
        //TODO: implement search
        //TODO: add in the StaffToDb to get all instructors in the db.
    }

    public void onAddButton(ActionEvent event) throws IOException{
        //TODO: implement teh action of adding the instructor
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
