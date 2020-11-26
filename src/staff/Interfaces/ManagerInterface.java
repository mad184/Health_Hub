package staff.Interfaces;

import java.util.List;

import database.EmptyQueryException;
import org.json.JSONObject;
import staff.UserID;

/** An interface for ManagerView class */
public interface ManagerInterface {

  /**
   * Returns the Instructors (via UserIDs) that are assigned to the ManagerView
   *
   * @return List of UserIDs for the Instructors
   */
  public List<UserID> getInstructors();

  /**
   * Adds an Instructor to the ManagerView's list.
   *
   * @param instructor: UserID of Instructor
   */
  public void addInstructor(UserID instructor);

  /**
   * Removes an Instructor from the ManagerView's list.
   *
   * @param instructor: UserID of Instructor
   */
  public void removeInstructor(UserID instructor);

  /**
   * Accesses instructor info from the Database.
   *
   * @param instructor: UserID of the Instructor
   */
  public JSONObject getInstructorInfo(UserID instructor) throws EmptyQueryException;
}
