package staff.OwnerViews;

import API.APIManager;
import API.FoodItem;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import staff.Controllers.OwnerController;

import java.io.IOException;
import java.util.ArrayList;


public class FoodSearchViewController {

    private APIManager foodSearch;
    private OwnerController controller;
    ArrayList<FoodItem> results = new ArrayList<>();

    // TextField
    @FXML TextField searchBar = new TextField();

    // Labels for search Results names
    @FXML Label topResultName = new Label();
    @FXML Label secondResultName = new Label();
    @FXML Label thirdResultName = new Label();

    // Labels for search Results calories
    @FXML Label topResultCals = new Label();
    @FXML Label secondResultCals = new Label();
    @FXML Label thirdResultCals = new Label();

    public void setUpScene(OwnerController controller){
        this.controller = controller;
    }

    /**
     * Searches for food with inputted string
     * @param event event of search button being pressed
     * @throws UnirestException exception
     */
    public void onSearchButtonPressed(ActionEvent event) throws UnirestException {
        results = foodSearch.searchForFoodItem(searchBar.getText());
        topResultName.setText(results.get(0).getFoodName());
        topResultCals.setText(String.valueOf(results.get(0).getCalories()));
        secondResultName.setText(results.get(1).getFoodName());
        secondResultCals.setText(String.valueOf(results.get(1).getCalories()));
        thirdResultName.setText(results.get(2).getFoodName());
        thirdResultCals.setText(String.valueOf(results.get(2).getCalories()));
    }

    /**
     * Changes scene to foodTypeSelectView scene
     * @param event of add button being clicked
     * @param food food to be added to client
     */
    private void changeScene(ActionEvent event, FoodItem food) throws IOException {
        // Loads Scene for main view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("foodTypeSelectView.fxml"));
        Parent root = loader.load();

        // Gets main view controller and passes client to it
        FoodTypeSelectViewController viewController = loader.getController();
        viewController.setUpScene(controller, food);

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }


    /**
     * Goes to food type select view to add food to client
     * @param event first result add button clicked
     */
    public void topResultAddButton(ActionEvent event) throws IOException {
        changeScene(event, results.get(0));
    }

    /**
     * Goes to food type select view to add food to client
     * @param event second result add button clicked
     */
    public void secondResultAddButton(ActionEvent event) throws IOException {
        changeScene(event, results.get(1));
    }

    /**
     * Goes to food type select view to add food to client
     * @param event third result add button clicked
     */
    public void thirdResultAddButton(ActionEvent event) throws IOException {
        changeScene(event, results.get(2));
    }

}