package staff.OwnerViews;

import API.FoodItem;
import database.EmptyQueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import staff.Controllers.InstructorController;
import staff.Controllers.OwnerController;
import staff.OwnerViews.FoodSearchView.FoodSearchViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OwnerNutrientViewController implements Initializable {

  private OwnerController controller;
  // VBoxes that display all food items
  @FXML VBox breakfastVBox = new VBox();
  @FXML VBox lunchVBox = new VBox();
  @FXML VBox dinnerVBox = new VBox();
  @FXML VBox snackVBox = new VBox();

  public void setupScene(OwnerController controller) {
    this.controller = controller;
    updateBreakfastVBox();
    updateLunchVBox();
    updateDinnerVBox();
    updateSnackVBox();
  }

  // Goes to main view scene when back button is pushed
  public void onBackButtonPressed(ActionEvent event) throws IOException, EmptyQueryException {
    // Loads Scene for main view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ownerMainView.fxml"));
    Parent root = loader.load();

    // Gets main view controller and passes client to it
    OwnerMainViewController MainViewController = loader.getController();
    MainViewController.setupScene(controller.getId());

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

    Scene viewScene = new Scene(root);
    // Gets stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(viewScene);
    window.show();
  }

  /** Updates breakfast items in v box */
  public void updateBreakfastVBox() {
    if (controller.getBreakfastFoods() != null) {
      ArrayList<FoodItem> bfList = (ArrayList<FoodItem>) controller.getBreakfastFoods();
      for (int i = 0; i < bfList.size(); i++) {
        Label foodLabel =
            new Label(
                bfList.get(i).getFoodName()
                    + "\n\tCalories: "
                    + bfList.get(i).getCalories()
                    + "\n\tServing size: "
                    + bfList.get(i).getServingAmount());
        foodLabel.setTextFill(Color.web("#ddd9d9"));
        breakfastVBox.getChildren().add(foodLabel);
      }
    }
  }

  /** Updates lunch items in v box */
  public void updateLunchVBox() {
    if (controller.getLunchFoods() != null) {
      ArrayList<FoodItem> bfList = (ArrayList<FoodItem>) controller.getLunchFoods();
      for (int i = 0; i < bfList.size(); i++) {
        Label foodLabel =
            new Label(
                bfList.get(i).getFoodName()
                    + "\n\tCalories: "
                    + bfList.get(i).getCalories()
                    + "\n\tServing size: "
                    + bfList.get(i).getServingAmount());
        foodLabel.setTextFill(Color.web("#ddd9d9"));
        lunchVBox.getChildren().add(foodLabel);
      }
    }
  }

  /** Updates dinner items in v box */
  public void updateDinnerVBox() {
    if (controller.getDinnerFoods() != null) {
      ArrayList<FoodItem> bfList = (ArrayList<FoodItem>) controller.getDinnerFoods();
      for (int i = 0; i < bfList.size(); i++) {
        Label foodLabel =
            new Label(
                bfList.get(i).getFoodName()
                    + "\n\tCalories: "
                    + bfList.get(i).getCalories()
                    + "\n\tServing size: "
                    + bfList.get(i).getServingAmount());
        foodLabel.setTextFill(Color.web("#ddd9d9"));
        dinnerVBox.getChildren().add(foodLabel);
      }
    }
  }

  /** Updates snack items in v box */
  public void updateSnackVBox() {
    if (controller.getSnackFoods() != null) {
      ArrayList<FoodItem> bfList = (ArrayList<FoodItem>) controller.getSnackFoods();
      for (int i = 0; i < bfList.size(); i++) {
        Label foodLabel =
            new Label(
                bfList.get(i).getFoodName()
                    + "\n\tCalories: "
                    + bfList.get(i).getCalories()
                    + "\n\tServing size: "
                    + bfList.get(i).getServingAmount());
        foodLabel.setTextFill(Color.web("#ddd9d9"));
        snackVBox.getChildren().add(foodLabel);
      }
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {}
}
