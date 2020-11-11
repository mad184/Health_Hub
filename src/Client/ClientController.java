package Client;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class ClientController {
  private Client model;

  public ClientController(Client model) {
    this.model = model;
  }

  //Setters
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


  //Getters
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
}
