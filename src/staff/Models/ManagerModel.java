package staff.Models;

import java.util.ArrayList;
import java.util.List;

import database.EmptyQueryException;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import staff.Interfaces.ManagerInterface;
import staff.UserID;

/** Storage class for ManagerView users. */
public class ManagerModel extends StaffModel implements ManagerInterface {
  private List<UserID> instructors;

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
  public ManagerModel(
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
      String username, // For connection to Dbms
      String password, // For connection to Dbms
      String dbName, // For connection to Dbms
      String tableName) // For connection to Dbms)
      {
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
        username,
        password,
        dbName,
        tableName);
    this.instructors = instructors;
  }

  /**
   * Converts a JSONObject representation of the manager to ManagerModel.
   *
   * @param manager: JSONObject of the manager
   * @return ManagerModel object
   *     <p>public static ManagerModel fromJson(JSONObject manager) { Gson converter = new Gson();
   *     return converter.fromJson(String.valueOf(manager), ManagerModel.class); }
   */

  /**
   * Gets the Instructor list (as UserIDs) for the ManagerView
   *
   * @return List of UserIDs for Instructors
   */
  @Override
  public List<UserID> getInstructors() {
    return this.instructors;
  }

  /**
   * Adds an Instructor (UserID) to the Instructor list.
   *
   * @param instructor: UserID of Instructor
   */
  @Override
  public void addInstructor(UserID instructor) {
    this.instructors.add(instructor);
  }

  /**
   * Removes and Instructor (UserID) from the list.
   *
   * @param instructor: UserID of Instructor
   */
  @Override
  public void removeInstructor(UserID instructor) {
    this.instructors.remove(instructor);
  }

  /**
   * Gets the instructor information from the Database.
   *
   * @param instructor : UserID of the Instructor
   */
  @Override
  public JSONObject getInstructorInfo(UserID instructor) throws EmptyQueryException {
    return this.db.readInstructorData(instructor.getId());
  }

  /**
   * Converts the ManagerModel to a JSON representation.
   *
   * @return JSON representation of a ManagerView
   */
  @Override
  public JSONObject toJson() throws JSONException {
    JSONObject json = super.toJson();

    if (this.instructors != null) {
      json.put(
          "Instructors",
          this.instructors.toString().substring(1, this.instructors.toString().length() - 1));
    } else {
      json.put("Instructors", "");
    }
    return json;
  }

  /**
   * A method that sets a JSONObject back to ManagerModel Class
   *
   * @param jsonObject ManagerModel class in JSONObject
   * @return ManagerModel Class
   */
  public Gson fromJson(JSONObject jsonObject) {
    Gson ObjectClass = super.fromJson(jsonObject);

    if (!jsonObject.get("Instructors").toString().equals("")) {
      String[] list = String.valueOf(jsonObject.get("Instructors")).split(",");
      ArrayList<UserID> instructorList = new ArrayList<>();
      for (String item : list) {
        String[] instructorInfo = item.split(";");
        UserID exerciseItem = new UserID(Integer.parseInt(instructorInfo[1]), instructorInfo[0]);
        instructorList.add(exerciseItem);
      }
      this.instructors = instructorList;
    }
    return ObjectClass;
  }
}
