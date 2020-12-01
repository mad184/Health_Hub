package Client.ClientView.ExerciseSearch;

import API.APIManager;
import API.ExerciseItem;
import Client.ClientController;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExerciseSearchViewController implements Initializable {

    // APIManager Object for food search
    private final APIManager apiManager = new APIManager();

    // TextField
    @FXML TextField searchBar = new TextField();

    //VBoxes for results
    @FXML VBox exerciseNameVBox = new VBox();
    @FXML VBox addButtonVBox = new VBox();

    //Array list initialized for results
    private ArrayList<ExerciseItem> results = new ArrayList<>();

    // Controller to hold client information
    private ClientController clientController = new ClientController(null);

    /**
     * Sets up viewController for scene
     * @param controller client info controller
     */
    public void setupScene(ClientController controller) {
        clientController = controller;
    }

    /**
     * Searches for exercise from string inputted into textfield, then displays results with add button for each result
     * @param event of search button being pressed
     */
    public void onSearchButtonPressed(ActionEvent event) throws UnirestException {
        exerciseNameVBox.getChildren().removeAll();
        addButtonVBox.getChildren().removeAll();
        results = apiManager.findExerciseSearchMatches(searchBar.getText());
        for (int i = 0; i < results.size(); i++) {
            Label exerciseLabel = new Label(results.get(i).getExerciseName());
            exerciseLabel.setTextFill(Color.web("#ddd9d9"));
            Button addButton = new Button();
            addButton.setText("+");
            addButton.setTextFill(Color.web("#ddd9d9"));
            addButton.setStyle(
                    "-fx-border-color: #9643a9; " +
                            "-fx-border-radius: 30; " +
                            "-fx-background-color: #2a0033; " +
                            "-fx-background-radius: 30");
            addButton.setPrefSize(33, 33);
            int finalI = i;
            addButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    try {
                        addButtonPressed(e, results.get(finalI));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
            exerciseNameVBox.getChildren().add(exerciseLabel);
            addButtonVBox.getChildren().add(addButton);
        }
    }

    /**
     * Goes to ExerciseRepsAndSetsView, passes client and the exercise being added to the controller for said view
     * @param event of add button being pressed
     * @param item exercise item wanting to be added
     */
    public void addButtonPressed(ActionEvent event, ExerciseItem item) throws IOException {
        // Load food search scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("exerciseRepsAndSetsView.fxml"));
        Parent root = loader.load();

        // Get controller for search scene
        ExerciseRepsAndSetsViewController viewController = loader.getController();
        // setup scene
        viewController.setupScene(clientController, item);

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
