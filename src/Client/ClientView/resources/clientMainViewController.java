package Client.ClientView.resources;

import Client.Client;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import javax.imageio.ImageIO;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class clientMainViewController implements Initializable {

  // Label for client name
  private Label nameLabel = new Label();

  // File chooser for profile picture
  private FileChooser fileChooser;
  private File filepath;

  // Displayed picture for profile picture
  private Image profilePicture;

  // test client for gui testing purposes
  public Client client1 =
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
          1000,
          null,
          null,
          null);

  // Changes scene to inputted fxml file scene
  private void changeSceneButtonAction(ActionEvent event, String viewFXML) throws IOException {
    Parent viewParent = FXMLLoader.load(getClass().getResource(viewFXML));
    Scene viewScene = new Scene(viewParent);

    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(viewScene);
    window.show();
  }

  // Changes to exercise scene when exercise button is pushed
  public void onExerciseButtonPushed(ActionEvent event) throws IOException {
    changeSceneButtonAction(event, "clientExerciseView.fxml");
  }

  // Changes to progress scene when progress button is pushed
  public void onProgressButtonPushed(ActionEvent event) throws IOException {
    changeSceneButtonAction(event, "clientProgressView.fxml");
  }

  // Changes to nutrient scene when nutrient button is pushed
  public void onNutrientButtonPushed(ActionEvent event) throws IOException {
    changeSceneButtonAction(event, "clientNutrientView.fxml");
  }

  // Changes to profile scene when profile button is pushed
  public void onProfileButtonPushed(ActionEvent event) throws IOException {
    changeSceneButtonAction(event, "clientProfileView.fxml");
  }

  // Changes to settings scene when settings button is pushed
  public void onSettingsButtonPushed(ActionEvent event) throws IOException {
    changeSceneButtonAction(event, "clientSettingsView.fxml");
  }

  /*
  Will allow the client to choose profile picture
  **THROWS ERROR**
     - On picture upload attempt
  */
  public void onProfilePictureButtonPushed(ActionEvent event) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    fileChooser = new FileChooser();
    fileChooser.setTitle("Open image");

    // Go to users specific dir
    String userDirectoryString = System.getProperty("user.home");
    File userDirectory = new File(userDirectoryString);

    if (!userDirectory.canRead()) {
      userDirectory = new File("Macintosh HD");
    }

    fileChooser.setInitialDirectory(userDirectory);
    this.filepath = fileChooser.showOpenDialog(stage);

    // Try to update image by loading new image
    try {
      BufferedImage bufferedImage = ImageIO.read(filepath);
      Image image = SwingFXUtils.toFXImage(bufferedImage, null);
      client1.setProfilePicture(image);

    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    nameLabel.setText(client1.getName()); // Changes name label to clients name
  }
}
