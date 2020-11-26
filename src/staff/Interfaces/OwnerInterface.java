package staff.Interfaces;

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
  public List<UserID> getManagers();

  /**
   * Adds a ManagerView to the organization.
   *
   * @param manager: UserID of the new ManagerView
   */
  public void addManager(UserID manager);

  /**
   * Removes a ManagerView from the organization.
   *
   * @param manager: UserID of the ManagerView
   */
  public void removeManager(UserID manager);

  /**
   * Gets the information about a ManagerView from the database.
   *
   * @param manager: UserID of the ManagerView
   * @return JSONObject representation of the ManagerView
   */
  public JSONObject getManagerInfo(UserID manager) throws EmptyQueryException;
}
