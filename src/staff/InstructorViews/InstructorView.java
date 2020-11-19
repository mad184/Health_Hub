package staff.InstructorViews;

import staff.Interfaces.InstructorViewInterface;
import staff.Interfaces.StaffInterface;
import javafx.scene.image.Image;
import org.json.JSONObject;
import com.google.gson.Gson;
import API.FoodItem;
import staff.UserID;

import java.util.ArrayList;

public class InstructorView implements StaffInterface, InstructorViewInterface {

  private String name, email, password, organization, phoneNumber;
  private int id, age, height, weight, goalWeight, goalCals, calories;
  private ArrayList<String> allergies, comment;
  private ArrayList<UserID> clients;
  private ArrayList<FoodItem> breakFoods, lunchFoods, dinnerFoods, snackFoods;
  private Image profilePicture;

  public InstructorView(
      String name,
      String email,
      String password,
      String organization,
      int id,
      int age,
      int height,
      int weight,
      String phoneNumber,
      int goalWeight,
      int goalCals,
      int calories,
      ArrayList<String> allergies,
      ArrayList<String> comment,
      ArrayList<UserID> clients,
      Image profilePicture,
      ArrayList<FoodItem> breakFoods,
      ArrayList<FoodItem> lunchFoods,
      ArrayList<FoodItem> dinnerFoods,
      ArrayList<FoodItem> snackFoods) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.organization = organization;
    this.id = id;
    this.age = age;
    this.height = height;
    this.weight = weight;
    this.phoneNumber = phoneNumber;
    this.goalWeight = goalWeight;
    this.goalCals = goalCals;
    this.calories = calories;
    this.allergies = allergies;
    this.comment = comment;
    this.clients = clients;
    this.profilePicture = profilePicture;
    this.breakFoods = breakFoods;
    this.lunchFoods = lunchFoods;
    this.dinnerFoods = dinnerFoods;
    this.snackFoods = snackFoods;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public void setWeight(int weight) {
    this.weight = weight;
  }

  @Override
  public void setOrganization(String orgName) {
    this.organization = orgName;
  }

  @Override
  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public void setGoalWeight(int goalWeight) {
    this.goalWeight = goalWeight;
  }

  @Override
  public void setGoalCals(int goalCals) {
    this.goalCals = goalCals;
  }

  @Override
  public void setCalories(int calories) {
    this.calories = calories;
  }

  @Override
  public void setAllergies(ArrayList<String> allergies) {
    this.allergies = allergies;
  }

  @Override
  public void setComment(ArrayList<String> comment) {
    this.comment = comment;
  }

  @Override
  public void setClients(ArrayList<UserID> clients) {
    this.clients = clients;
  }

  @Override
  public void setBreakFoods(ArrayList<FoodItem> breakFood) {
    this.breakFoods = breakFood;
  }

  @Override
  public void setLunchFoods(ArrayList<FoodItem> lunchFoods) {
    this.lunchFoods = lunchFoods;
  }

  @Override
  public void setDinnerFoods(ArrayList<FoodItem> dinnerFoods) {
    this.dinnerFoods = dinnerFoods;
  }

  @Override
  public void setSnackFoods(ArrayList<FoodItem> snackFoods) {
    this.snackFoods = snackFoods;
  }

  @Override
  public void setProfilePicture(Image profilePicture) {
    this.profilePicture = profilePicture;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public int getGoalWeight() {
    return this.id;
  }

  @Override
  public int getGoalCals() {
    return this.goalCals;
  }

  @Override
  public int getCalories() {
    return this.calories;
  }

  @Override
  public ArrayList<String> getAllergies() {
    return this.allergies;
  }

  @Override
  public ArrayList<String> getComment() {
    return this.comment;
  }

  @Override
  public ArrayList<UserID> getClients() {
    return this.clients;
  }

  @Override
  public ArrayList<FoodItem> getBreakFoods() {
    return this.breakFoods;
  }

  @Override
  public ArrayList<FoodItem> getLunchFoods() {
    return this.lunchFoods;
  }

  @Override
  public ArrayList<FoodItem> getDinnerFoods() {
    return this.dinnerFoods;
  }

  @Override
  public ArrayList<FoodItem> getSnackFoods() {
    return this.snackFoods;
  }

  @Override
  public Image getProfilePicture() {
    return this.profilePicture;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getAge() {
    return this.age;
  }

  @Override
  public String getEmail() {
    return this.email;
  }

  @Override
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWeight() {
    return this.weight;
  }

  @Override
  public String getOrganization() {
    return this.organization;
  }
}
