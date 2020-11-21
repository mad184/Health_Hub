package Client.ClientView;

import API.FoodItem;
import Client.Client;
import Client.ClientController;
import Client.ClientToDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ClientView extends Application {

  // test client for ui testing purposes
  public ClientController clientController =
      new ClientController(
          new Client(
              "Justyn Pollard",
              "Justynpollard12@hotmail.com",
              "1234",
              "Pete",
              "Gym1",
              1,
              20,
              180,
              180,
              "3068500727",
              170,
              3000,
              1000,
              null,
              null,
              null,
              new ArrayList<FoodItem>(),
              new ArrayList<FoodItem>(),
              new ArrayList<FoodItem>(),
              new ArrayList<FoodItem>()));

  // DB object (Currently setup to test db)
  ClientToDB DB = new ClientToDB();

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Runs when application is started
   * @param stage Window to hold all the scenes for the ui from javafx
   * @throws Exception exception
   */
  @Override
  public void start(Stage stage) throws Exception {
    // Add client to database for testing purpose
    DB.createClient(clientController.getClientID(), clientController.clientToJson());

    clientController.jsonToClient(DB.getClient(clientController.getClientID()));
    // Load main scene
    FXMLLoader loader = new FXMLLoader(getClass().getResource("clientMainView.fxml"));
    Parent root = loader.load();

    // Get controller for main scene
    ClientMainViewController viewController = loader.getController();
    // setup scene
    viewController.setupScene(clientController);

    // Show scene in window
    stage.setTitle("Client View");
    stage.setScene(new Scene(root, 600, 400));
    stage.show();
  }

  /** Runs when application is closed */
  @Override
  public void stop() {
    DB.removeClient(1); // Removes test client at end of application
    System.out.println("Health Hub application exited");
  }
}
