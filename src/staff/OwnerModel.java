package staff;

import java.util.List;
import org.json.JSONObject;
import com.google.gson.Gson;

/** Storage class for Owner users. */
public class OwnerModel extends ManagerModel implements OwnerInterface {
  private List<UserID> managers;

  /**
   * Constructs a new ManagerModel object.
   *
   * @param name : Name of the manager
   * @param age : Age of the manager (years)
   * @param email : Email of the manager
   * @param phoneNumber : Phone number of the manager
   * @param height : Height of the manager (cm)
   * @param weight : Weight of the manager (lbs)
   * @param organization : Organization the manager is affiliated with
   * @param id : Database ID of the manager
   * @param instructors : List of Instructors
   */
  public OwnerModel(
      String name,
      int age,
      String email,
      String phoneNumber,
      int height,
      int weight,
      String organization,
      int id,
      List<UserID> instructors,
      List<UserID> managers) {
    super(name, age, email, phoneNumber, height, weight, organization, id, instructors);
    this.managers = managers;
  }

  /**
   * Converts a JSONObject representation of an Owner to an OwnerModel object.
   *
   * @param owner: JSONObject representation of an Owner.
   * @return OwnerModel object
   */
  public static OwnerModel fromJson(JSONObject owner) {
    Gson converter = new Gson();
    return converter.fromJson(String.valueOf(owner), OwnerModel.class);
  }

  /**
   * Gets the list of Managers (UserIDs) for the Owner.
   *
   * @return List of UserIDs for Managers
   */
  @Override
  public List<UserID> getManagers() {
    return this.managers;
  }

  /**
   * Adds a Manager (UserID) to the Manager list.
   *
   * @param manager: UserID of the new Manager
   */
  @Override
  public void addManager(UserID manager) {
    this.managers.add(manager);
  }

  /**
   * Removes a Manager (UserID) from the Manager list.
   *
   * @param manager: UserID of the Manager
   */
  @Override
  public void removeManager(UserID manager) {
    this.managers.remove(manager);
  }

  /**
   * Converts the OwnerModel to a JSONObject representation.
   *
   * @return JSONObject representation of an OwnerModel
   */
  public JSONObject toJson() {
    JSONObject json = super.toJson();
    json.put("Managers", this.managers);
    return json;
  }
}
