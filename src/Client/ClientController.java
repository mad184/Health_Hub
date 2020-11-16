package Client;

import API.FoodItem;
import com.google.gson.Gson;
import javafx.scene.image.Image;
import org.json.JSONObject;

import java.util.ArrayList;

public class ClientController {
  private Client model;

  public ClientController(Client model) {
    this.model = model;
  }

  public void setClientGoalWeight(int goal) {
    model.setWeightGoal(goal);
  }

  public void setClientGoalCals(int goal) {
    model.setCalGoal(goal);
  }

  public void setClientBreakfastFood(ArrayList<FoodItem> breakfastFood) {
    model.setBreakfastFoods(breakfastFood);
  }

  public void setClientLunchFood(ArrayList<FoodItem> lunchFood) {
    model.setLunchFoods(lunchFood);
  }

  public void setClientDinnerFood(ArrayList<FoodItem> dinnerFood) {
    model.setDinnerFoods(dinnerFood);
  }

  public void setClientSnackFood(ArrayList<FoodItem> snackFood) {
    model.setSnackFoods(snackFood);
  }

  // Getters
  public Client getModel() {
    return this.model;
  }

  // Setters
  public void setModel(Client model) {
    this.model = model;
  }

  public String getClientName() {
    return model.getName();
  }

  public void setClientName(String name) {
    model.setName(name);
  }

  public String getClientOrg() {
    return model.getOrganization();
  }

  public void setClientOrg(String org) {
    model.setOrganization(org);
  }

  public String getClientInstructor() {
    return model.getInstructor();
  }

  public void setClientInstructor(String name) {
    model.setInstructor(name);
  }

  public String getClientEmail() {
    return model.getEmail();
  }

  public void setClientEmail(String email) {
    model.setEmail(email);
  }

  public int getClientID() {
    return model.getId();
  }

  public void setClientID(int ID) {
    model.setId(ID);
  }

  public int getClientAge() {
    return model.getAge();
  }

  public void setClientAge(int age) {
    model.setAge(age);
  }

  public int getClientWeight() {
    return model.getWeight();
  }

  public void setClientWeight(int weight) {
    model.setWeight(weight);
  }

  public int getClientHeight() {
    return model.getHeight();
  }

  public void setClientHeight(int height) {
    model.setHeight(height);
  }

  public String getClientPhoneNum() {
    return model.getPhoneNum();
  }

  public void setClientPhoneNum(String num) {
    model.setPhoneNum(num);
  }

  public int getClientWeightGoal() {
    return model.getWeightGoal();
  }

  public int getClientCalGoal() {
    return model.getCalGoal();
  }

  public int getClientCals() {
    return model.getCalories();
  }

  public void setClientCals(int cals) {
    model.setCalories(cals);
  }

  public ArrayList<String> getClientAllergies() {
    return model.getAllergies();
  }

  public void setClientAllergies(ArrayList<String> allergies) {
    model.setAllergies(allergies);
  }

  public ArrayList<String> getClientComment() {
    return model.getComment();
  }

  public void setClientComment(ArrayList<String> comment) {
    model.setComment(comment);
  }

  public Image getClientProfilePicture() {
    return model.getProfilePicture();
  }

  public void setClientProfilePicture(Image picture) {
    model.setProfilePicture(picture);
  }

  public ArrayList<FoodItem> getClientBreakfastFoods() {
    return model.getBreakfastFoods();
  }

  public ArrayList<FoodItem> getClientLunchFoods() {
    return model.getLunchFoods();
  }

  public ArrayList<FoodItem> getClientDinnerFoods() {
    return model.getDinnerFoods();
  }

  public ArrayList<FoodItem> getClientSnackFoods() {
    return model.getSnackFoods();
  }

  // Food array methods
  public void addClientBreakfastFood(FoodItem foodItem) {
    model.addBreakfastFood(foodItem);
  }

  public void addClientLunchFood(FoodItem foodItem) {
    model.addLunchFood(foodItem);
  }

  public void addClientDinnerFood(FoodItem foodItem) {
    model.addDinnerFood(foodItem);
  }

  public void addClientSnackFood(FoodItem foodItem) {
    model.addSnackFood(foodItem);
  }

  // JSONObject conversion methods
  public JSONObject clientToJson() {
    Gson json = new Gson();
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("name", model.getName());
    jsonObject.put("email", model.getEmail());
    jsonObject.put("instructor", model.getInstructor());
    jsonObject.put("organization", model.getOrganization());
    jsonObject.put("id", model.getId());
    jsonObject.put("age", model.getAge());
    jsonObject.put("height", model.getHeight());
    jsonObject.put("weight", model.getWeight());
    jsonObject.put("phoneNumber", model.getPhoneNum());
    jsonObject.put("goalWeight", model.getWeightGoal());
    jsonObject.put("goalCals", model.getCalGoal());
    jsonObject.put("calories", model.getCalories());
    jsonObject.put("allergies", model.getAllergies());
    jsonObject.put("comment", model.getComment());
    jsonObject.put("profilePicture", model.getProfilePicture());
    // Converts Array Lists toString
    jsonObject.put(
        "breakfastFoods",
        model
            .getBreakfastFoods()
            .toString()
            .substring(1, model.getBreakfastFoods().toString().length() - 1));

    jsonObject.put(
        "lunchFoods",
        model
            .getLunchFoods()
            .toString()
            .substring(1, model.getLunchFoods().toString().length() - 1));

    jsonObject.put(
        "dinnerFoods",
        model
            .getDinnerFoods()
            .toString()
            .substring(1, model.getDinnerFoods().toString().length() - 1));

    jsonObject.put(
        "snackFoods",
        model
            .getSnackFoods()
            .toString()
            .substring(1, model.getSnackFoods().toString().length() - 1));

    return jsonObject;
  }

  /**
   * Converts JSONObject obtained from database to client model and sets client to it
   *
   * @param clientJson JSONObject obtained from database
   */
  public void jsonToClient(JSONObject clientJson) {
    Gson json = new Gson();
    setClientName(json.fromJson(String.valueOf(clientJson.get("name")), String.class));
    setClientEmail(json.fromJson(String.valueOf(clientJson.get("email")), String.class));
    setClientInstructor(json.fromJson(String.valueOf(clientJson.get("instructor")), String.class));
    setClientOrg(json.fromJson(String.valueOf(clientJson.get("organization")), String.class));
    setClientID(json.fromJson(String.valueOf(clientJson.get("id")), Integer.class));
    setClientAge(json.fromJson(String.valueOf(clientJson.get("age")), Integer.class));
    setClientHeight(json.fromJson(String.valueOf(clientJson.get("height")), Integer.class));
    setClientWeight(json.fromJson(String.valueOf(clientJson.get("weight")), Integer.class));
    setClientPhoneNum(json.fromJson(String.valueOf(clientJson.get("phoneNumber")), String.class));
    setClientGoalWeight(json.fromJson(String.valueOf(clientJson.get("goalWeight")), Integer.class));
    setClientGoalCals(json.fromJson(String.valueOf(clientJson.get("goalCals")), Integer.class));
    setClientCals(json.fromJson(String.valueOf(clientJson.get("calories")), Integer.class));
    setClientAllergies(json.fromJson(String.valueOf(clientJson.get("allergies")), ArrayList.class));
    setClientComment(json.fromJson(String.valueOf(clientJson.get("comment")), ArrayList.class));

    // setClientProfilePicture(json.fromJson(String.valueOf(clientJson.get("profilePicture")),
    // Image.class));

    // Converts String to array list of food items.
    String list[] = String.valueOf(clientJson.get("breakfastFoods")).split(" ");
    ArrayList<FoodItem> breakfastFoods = new ArrayList<>();
    for (String item : list) {
      String foodInfo[] = item.split(",");
      FoodItem food =
          new FoodItem(foodInfo[0], Double.parseDouble(foodInfo[2]), Integer.parseInt(foodInfo[1]));
      breakfastFoods.add(food);
    }
    setClientBreakfastFood(breakfastFoods);

    list = String.valueOf(clientJson.get("lunchFoods")).split(" ");
    ArrayList<FoodItem> lunchFoods = new ArrayList<>();
    for (String item : list) {
      String foodInfo[] = item.split(",");
      FoodItem food =
          new FoodItem(foodInfo[0], Double.parseDouble(foodInfo[2]), Integer.parseInt(foodInfo[1]));
      lunchFoods.add(food);
    }
    setClientLunchFood(lunchFoods);

    list = String.valueOf(clientJson.get("dinnerFoods")).split(" ");
    ArrayList<FoodItem> dinnerFoods = new ArrayList<>();
    for (String item : list) {
      String foodInfo[] = item.split(",");
      FoodItem food =
          new FoodItem(foodInfo[0], Double.parseDouble(foodInfo[2]), Integer.parseInt(foodInfo[1]));
      dinnerFoods.add(food);
    }
    setClientDinnerFood(dinnerFoods);

    list = String.valueOf(clientJson.get("snackFoods")).split(" ");
    ArrayList<FoodItem> snackFoods = new ArrayList<>();
    for (String item : list) {
      String foodInfo[] = item.split(",");
      FoodItem food =
          new FoodItem(foodInfo[0], Double.parseDouble(foodInfo[2]), Integer.parseInt(foodInfo[1]));
      snackFoods.add(food);
    }
    setClientSnackFood(snackFoods);
  }
}
