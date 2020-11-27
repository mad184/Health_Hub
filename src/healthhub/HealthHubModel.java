package healthhub;

import com.mongodb.MongoException;
import database.Dbms;
import database.EmptyQueryException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

public class HealthHubModel {
  private final Dbms database; // actual database that the healthhub model connects

  public HealthHubModel() {
    // Need to be changed in the future. This is for Development
  database = new Dbms("test-user", "healthhub1", "Test-General-Database", "testCollection");
  }

  /**
   * TEST METHOD: This method is used for integration testing with Database It retrieves the
   * database that the HealthHub model is using
   *
   * @return Dbms object which the HealthHub is connected
   */
  public Dbms testGetDatabase() {
    return database;
  }

  /**
   * This function is used to determine if the newEmail is unique or not It loops through the whole
   * database which the method will return false if it detects that the email is not unique
   *
   * @param newEmail: Email to be check if unique or not
   * @return boolean: true if email is unique, false otherwise
   */
  protected boolean determineUniqueEmail(String newEmail) {

    JSONArray allClients = database.getAllClients();
    JSONArray allInstructors = database.getAllInstructors();
    JSONArray allManagers = database.getAllManagers();

    // if everything is empty, return false
    // isEmpty does not work as it returns null
    if (allClients == null && allInstructors == null && allManagers == null) {
      return true;
    }

    JSONArray allData = new JSONArray();

    // Only append when the all the array is not null
    if (allClients != null) {
      allClients.forEach(
          (e) -> {
            allData.put(e);
          });
    }

    if (allInstructors != null) {
      allInstructors.forEach(
          (e) -> {
            allData.put(e);
          });
    }

    if (allManagers != null) {
      allManagers.forEach(
          (e) -> {
            allData.put(e);
          });
    }

    // Check if the email is unique or not
    for (int i = 0; i < allData.length(); i++) {

      String existingEmail = allData.getJSONObject(i).getString("email");

      if (existingEmail.equals(newEmail)) {
        return false;
      }
    }

    // None of the cases were triggered above, so must be unique
    return true;
  }

  /**
   * This method adds/creates the client within the database. It checks for uniqueness of the ID and
   * Email for ALL the instructor,client and Manager within the database
   *
   * @param clientInitialData: JSONObject that contains the data from the new client to be created
   * @return 403 if the email is not unique, 500 for server errors and "Unique Client ID" for
   *     successful creations
   */
  public int addClient(int uniqueId, JSONObject clientInitialData) {
    Random randomID = new Random();
    int clientId = randomID.nextInt(RANDOMBOUND);
    //int clientId = determineUniqueId();
    boolean emailUnique = determineUniqueEmail(clientInitialData.getString("email"));

    if (!emailUnique) {
      return 403;
    }

    try {
      clientInitialData.put("id", clientId);
      database.createClient(clientId, clientInitialData);
      return clientId;
    } catch (MongoException me) {
      return 500;
    }
  }

  /**
   * This method adds/creates the instructor within the database It checks for uniqueness of the ID
   * and Email for ALL the instructor,client and Manager within the database
   *
   * @param instrInitialData: JSONObject that contains the data from the new instructor to be
   *     created
   * @return 403 if the email is not unique, 500 for server errors and "Unique Instructor Id" for
   *     successful creation
   */
  public int addInstructor(JSONObject instrInitialData) {
    int instructorId = determineUniqueId();
    boolean emailUnique = determineUniqueEmail(instrInitialData.getString("email"));

    if (!emailUnique) {
      return 403;
    }

    try {
      database.createInstructor(instructorId, instrInitialData);
      return instructorId;
    } catch (MongoException me) {
      return 500;
    }
  }

  /**
   * This method adds/creates the manager within the database It checks for uniqueness of the ID and
   * Email for ALL the instructor,client and Manager within the database
   *
   * @param managerInitialData: JSONObject that contains the data from the new instructor to be
   *     created
   * @return 403 if the email is not unique, 500 for server errors and "Unique Manager ID" for
   *     successful Creation
   */
  public int addManager(JSONObject managerInitialData) {
    int managerId = determineUniqueId();
    boolean emailUnique = determineUniqueEmail(managerInitialData.getString("email"));

    if (!emailUnique) {
      return 403;
    }

    try {
      database.createManager(managerId, managerInitialData);
      return managerId;
    } catch (MongoException me) {
      return 500;
    }
  }

  /**
   * This method is used to login to the system. Primarily, it checks the validity of the
   * Email/Password
   *
   * @param emailUserName: Username to be checked
   * @param realPassword: Password to be checked
   * @param loginSelection: Can be "Client", "Manager", or "Instructor". Use to determine who are
   *     you logging in as
   * @throws IllegalArgumentException: when the loginSelection is invalid. It needs to be Client,
   *     Manager or Instructor
   * @return: 200 for successful found. 401 for incorrect password. 404 for not found
   */
  public int systemLogin(String emailUserName, String realPassword, String loginSelection)
      throws IllegalArgumentException {

    try {
      JSONArray CIMData;

      switch (loginSelection) {
        case "Client":
          CIMData = database.getAllClients();
          break;
        case "Instructor":
          CIMData = database.getAllInstructors();
          break;
        case "Manager":
          CIMData = database.getAllManagers();
          break;
        default:
          throw new IllegalArgumentException();
      }

      // If theres no client to login then return 404
      if (CIMData == null) {
        return 404;
      } else {
        for (int each = 0; each < CIMData.length(); each++) {

          // I am trusting that the email and password will be string here
          String indexUserName = (String) CIMData.getJSONObject(each).get("email");
          String indexPassWord = (String) CIMData.getJSONObject(each).get("password");
          int indexId = (int) CIMData.getJSONObject(each).get("_id");

          if (emailUserName.equals(indexUserName) && realPassword.equals(indexPassWord)) {
            return indexId;
          } else if (emailUserName.equals(indexUserName) && (!realPassword.equals(indexPassWord))) {
            return 401;
          }
        }

        return 404; // if we cant find the data
      }
    } catch (MongoException me) {
      return 500;
    } catch (Exception e) {
      return -1;
    }
  }

  /**
   * Creates the Organization with a specified unique organization name and JSON Object Data
   *
   * @param uniqueOrgName: Unique Organization Name
   * @param jsonOrgData: Json data involving the organization
   * @return 200 for successful, 500 for server issues and -1 for unknown exceptions
   */
  public int createOrganization(String uniqueOrgName, JSONObject jsonOrgData) {

    try {
      database.createOrganization(uniqueOrgName, jsonOrgData);
      return 200;
    } catch (MongoException me) {
      return 500;
    } catch (Exception e) {
      return -1;
    }
  }
}
