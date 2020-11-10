package Client;

import javafx.scene.image.Image;

import java.io.File;

public interface ClientInterface {

  // setters for client attributes
  void setName(String name);

  void setAge(int age);

  void setId(int id);

  void setEmail(String email);

  void setPhoneNum(String phoneNumber);

  void setHeight(int height);

  void setWeight(int weight);

  void setGoal(double goal);

  void setCalories(double calories);

  void setAllergies(String[] allergies);

  void setInstructor(String instructor);

  void setOrganization(String organization);

  void setComment(String[] comment);

  void setProfilePicture(Image picture);
  // void controller(Controller);

  // getters for client
  String getName();

  int getId();

  int getAge();

  String getEmail();

  String getPhoneNum();

  int getHeight();

  int getWeight();

  double getGoal();

  double getCalories();

  String[] getAllergies();

  String getInstructor();

  String getOrganization();
  // void controller();
  String[] getComment();

  Image getProfilePicture();
}
