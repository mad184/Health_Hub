package staff.InstructorViews;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import staff.Controllers.InstructorController;
import database.EmptyQueryException;
import database.JsonObjectException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class InstructorSettingViewController {

    // Label to display Instructor information
    @FXML public Label nameLabel = new Label();
    @FXML public Label ageLabel = new Label();
    @FXML public Label heightLabel = new Label();
    @FXML public Label weightGoalLabel = new Label();
    @FXML public Label calGoalLabel = new Label();

    // Text inputs for editing Instructor info
    @FXML public TextInputDialog nameInput = new TextInputDialog("Enter your new name");
    @FXML public TextInputDialog ageInput = new TextInputDialog("Enter your new age");
    @FXML public TextInputDialog heightInput = new TextInputDialog("Enter your new height in cm");
    @FXML public TextInputDialog weightGoalInput = new TextInputDialog("Enter your new weight goal in kg");
    @FXML public TextInputDialog calorieGoalInput = new TextInputDialog("Enter your new calorie goal");

    // Controller for Instructor
    private InstructorController controller = new InstructorController(null);

    public void setupScene(InstructorController instructor) {
        controller = instructor;
        nameLabel.setText(controller.getName());
        ageLabel.setText(String.valueOf(controller.getAge()));
        heightLabel.setText(String.valueOf(controller.getHeight()) + "cm");
        weightGoalLabel.setText(String.valueOf(controller.getWeight()) + "Kg");
        calGoalLabel.setText(String.valueOf(controller.getCaloriesGoal()) + "Kcal");
    }

    public void onNamePressed(ActionEvent event) throws JsonObjectException, EmptyQueryException {
        nameInput.showAndWait();
        String input = nameInput.getEditor().getText();
        controller.setName(input);
        nameLabel.setText(controller.getName());
        controller.model.db.updateInstructor(controller.getId(), controller.model.toJson());
    }

    public void onAgePressed(ActionEvent event) throws JsonObjectException, EmptyQueryException {
        ageInput.showAndWait();
        String input = ageInput.getEditor().getText();
        controller.setAge(Integer.parseInt(input));
        ageLabel.setText(input);
        controller.model.db.updateInstructor(controller.getId(), controller.model.toJson());
    }

    public void onHeightPressed(ActionEvent event) throws JsonObjectException, EmptyQueryException {
        heightInput.showAndWait();
        String input = heightInput.getEditor().getText();
        controller.setHeight(Integer.parseInt(input));
        heightLabel.setText(input);
        controller.model.db.updateInstructor(controller.getId(), controller.model.toJson());
    }

    public void onWeightGoalPressed(ActionEvent event)
            throws JsonObjectException, EmptyQueryException {
        weightGoalInput.showAndWait();
        String input = weightGoalInput.getEditor().getText();
        controller.setWeight(Integer.parseInt(input));
        weightGoalLabel.setText(input);
        controller.model.db.updateInstructor(controller.getId(), controller.model.toJson());
    }

    public void onCalorieGoalPressed(ActionEvent event)
            throws JsonObjectException, EmptyQueryException {
        calorieGoalInput.showAndWait();
        String input = calorieGoalInput.getEditor().getText();
        controller.setGoalCal(Integer.parseInt(input));
        calGoalLabel.setText(input);
        controller.model.db.updateInstructor(controller.getId(), controller.model.toJson());
    }

    // Goes to main view scene when back button is pushed
    public void onBackButtonPressed(ActionEvent event) throws IOException {
        // Loads Scene for main view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("instructorMainView.fxml"));
        Parent root = loader.load();

        // Gets main view controller and passes client to it
        InstructorMainViewController viewController = loader.getController();
        viewController.setupScene(controller);

        Scene viewScene = new Scene(root);
        // Gets stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }
}
