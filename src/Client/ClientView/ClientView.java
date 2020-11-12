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


  // Loads window/stage for client view, then loads main scene
  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("clientMainView.fxml"));
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


