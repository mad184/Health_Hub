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

  public Client getModel() {
    return this.model;
  }

  public void setModel(Client model) {
    this.model = model;
  }

  public String getClientName() {
    return model.getName();
  }

  public void setClientName(String name) {
    model.setName(name);
  }

  public String getClientPassword() {
    return model.getPassword();
  }

  public void setClientPassword(String password) {
    model.setPassword(password);
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
    return model.toJSON();
  }

  /**
   * Converts JSONObject obtained from database to client model and sets client to it
   *
   * @param clientJson JSONObject obtained from database
   */
  public void jsonToClient(JSONObject clientJson) {
    model.jsonToClient(clientJson);
  }
}
