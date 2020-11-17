package Client.ClientView.FoodSearchView;

import API.FoodItem;
import Client.ClientController;
import Client.ClientToDB;
import database.EmptyQueryException;
import database.JsonObjectException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class FoodTypeSelectViewController {
  // Database connection
  ClientToDB DB = new ClientToDB();

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
  public void breakfastButtonPressed(ActionEvent event)
      throws JsonObjectException, EmptyQueryException {
    clientController.addClientBreakfastFood(food);
    DB.updateClient(clientController.getClientID(), clientController.clientToJson());
    closeWindow(event);
  }

  /**
   * Adds food to lunch food group
   *
   * @param event lunch button clicked
   */
  public void lunchButtonPressed(ActionEvent event)
      throws JsonObjectException, EmptyQueryException {
    clientController.addClientLunchFood(food);
    DB.updateClient(clientController.getClientID(), clientController.clientToJson());
    closeWindow(event);
  }

  /**
   * Adds food to dinner food group
   *
   * @param event dinner button clicked
   */
  public void dinnerButtonPressed(ActionEvent event)
      throws JsonObjectException, EmptyQueryException {
    clientController.addClientDinnerFood(food);
    DB.updateClient(clientController.getClientID(), clientController.clientToJson());
    closeWindow(event);
  }

  /**
   * Adds food to snack food group
   *
   * @param event snack button clicked
   */
  public void snackButtonPressed(ActionEvent event)
      throws JsonObjectException, EmptyQueryException {
    clientController.addClientSnackFood(food);
    DB.updateClient(clientController.getClientID(), clientController.clientToJson());
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
