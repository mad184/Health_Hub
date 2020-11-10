package Client.ClientView.resources;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientView extends Application {

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
}
