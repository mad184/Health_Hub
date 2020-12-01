package Client.ClientView.ExerciseSearch;

import API.APIManager;
import API.ExerciseItem;
import Client.ClientController;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExerciseSearchViewController implements Initializable {

    // APIManager Object for food search
    private final APIManager apiManager = new APIManager();

    // TextField
    private @FXML
    final TextField searchBar = new TextField();

    //VBoxes for results
    @FXML
    private final VBox exerciseNameVBox = new VBox();
    @FXML
    private final VBox addButtonVBox = new VBox();

    //Array list initialized for results
    private ArrayList<ExerciseItem> results = new ArrayList<>();

    // Controller to hold client information
    private ClientController clientController = new ClientController(null);

    public void setupScene(ClientController controller) {
        clientController = controller;
    }

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
                    addButtonPressed(e, results.get(finalI));
                }
            });
            exerciseNameVBox.getChildren().add(exerciseLabel);
            addButtonVBox.getChildren().add(addButton);
        }
    }

    public void addButtonPressed(ActionEvent event, ExerciseItem item) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
