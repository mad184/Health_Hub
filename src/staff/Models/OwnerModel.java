package staff.Models;

import java.util.List;

import database.EmptyQueryException;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import staff.Interfaces.OwnerInterface;
import staff.UserID;

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
      List<UserID> managers,
      String username,
      String password,
      String dbName,
      String tableName) {
    super(name, age, email, phoneNumber, height, weight, organization, id, instructors, username, password, dbName, tableName);
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
   * Adds a ManagerView (UserID) to the ManagerView list.
   *
   * @param manager: UserID of the new ManagerView
   */
  @Override
  public void addManager(UserID manager) {
    this.managers.add(manager);
  }

  /**
   * Removes a ManagerView (UserID) from the ManagerView list.
   *
   * @param manager: UserID of the ManagerView
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
  public JSONObject toJson() throws JSONException {
    JSONObject json = super.toJson();
    json.put("Managers", this.managers);
    return json;
  }

  /**
   * Gets the information about a ManagerView from the database.
   *
   * @param manager: UserID of the ManagerView
   * @return JSONObject representation of the ManagerView
   */
  @Override
  public JSONObject getManagerInfo(UserID manager) throws EmptyQueryException {
    return this.db.readManagerData(manager.getId());
  }
}
