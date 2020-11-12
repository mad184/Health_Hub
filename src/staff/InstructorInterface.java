package staff;

import java.util.List;

public interface InstructorInterface {

  // setters for instructor attributes
  void setName(String name);

  void setAge(int age);

  void setEmail(String email);

  void setPhoneNumber(int phoneNumber);

  void setHeight(int height);

  void setWeight(int weight);

  void setClients(List<String> clients);

  void setOrganization(String organization);

  void setController(Controller controller);

  void setId(int id);

  // getters for instructor attributes
  String getName();

  int getAge();

  String getEmail();

  int getPhoneNumber();

  int getHeight();

  int getWeight();

  List<String> getClients();

  String getOrganization();

  Controller getController();

  int getId();
}
