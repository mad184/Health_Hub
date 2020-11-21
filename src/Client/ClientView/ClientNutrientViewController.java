package Client.ClientView;

import API.FoodItem;
import Client.ClientController;
import Client.ClientToDB;
import Client.ClientView.FoodSearchView.FoodSearchViewController;
import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientNutrientViewController implements Initializable {

  private ClientController clientController = new ClientController(null);

  private VBox breakfastVBox = new VBox();

  // DB object (Currently setup to test db)
  ClientToDB DB = new ClientToDB();

  public void setupScene(ClientController client) {
    clientController = client;
    updateBreakfastVBox();
  }

  // Goes to main view scene when back button is pushed
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

  public void updateBreakfastVBox(){
    if (clientController.getClientBreakfastFoods() != null) {
      ArrayList<FoodItem> bfList = clientController.getClientBreakfastFoods();
      for (int i = 0; i < bfList.size(); i++){
        Label foodLabel = new Label(
                bfList.get(i).getFoodName() +
                ", Calories: " +
                bfList.get(i).getCalories() +
                        ", Serving size: " +
                        bfList.get(i).getServingAmount());
        breakfastVBox.getChildren().add(foodLabel);
      }
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {}
}
