package staff.Models;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import staff.Interfaces.StaffInterface;
import database.Dbms;

/** Storage class for Staff users. */
public class StaffModel implements StaffInterface {
  private String name;
  private int age;
  private String email;
  private String phoneNumber;
  private int height;
  private int weight;
  private String organization;
  private int id;
  public Dbms db;
  private int calories;

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
    this.age = age;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.height = height;
    this.weight = weight;
    this.organization = organization;
    this.id = id;
    this.calories = 0;
    this.db = new Dbms(username, password, dbName, tableName);
  }

  /**
   * Converts a JSON representation of a Staff member into a StaffModel object.
   *
   * @param staff: JSONObject representation of a StaffModel object
   * @return a StaffModel object
   *
  public static StaffModel fromJson(JSONObject staff) {
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
    return 0;
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

  /**
   * Gets the database ID of the staff member.
   *
   * @return database ID
   */
  @Override
  public int getId() {
    return this.id;
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

  }

  /**
   * Sets the goal calorie
   *
   * @param goalCal the calorie that is the goal
   */
  @Override
  public void setGoalCal(int goalCal) {

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
   * Converts the staff member into a JSONObject representation
   *
   * @return JSONObject representation of a staff member
   */
  public JSONObject toJson() throws JSONException {
    JSONObject json = new JSONObject();
    json.put("Name", this.name);
    json.put("Age", this.age);
    json.put("email", this.email);
    json.put("Phone Number", this.phoneNumber);
    json.put("Height", this.height);
    json.put("Weight", this.weight);
    json.put("Organization", this.organization);
    json.put("ID", this.id);
    return json;
  }

  public Gson fromJson(JSONObject jsonObject){
    Gson ObjectClass = new Gson();
    setName(ObjectClass.fromJson(String.valueOf(jsonObject.get("Name")), String.class));
    setAge(ObjectClass.fromJson(String.valueOf(jsonObject.get("Age")), int.class));
    setEmail(ObjectClass.fromJson(String.valueOf(jsonObject.get("email")), String.class));
    setPhoneNumber(ObjectClass.fromJson(String.valueOf(jsonObject.get("Phone Number")), String.class));
    setHeight(ObjectClass.fromJson(String.valueOf(jsonObject.get("Height")), int.class));
    setWeight(ObjectClass.fromJson(String.valueOf(jsonObject.get("Weight")), int.class));
    //setOrganization(ObjectClass.fromJson(String.valueOf(jsonObject.get("Organization")), String.class));
    return ObjectClass;
  }
}
