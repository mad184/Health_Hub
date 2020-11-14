package Client;

import API.FoodItem;
import javafx.scene.image.Image;

import java.util.ArrayList;

public interface ClientInterface {

  // setters for client attributes
  void setName(String name);

  void setAge(int age);

  void setId(int id);

  void setEmail(String email);

  void setPhoneNum(String phoneNumber);

  void setHeight(int height);

  void setWeight(int weight);

  void setWeightGoal(int goal);

  void setCalGoal(int goal);

  void setCalories(int calories);

  void setAllergies(ArrayList<String> allergies);

  void setInstructor(String instructor);

  void setOrganization(String organization);

  void setComment(ArrayList<String> comment);

  void setProfilePicture(Image picture);

  void setBreakfastFoods(ArrayList<FoodItem> breakfastFoods);

  void setLunchFoods(ArrayList<FoodItem> lunchFoods);

  void setDinnerFoods(ArrayList<FoodItem> dinnerFoods);

  void setSnackFoods(ArrayList<FoodItem> snackFoods);

  // void controller(Controller);

  // getters for client
  String getName();

  int getId();

  int getAge();

  String getEmail();

  String getPhoneNum();

  int getHeight();

  int getWeight();

  int getWeightGoal();

  int getCalGoal();

  int getCalories();

  ArrayList<String> getAllergies();

  String getInstructor();

  String getOrganization();
  // void controller();
  ArrayList<String> getComment();

  Image getProfilePicture();

  ArrayList<FoodItem> getBreakfastFoods();

  ArrayList<FoodItem> getLunchFoods();

  ArrayList<FoodItem> getDinnerFoods();

  ArrayList<FoodItem> getSnackFoods();

  // Add food items to food arrays
  void addBreakfastFood(FoodItem foodItem);

  void addLunchFood(FoodItem foodItem);

  void addDinnerFood(FoodItem foodItem);

  void addSnackFood(FoodItem foodItem);
}
