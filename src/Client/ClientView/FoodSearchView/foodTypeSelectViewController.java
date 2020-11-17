package Client.ClientView.FoodSearchView;

import API.FoodItem;
import Client.ClientController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class foodTypeSelectViewController {

  // Controller for client
  private ClientController clientController = new ClientController(null);

  // Food item to be added to client
  private FoodItem food;

  /**
   * Setups of scene
   *
   * @param client the clientController
   * @param food food to be added to client
   */
  public void setupScene(ClientController client, FoodItem food) {
    clientController = client;
    this.food = food;
  }

  /**
   * Adds food to breakfast food group
   *
   * @param event breakfast button clicked
   */
  public void breakfastButtonPressed(ActionEvent event) {
    clientController.addClientBreakfastFood(food);
    closeWindow(event);
  }

  /**
   * Adds food to lunch food group
   *
   * @param event lunch button clicked
   */
  public void lunchButtonPressed(ActionEvent event) {
    clientController.addClientLunchFood(food);
    closeWindow(event);
  }

  /**
   * Adds food to dinner food group
   *
   * @param event dinner button clicked
   */
  public void dinnerButtonPressed(ActionEvent event) {
    clientController.addClientDinnerFood(food);
    closeWindow(event);
  }

  /**
   * Adds food to snack food group
   *
   * @param event snack button clicked
   */
  public void snackButtonPressed(ActionEvent event) {
    clientController.addClientSnackFood(food);
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
