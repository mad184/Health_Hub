package Client.ClientView;

import Client.Client;
import Client.ClientController;
import database.Dbms;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientView extends Application {

  //DB object (Currently setup to test db)
  Dbms DB = new Dbms("Justyn", "Staff1", "Test-Justyn-Db", "testCollection");

  // test client for ui testing purposes
  public ClientController clientController =
          new ClientController(
                  new Client(
                          "Justyn Pollard",
                          "Justynpollard12@hotmail.com",
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
                          null));

  // Loads window/stage for client view, then loads main scene
  @Override
  public void start(Stage stage) throws Exception {
    //Add client to database for testing purpose
    DB.createClient(clientController.getClientID(), clientController.clientToJson());

    //Load main scene
    FXMLLoader loader = new FXMLLoader(getClass().getResource("clientMainView.fxml"));
    Parent root = loader.load();

    //Get controller for main scene
    ClientMainViewController viewController = loader.getController();
    //setup scene
    viewController.setupScene(clientController.getModel());

    //Show scene in window
    stage.setTitle("Client View");
    stage.setScene(new Scene(root, 600, 400));
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void stop(){
    DB.removeClient(1); //Removes test client at end of application
    System.out.println("Health Hub application exited");
  }
}


