package Client.ClientView;

import Client.ClientController;
import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientProfileViewController implements Initializable {

  public ClientController clientController = new ClientController(null);
  private Label nameLabel = new Label();
  private Label ageLabel = new Label();

  public void setupScene(ClientController client) {
    clientController = client;
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

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {}
}
