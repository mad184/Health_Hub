package staff;

import java.util.List;

/** An interface for Owner users */
public interface OwnerInterface extends ManagerInterface {

  /**
   * Gets the list of all Managers in the organization.
   *
   * @return List of UserIDs of the Managers
   */
  public List<UserID> getManagers();

  /**
   * Adds a Manager to the organization.
   *
   * @param manager: UserID of the new Manager
   */
  public void addManager(UserID manager);

  /**
   * Removes a Manager from the organization.
   *
   * @param manager: UserID of the Manager
   */
  public void removeManager(UserID manager);
}
