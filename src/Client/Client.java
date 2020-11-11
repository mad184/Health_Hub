package Client;

import javafx.scene.image.Image;
import java.util.ArrayList;

public class Client implements ClientInterface {
  private String name, email, instructor, organization, phoneNumber;
  private int id, age, height, weight;
  private int goalWeight, goalCals, calories;
  private ArrayList<String> allergies, comment;
  private Image profilePicture;

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
      Image profilePicture) {
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
  }

  // setters
  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public void setPhoneNum(String phoneNumber) {
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
  public void setWeightGoal(int goal) {
    this.goalWeight = goal;
  }

  @Override
  public void setCalGoal(int goal) { this.goalCals = goal; }

  @Override
  public void setCalories(int calories) {
    this.calories = calories;
  }

  @Override
  public void setAllergies(ArrayList<String> allergies) {
    this.allergies = allergies;
  }

  @Override
  public void setInstructor(String instructor) {
    this.instructor = instructor;
  }

  @Override
  public void setOrganization(String organization) {
    this.organization = organization;
  }

  @Override
  public void setComment(ArrayList<String> comment) {
    this.comment = comment;
  }

  @Override
  public void setProfilePicture(Image picture) {
    this.profilePicture = picture;
  }

  // getters
  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getAge() {
    return this.age;
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public String getEmail() {
    return this.email;
  }

  @Override
  public String getPhoneNum() {
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
  public int getWeightGoal() {
    return this.goalWeight;
  }

  @Override
  public int getCalGoal(){ return this.goalCals; }

  @Override
  public int getCalories() {
    return this.calories;
  }

  @Override
  public ArrayList<String> getAllergies() {
    return this.allergies;
  }

  @Override
  public String getInstructor() {
    return this.instructor;
  }

  @Override
  public String getOrganization() {
    return this.organization;
  }

  @Override
  public ArrayList<String> getComment() {
    return this.comment;
  }

  @Override
  public Image getProfilePicture() {
    return this.profilePicture;
  }
}
