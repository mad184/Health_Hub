package Client.ClientView.FoodSearchView;

import API.APIManager;
import API.FoodItem;
import Client.ClientController;
import Client.ClientView.ClientMainViewController;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class FoodSearchViewController {
  // APIManager Object for food search
  APIManager foodSearch = new APIManager();
  // TextField
  @FXML TextField searchBar = new TextField();
  // Labels for search Results names
  @FXML Label topResultName = new Label();
  @FXML Label secondResultName = new Label();
  @FXML Label thirdResultName = new Label();
  // Labels for search Results calories
  @FXML Label topResultCals = new Label();
  @FXML Label secondResultCals = new Label();
  @FXML Label thirdResultCals = new Label();
  // Controller to hold client information
  private ClientController clientController = new ClientController(null);

  // Sets up scene on start of scene
  public void setupScene(ClientController client) {
    clientController = client;
  }

  /**
   * Searches for food with inputted string
   * @param event event of search button being pressed
   * @throws UnirestException exception
   */
  public void onSearchButtonPressed(ActionEvent event) throws UnirestException {
    ArrayList<FoodItem> results = foodSearch.searchForFoodItem(searchBar.getText());
    topResultName.setText(results.get(0).getFoodName());
    topResultCals.setText(String.valueOf(results.get(0).getCalories()));
    secondResultName.setText(results.get(1).getFoodName());
    secondResultCals.setText(String.valueOf(results.get(1).getCalories()));
    thirdResultName.setText(results.get(2).getFoodName());
    thirdResultCals.setText(String.valueOf(results.get(2).getCalories()));
  }


  public void topResultAddButton(ActionEvent event) throws IOException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("foodTypeSelectView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    foodTypeSelectViewController viewController = loader.getController();
    viewController.setupScene(clientController);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }

  public void secondResultAddButton(){

  }

  public void thirdResultAddButton(){

  }
}
