package Client.ClientView;

import Client.ClientController;
import Client.ClientToDB;
import Client.ClientView.FoodSearchView.FoodSearchViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientNutrientViewController implements Initializable {

  private ClientController clientController = new ClientController(null);

  // DB object (Currently setup to test db)
  ClientToDB DB = new ClientToDB();

  public void setupScene(ClientController client) {
    clientController = client;
  }

  // Goes to main view scene when back button is pushed
  public void onBackButtonPressed(ActionEvent event) throws IOException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("clientMainView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    ClientMainViewController viewController = loader.getController();
    viewController.setupScene(clientController);

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

    // Create popup window/stage and make it a sub-window of the main stage
    final Stage foodSearchStage = new Stage();
    foodSearchStage.initModality(Modality.APPLICATION_MODAL);
    foodSearchStage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());

    Scene foodSearchScene = new Scene(root, 400, 267);
    foodSearchStage.setScene(foodSearchScene);
    foodSearchStage.show();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {}
}
