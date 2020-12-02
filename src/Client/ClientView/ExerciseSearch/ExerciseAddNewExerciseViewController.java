package Client.ClientView.ExerciseSearch;

import Client.ClientController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ExerciseAddNewExerciseViewController implements Initializable {

    private ClientController controller = new ClientController(null);

    public void setupScene(ClientController clientController) {
        controller = clientController;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
