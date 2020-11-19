package staff.Controllers;

import org.json.JSONException;
import org.json.JSONObject;
import staff.Interfaces.InstructorInterface;
import staff.Interfaces.StaffInterface;
import staff.Models.InstructorModel;
import staff.UserID;

import java.util.List;

public class InstructorController implements InstructorInterface, StaffInterface {

  private InstructorModel model;

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
  public void addComment(UserID client, String comment) throws JSONException {
    model.addComment(client, comment);
  }

  @Override
  public void removeComment(UserID client, String comment) throws JSONException {
    model.removeComment(client, comment);
  }

  @Override
  public JSONObject getClientInfo(UserID client) {
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
  public void setOrganization(String orgName) {
    model.setOrganization(orgName);
  }
}
