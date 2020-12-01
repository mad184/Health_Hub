package staff.Interfaces;

import com.google.gson.Gson;
import database.EmptyQueryException;
import org.json.JSONObject;
import staff.UserID;

import java.util.List;

/** An interface for Owner users */
public interface OwnerInterface {

  /**
   * Gets the list of all Managers in the organization.
   *
   * @return List of UserIDs of the Managers
   */
  List<UserID> getManagers();

  /**
   * Adds a ManagerView to the organization.
   *
   * @param manager: UserID of the new ManagerView
   */
  void addManager(UserID manager);

  /**
   * Removes a ManagerView from the organization.
   *
   * @param manager: UserID of the ManagerView
   */
  void removeManager(UserID manager);

  /**
   * Gets the information about a ManagerView from the database.
   *
   * @param manager: UserID of the ManagerView
   * @return JSONObject representation of the ManagerView
   */
  JSONObject getManagerInfo(UserID manager) throws EmptyQueryException;


  /**
   * Get a JSONObject from the class
   *
   * @return Class JSONObject
   */
  JSONObject toJson();

  /** Get a JSONObject and set it back to a class - Instructor, Manager or owner */
  Gson fromJson(JSONObject staff);
}
