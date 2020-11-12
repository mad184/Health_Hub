package staff;

import java.util.List;

/** View class for an Instructor user */
public class Instructor implements InstructorInterface {
  private String name;
  private int age;
  private String email;
  private int phoneNumber;
  private int height;
  private int weight;
  private List<String> clients;
  private String organization;
  private Controller controller;
  private int id;

  /**
   * Constructor
   *
   * @param name: Name of the user
   * @param age: Age of the user
   * @param email: Email of the user
   * @param phoneNumber: Phone number of the user
   * @param height: Height of the user (cm)
   * @param weight: Weight of the user (lbs)
   * @param clients: List of clients assigned to the Instructor user
   * @param organization: Organization that the Instructor is affiliated with
   * @param controller: Controller class for the Instructor
   * @param id: Database ID for the Instructor
   */
  public Instructor(
      String name,
      int age,
      String email,
      int phoneNumber,
      int height,
      int weight,
      List<String> clients,
      String organization,
      Controller controller,
      int id) {
    this.name = name;
    this.age = age;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.height = height;
    this.weight = weight;
    this.clients = clients;
    this.organization = organization;
    this.controller = controller;
    this.id = id;
  }

  /**
   * Sets the name of the user
   *
   * @param name: Name of the user
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the age of the user
   *
   * @param age: Age in years
   */
  @Override
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Sets the email of the user
   *
   * @param email: String of the user's email
   */
  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Sets the phone number of the user
   *
   * @param phoneNumber: Their phone number
   */
  @Override
  public void setPhoneNumber(int phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Sets the height of the user
   *
   * @param height: Height in cm
   */
  @Override
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Sets the weight of the user
   *
   * @param weight: Weight in lbs
   */
  @Override
  public void setWeight(int weight) {
    this.weight = weight;
  }

  /**
   * Sets the client list for the Instructor
   *
   * @param clients: List of Client objects
   */
  @Override
  public void setClients(List<String> clients) {
    this.clients = clients;
  }

  /**
   * Sets the organization that the Instructor is affiliated with
   *
   * @param organization: Name of the organization
   */
  @Override
  public void setOrganization(String organization) {
    this.organization = organization;
  }

  /**
   * Sets the controller that interfaces between the view and model.
   *
   * @param controller: A controller for the program
   */
  @Override
  public void setController(Controller controller) {
    this.controller = controller;
  }

  /**
   * Sets the database ID of the user
   *
   * @param id: Database ID
   */
  @Override
  public void setId(int id) {

    this.id = id;
  }

  /**
   * Gets the name of the user
   *
   * @return Name of the user
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Gets the age of the user
   *
   * @return Age of the user
   */
  @Override
  public int getAge() {
    return this.age;
  }

  /**
   * Gets the email of the user
   *
   * @return Email of the user
   */
  @Override
  public String getEmail() {
    return this.email;
  }

  /**
   * Gets the phone number of the user
   *
   * @return The user's phone number
   */
  @Override
  public int getPhoneNumber() {
    return this.phoneNumber;
  }

  /**
   * Gets the height of the user
   *
   * @return Height of the user
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Gets the weight of the user
   *
   * @return Weight of the user
   */
  @Override
  public int getWeight() {
    return this.weight;
  }

  /**
   * Gets the client list for the Instructor
   *
   * @return List of Clients
   */
  @Override
  public List<String> getClients() {
    return this.clients;
  }

  /**
   * Gets the organization name that the Instructor is affiliated with
   *
   * @return Name of the organization
   */
  @Override
  public String getOrganization() {
    return this.organization;
  }

  /**
   * Gets the controller associated with the Instructor
   *
   * @return Controller for the Instructor
   */
  @Override
  public Controller getController() {
    return this.controller;
  }

  /**
   * Gets the ID of the Instructor for the Database
   *
   * @return Database ID
   */
  @Override
  public int getId() {
    return id;
  }
}
