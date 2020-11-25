package staff.ManagerViews;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import staff.Controllers.InstructorController;
import staff.Controllers.ManagerController;
import staff.Controllers.OwnerController;
import staff.Models.InstructorModel;
import staff.Models.ManagerModel;
import staff.Models.OwnerModel;
import staff.UserID;

import java.util.ArrayList;

public class ManagerView extends Application {

  public ManagerController controller = new ManagerController(
          new ManagerModel(
            "John Wick",
            21,
                  "john@usask.ca",
                  "306-555-5555",
                  175,
                  160,
                  "Average Joes",
                  1,
                  new ArrayList<UserID>(),
          "Remington",
                  "Instructor1",
                  "Dev-Remington-Db",
                  "Instructors") );
  
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
    FXMLLoader loader = new FXMLLoader(getClass().getResource("managerMainView.fxml"));
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
