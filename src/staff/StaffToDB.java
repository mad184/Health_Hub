package staff;

import com.mongodb.util.JSON;
import database.Dbms;
import database.EmptyQueryException;
import database.JsonObjectException;
import org.json.JSONArray;
import org.json.JSONObject;

public class StaffToDB {

  Dbms DB = new Dbms("production_user", "healthhub1", "production-database", "testCollection");

  /**
   * Creates instructor in database from json object
   *
   * @param instructorID Id used to identify instructor
   * @param instructorJSON object with instructor info
   */
  public void createInstructor(int instructorID, JSONObject instructorJSON) {
    DB.createInstructor(instructorID, instructorJSON);
  }

  /**
   * Grabs instructor JSON from database
   *
   * @param instructorID The id used to identify instructor
   * @return instructor JSONObject
   * @throws EmptyQueryException If the client with id doesn't exist
   */
  public JSONObject getInstructor(int instructorID) throws EmptyQueryException {
    return DB.readInstructorData(instructorID);
  }

  /**
   * Overwrites current instructor info with new info from json
   *
   * @param instructorID The id used to identify instructor
   * @param instructorJSON JSON instructor with instructor info
   * @throws EmptyQueryException When query returns nothing
   */
  public void updateInstructor(int instructorID, JSONObject instructorJSON)
      throws JsonObjectException, EmptyQueryException {
    DB.updateInstructor(instructorID, instructorJSON);
  }

  /**
   * Removes instructor from data
   *
   * @param instructorID The id used to identify instructor
   */
  public void removeInstructor(int instructorID) {
    DB.removeInstructor(instructorID);
  }

  /**
   * Creates manager in database from json object
   *
   * @param managerID Id used to identify manager
   * @param managerJSON object with manager info
   */
  public void createManager(int managerID, JSONObject managerJSON) {
    DB.createManager(managerID, managerJSON);
  }

  /**
   * Grabs manager JSON from database
   *
   * @param managerID The id used to identify manager
   * @return manager JSONObject
   * @throws EmptyQueryException If the manager with id doesn't exist
   */
  public JSONObject getManager(int managerID) throws EmptyQueryException {
    return DB.readManagerData(managerID);
  }

  /**
   * Overwrites current manager info with new info from json
   *
   * @param managerID The id used to identify manager
   * @param managerJSON JSON manager with manager info
   * @throws EmptyQueryException When query returns nothing
   */
  public void updateManager(int managerID, JSONObject managerJSON)
      throws JsonObjectException, EmptyQueryException {
    DB.updateManager(managerID, managerJSON);
  }

  /**
   * Removes manager from data
   *
   * @param managerID The id used to identify manager
   */
  public void removeManager(int managerID) {
    DB.removeManager(managerID);
  }

  /**
   * method to return all the instructors in the Db
   *
   * @return JSONArray of the objects
   */
  public JSONArray getAllInstructors() {
    return DB.getAllInstructors();
  }

  /**
   * method to return all the clients in the Db
   *
   * @return JSONArray of the objects
   */
  public JSONArray getAllClients() {
    return DB.getAllClients();
  }

  /**
   * method to return all the managers in the Db
   *
   * @return JSONArray of the objects
   */
  public JSONArray getAllManagers() {
    return DB.getAllManagers();
  }

}
