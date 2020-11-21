package Client;

import API.FoodItem;
import com.google.gson.Gson;
import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Client implements ClientInterface {
  private String name, email, password, instructor, organization, phoneNumber;
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
      String password,
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
    this.password = password;
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

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public void setPassword(String password) {
    this.password = password;
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

  public JSONObject toJSON(){
    Gson json = new Gson();
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("name", getName());
    jsonObject.put("password", getPassword());
    jsonObject.put("email", getEmail());
    jsonObject.put("instructor", getInstructor());
    jsonObject.put("organization", getOrganization());
    jsonObject.put("id", getId());
    jsonObject.put("age", getAge());
    jsonObject.put("height", getHeight());
    jsonObject.put("weight", getWeight());
    jsonObject.put("phoneNumber", getPhoneNum());
    jsonObject.put("goalWeight", getWeightGoal());
    jsonObject.put("goalCals", getCalGoal());
    jsonObject.put("calories", getCalories());
    jsonObject.put("allergies", getAllergies());
    jsonObject.put("comment", getComment());
    jsonObject.put("profilePicture", getProfilePicture());

    // Converts Array Lists toString
    jsonObject.put(
            "breakfastFoods",
            getBreakfastFoods()
                    .toString()
                    .substring(1, getBreakfastFoods().toString().length() - 1));

    jsonObject.put(
            "lunchFoods",
            getLunchFoods()
                    .toString()
                    .substring(1, getLunchFoods().toString().length() - 1));

    jsonObject.put(
            "dinnerFoods",
                    getDinnerFoods()
                    .toString()
                    .substring(1, getDinnerFoods().toString().length() - 1));

    jsonObject.put(
            "snackFoods",
                    getSnackFoods()
                    .toString()
                    .substring(1, getSnackFoods().toString().length() - 1));

    return jsonObject;
  }

  public void jsonToClient(JSONObject clientJson) {
    Gson json = new Gson();
    setName(String.valueOf(clientJson.get("name")));
    setPassword(json.fromJson(String.valueOf(clientJson.get("password")), String.class));
    setEmail(json.fromJson(String.valueOf(clientJson.get("email")), String.class));
    setInstructor(json.fromJson(String.valueOf(clientJson.get("instructor")), String.class));
    setOrganization(json.fromJson(String.valueOf(clientJson.get("organization")), String.class));
    setId(json.fromJson(String.valueOf(clientJson.get("id")), Integer.class));
    setAge(json.fromJson(String.valueOf(clientJson.get("age")), Integer.class));
    setHeight(json.fromJson(String.valueOf(clientJson.get("height")), Integer.class));
    setWeight(json.fromJson(String.valueOf(clientJson.get("weight")), Integer.class));
    setPhoneNum(json.fromJson(String.valueOf(clientJson.get("phoneNumber")), String.class));
    setWeightGoal(json.fromJson(String.valueOf(clientJson.get("goalWeight")), Integer.class));
    setCalGoal(json.fromJson(String.valueOf(clientJson.get("goalCals")), Integer.class));
    setCalories(json.fromJson(String.valueOf(clientJson.get("calories")), Integer.class));
    setAllergies(json.fromJson(String.valueOf(clientJson.get("allergies")), ArrayList.class));
    setComment(json.fromJson(String.valueOf(clientJson.get("comment")), ArrayList.class));

    // setClientProfilePicture(json.fromJson(String.valueOf(clientJson.get("profilePicture")),
    // Image.class))

    // Converts String to array list of food items.
    if (clientJson.isNull("breakfastFoods")) {
      String list[] = String.valueOf(clientJson.get("breakfastFoods")).split(" ");
      ArrayList<FoodItem> breakfastFoods = new ArrayList<>();
      for (String item : list) {
        String foodInfo[] = item.split(",");
        System.out.println(foodInfo[0]);
        FoodItem food =
            new FoodItem(
                foodInfo[0], Double.parseDouble(foodInfo[2]), Integer.parseInt(foodInfo[1]));
        breakfastFoods.add(food);
      }
      this.setBreakfastFoods(breakfastFoods);
    }
    if (clientJson.isNull("lunchFoods")) {
      String list[] = String.valueOf(clientJson.get("lunchFoods")).split(" ");
      ArrayList<FoodItem> lunchFoods = new ArrayList<>();
      for (String item : list) {
        String foodInfo[] = item.split(",");
        FoodItem food =
            new FoodItem(
                foodInfo[0], Double.parseDouble(foodInfo[2]), Integer.parseInt(foodInfo[1]));
        lunchFoods.add(food);
      }
      setLunchFoods(lunchFoods);
    }
    if (clientJson.isNull("dinnerFoods")) {
      String list[] = String.valueOf(clientJson.get("dinnerFoods")).split(" ");
      ArrayList<FoodItem> dinnerFoods = new ArrayList<>();
      for (String item : list) {
        String foodInfo[] = item.split(",");
        FoodItem food =
            new FoodItem(
                foodInfo[0], Double.parseDouble(foodInfo[2]), Integer.parseInt(foodInfo[1]));
        dinnerFoods.add(food);
      }
      setDinnerFoods(dinnerFoods);
    }
    if (clientJson.isNull("snackFoods")) {
      String list[] = String.valueOf(clientJson.get("snackFoods")).split(" ");
      ArrayList<FoodItem> snackFoods = new ArrayList<>();
      for (String item : list) {
        String foodInfo[] = item.split(",");
        FoodItem food =
            new FoodItem(
                foodInfo[0], Double.parseDouble(foodInfo[2]), Integer.parseInt(foodInfo[1]));
        snackFoods.add(food);
      }
      setSnackFoods(snackFoods);
    }
    }
}
