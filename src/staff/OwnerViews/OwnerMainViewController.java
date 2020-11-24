package staff.OwnerViews;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

import staff.Controllers.OwnerController;

public class OwnerMainViewController {

    OwnerController controller;

    // Label for Instructor name
    @FXML private Label nameLabel = new Label();

    // Label for most recent client recommendations
    @FXML private Label recommendationLabel = new Label();

    // Label for most recent client comments
    @FXML private Label commentLabel = new Label();

    public void setupScene(OwnerController ownerController) {
        // Sets client to client controller for scene
        controller = ownerController;

        // Changes name label to clients name
        nameLabel.setText(controller.getName());

        // Changes recommendation label to client's recommendations
        recommendationLabel.setText("None");

        // Sets clients comments label to none if there are no comments, else sets to first comment

    }

    /**
     * Goes to exercise page when exercise button is pressed
     *
     * @param event Event of when exercise button is pressed
     * @throws IOException throws exception
     */
    public void onExerciseButtonPushed(ActionEvent event) throws IOException {
        // Loads Scene for exercise view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OwnerExerciseView.fxml"));
        Parent root = loader.load();

        // Gets exercise view controller and passes client to it
        OwnerExerciseViewController ExerciseViewController = loader.getController();
        ExerciseViewController.setupScene(controller);

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }

    /**
     * Goes to progress page when progress button is pressed
     *
     * @param event Event of when progress button pressed
     * @throws IOException throws exception
     */
    public void onProgressButtonPushed(ActionEvent event) throws IOException {
        // Loads Scene for progress view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OwnerProgressView.fxml"));
        Parent root = loader.load();

        // Gets progress view controller and passes client to it
        OwnerProgressViewController ProgressViewController = loader.getController();
        ProgressViewController.setupScene(controller);

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }

    /**
     * Goes to nutrient page when nutrient button is pressed
     *
     * @param event Event of nutrient button pressed
     * @throws IOException throws exception
     */
    public void onNutrientButtonPushed(ActionEvent event) throws IOException {
        // Loads Scene for nutrient view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OwnerNutrientView.fxml"));
        Parent root = loader.load();

        // Gets nutrient view controller and passes client to it
        OwnerNutrientViewController nutrientViewController = loader.getController();
        nutrientViewController.setupScene(controller);

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }

    /**
     * Goes to profile page when profile button is pressed
     *
     * @param event Event of profile button pressed
     * @throws IOException throws exception
     */
    public void onProfileButtonPushed(ActionEvent event) throws IOException {
        // Loads Scene for profile view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OwnerProfileView.fxml"));
        Parent root = loader.load();

        // Gets profile view controller and passes client to it
        OwnerProfileViewController ProfileViewController = loader.getController();
        ProfileViewController.setupScene(controller);

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }

    /**
     * Goes to settings page when settings button is pressed
     *
     * @param event Event of setting button pressed
     * @throws IOException throws exception
     */
    public void onSettingsButtonPushed(ActionEvent event) throws IOException {
        // Loads Scene for settings view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OwnerSettingsView.fxml"));
        Parent root = loader.load();

        // Gets Setting view controller and passes client to it
        OwnerSettingViewController SettingViewController = loader.getController();
        SettingViewController.setupScene(controller);

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }
}
