package staff.Interfaces;

public interface StaffInterface {

  /**
   * Gets the name of the user
   *
   * @return the name of the user
   */
  String getName();

  /**
   * Gets the age of the user, in years
   *
   * @return age of the user
   */
  int getAge();

  /**
   * Gets the email of the user
   *
   * @return the email of the user
   */
  String getEmail();

  /**
   * Gets the phone number of the user
   *
   * @return the user's phone number, as a String
   */
  String getPhoneNumber();

  /**
   * Gets the height of the user
   *
   * @return user's height in cm
   */
  int getHeight();

  /**
   * Gets the weight of the user
   *
   * @return user's weight in lbs
   */
  int getWeight();

  /**
   * Gets the calories of the user
   *
   * @return client calorie
   */
  int getCalories();

  /**
   * Gets the goal calorie of the client
   *
   * @return the goal set for calories
   */
  int getCaloriesGoal();

  /**
   * Gets the organization that the user is affiliated with
   *
   * @return the organization name
   */
  String getOrganization();

  /**
   * Gets the database ID for the user
   *
   * @return database ID
   */
  int getId();

  /**
   * Sets the name of the Staff member
   *
   * @param name: Name of the Staff member
   */
  void setName(String name);

  /**
   * Sets the age of the user
   *
   * @param age: Age in years
   */
  void setAge(int age);

  /**
   * Sets the email of the user
   *
   * @param email: Email as a String
   */
  void setEmail(String email);

  /**
   * Sets the phone number of the user
   *
   * @param phoneNumber: Phone number as a String
   */
  void setPhoneNumber(String phoneNumber);

  /**
   * Sets the height of the user
   *
   * @param height: Height in cm
   */
  void setHeight(int height);

  /**
   * Sets the weight of the user
   *
   * @param weight: Weight in lbs
   */
  void setWeight(int weight);

  /**
   * Sets calorie of the user
   *
   * @param calories calories
   */
  void setCalories(int calories);

  /**
   * Sets the goal calorie
   *
   * @param goalCal the calorie that is the goal
   */
  void setGoalCal(int goalCal);

  /**
   * Sets the name of the organization the user is affiliated with
   *
   * @param orgName: Name of the organization
   */
  void setOrganization(String orgName);
}
