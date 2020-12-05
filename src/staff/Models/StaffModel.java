package staff.Models;

import API.ExerciseItem;
import API.FoodItem;
import com.google.gson.Gson;
import database.Dbms;
import org.json.JSONException;
import org.json.JSONObject;
import staff.Interfaces.StaffInterface;

import java.util.ArrayList;

/** Storage class for Staff users. */
public class StaffModel implements StaffInterface {
  private String name;
  private String userPassword;
  private int age;
  private String email;
  private String phoneNumber;
  private int height;
  private int weight;
  private String organization;
  private int id;
  public Dbms db;
  private int calories;
  private int calorieGoal;
  private ArrayList<FoodItem> breakfastFoods;
  private ArrayList<FoodItem> lunchFoods;
  private ArrayList<FoodItem> dinnerFoods;
  private ArrayList<FoodItem> snackFoods;
  private ArrayList<ExerciseItem> exercises;

  /**
   * Constructs a new StaffModel object.
   *
   * @param name: Name of the staff member
   * @param age: Age of the staff member (years)
   * @param email: Email of the staff member
   * @param phoneNumber: Phone number of the staff member
   * @param height: Height of the staff member (cm)
   * @param weight: Weight of the staff member (lbs)
   * @param organization: Organization the staff member is affiliated with
   * @param id: Database ID of the staff member
   */
  public StaffModel(
      String name,
      String userPassword,
      int age,
      String email,
      String phoneNumber,
      int height,
      int weight,
      String organization,
      int id,
      String username,  // For connection to Dbms
      String password,  // For connection to Dbms
      String dbName,    // For connection to Dbms
      String tableName  // For connection to Dbms
      ) {
    this.name = name;
    this.userPassword = userPassword;
    this.age = age;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.height = height;
    this.weight = weight;
    this.organization = organization;
    this.id = id;
    this.calories = 0;
    this.calorieGoal = 0;
    this.db = new Dbms(username, password, dbName, tableName);
    this.breakfastFoods = new ArrayList<>();
    this.lunchFoods = new ArrayList<>();
    this.dinnerFoods = new ArrayList<>();
    this.snackFoods = new ArrayList<>();
    this.exercises = new ArrayList<>();
  }

  /**
   * Converts a JSON representation of a Staff member into a StaffModel object.
   *
   * @param staff: json representation of a StaffModel object
   * @return a StaffModel object
   *
  public static StaffModel fromJson(json staff) {
    Gson converter = new Gson();
    System.out.println(String.valueOf(staff));
    return converter.fromJson(String.valueOf(staff), StaffModel.class);
  }
   */

  /**
   * Gets the name of the staff member.
   *
   * @return their name
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Gets the age of the staff member.
   *
   * @return age in years
   */
  @Override
  public int getAge() {
    return this.age;
  }

  /**
   * Gets the email of the staff member.
   *
   * @return their email
   */
  @Override
  public String getEmail() {
    return this.email;
  }

  /**
   * Gets the phone number of the staff member.
   *
   * @return their phone number
   */
  @Override
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  /**
   * Gets the height of the staff member.
   *
   * @return their height in cm
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Gets the weight of the staff member.
   *
   * @return weight in lbs
   */
  @Override
  public int getWeight() {
    return this.weight;
  }

  /**
   * Gets the calories of the user
   *
   * @return client calorie
   */
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
    return this.calorieGoal;
  }

  /**
   * Gets the organization who employs the staff member.
   *
   * @return organization name
   */
  @Override
  public String getOrganization() {
    return this.organization;
  }

    @Override
    public String getUserPassword() {
        return this.userPassword;
    }

    /**
   * Gets the database ID of the staff member.
   *
   * @return database ID
   */
  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public int setID(int _id) {
      return this.id = _id;
  }

    @Override
    public void setUserPassword(String password) {
        this.userPassword = password;
    }


    /**
   * Sets the name of the staff member
   *
   * @param name: Name of the Staff member
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the age of the staff member
   *
   * @param age: Age in years
   */
  @Override
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Sets the email of the staff member
   *
   * @param email: Email as a String
   */
  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Sets the phone number of the staff member
   *
   * @param phoneNumber: Phone number as a String
   */
  @Override
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Sets the height of the staff member
   *
   * @param height: Height in cm
   */
  @Override
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Sets the weight of the staff member
   *
   * @param weight: Weight in lbs
   */
  @Override
  public void setWeight(int weight) {
    this.weight = weight;
  }

  /**
   * Sets calorie of the user
   *
   * @param calories calories
   */
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
    this.calorieGoal = goalCal;
  }

  /**
   * Sets the organization of the staff member
   *
   * @param orgName: Name of the organization
   */
  @Override
  public void setOrganization(String orgName) {
    this.organization = orgName;
  }

  /**
   * Setter for the Database connection.
   * @param db: Database
   */
  private void setDbms(Dbms db) {
    this.db = db;
  }

  /**
   * Gets the breakfast foods logged by the user.
   *
   * @return List of foods as FoodItems.
   */
  public ArrayList<FoodItem> getBreakfastFoods() {
    return this.breakfastFoods;
  }

  /**
   * Gets the lunch foods logged by the user.
   *
   * @return List of foods as FoodItems.
   */
  public ArrayList<FoodItem> getLunchFoods() {
    return this.lunchFoods;
  }

  /**
   * Gets the dinner foods logged by the user.
   *
   * @return List of foods as FoodItems.
   */
  public ArrayList<FoodItem> getDinnerFoods() {
    return this.dinnerFoods;
  }

  /**
   * Gets the snack foods logged by the user.
   *
   * @return List of foods as FoodItems.
   */
  public ArrayList<FoodItem> getSnackFoods() {
    return this.snackFoods;
  }

  /**
   * Adds an item to the list of Breakfast foods.
   *
   * @param food: FoodItem object
   */
  public void addBreakfastFood(FoodItem food) {
    this.breakfastFoods.add(food);
  }

  /**
   * Adds an item to the list of Lunch foods.
   *
   * @param food: FoodItem object
   */
  public void addLunchFood(FoodItem food) {
    this.lunchFoods.add(food);
  }

  /**
   * Adds an item to the list of Dinner foods.
   *
   * @param food: FoodItem object
   */
  public void addDinnerFood(FoodItem food) {
    this.dinnerFoods.add(food);
  }

  /**
   * Adds an item to the list of Snack foods.
   *
   * @param food: FoodItem object
   */
  public void addSnackFood(FoodItem food) {
    this.snackFoods.add(food);
  }

  /**
   * Sets the breakfast foods for the Staff member.
   *
   * @param breakfast: ArrayList of FoodItems.
   */
  public void setBreakfastFoods(ArrayList<FoodItem> breakfast) {
    this.breakfastFoods = breakfast;
  }

  /**
   * Sets the lunch foods for the Staff member.
   *
   * @param lunch: ArrayList of FoodItems.
   */
  public void setLunchFoods(ArrayList<FoodItem> lunch) {
    this.lunchFoods = lunch;
  }

  /**
   * Sets the dinner foods for the Staff member.
   *
   * @param dinner: ArrayList of FoodItems.
   */
  public void setDinnerFoods(ArrayList<FoodItem> dinner) {
    this.dinnerFoods = dinner;
  }

  /**
   * Sets the snack foods for the Staff member.
   *
   * @param snack: ArrayList of FoodItems
   */
  public void setSnackFoods(ArrayList<FoodItem> snack) {
    this.snackFoods = snack;
  }

  /**
   * gets exercise array list
   *
   * @return array list belonging to staff user
   */
  public ArrayList<ExerciseItem> getExercises() {
    return this.exercises;
  }


  /**
   * Sets exercises for staff user
   *
   * @param exercises ArrayList of ExerciseItems
   */
  public void setExercises(ArrayList<ExerciseItem> exercises) {
    this.exercises = exercises;
  }

  /**
   * Adds Exercise item to exercises ArrayList
   *
   * @param exerciseItem Exercise Item to be added
   */
  public void addExercise(ExerciseItem exerciseItem) {
    this.exercises.add(exerciseItem);
  }

  /**
   * Converts the staff member into a json representation
   *
   * @return json representation of a staff member
   */
  public JSONObject toJson() throws JSONException {
    JSONObject json = new JSONObject();
    json.put("name", this.name);
    json.put("password", this.userPassword);
    json.put("age", this.age);
    json.put("email", this.email);
    json.put("phoneNumber", this.phoneNumber);
    json.put("height", this.height);
    json.put("weight", this.weight);
    json.put("organization", this.organization);
    json.put("id", this.id);

    // Converts Array Lists toString
    if (getBreakfastFoods() == null) {
      json.put("breakfastFoods", "");
    } else {
      json.put(
              "breakfastFoods",
              getBreakfastFoods().toString().substring(1, getBreakfastFoods().toString().length() - 1));
    }

    if (getLunchFoods() == null) {
      json.put("lunchFoods", "");
    } else {
      json.put(
              "lunchFoods",
              getLunchFoods().toString().substring(1, getLunchFoods().toString().length() - 1));
    }

    if (getDinnerFoods() == null) {
      json.put("dinnerFoods", "");
    } else {
      json.put(
              "dinnerFoods",
              getDinnerFoods().toString().substring(1, getDinnerFoods().toString().length() - 1));
    }

    if (getSnackFoods() == null) {
      json.put("snackFoods", "");
    } else {
      json.put(
              "snackFoods",
              getSnackFoods().toString().substring(1, getSnackFoods().toString().length() - 1));
    }

    if (getExercises() == null) {
      json.put("exercises", "");
    } else {
      json.put(
              "exercises",
              getExercises().toString().substring(1, getExercises().toString().length() - 1));
    }
    System.out.println(json.toString());
    return json;
  }

  public Gson fromJson(JSONObject json){
    Gson ObjectClass = new Gson();
    setName(String.valueOf(json.get("name")));
    setUserPassword(ObjectClass.fromJson(String.valueOf(json.get("password")), String.class));
    setAge(ObjectClass.fromJson(String.valueOf(json.get("age")), int.class));
    setID(ObjectClass.fromJson(String.valueOf(json.get("id")), int.class));
    setEmail(ObjectClass.fromJson(String.valueOf(json.get("email")), String.class));
    setPhoneNumber(ObjectClass.fromJson(String.valueOf(json.get("phoneNumber")), String.class));
    setHeight(ObjectClass.fromJson(String.valueOf(json.get("height")), int.class));
    setWeight(ObjectClass.fromJson(String.valueOf(json.get("weight")), int.class));
    setOrganization(String.valueOf(json.get("organization")));

    // Converts String to array list of food items.
    if (!json.get("breakfastFoods").toString().equals("")) {
      String[] list = String.valueOf(json.get("breakfastFoods")).split("/");
      ArrayList<FoodItem> breakfastFoods = new ArrayList<>();
      for (String item : list) {
        String[] foodInfo = item.split(";");
        FoodItem food =
                new FoodItem(
                        foodInfo[0], Double.parseDouble(foodInfo[2]), Integer.parseInt(foodInfo[1]));
        breakfastFoods.add(food);
      }
      this.setBreakfastFoods(breakfastFoods);
    }

    if (!json.get("lunchFoods").toString().equals("")) {
      String[] list = String.valueOf(json.get("lunchFoods")).split("/");
      ArrayList<FoodItem> lunchFoods = new ArrayList<>();
      for (String item : list) {
        String[] foodInfo = item.split(";");
        FoodItem food =
                new FoodItem(
                        foodInfo[0], Double.parseDouble(foodInfo[2]), Integer.parseInt(foodInfo[1]));
        lunchFoods.add(food);
      }
      setLunchFoods(lunchFoods);
    }
    if (!json.get("dinnerFoods").toString().equals("")) {
      String[] list = String.valueOf(json.get("dinnerFoods")).split("/");
      ArrayList<FoodItem> dinnerFoods = new ArrayList<>();
      for (String item : list) {
        String[] foodInfo = item.split(";");
        FoodItem food =
                new FoodItem(
                        foodInfo[0], Double.parseDouble(foodInfo[2]), Integer.parseInt(foodInfo[1]));
        dinnerFoods.add(food);
      }
      setDinnerFoods(dinnerFoods);
    }
    if (!json.get("snackFoods").toString().equals("")) {
      String[] list = String.valueOf(json.get("snackFoods")).split("/");
      ArrayList<FoodItem> snackFoods = new ArrayList<>();
      for (String item : list) {
        String[] foodInfo = item.split(";");
        FoodItem food =
                new FoodItem(
                        foodInfo[0], Double.parseDouble(foodInfo[2]), Integer.parseInt(foodInfo[1]));
        snackFoods.add(food);
      }
      setSnackFoods(snackFoods);
    }

    if (!json.get("exercises").toString().equals("")) {
      String[] list = String.valueOf(json.get("exercises")).split("/");
      ArrayList<ExerciseItem> exerciseList = new ArrayList<>();
      for (String item : list) {
        String[] exerciseInfo = item.split(";");
        ExerciseItem exerciseItem =
                new ExerciseItem(
                        exerciseInfo[0], Integer.parseInt(exerciseInfo[1]), Integer.parseInt(exerciseInfo[2]));
        exerciseList.add(exerciseItem);
      }
      setExercises(exerciseList);
    }
    return ObjectClass;
  }

  public Dbms getDbms() {
    return this.db;
  }
}
