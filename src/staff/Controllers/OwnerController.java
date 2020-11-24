package staff.Controllers;

import org.json.JSONObject;
import staff.Interfaces.StaffInterface;
import staff.Models.OwnerModel;
import staff.Interfaces.OwnerInterface;
import staff.UserID;

import java.util.List;

public class OwnerController implements OwnerInterface, StaffInterface {

  private OwnerModel model;

  public OwnerController(OwnerModel model) {
    this.model = model;
  }

  @Override
  public List<UserID> getManagers() {
    return model.getManagers();
  }

  @Override
  public void addManager(UserID manager) {
    model.addManager(manager);
  }

  @Override
  public void removeManager(UserID manager) {
    model.removeManager(manager);
  }

  @Override
  public JSONObject getManagerInfo(UserID manager) {
    return model.getManagerInfo(manager);
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
