package Client.ClientView.ExerciseSearch;

import API.ExerciseItem;
import Client.ClientController;
import Client.ClientToDB;
import Client.ClientView.ClientExerciseViewController;
import database.EmptyQueryException;
import database.JsonObjectException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExerciseAddNewExerciseViewController implements Initializable {

    //Connection to database object
    ClientToDB DB = new ClientToDB();

    //Exercise info textFields
    @FXML
    TextField exerciseNameInput = new TextField();
    @FXML
    TextField setInput = new TextField();
    @FXML
    TextField repInput = new TextField();

    private ClientController controller = new ClientController(null);

    public void setupScene(ClientController clientController) {
        controller = clientController;
    }


    /**
     * Goes back to main view
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

    public void onExerciseButtonPushed(ActionEvent event) throws JsonObjectException, EmptyQueryException, IOException {
        //Creates new exercise Item
        ExerciseItem exerciseItem = new ExerciseItem(
                exerciseNameInput.getText(),
                Integer.parseInt(setInput.getText()),
                Integer.parseInt(repInput.getText()));

        //Adds Exercise to client
        controller.addExercise(exerciseItem);

        //Updates client in database
        DB.updateClient(controller.getClientID(), controller.clientToJson());

        //Goes back to main exercise view
        goToExerciseView(event);
    }

    public void goToExerciseView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../clientExerciseView.fxml"));
        Parent root = loader.load();

        // Gets main view controller and passes client to it
        ClientExerciseViewController viewController = loader.getController();
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
