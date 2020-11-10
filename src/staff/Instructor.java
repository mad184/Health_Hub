package staff;

import java.util.List;

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

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhoneNumber(int phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public void setClients(List<String> clients) {
    this.clients = clients;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public void setController(Controller controller) {
    this.controller = controller;
  }

  @Override
  public void setId(int id) {

    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public String getEmail() {
    return this.email;
  }

  public int getPhoneNumber() {
    return this.phoneNumber;
  }

  public int getHeight() {
    return this.height;
  }

  public int getWeight() {
    return this.weight;
  }

  public List<String> getClients() {
    return this.clients;
  }

  public String getOrganization() {
    return this.organization;
  }

  public Controller getController() {
    return this.controller;
  }

  @Override
  public int getId() {
    return id;
  }


}
