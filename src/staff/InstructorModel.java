package staff;

import java.util.List;
import org.json.JSONObject;
import com.google.gson.Gson;

/**
 * Storage class for Instructor users.
 */
public class InstructorModel extends StaffModel implements InstructorInterface {
  private List<UserID> clients;


  /**
   * Constructs a new StaffModel object.
   *
   * @param name         : Name of the staff member
   * @param age          : Age of the staff member (years)
   * @param email        : Email of the staff member
   * @param phoneNumber  : Phone number of the staff member
   * @param height       : Height of the staff member (cm)
   * @param weight       : Weight of the staff member (lbs)
   * @param organization : Organization the staff member is affiliated with
   * @param id           : Database ID of the staff member
   * @param clients      : Client list for the Instructor
   */
  public InstructorModel(
          String name,
          int age,
          String email,
          String phoneNumber,
          int height,
          int weight,
          String organization,
          int id,
          List<UserID> clients) {
    super(name, age, email, phoneNumber, height, weight, organization, id);
    this.clients = clients;
  }

  @Override
  public List<UserID> getClients() {
    return null;
  }

  @Override
  public void addClient(UserID client) {

  }

  @Override
  public void removeClient(UserID client) {

  }

  @Override
  public void addComment(UserID client, String comment) {

  }

  @Override
  public void removeComment(UserID client, String comment) {

  }

  @Override
  public Client getClientInfo(UserID client) {
    return null;
  }
}
