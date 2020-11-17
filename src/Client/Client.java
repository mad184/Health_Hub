package Client;

import API.FoodItem;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Client implements ClientInterface {
  private String name, email, instructor, organization, phoneNumber;
  private int id, age, height, weight;
  private int goalWeight, goalCals, calories;
  private ArrayList<String> allergies, comment;
  private Image profilePicture;
  private ArrayList<FoodItem> breakfastFoods;
  private ArrayList<FoodItem> lunchFoods;
  private ArrayList<FoodItem> dinnerFoods;
  private ArrayList<FoodItem> snackFoods;

  public Client(
      String name,
      String email,
      String instructor,
      String organization,
      int id,
      int age,
      int height,
      int weight,
      String phoneNumber, // we have this listed as a int, kept throwing errors for being to long
      int goalWeight,
      int goalCals,
      int calories,
      ArrayList<String> allergies,
      ArrayList<String> comment,
      Image profilePicture,
      ArrayList<FoodItem> breakfastFoods,
      ArrayList<FoodItem> lunchFoods,
      ArrayList<FoodItem> dinnerFoods,
      ArrayList<FoodItem> snackFoods) {
    this.name = name;
    this.email = email;
    this.instructor = instructor;
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
    this.profilePicture = profilePicture;
    this.breakfastFoods = breakfastFoods;
    this.lunchFoods = lunchFoods;
    this.dinnerFoods = dinnerFoods;
    this.snackFoods = snackFoods;
  }

  // getters
  @Override
  public String getName() {
    return this.name;
  }

  // setters
  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int getAge() {
    return this.age;
  }

  @Override
  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getEmail() {
    return this.email;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String getPhoneNum() {
    return this.phoneNumber;
  }

  @Override
  public void setPhoneNum(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public int getWeight() {
    return this.weight;
  }

  @Override
  public void setWeight(int weight) {
    this.weight = weight;
  }

  @Override
  public int getWeightGoal() {
    return this.goalWeight;
  }

  @Override
  public void setWeightGoal(int goal) {
    this.goalWeight = goal;
  }

  @Override
  public int getCalGoal() {
    return this.goalCals;
  }

  @Override
  public void setCalGoal(int goal) {
    this.goalCals = goal;
  }

  @Override
  public int getCalories() {
    return this.calories;
  }

  @Override
  public void setCalories(int calories) {
    this.calories = calories;
  }

  @Override
  public ArrayList<String> getAllergies() {
    return this.allergies;
  }

  @Override
  public void setAllergies(ArrayList<String> allergies) {
    this.allergies = allergies;
  }

  @Override
  public String getInstructor() {
    return this.instructor;
  }

  @Override
  public void setInstructor(String instructor) {
    this.instructor = instructor;
  }

  @Override
  public String getOrganization() {
    return this.organization;
  }

  @Override
  public void setOrganization(String organization) {
    this.organization = organization;
  }

  @Override
  public ArrayList<String> getComment() {
    return this.comment;
  }

  @Override
  public void setComment(ArrayList<String> comment) {
    this.comment = comment;
  }

  @Override
  public Image getProfilePicture() {
    return this.profilePicture;
  }

  @Override
  public void setProfilePicture(Image picture) {
    this.profilePicture = picture;
  }

  @Override
  public ArrayList<FoodItem> getBreakfastFoods() {
    return this.breakfastFoods;
  }

  @Override
  public void setBreakfastFoods(ArrayList<FoodItem> breakfastFoods) {
    this.breakfastFoods = breakfastFoods;
  }

  @Override
  public ArrayList<FoodItem> getLunchFoods() {
    return this.lunchFoods;
  }

  @Override
  public void setLunchFoods(ArrayList<FoodItem> lunchFoods) {
    this.lunchFoods = lunchFoods;
  }

  @Override
  public ArrayList<FoodItem> getDinnerFoods() {
    return this.dinnerFoods;
  }

  @Override
  public void setDinnerFoods(ArrayList<FoodItem> dinnerFoods) {
    this.dinnerFoods = dinnerFoods;
  }

  @Override
  public ArrayList<FoodItem> getSnackFoods() {
    return this.snackFoods;
  }

  @Override
  public void setSnackFoods(ArrayList<FoodItem> snackFoods) {
    this.snackFoods = snackFoods;
  }

  @Override
  public void addBreakfastFood(FoodItem foodItem) {
    this.breakfastFoods.add(foodItem);
  }

  @Override
  public void addLunchFood(FoodItem foodItem) {
    this.lunchFoods.add(foodItem);
  }

  @Override
  public void addDinnerFood(FoodItem foodItem) {
    this.dinnerFoods.add(foodItem);
  }

  @Override
  public void addSnackFood(FoodItem foodItem) {
    this.snackFoods.add(foodItem);
  }
}
