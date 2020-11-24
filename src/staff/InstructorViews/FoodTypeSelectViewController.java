package staff.InstructorViews;

import database.EmptyQueryException;
import database.JsonObjectException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import API.FoodItem;
import staff.Controllers.InstructorController;

public class FoodTypeSelectViewController {

  private InstructorController controller;
  private FoodItem food;

  public void setUpScene(InstructorController controller, FoodItem food) {
    this.controller = controller;
    this.food = food;
  }

  /**
   * Adds food to breakfast food group
   *
   * @param event breakfast button clicked
   */
  public void breakfastButtonPressed(ActionEvent event)
      throws JsonObjectException, EmptyQueryException {
    // TODO: controller.Breafast(food);
    // TODO: maybe do it on model? DB.updateClient(clientController.getClientID(),
    // clientController.clientToJson());
    closeWindow(event);
  }

  /**
   * Adds food to lunch food group
   *
   * @param event lunch button clicked
   */
  public void lunchButtonPressed(ActionEvent event)
      throws JsonObjectException, EmptyQueryException {
    // TODO: controller.addLunchFood(food);
    // TODO: maybe do it on model? DB.updateClient(clientController.getClientID(),
    // clientController.clientToJson());
    closeWindow(event);
  }

  /**
   * Adds food to dinner food group
   *
   * @param event dinner button clicked
   */
  public void dinnerButtonPressed(ActionEvent event)
      throws JsonObjectException, EmptyQueryException {
    // TODO: controller.addDinnerFood(food);
    // TODO: maybe do it on model? DB.updateClient(clientController.getClientID(),
    // clientController.clientToJson());
    closeWindow(event);
  }

  /**
   * Adds food to snack food group
   *
   * @param event snack button clicked
   */
  public void snackButtonPressed(ActionEvent event)
      throws JsonObjectException, EmptyQueryException {
    // TODO: controller.addSnackFood(food);
    // TODO: maybe do it on model? DB.updateClient(clientController.getClientID(),
    // clientController.clientToJson());
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
