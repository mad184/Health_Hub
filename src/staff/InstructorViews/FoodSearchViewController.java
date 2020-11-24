package staff.InstructorViews;

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

import java.io.IOException;
import java.util.ArrayList;


public class FoodSearchViewController {

    private APIManager foodSearch;
    private

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



}
