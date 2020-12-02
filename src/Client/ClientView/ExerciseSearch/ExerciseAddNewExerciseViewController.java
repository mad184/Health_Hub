package Client.ClientView.ExerciseSearch;

import Client.ClientController;
import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExerciseAddNewExerciseViewController implements Initializable {

    private ClientController controller = new ClientController(null);

    public void setupScene(ClientController clientController) {
        controller = clientController;
    }


    /**
     * Goes back to main view
     *
     * @param event of back button being pushed
     */
    public void onBackButtonPressed(ActionEvent event) throws IOException, EmptyQueryException {
        // Loads Scene for main view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("exerciseSearchView.fxml"));
        Parent root = loader.load();

        // Gets main view controller and passes client to it
        ExerciseSearchViewController viewController = loader.getController();
        viewController.setupScene(controller);

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
