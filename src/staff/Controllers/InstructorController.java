package staff.Controllers;

import database.EmptyQueryException;
import database.JsonObjectException;
import org.json.JSONException;
import org.json.JSONObject;
import staff.Interfaces.InstructorInterface;
import staff.Interfaces.StaffInterface;
import staff.Models.InstructorModel;
import staff.UserID;

import java.util.List;

public class InstructorController implements InstructorInterface, StaffInterface {

  public InstructorModel model;

  public InstructorController(InstructorModel model) {
    this.model = model;
  }

  @Override
  public List<UserID> getClients() {
    return model.getClients();
  }

  @Override
  public void addClient(UserID client) {
    model.addClient(client);
  }

  @Override
  public void removeClient(UserID client) {
    model.removeClient(client);
  }

  @Override
  public void addComment(UserID client, String comment) throws JSONException, JsonObjectException, EmptyQueryException {
    model.addComment(client, comment);
  }

  @Override
  public void removeComment(UserID client, String comment) throws JSONException, JsonObjectException, EmptyQueryException {
    model.removeComment(client, comment);
  }

  @Override
  public JSONObject getClientInfo(UserID client) throws EmptyQueryException {
    return model.getClientInfo(client);
  }

  @Override
  public String getName() {
    return model.getName();
  }

  @Override
  public int getAge() {
    return model.getAge();
  }

  @Override
  public String getEmail() {
    return model.getEmail();
  }

  @Override
  public String getPhoneNumber() {
    return model.getPhoneNumber();
  }

  @Override
  public int getHeight() {
    return model.getHeight();
  }

  @Override
  public int getWeight() {
    return model.getWeight();
  }

  @Override
  public int getCalories() {
    return model.getCalories();
  }

  @Override
  public int getCaloriesGoal() {
    return model.getCaloriesGoal();
  }

  @Override
  public String getOrganization() {
    return model.getOrganization();
  }

  @Override
  public int getId() {
    return model.getId();
  }

  @Override
  public JSONObject toJson() {
    return model.toJson();
  }

  @Override
  public void setName(String name) {
    model.setName(name);
  }

  @Override
  public void setAge(int age) {
    model.setAge(age);
  }

  @Override
  public void setEmail(String email) {
    model.setEmail(email);
  }

  @Override
  public void setPhoneNumber(String phoneNumber) {
    model.setPhoneNumber(phoneNumber);
  }

  @Override
  public void setHeight(int height) {
    model.setHeight(height);
  }

  @Override
  public void setWeight(int weight) {
    model.setWeight(weight);
  }

  @Override
  public void setCalories(int calories) {
    model.setCalories(calories);
  }

  @Override
  public void setGoalCal(int goalCal) {
    model.setGoalCal(goalCal);
  }

  @Override
  public void setOrganization(String orgName) {
    model.setOrganization(orgName);
  }
}
