package Client.ClientView;

import Client.Client;
import Client.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientSettingsViewController {

  private ClientController clientController = new ClientController(null);

  // Label to display clients information
  @FXML public Label nameLabel = new Label();
  @FXML public Label ageLabel = new Label();
  @FXML public Label heightLabel = new Label();
  @FXML public Label weightGoalLabel = new Label();
  @FXML public Label calorieGoalLabel = new Label();

  //Text inputs for editing client info
  @FXML public TextInputDialog nameInput = new TextInputDialog("Enter your new name");
  @FXML public TextInputDialog ageInput = new TextInputDialog("Enter your new age");
  @FXML public TextInputDialog heightInput = new TextInputDialog("Enter your new height");
  @FXML public TextInputDialog weightGoalInput = new TextInputDialog("Enter your new weight goal");
  @FXML public TextInputDialog calorieGoalInput = new TextInputDialog("Enter your new calorie goal");

  public void setupScene(Client client) {
    clientController.setModel(client);
    nameLabel.setText(clientController.getClientName());
    ageLabel.setText(String.valueOf(clientController.getClientAge()));
    heightLabel.setText(String.valueOf(clientController.getClientHeight()) + "cm");
    weightGoalLabel.setText(String.valueOf(clientController.getClientWeightGoal()) + "Kg");
    calorieGoalLabel.setText(String.valueOf(clientController.getClientCalGoal()) + "Kcal");
  }

  public void onNamePressed(ActionEvent event){
    nameInput.showAndWait();
    String input = nameInput.getEditor().getText();
    clientController.setClientName(input);
    nameLabel.setText(clientController.getClientName());

  }

  // Goes to main view scene when back button is pushed
  public void onBackButtonPressed(ActionEvent event) throws IOException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("clientMainView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    ClientMainViewController viewController = loader.getController();
    viewController.setupScene(clientController.getModel());

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }
  }

