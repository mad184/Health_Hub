package staff.OwnerViews;

import database.EmptyQueryException;
import database.JsonObjectException;
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
import staff.Controllers.OwnerController;
import staff.StaffToDB;
import staff.UserID;

import java.io.IOException;

public class OwnerAddManagerController {

  StaffToDB db;

  // TextField
  @FXML TextField ManagerSearchBar = new TextField();

  @FXML Label resultManager = new Label();

  private OwnerController controller;

  JSONObject managerToBeAdded;

  /**
   * set up a new scene from the owner controller
   *
   * @param controller Owner controller
   */
  public void setupScene(OwnerController controller) {
    this.controller = controller;
    this.db = new StaffToDB();
  }

  public void onSearchButtonPressed(ActionEvent event) throws IOException {
    JSONArray managersJson = db.getAllManagers();
    if (!ManagerSearchBar.getText().isEmpty()) {
      for (int i = 0; i < managersJson.length() - 1; i++) {
        JSONObject JsonManager = managersJson.getJSONObject(i);
        if (String.valueOf(JsonManager.get("email")).equals(ManagerSearchBar.getText())) {
          managerToBeAdded = JsonManager;
          resultManager.setText(
              "Name: "
                  + String.valueOf(JsonManager.get("name"))
                  + " ID: "
                  + String.valueOf(JsonManager.get("_id"))
                  + " Email: "
                  + String.valueOf(JsonManager.get("email")));
        }
      }
    }
  }

  public void onAddButton(ActionEvent event)
      throws IOException, EmptyQueryException, JsonObjectException {
    if (!resultManager.getText().isEmpty()) {
      UserID newInstructor =
          new UserID(
              (Integer) managerToBeAdded.get("_id"), String.valueOf(managerToBeAdded.get("name")));
      controller.addManager(newInstructor);
      controller.toJson();
      db.updateManager(controller.getId(), controller.toJson());
      onBackButtonPressed(event);
    }
  }

  public void onBackButtonPressed(ActionEvent event) throws IOException, EmptyQueryException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ownerManagerListView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    OwnerManagerListController ownerManagerListController = loader.getController();
    ownerManagerListController.setupScene(controller);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }
}
