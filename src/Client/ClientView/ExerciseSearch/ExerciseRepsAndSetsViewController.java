package Client.ClientView.ExerciseSearch;

import API.ExerciseItem;
import Client.ClientController;

public class ExerciseRepsAndSetsViewController {

    //Exercise Item
    ExerciseItem exerciseItem;

    // Controller to hold client information
    private ClientController clientController = new ClientController(null);

    public void setupScene(ClientController controller, ExerciseItem exerciseItem) {
        clientController = controller;
        this.exerciseItem = exerciseItem;
    }
}
