package staff.Interfaces;

import API.FoodItem;
import javafx.scene.image.Image;
import staff.UserID;

import java.util.ArrayList;

public interface ManagerViewInterface {

  void setPassword(String password);

  void setId(int id);

  void setGoalWeight(int goalWeight);

  void setGoalCals(int goalCals);

  void setCalories(int calories);

  void setAllergies(ArrayList<String> allergies);

  void setComment(ArrayList<String> comment);

  void setInstructors(ArrayList<UserID> instructors);

  void setBreakFoods(ArrayList<FoodItem> breakFood);

  void setLunchFoods(ArrayList<FoodItem> lunchFoods);

  void setDinnerFoods(ArrayList<FoodItem> dinnerFoods);

  void setSnackFoods(ArrayList<FoodItem> snackFoods);

  void setProfilePicture(Image profilePicture);

  // ----------------GETTERS-------------//

  String getPassword();

  int getId();

  int getGoalWeight();

  int getGoalCals();

  int getCalories();

  ArrayList<String> getAllergies();

  ArrayList<String> getComment();

  ArrayList<UserID> getInstructors();

  ArrayList<FoodItem> getBreakFoods();

  ArrayList<FoodItem> getLunchFoods();

  ArrayList<FoodItem> getDinnerFoods();

  ArrayList<FoodItem> getSnackFoods();

  Image getProfilePicture();

  // ------------ADD TO ARRAYLIST<>-------------//

  void addToBreakFoods(FoodItem food);

  void addToLunchFoods(FoodItem food);

  void addToDinnerFoods(FoodItem food);

  void addToSnackFoods(FoodItem food);

  void addToInstructors(UserID instructor);
}
