package Client.ClientView;

import Client.Client;
import Client.ClientController;
import Client.ClientToDB;
import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ClientMainViewController {

  // test client for gui testing purposes
  public ClientController clientController;
  // Client information

  // DB object (Currently setup to test db)
  ClientToDB DB = new ClientToDB();

  // UI
  // Label for client name
  @FXML private Label nameLabel = new Label();

  // Label for most recent client recommendations
  @FXML private Label recommendationLabel = new Label();

  // Label for most recent client comments
  @FXML private Label commentLabel = new Label();

  public void setupScene(int clientID) throws EmptyQueryException {
    // Sets client to client controller for scene
    clientController = new ClientController(new Client(DB.getClient(clientID)));

    // Changes name label to clients name
    nameLabel.setText(clientController.getClientName());

    // Changes recommendation label to client's recommendations
    recommendationLabel.setText("None");

    // Add comment to client for testing
    ArrayList<String> comment = new ArrayList<String>();
    comment.add("If you got time to sit, you have time to squat");

    // Sets clients comments label to none if there are no comments, else sets to first comment
    clientController.setClientComment(comment);
    if (clientController.getClientComment() == null) {
      commentLabel.setText("None");
    } else {
      commentLabel.setText(clientController.getClientComment().get(0));
    }
  }

  /**
   * Goes to exercise page when exercise button is pressed
   *
   * @param event Event of when exercise button is pressed
   * @throws IOException throws exception
   */
  public void onExerciseButtonPushed(ActionEvent event) throws IOException {
    // Loads Scene for exercise view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("clientExerciseView.fxml"));
    Parent root = loader.load();

    // Gets exercise view controller and passes client to it
    ClientExerciseViewController viewController = loader.getController();
    viewController.setupScene(clientController);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }

  /**
   * Goes to progress page when progress button is pressed
   *
   * @param event Event of when progress button pressed
   * @throws IOException throws exception
   */
  public void onProgressButtonPushed(ActionEvent event) throws IOException {
    // Loads Scene for progress view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("clientProgressView.fxml"));
    Parent root = loader.load();

    // Gets progress view controller and passes client to it
    ClientProgressViewController viewController = loader.getController();
    viewController.setupScene(clientController);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }

  /**
   * Goes to nutrient page when nutrient button is pressed
   *
   * @param event Event of nutrient button pressed
   * @throws IOException throws exception
   */
  public void onNutrientButtonPushed(ActionEvent event) throws IOException {
    // Loads Scene for nutrient view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("clientNutrientView.fxml"));
    Parent root = loader.load();

    // Gets nutrient view controller and passes client to it
    ClientNutrientViewController viewController = loader.getController();
    viewController.setupScene(clientController);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }

  /**
   * Goes to profile page when profile button is pressed
   *
   * @param event Event of profile button pressed
   * @throws IOException throws exception
   */
  public void onProfileButtonPushed(ActionEvent event) throws IOException {
    // Loads Scene for profile view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("clientProfileView.fxml"));
    Parent root = loader.load();

    // Gets profile view controller and passes client to it
    ClientProfileViewController viewController = loader.getController();
    viewController.setupScene(clientController);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }

  /**
   * Goes to settings page when settings button is pressed
   *
   * @param event Event of setting button pressed
   * @throws IOException throws exception
   */
  public void onSettingsButtonPushed(ActionEvent event) throws IOException {
    // Loads Scene for settings view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("clientSettingsView.fxml"));
    Parent root = loader.load();

    // Gets Setting view controller and passes client to it
    ClientSettingsViewController viewController = loader.getController();
    viewController.setupScene(clientController);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }
}
