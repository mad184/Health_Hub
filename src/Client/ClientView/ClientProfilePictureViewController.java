package Client.ClientView;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientProfilePictureViewController {

  // Goes to main view scene when back button is pushed
  public void onBackButtonPressed(ActionEvent event) throws IOException {
    Parent mainViewParent = FXMLLoader.load(getClass().getResource("clientMainView.fxml"));
    Scene mainViewScene = new Scene(mainViewParent);

    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(mainViewScene);
    window.show();
  }
}
