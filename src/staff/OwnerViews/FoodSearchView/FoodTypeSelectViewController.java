package staff.OwnerViews.FoodSearchView;

import API.FoodItem;
import Client.ClientToDB;
import database.Dbms;
import database.EmptyQueryException;
import database.JsonObjectException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import staff.Controllers.InstructorController;
import staff.Controllers.OwnerController;
import staff.ManagerViews.ManagerNutrientViewController;
import staff.OwnerViews.OwnerNutrientViewController;

import java.io.IOException;

public class FoodTypeSelectViewController {
  // Database connection
  private Dbms db = null;

  // Controller for client
  private OwnerController controller = new OwnerController(null);

  // Food item to be added to client
  private FoodItem food;

  /**
   * Setups of scene
   *
   * @param owner the OwnerController
   * @param food food to be added to client
   */
  public void setupScene(OwnerController owner, FoodItem food) {
    controller = owner;
    db = controller.getDbms();
    this.food = food;
  }

  /**
   * Adds food to breakfast food group
   *
   * @param event breakfast button clicked
   */
  public void breakfastButtonPressed(ActionEvent event)
          throws JsonObjectException, EmptyQueryException, IOException {
    controller.addBreakfastFood(food);
    controller.setCalories(controller.getCalories() + food.getCalories());
    db.updateClient(controller.getId(), controller.model.toJson());
    nutrientView(event);
  }

  /**
   * Adds food to lunch food group
   *
   * @param event lunch button clicked
   */
  public void lunchButtonPressed(ActionEvent event)
          throws JsonObjectException, EmptyQueryException, IOException {
    controller.addLunchFood(food);
    controller.setCalories(controller.getCalories() + food.getCalories());
    db.updateClient(controller.getId(), controller.model.toJson());
    nutrientView(event);
  }

  /**
   * Adds food to dinner food group
   *
   * @param event dinner button clicked
   */
  public void dinnerButtonPressed(ActionEvent event)
          throws JsonObjectException, EmptyQueryException, IOException {
    controller.addDinnerFood(food);
    controller.setCalories(controller.getCalories() + food.getCalories());
    db.updateClient(controller.getId(), controller.model.toJson());
    nutrientView(event);
  }

  /**
   * Adds food to snack food group
   *
   * @param event snack button clicked
   */
  public void snackButtonPressed(ActionEvent event)
          throws JsonObjectException, EmptyQueryException, IOException {
    controller.addSnackFood(food);
    controller.setCalories(controller.getCalories() + food.getCalories());
    db.updateClient(controller.getId(), controller.model.toJson());
    nutrientView(event);
  }

  /**
   * closes window
   *
   * @param event button clicked
   */
  private void nutrientView(ActionEvent event) throws IOException, EmptyQueryException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../ownerNutrientView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    OwnerNutrientViewController viewController = loader.getController();
    viewController.setupScene(controller);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();

  }

  /**
   * closes window
   *
   * @param event button clicked
   */
  private void closeWindow(ActionEvent event) {
    // Closes popup window
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.close();
  }
}
