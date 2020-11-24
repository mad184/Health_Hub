package staff.ManagerViews;

import API.FoodItem;
import com.google.gson.Gson;
import javafx.scene.image.Image;
import org.json.JSONException;
import org.json.JSONObject;
import staff.Interfaces.ManagerViewInterface;
import staff.Interfaces.StaffInterface;
import staff.UserID;

import java.util.ArrayList;

public class ManagerView implements StaffInterface, ManagerViewInterface {

  private String name, email, password, organization, phoneNumber;
  private int id, age, height, weight, goalWeight, goalCals, calories;
  private ArrayList<String> allergies, comment;
  private ArrayList<UserID> instructors;
  private ArrayList<FoodItem> breakFoods, lunchFoods, dinnerFoods, snackFoods;
  private Image profilePicture;

  public ManagerView(
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
      ArrayList<UserID> instructors,
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
    this.instructors = instructors;
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
    this.goalWeight = weight;
  }

  @Override
  public void setGoalCals(int goalCals) {
    this.goalCals = goalCals;
  }

  @Override
  public void setCalories(int calories) {
    this.calories = calories;
  }

  /**
   * Sets the goal calorie
   *
   * @param goalCal the calorie that is the goal
   */
  @Override
  public void setGoalCal(int goalCal) {

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
  public void setInstructors(ArrayList<UserID> instructors) {
    this.instructors = instructors;
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

  // ----------------GETTERS-------------//

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public int getGoalWeight() {
    return this.goalWeight;
  }

  @Override
  public int getGoalCals() {
    return this.goalCals;
  }

  @Override
  public int getCalories() {
    return this.calories;
  }

  /**
   * Gets the goal calorie of the client
   *
   * @return the goal set for calories
   */
  @Override
  public int getCaloriesGoal() {
    return this.goalCals;
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
  public ArrayList<UserID> getInstructors() {
    return this.instructors;
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

  @Override
  public int getId() {
    return this.id;
  }

  // ------------ADD TO ARRAYLIST<>-------------//

  @Override
  public void addToBreakFoods(FoodItem food) {
    breakFoods.add(food);
  }

  @Override
  public void addToLunchFoods(FoodItem food) {
    lunchFoods.add(food);
  }

  @Override
  public void addToDinnerFoods(FoodItem food) {
    dinnerFoods.add(food);
  }

  @Override
  public void addToSnackFoods(FoodItem food) {
    snackFoods.add(food);
  }

  @Override
  public void addToInstructors(UserID instructor) {
    instructors.add(instructor);
  }

  public JSONObject toJSON() throws JSONException {
    Gson json = new Gson();
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("name", getName());
    jsonObject.put("password", getPassword());
    jsonObject.put("email", getEmail());
    jsonObject.put("organization", getOrganization());
    jsonObject.put("id", getId());
    jsonObject.put("age", getAge());
    jsonObject.put("height", getHeight());
    jsonObject.put("weight", getWeight());
    jsonObject.put("phoneNumber", getPhoneNumber());
    jsonObject.put("goalWeight", getGoalWeight());
    jsonObject.put("goalCals", getGoalCals());
    jsonObject.put("calories", getCalories());
    jsonObject.put("allergies", getAllergies());
    jsonObject.put("comment", getComment());

    jsonObject.put(
        "breakfastFoods",
        getBreakFoods().toString().substring(1, getBreakFoods().toString().length() - 1));

    jsonObject.put(
        "lunchFoods",
        getLunchFoods().toString().substring(1, getLunchFoods().toString().length() - 1));

    jsonObject.put(
        "dinnerFoods",
        getDinnerFoods().toString().substring(1, getDinnerFoods().toString().length() - 1));

    jsonObject.put(
        "snackFoods",
        getSnackFoods().toString().substring(1, getSnackFoods().toString().length() - 1));

    jsonObject.put(
        "instructors",
        getInstructors().toString().substring(1, getInstructors().toString().length() - 1));

    return jsonObject;
  }

  public void jsonToManager(JSONObject ManagerJson) throws JSONException {
    Gson json = new Gson();
    setName(json.fromJson(String.valueOf(ManagerJson.get("name")), String.class));
    setPassword(json.fromJson(String.valueOf(ManagerJson.get("password")), String.class));
    setEmail(json.fromJson(String.valueOf(ManagerJson.get("email")), String.class));
    setOrganization(json.fromJson(String.valueOf(ManagerJson.get("organization")), String.class));
    setId(json.fromJson(String.valueOf(ManagerJson.get("id")), int.class));
    setAge(json.fromJson(String.valueOf(ManagerJson.get("age")), int.class));
    setHeight(json.fromJson(String.valueOf(ManagerJson.get("height")), int.class));
    setWeight(json.fromJson(String.valueOf(ManagerJson.get("weight")), int.class));
    setPhoneNumber(json.fromJson(String.valueOf(ManagerJson.get("phoneNumber")), String.class));
    setGoalWeight(json.fromJson(String.valueOf(ManagerJson.get("goalWeight")), int.class));
    setCalories(json.fromJson(String.valueOf(ManagerJson.get("calories")), int.class));
    setAllergies(json.fromJson(String.valueOf(ManagerJson.get("allergies")), ArrayList.class));
    setComment(json.fromJson(String.valueOf(ManagerJson.get("comment")), ArrayList.class));

    // BREAKFAST
    String ArrayList[] = String.valueOf(ManagerJson.get("breakfastFoods")).split(" ");
    ArrayList<FoodItem> breakfastFoods = new ArrayList<FoodItem>();
    for (String item : ArrayList) {
      String foodInfo[] = item.split(",");
      FoodItem food =
          new FoodItem(foodInfo[0], Double.parseDouble(foodInfo[2]), Integer.parseInt(foodInfo[1]));
      breakfastFoods.add(food);
    }
    setBreakFoods(breakfastFoods);

    // LUNCH
    ArrayList = String.valueOf(ManagerJson.get("lunchFoods")).split(" ");
    ArrayList<FoodItem> lunchFoods = new ArrayList<FoodItem>();
    for (String item : ArrayList) {
      String foodInfo[] = item.split(",");
      FoodItem food =
          new FoodItem(foodInfo[0], Double.parseDouble(foodInfo[2]), Integer.parseInt(foodInfo[1]));
      lunchFoods.add(food);
    }
    setLunchFoods(lunchFoods);

    // DINNER
    ArrayList = String.valueOf(ManagerJson.get("dinnerFoods")).split(" ");
    ArrayList<FoodItem> dinnerFoods = new ArrayList<FoodItem>();
    for (String item : ArrayList) {
      String foodInfo[] = item.split(",");
      FoodItem food =
          new FoodItem(foodInfo[0], Double.parseDouble(foodInfo[2]), Integer.parseInt(foodInfo[1]));
      dinnerFoods.add(food);
    }
    setDinnerFoods(dinnerFoods);

    // Snacks
    ArrayList = String.valueOf(ManagerJson.get("snacksFoods")).split(" ");
    ArrayList<FoodItem> snackFoods = new ArrayList<FoodItem>();
    for (String item : ArrayList) {
      String foodInfo[] = item.split(",");
      FoodItem food =
          new FoodItem(foodInfo[0], Double.parseDouble(foodInfo[2]), Integer.parseInt(foodInfo[1]));
      snackFoods.add(food);
    }
    setSnackFoods(snackFoods);

    // Instructors
    ArrayList = String.valueOf(ManagerJson.get("instructors")).split(" ");
    ArrayList<UserID> instructor = new ArrayList<UserID>();
    for (String item : ArrayList) {
      String instructorInfo[] = item.split(",");
      UserID user = new UserID(Integer.parseInt(instructorInfo[0]), instructorInfo[1]);
      instructor.add(user);
    }
    setInstructors(instructor);
  }
}
