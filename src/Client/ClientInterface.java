package Client;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Hashtable;

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

  void setFoodLog(Hashtable<String, Hashtable<String, Integer>> foodLog);

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

  Hashtable<String, Hashtable<String, Integer>> getFoodLog();

  //FoodLog methods
  void addBreakfastFood(String foodName, Integer calories);

  void addLunchFood(String foodName, Integer calories);

  void addDinnerFood(String foodName, Integer calories);

  void addSnackFood(String foodName, Integer calories);

}
