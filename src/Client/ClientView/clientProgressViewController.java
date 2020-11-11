package Client.ClientView;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class clientProgressViewController implements Initializable {


  private Label progressLabel = new Label();

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
    progressLabel.setText("Goal");
  }
}
