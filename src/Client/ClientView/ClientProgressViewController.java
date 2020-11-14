package Client.ClientView;

import Client.Client;
import Client.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;


import java.io.IOException;

public class ClientProgressViewController {

  // Controller for client
  public ClientController clientController = new ClientController(null);

  // Label with percentage calories out of goal calories eaten so far
  @FXML private Label progressLabel = new Label();

  // Label for calorie intake
  @FXML private Label calorieLabel = new Label();

  // Label for calorie goal
  @FXML private Label calorieGoalLabel = new Label();

  // Progress bar for daily calorie intake progress
  @FXML private ProgressBar pBar = new ProgressBar();

  public void setupScene(Client client) {
    //Set controller to use current information for client
    clientController.setModel(client);

    //Set calorie labels to current values in client
    calorieLabel.setText(String.valueOf(clientController.getClientCals()));
    calorieGoalLabel.setText(String.valueOf(clientController.getClientCalGoal()));

    //Set progress label to clients current progress of daily calories
    progressLabel.setText(String.format("%.4f", calculateDailyProgress()));

    //Set progress bar value
    pBar.setProgress(calculateDailyProgress());
  }

  public double calculateDailyProgress(){
    double percentage = (double)clientController.getClientCals()/(double)clientController.getClientCalGoal();
    return percentage;
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
