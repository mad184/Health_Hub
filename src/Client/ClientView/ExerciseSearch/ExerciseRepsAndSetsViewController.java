package Client.ClientView.ExerciseSearch;

import Client.ClientController;

public class ExerciseRepsAndSetsViewController {

    // Controller to hold client information
    private ClientController clientController = new ClientController(null);

    public void setupScene(ClientController controller) {
        clientController = controller;
    }
}
