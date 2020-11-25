package staff.ManagerViews;

import API.FoodItem;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;
import staff.Controllers.ManagerController;
import staff.ManagerViews.ManagerMainViewController;
import staff.Interfaces.ManagerViewInterface;
import staff.Interfaces.StaffInterface;
import staff.Models.ManagerModel;
import staff.UserID;

import java.util.ArrayList;

public class ManagerView extends Application {

  public ManagerController controller = new ManagerController(
          new ManagerModel(
                  "Manager",
                  21,
                  "manager@usask.ca",
                  "306-555-5555",
                  175,
                  160,
                  "Average Joes",
                  5,
                  new ArrayList<UserID>(),
                  "Remington",
                  "Instructor1",
                  "Dev-Remington-Db",
                  "Managers") );

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Runs when application is started
   * @param stage Window to hold all the scenes for the ui from javafx
   * @throws Exception exception
   */
  @Override
  public void start(Stage stage) throws Exception {
    // Add client to database for testing purpose
    controller.model.db.createManager(controller.getId(), controller.model.toJson());

    // Load main scene
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerMainView.fxml"));
    Parent root = loader.load();

    // Get controller for main scene
    ManagerMainViewController viewController = loader.getController();
    // setup scene
    viewController.setupScene(controller);

    // Show scene in window
    stage.setTitle("Manager View");
    stage.setScene(new Scene(root, 600, 400));
    stage.show();
  }

  /** Runs when application is closed */
  @Override
  public void stop() {
    controller.model.db.removeManager(controller.getId()); // Removes test instructor at end of application
    System.out.println("Health Hub application exited");
  }
}

