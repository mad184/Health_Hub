package Client.ClientView.ExerciseSearch;

import API.APIManager;
import API.ExerciseItem;
import Client.ClientController;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExerciseSearchViewController implements Initializable {

    // APIManager Object for food search
    private final APIManager apiManager = new APIManager();

    // TextField
    private @FXML
    final TextField searchBar = new TextField();

    //Array list initialized for results
    private ArrayList<ExerciseItem> results = new ArrayList<>();

    // Controller to hold client information
    private ClientController clientController = new ClientController(null);

    public void setupScene(ClientController controller) {
        clientController = controller;
    }

    public void onSearchButtonPressed(ActionEvent event) throws UnirestException {
        results = apiManager.findExerciseSearchMatches(searchBar.getText());
        for (int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
