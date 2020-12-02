package staff.InstructorViews.ExerciseSearch;

import API.APIManager;
import API.ExerciseItem;
import Client.ClientController;
import Client.ClientView.ClientExerciseViewController;
import com.mashape.unirest.http.exceptions.UnirestException;
import database.EmptyQueryException;
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
    @FXML
    TextField searchBar = new TextField();

    //VBoxes for results
    @FXML
    VBox exerciseNameVBox = new VBox();
    @FXML
    VBox addButtonVBox = new VBox();

    //Array list initialized for results
    private ArrayList<ExerciseItem> results = new ArrayList<>();

    // Controller to hold client information
    private ClientController clientController = new ClientController(null);

    /**
     * Sets up viewController for scene
     *
     * @param controller client info controller
     */
    public void setupScene(ClientController controller) {
        clientController = controller;
    }

    /**
     * Searches for exercise from string inputted into textfield, then displays results with add button for each result
     *
     * @param event of search button being pressed
     */
    public void onSearchButtonPressed(ActionEvent event) throws UnirestException {
        exerciseNameVBox.getChildren().removeAll();
        addButtonVBox.getChildren().removeAll();
        results = apiManager.findExerciseSearchMatches(searchBar.getText());
        if (results.isEmpty() || results == null) {
            //Label for no results
            Label noResultsLabel = new Label("No results have been found");
            noResultsLabel.setTextFill(Color.web("#ddd9d9"));

            //Button to add own exercise
            Button addNewButton = new Button();
            addNewButton.setText("Add exercise");
            addNewButton.setTextFill(Color.web("#ddd9d9"));
            addNewButton.setStyle(
                    "-fx-border-color: #9643a9; "
                            + "-fx-border-radius: 10; "
                            + "-fx-background-color: #2a0033; "
                            + "-fx-background-radius: 10");
            addNewButton.setPrefSize(125, 33);

            //Setup action for addNewButton
            //Adds action to add button
            addNewButton.setOnAction(
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            try {
                                addNewExerciseButtonPressed(e);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    });

            //Add label and button to screen
            exerciseNameVBox.getChildren().add(noResultsLabel);
            addButtonVBox.getChildren().add(addNewButton);
        } else {
            for (int i = 0; i < results.size(); i++) {
                //Setup Label for exercise
                Label exerciseLabel = new Label(results.get(i).getExerciseName());
                exerciseLabel.setTextFill(Color.web("#ddd9d9"));

                //Setups add button
                Button addButton = new Button();
                addButton.setText("+");
                addButton.setTextFill(Color.web("#ddd9d9"));
                addButton.setStyle(
                        "-fx-border-color: #9643a9; "
                                + "-fx-border-radius: 30; "
                                + "-fx-background-color: #2a0033; "
                                + "-fx-background-radius: 30");
                addButton.setPrefSize(33, 33);
                int finalI = i;

                //Adds action to add button
                addButton.setOnAction(
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                                try {
                                    addButtonPressed(e, results.get(finalI));
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            }
                        });

                //Adds exercise name and add button to screen
                exerciseNameVBox.getChildren().add(exerciseLabel);
                addButtonVBox.getChildren().add(addButton);
            }
        }
    }

    /**
     * Goes to ExerciseRepsAndSetsView, passes client and the exercise being added to the controller for said view
     *
     * @param event of add button being pressed
     * @param item  exercise item wanting to be added
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

    public void addNewExerciseButtonPressed(ActionEvent event) throws IOException {
        // Load food search scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("exerciseAddNewExerciseView.fxml"));
        Parent root = loader.load();

        // Get controller for search scene
        ExerciseAddNewExerciseViewController viewController = loader.getController();
        // setup scene
        viewController.setupScene(clientController);

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }

    /**
     * Goes back to main view
     *
     * @param event of back button being pushed
     */
    public void onBackButtonPressed(ActionEvent event) throws IOException, EmptyQueryException {
        // Loads Scene for main view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../clientExerciseView.fxml"));
        Parent root = loader.load();

        // Gets main view controller and passes client to it
        ClientExerciseViewController viewController = loader.getController();
        viewController.setupScene(clientController);

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
