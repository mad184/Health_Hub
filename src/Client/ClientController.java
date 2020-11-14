package Client;

import com.google.gson.JsonObject;
import javafx.scene.image.Image;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Hashtable;

public class ClientController {
  private Client model;

  public ClientController(Client model) {
    this.model = model;
  }

  //Setters
  public void setModel(Client model){this.model = model;}

  public void setClientName(String name) {
    model.setName(name);
  }

  public void setClientOrg(String org) {
    model.setOrganization(org);
  }

  public void setClientInstructor(String name) {
    model.setInstructor(name);
  }

  public void setClientEmail(String email) {
    model.setEmail(email);
  }

  public void setClientID(int ID) {
    model.setId(ID);
  }

  public void setClientAge(int age) {
    model.setAge(age);
  }

  public void setClientWeight(int weight) {
    model.setWeight(weight);
  }

  public void setClientHeight(int height) {
    model.setHeight(height);
  }

  public void setClientPhoneNum(String num) {
    model.setPhoneNum(num);
  }

  public void setClientGoalWeight(int goal) {
    model.setWeightGoal(goal);
  }

  public void setClientGoalCals(int goal) {
    model.setCalGoal(goal);
  }

  public void setClientCals(int cals) {
    model.setCalories(cals);
  }

  public void setClientAllergies(ArrayList<String> allergies) {
    model.setAllergies(allergies);
  }

  public void setClientComment(ArrayList<String> comment) {
    model.setComment(comment);
  }

  public void setClientProfilePicture(Image picture) {
    model.setProfilePicture(picture);
  }

  public void setClientFoodLog(Hashtable<String, Hashtable<String, Integer>> foodLog) {model.setFoodLog(foodLog);}

  //Getters
  public Client getModel(){
    return this.model;
  }

  public String getClientName() {
    return model.getName();
  }

  public String getClientOrg() {
    return model.getOrganization();
  }

  public String getClientInstructor() {
    return model.getInstructor();
  }

  public String getClientEmail() {
    return model.getEmail();
  }

  public int getClientID() {
    return model.getId();
  }

  public int getClientAge() {
    return model.getAge();
  }

  public int getClientWeight() {
    return model.getWeight();
  }

  public int getClientHeight() {
    return model.getHeight();
  }

  public String getClientPhoneNum() {
    return model.getPhoneNum();
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

  public ArrayList<String> getClientAllergies() {
    return model.getAllergies();
  }

  public ArrayList<String> getClientComment() {
    return model.getComment();
  }

  public Image getClientProfilePicture() {
    return model.getProfilePicture();
  }

  public Hashtable<String, Hashtable<String, Integer>> getClientFoodLog() {return model.getFoodLog();}

  public JSONObject clientToJson (){
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
    return jsonObject;
  }

  public void jsonToClient (JSONObject clientJson){
    Gson json = new Gson();
    model = json.fromJson(String.valueOf(clientJson), Client.class);
  }


  //foodLog methods
  public void addClientBreakfastFood(String foodName, int calories){
    model.addBreakfastFood(foodName, calories);
  }

  public void addClientLunchFood(String foodName, int calories){
    model.addLunchFood(foodName, calories);
  }

  public void addClientDinnerFood(String foodName, int calories){
    model.addDinnerFood(foodName, calories);
  }

  public void addClientSnackFood(String foodName, int calories){
    model.addSnackFood(foodName, calories);
  }
}
