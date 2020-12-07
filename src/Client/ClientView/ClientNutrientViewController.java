package Client.ClientView;

import API.FoodItem;
import Client.ClientController;
import Client.ClientToDB;
import Client.ClientView.FoodSearchView.FoodSearchViewController;
import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientNutrientViewController implements Initializable {

  private ClientController clientController = new ClientController(null);

  //VBoxs that display all clients food items
  @FXML VBox breakfastVBox = new VBox();
  @FXML VBox lunchVBox = new VBox();
  @FXML VBox dinnerVBox = new VBox();
  @FXML VBox snackVBox = new VBox();

  // DB object (Currently setup to test db)
  ClientToDB DB = new ClientToDB();

  /**
   * Sets up scene for nutrient view
   * @param client client data
   */
  public void setupScene(ClientController client) {
    clientController = client;
    updateBreakfastVBox();
    updateLunchVBox();
    updateDinnerVBox();
    updateSnackVBox();
  }

  /**
   * Goes to main view when pressed
   * @param event event of back button pushed
   */
  public void onBackButtonPressed(ActionEvent event) throws IOException, EmptyQueryException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("clientMainView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    ClientMainViewController viewController = loader.getController();
    viewController.setupScene(clientController.getClientID());

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }

  /**
   * Goes to food search view
   * @param event add button pressed
   */
  public void foodSearch(ActionEvent event) throws IOException {
    // Load food search scene
    FXMLLoader loader =
        new FXMLLoader(getClass().getResource("FoodSearchView/foodSearchView.fxml"));
    Parent root = loader.load();

    // Get controller for search scene
    FoodSearchViewController viewController = loader.getController();
    // setup scene
    viewController.setupScene(clientController);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }

  /**
   * Updates breakfast items in v box
   */
  public void updateBreakfastVBox(){
    if (clientController.getClientBreakfastFoods() != null) {
      ArrayList<FoodItem> bfList = clientController.getClientBreakfastFoods();
      for (int i = 0; i < bfList.size(); i++){
        Label foodLabel = new Label(
                bfList.get(i).getFoodName() +
                "\n\tCalories: " +
                bfList.get(i).getCalories() +
                        "\n\tServing size: " +
                        bfList.get(i).getServingAmount());
        foodLabel.setTextFill(Color.web("#ddd9d9"));
        breakfastVBox.getChildren().add(foodLabel);
      }
    }
  }

  /**
   * Updates lunch items in v box
   */
  public void updateLunchVBox(){
    if (clientController.getClientLunchFoods() != null) {
      ArrayList<FoodItem> bfList = clientController.getClientLunchFoods();
      for (int i = 0; i < bfList.size(); i++){
        Label foodLabel = new Label(
                bfList.get(i).getFoodName() +
                        "\n\tCalories: " +
                        bfList.get(i).getCalories() +
                        "\n\tServing size: " +
                        bfList.get(i).getServingAmount());
        foodLabel.setTextFill(Color.web("#ddd9d9"));
        lunchVBox.getChildren().add(foodLabel);
      }
    }
  }

  /**
   * Updates dinner items in v box
   */
  public void updateDinnerVBox(){
    if (clientController.getClientDinnerFoods() != null) {
      ArrayList<FoodItem> bfList = clientController.getClientDinnerFoods();
      for (int i = 0; i < bfList.size(); i++){
        Label foodLabel = new Label(
                bfList.get(i).getFoodName() +
                        "\n\tCalories: " +
                        bfList.get(i).getCalories() +
                        "\n\tServing size: " +
                        bfList.get(i).getServingAmount());
        foodLabel.setTextFill(Color.web("#ddd9d9"));
        dinnerVBox.getChildren().add(foodLabel);
      }
    }
  }

  /**
   * Updates snack items in v box
   */
  public void updateSnackVBox(){
    if (clientController.getClientSnackFoods() != null) {
      ArrayList<FoodItem> bfList = clientController.getClientSnackFoods();
      for (int i = 0; i < bfList.size(); i++){
        Label foodLabel = new Label(
                bfList.get(i).getFoodName() +
                        "\n\tCalories: " +
                        bfList.get(i).getCalories() +
                        "\n\tServing size: " +
                        bfList.get(i).getServingAmount());
        foodLabel.setTextFill(Color.web("#ddd9d9"));
        snackVBox.getChildren().add(foodLabel);
      }
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {}
}
