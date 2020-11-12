package Client.ClientView;

import Client.Client;
import Client.ClientController;
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

  public void setupScene(Client client){
    clientController.setModel(client);
  }

  // Goes to main view scene when back button is pushed
  public void onBackButtonPressed(ActionEvent event) throws IOException {
    Parent mainViewParent = FXMLLoader.load(getClass().getResource("clientMainView.fxml"));
    Scene mainViewScene = new Scene(mainViewParent);

    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(mainViewScene);
    window.show();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }
}
