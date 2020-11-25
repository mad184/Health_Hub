package staff.OwnerViews.FoodSearchView;

import API.FoodItem;
import Client.ClientToDB;
import database.EmptyQueryException;
import database.JsonObjectException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import staff.Controllers.InstructorController;
import staff.Controllers.OwnerController;

public class FoodTypeSelectViewController {
  // Database connection
  ClientToDB DB = new ClientToDB();

  // Controller for client
  private OwnerController ownerController = new OwnerController(null);

  // Food item to be added to client
  private FoodItem food;

  /**
   * Setups of scene
   *
   * @param owner the OwnerController
   * @param food food to be added to client
   */
  public void setupScene(OwnerController owner, FoodItem food) {
    ownerController = owner;
    this.food = food;
  }

  /**
   * Adds food to breakfast food group
   *
   * @param event breakfast button clicked
   */
  public void breakfastButtonPressed(ActionEvent event)
      throws JsonObjectException, EmptyQueryException {
    //instructorController.(food);
    DB.updateClient(ownerController.getId(), ownerController.model.toJson());
    closeWindow(event);
  }

  /**
   * Adds food to lunch food group
   *
   * @param event lunch button clicked
   */
  public void lunchButtonPressed(ActionEvent event)
      throws JsonObjectException, EmptyQueryException {
    //instructorController.addClientLunchFood(food);
    DB.updateClient(ownerController.getId(), ownerController.model.toJson());
    closeWindow(event);
  }

  /**
   * Adds food to dinner food group
   *
   * @param event dinner button clicked
   */
  public void dinnerButtonPressed(ActionEvent event)
      throws JsonObjectException, EmptyQueryException {
    // instructorController.addClientDinnerFood(food);
    DB.updateClient(ownerController.getId(), ownerController.model.toJson());
    closeWindow(event);
  }

  /**
   * Adds food to snack food group
   *
   * @param event snack button clicked
   */
  public void snackButtonPressed(ActionEvent event)
      throws JsonObjectException, EmptyQueryException {
    // instructorController.addClientSnackFood(food);
    DB.updateClient(ownerController.getId(), ownerController.model.toJson());
    closeWindow(event);
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
