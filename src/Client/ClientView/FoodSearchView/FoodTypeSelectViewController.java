package Client.ClientView.FoodSearchView;

import API.FoodItem;
import Client.ClientController;
import Client.ClientToDB;
import Client.ClientView.ClientNutrientViewController;
import database.EmptyQueryException;
import database.JsonObjectException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
          throws JsonObjectException, EmptyQueryException, IOException {
    clientController.addClientBreakfastFood(food);
    clientController.setClientCals(clientController.getClientCals() + food.getCalories());
    DB.updateClient(clientController.getClientID(), clientController.clientToJson());
    nutrientView(event);
  }

  /**
   * Adds food to lunch food group
   *
   * @param event lunch button clicked
   */
  public void lunchButtonPressed(ActionEvent event)
          throws JsonObjectException, EmptyQueryException, IOException {
    clientController.addClientLunchFood(food);
    clientController.setClientCals(clientController.getClientCals() + food.getCalories());
    DB.updateClient(clientController.getClientID(), clientController.clientToJson());
    nutrientView(event);
  }

  /**
   * Adds food to dinner food group
   *
   * @param event dinner button clicked
   */
  public void dinnerButtonPressed(ActionEvent event)
          throws JsonObjectException, EmptyQueryException, IOException {
    clientController.addClientDinnerFood(food);
    clientController.setClientCals(clientController.getClientCals() + food.getCalories());
    DB.updateClient(clientController.getClientID(), clientController.clientToJson());
    nutrientView(event);
  }

  /**
   * Adds food to snack food group
   *
   * @param event snack button clicked
   */
  public void snackButtonPressed(ActionEvent event)
          throws JsonObjectException, EmptyQueryException, IOException {
    clientController.addClientSnackFood(food);
    clientController.setClientCals(clientController.getClientCals() + food.getCalories());
    DB.updateClient(clientController.getClientID(), clientController.clientToJson());
    nutrientView(event);
  }

  /**
   * closes window
   *
   * @param event button clicked
   */
  private void nutrientView(ActionEvent event) throws IOException, EmptyQueryException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../clientNutrientView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    ClientNutrientViewController viewController = loader.getController();
    viewController.setupScene(clientController);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();

  }
}
