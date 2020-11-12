package Client.ClientView;

import Client.ClientController;
import Client.Client;
import database.Dbms;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientMainViewController implements Initializable {

  //Client information
    //Database object (Currently setup to test db)
    private Dbms DB = new Dbms("Justyn", "Staff1", "Test-Justyn-Db", "testCollection");

    // test client for gui testing purposes
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

  //UI
    // Label for client name
    @FXML private Label nameLabel = new Label();

    //Label for most recent client recommendations
    @FXML private Label recommendationLabel = new Label();

    //Label for most recent client comments
    @FXML private Label commentLabel = new Label();

    // File chooser for profile picture
    private FileChooser fileChooser;
    private File filepath;

    // Displayed picture for profile picture
    private Image profilePicture;


  /**
   * Ran on when scene is initialized
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    //Client added to db for test purposes
    DB.createClient(clientController.getClientID(), clientController.clientToJson());

    // Changes name label to clients name
    nameLabel.setText(clientController.getClientName());

    // Changes recommendation label to client's recommendations
    recommendationLabel.setText("None");

    //Add comment to client for testing
    ArrayList<String> comment = new ArrayList<String>();
    comment.add("Get up lazy fuck");

    //Sets clients comments label to none if there are no comments, else sets to first comment
    clientController.setClientComment(comment);
    if (clientController.getClientComment() == null) {
      commentLabel.setText("None");
    } else {
      commentLabel.setText(clientController.getClientComment().get(0));
    }
  }


  /**
   * Changes scene to specified scene
   * @param event Event of a certain button being pushed
   * @param viewFXML FXML file represents certain view to go to
   * @throws IOException throws exception
   */
  private void changeSceneButtonAction(ActionEvent event, String viewFXML) throws IOException {
    Parent viewParent = FXMLLoader.load(getClass().getResource(viewFXML));
    Scene viewScene = new Scene(viewParent);

    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(viewScene);
    window.show();
  }

  /**
   * Goes to exercise page when exercise button is pressed
   * @param event Event of when exercise button is pressed
   * @throws IOException throws exception
   */
  public void onExerciseButtonPushed(ActionEvent event) throws IOException {
    changeSceneButtonAction(event, "clientExerciseView.fxml");
  }

  /**
   * Goes to progress page when progress button is pressed
   * @param event Event of when progress button pressed
   * @throws IOException throws exception
   */
  public void onProgressButtonPushed(ActionEvent event) throws IOException {
    changeSceneButtonAction(event, "clientProgressView.fxml");
  }

  /**
   * Goes to nutrient page when nutrient button is pressed
   * @param event Event of nutrient button pressed
   * @throws IOException throws exception
   */
  public void onNutrientButtonPushed(ActionEvent event) throws IOException {
    changeSceneButtonAction(event, "clientNutrientView.fxml");
  }

  /**
   * Goes to profile page when profile button is pressed
   * @param event Event of profile button pressed
   * @throws IOException throws exception
   */
  public void onProfileButtonPushed(ActionEvent event) throws IOException {
    changeSceneButtonAction(event, "clientProfileView.fxml");
  }

  /**
   * Goes to settings page when settings button is pressed
   * @param event Event of setting button pressed
   * @throws IOException throws exception
   */
  public void onSettingsButtonPushed(ActionEvent event) throws IOException {
    changeSceneButtonAction(event, "clientSettingsView.fxml");
  }

  /**
   * Opens file chooser to grab picture and then saves it under the client and displays on UI
   * **THROWS ERROR**
   * @param event Event of profile picture button being pressed
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
      clientController.setClientProfilePicture(image);

    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
