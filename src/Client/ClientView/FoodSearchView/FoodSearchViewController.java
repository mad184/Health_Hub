package Client.ClientView.FoodSearchView;

import API.APIManager;
import API.FoodItem;
import Client.ClientController;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class FoodSearchViewController {
    //APIManager Object for food search
    APIManager foodSearch = new APIManager();

    //Controller to hold client information
    private ClientController clientController = new ClientController(null);

    //TextField
    @FXML TextField searchBar = new TextField();

    //Labels for search Results names
    @FXML Label topResultName = new Label();
    @FXML Label secondResultName = new Label();
    @FXML Label thirdResultName = new Label();

    //Labels for search Results calories
    @FXML Label topResultCals = new Label();
    @FXML Label secondResultCals = new Label();
    @FXML Label thirdResultCals = new Label();

    //Sets up scene on start of scene
    public void setupScene(ClientController client) {
        clientController = client;
    }

    public void onSearchButtonPressed(ActionEvent event) throws UnirestException {
        ArrayList<FoodItem> results = foodSearch.searchForFoodItem(searchBar.getText());
        topResultName.setText(results.get(0).getFoodName());
        topResultCals.setText(String.valueOf(results.get(0).getCalories()));
        secondResultName.setText(results.get(1).getFoodName());
        secondResultCals.setText(String.valueOf(results.get(1).getCalories()));
        thirdResultName.setText(results.get(2).getFoodName());
        thirdResultCals.setText(String.valueOf(results.get(2).getCalories()));
    }

}
