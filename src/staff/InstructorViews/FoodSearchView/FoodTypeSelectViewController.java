package staff.InstructorViews.FoodSearchView;

import API.FoodItem;
import Client.ClientController;
import Client.ClientToDB;
import Client.ClientView.ClientNutrientViewController;
import database.Dbms;
import database.EmptyQueryException;
import database.JsonObjectException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import staff.Controllers.InstructorController;
import staff.InstructorViews.InstructorNutrientController;

import java.io.IOException;

public class FoodTypeSelectViewController {
  // Database connection
  //ClientToDB DB = new ClientToDB();

  // Controller for client
  private InstructorController instructorController = new InstructorController(null);
  private Dbms db = null;

  // Food item to be added to client
  private FoodItem food;

  /**
   * Setups of scene
   *
   * @param instructor the InstructorController
   * @param food food to be added to client
   */
  public void setupScene(InstructorController instructor, FoodItem food) {
    instructorController = instructor;
    db = instructorController.getDbms();
    this.food = food;
  }

  /**
   * Adds food to breakfast food group
   *
   * @param event breakfast button clicked
   */
  public void breakfastButtonPressed(ActionEvent event)
          throws JsonObjectException, EmptyQueryException, IOException {
    instructorController.addBreakfastFood(food);
    instructorController.setCalories(instructorController.getCalories() + food.getCalories());
    db.updateInstructor(instructorController.getId(), instructorController.model.toJson());
    nutrientView(event);
  }

  /**
   * Adds food to lunch food group
   *
   * @param event lunch button clicked
   */
  public void lunchButtonPressed(ActionEvent event)
          throws JsonObjectException, EmptyQueryException, IOException {
    instructorController.addLunchFood(food);
    instructorController.setCalories(instructorController.getCalories() + food.getCalories());
    db.updateInstructor(instructorController.getId(), instructorController.model.toJson());
    nutrientView(event);
  }

  /**
   * Adds food to dinner food group
   *
   * @param event dinner button clicked
   */
  public void dinnerButtonPressed(ActionEvent event)
          throws JsonObjectException, EmptyQueryException, IOException {
    instructorController.addDinnerFood(food);
    instructorController.setCalories(instructorController.getCalories() + food.getCalories());
    db.updateInstructor(instructorController.getId(), instructorController.model.toJson());
    nutrientView(event);
  }

  /**
   * Adds food to snack food group
   *
   * @param event snack button clicked
   */
  public void snackButtonPressed(ActionEvent event)
          throws JsonObjectException, EmptyQueryException, IOException {
    instructorController.addSnackFood(food);
    instructorController.setCalories(instructorController.getCalories() + food.getCalories());
    db.updateInstructor(instructorController.getId(), instructorController.model.toJson());
    nutrientView(event);
  }

  /**
   * closes window
   *
   * @param event button clicked
   */
  private void nutrientView(ActionEvent event) throws IOException, EmptyQueryException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../InstructorNutrientView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    InstructorNutrientController viewController = loader.getController();
    viewController.setupScene(instructorController);

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();

  }

  /**
   * closes window
   *
   * @param event button clicked
   */
  private void closeWindow(ActionEvent event) {
    // Closes popup window
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.close();
  }
}
