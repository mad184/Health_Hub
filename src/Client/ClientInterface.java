package Client;

import API.ExerciseItem;
import API.FoodItem;
import javafx.scene.image.Image;
import org.json.JSONObject;

import java.util.ArrayList;

public interface ClientInterface {

  // getters for client
  String getName();

  // setters for client attributes
  void setName(String name);

  String getPassword();

  void setPassword(String password);

  int getId();

  void setId(int id);

  int getAge();

  void setAge(int age);

  String getEmail();

  void setEmail(String email);

  String getPhoneNum();

  void setPhoneNum(String phoneNumber);

  int getHeight();

  void setHeight(int height);

  int getWeight();

  void setWeight(int weight);

  int getWeightGoal();

  void setWeightGoal(int goal);

  int getCalGoal();

  void setCalGoal(int goal);

  int getCalories();

  void setCalories(int calories);

  ArrayList<String> getAllergies();

  void setAllergies(ArrayList<String> allergies);

  String getInstructor();

  void setInstructor(String instructor);

  String getOrganization();

  void setOrganization(String organization);

  // void controller();
  ArrayList<String> getComment();

  void setComment(ArrayList<String> comment);

  Image getProfilePicture();

  void setProfilePicture(Image picture);

  ArrayList<FoodItem> getBreakfastFoods();

  void setBreakfastFoods(ArrayList<FoodItem> breakfastFoods);

  ArrayList<FoodItem> getLunchFoods();

  void setLunchFoods(ArrayList<FoodItem> lunchFoods);

  ArrayList<FoodItem> getDinnerFoods();

  void setDinnerFoods(ArrayList<FoodItem> dinnerFoods);

  ArrayList<FoodItem> getSnackFoods();

  void setSnackFoods(ArrayList<FoodItem> snackFoods);

  // Add food items to food arrays
  void addBreakfastFood(FoodItem foodItem);

  void addLunchFood(FoodItem foodItem);

  void addDinnerFood(FoodItem foodItem);

  void addSnackFood(FoodItem foodItem);

  ArrayList<ExerciseItem> getExercises();

  void setExercises(ArrayList<ExerciseItem> exercises);

  void addExercise(ExerciseItem exerciseItem);

  JSONObject toJSON();

  void jsonToClient(JSONObject clientJson);
}
