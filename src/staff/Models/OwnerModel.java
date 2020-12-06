package staff.Models;

import java.util.ArrayList;
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
      String userPassword,
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
    super(
        name,
        userPassword,
        age,
        email,
        phoneNumber,
        height,
        weight,
        organization,
        id,
        instructors,
        username,
        password,
        dbName,
        tableName);
    this.managers = managers;
  }

  /**
   * Converts a JSONObject representation of an Owner to an OwnerModel object.
   *
   * @param owner: JSONObject representation of an Owner.
   * @return OwnerModel object
   *     <p>public static OwnerModel fromJson(JSONObject owner) { Gson converter = new Gson();
   *     return converter.fromJson(String.valueOf(owner), OwnerModel.class); }
   */

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
  @Override
  public JSONObject toJson() throws JSONException {
    JSONObject json = super.toJson();

    if (this.managers != null) {
      json.put(
              "Managers",
              this.managers.toString().substring(1, this.managers.toString().length() - 1));
    }
    else {
      json.put(
              "Managers", "");
    }
    return json;
  }

  /**
   * A method that sets a JSONObject back to OwnerModel Class
   *
   * @param jsonObject Owner class in JSONObject
   * @return OwnerModel Class
   */
  public Gson fromJson(JSONObject jsonObject) {
    Gson ObjectClass = super.fromJson(jsonObject);

    if (!jsonObject.get("Managers").toString().equals("")) {
      String[] list = String.valueOf(jsonObject.get("Managers")).split(",");
      ArrayList<UserID> managersList = new ArrayList<>();
      for (String item : list) {
        String[] managerInfo = item.split(";");
        UserID exerciseItem =
                new UserID (Integer.parseInt(managerInfo[1]), managerInfo[0]);
        managersList.add(exerciseItem);
      }
      this.managers = managersList;
    }
    return ObjectClass;
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
