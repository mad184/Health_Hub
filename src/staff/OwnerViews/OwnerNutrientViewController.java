package staff.OwnerViews;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import staff.Controllers.InstructorController;
import staff.Controllers.OwnerController;
import staff.OwnerViews.FoodSearchView.FoodSearchViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OwnerNutrientViewController implements Initializable {

    private OwnerController controller;

    public void setupScene(OwnerController controller) {
        this.controller = controller;
    }

    // Goes to main view scene when back button is pushed
    public void onBackButtonPressed(ActionEvent event) throws IOException {
        // Loads Scene for main view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ownerMainView.fxml"));
        Parent root = loader.load();

        // Gets main view controller and passes client to it
        OwnerMainViewController MainViewController = loader.getController();
        MainViewController.setupScene(controller);

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }

    public void foodSearch(ActionEvent event) throws IOException {
        // Load food search scene
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("FoodSearchView/foodSearchView.fxml"));
        Parent root = loader.load();

        // Get controller for search scene
        FoodSearchViewController viewController = loader.getController();
        // setup scene
        viewController.setupScene(controller);

        // Create popup window/stage and make it a sub-window of the main stage
        final Stage foodSearchStage = new Stage();
        foodSearchStage.initModality(Modality.APPLICATION_MODAL);
        foodSearchStage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());

        Scene foodSearchScene = new Scene(root, 400, 267);
        foodSearchStage.setScene(foodSearchScene);
        foodSearchStage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
