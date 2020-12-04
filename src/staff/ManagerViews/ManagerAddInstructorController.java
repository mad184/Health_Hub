package staff.ManagerViews;



import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import staff.Controllers.ManagerController;
import staff.StaffToDB;

import java.io.IOException;
import java.util.ArrayList;

public class ManagerAddInstructorController {

    StaffToDB db;

    // TextField
    @FXML
    TextField InstructorSearchBar = new TextField();

    @FXML
    Label resultInstructor = new Label();

    private ManagerController controller;

    /**
     *
     * @param controller
     */
    public void setupScene(ManagerController controller) {
        this.controller = controller;
        db = new StaffToDB();
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    public void onSearchButtonPressed(ActionEvent event) throws IOException{
        JSONArray instructorsJson = db.getAllInstructors();
        if (!InstructorSearchBar.getText().isEmpty()){
            for (int i = 0 ; i < instructorsJson.length()-1; i++){
                JSONObject JsonInstructor = instructorsJson.getJSONObject(i);
                if (String.valueOf(JsonInstructor.get("email")).equals(InstructorSearchBar.getText())){
                    resultInstructor.setText("Name: "+String.valueOf(JsonInstructor.get("name")) +" ID: " +
                            String.valueOf(JsonInstructor.get("_id")) + " Email: " +String.valueOf(JsonInstructor.get("email")));
                }
            }
            //TODO: Maybe add a pop up sail that the search view is empty
        }
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
