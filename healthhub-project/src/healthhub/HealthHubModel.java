package healthhub;

import database.EmptyQueryException;
import com.mongodb.MongoException;
import database.Dbms;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

public class HealthHubModel {
  private final Dbms database; // actual database that the healthhub model connects
  private final int RANDOMBOUND = 5; // this is the maximum random number unique id can generate

  public HealthHubModel() {
    // Need to be changed in the future. This is for Development
    database = new Dbms("test-user", "healthhub1", "Test-General-Database", "testCollection");
  }

  /**
   * Special method for testing where it returns the database
   * @return
   */
  public Dbms getDatabase(){
    return database;
  }

  /**
   * This method determines the unique id that will be used to write within the database The
   * database will throw exception if the query returned nothing when reading. Thus, I am using that
   * to determine the uniqueness of the ID. If the read does return something, its not unique If the
   * read does not return and throws exception, that means that the chosen id is unique, thus we use
   * that to determine the uniqueness of the id NOTE: The unique id system is set up in the way that
   * the "UNIQUE ID" will be unique for all managers, clients and Instructor
   *
   * @return uniqueId: unique id that is within the max bounds of java int This value is unique to
   *     all manager,client and instructor
   */
  private int determineUniqueId() {
    Random randomID = new Random();
    int uniqueId = 0;
    while (true) {

      // Make sures unique ID is not within of the status codes
      // May cause heisenbugg in the future
      do {
        uniqueId = randomID.nextInt(RANDOMBOUND); // Should be changed back to a max int value
      } while (uniqueId == 400
          || uniqueId == 500
          || uniqueId == 403
          || uniqueId == 404
          || uniqueId == 401
          || uniqueId == 0);

      try {
        database.readClientData(uniqueId);
        database.readInstructorData(uniqueId);
        database.readManagerData(uniqueId);
      } catch (EmptyQueryException eqe) {
        break;
      }
    }

    return uniqueId;
  }

  /**
   * This function is used to determine if the newEmail is unique or not It loops through the whole
   * database which the method will return false if it detects that the email is not unique
   *
   * @param newEmail: Email to be check if unique or not
   * @return boolean: true if email is unique, false otherwise
   */
  private boolean determineUniqueEmail(String newEmail) {

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
    if(allClients != null){
      allClients.forEach((e)->{
        allData.put(e);
      });
    }

    if (allInstructors != null) {
      allInstructors.forEach((e)->{
        allData.put(e);
      });
    }

    if(allManagers != null){
      allManagers.forEach((e)->{
        allData.put(e);
      });
    }

    for (int i = 0; i < allData.length(); i++) {

      String existingEmail = allData.getJSONObject(i).getString("email");

      if (existingEmail.equals(newEmail)) {
        return false;
      }
    }
    return true;
  }

  /**
   * This method adds/creates the client within the database. It checks for uniqueness of the ID and
   * Email for ALL the instructor,client and Manager within the database
   *
   * @param clientInitialData: JSONObject that contains the data from the new client to be created
   * @return 403 if the email is not unique, 500 for server errors and "Unique Client ID for
   *     successful
   */
  public int addClient(JSONObject clientInitialData) {
    int clientId = determineUniqueId();
    boolean emailUnique = determineUniqueEmail(clientInitialData.getString("email"));

    if (!emailUnique) {
      return 403;
    }

    try {
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
   * @return 403 if the email is not unique, 500 for server errors and 200 for successful
   */
  public int addInstructor(JSONObject instrInitialData) {
    int instructorId = determineUniqueId();
    boolean emailUnique = determineUniqueEmail(instrInitialData.getString("email"));

    if (!emailUnique) {
      return 403;
    }

    try {
      database.createInstructor(instructorId, instrInitialData);
      return 200;
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
   * @return 403 if the email is not unique, 500 for server errors and 200 for successful
   */
  public int addManager(JSONObject managerInitialData) {
    int managerId = determineUniqueId();
    boolean emailUnique = determineUniqueEmail(managerInitialData.getString("email"));

    if (!emailUnique) {
      return 403;
    }

    try {
      database.createManager(managerId, managerInitialData);
      return 200;
    } catch (MongoException me) {
      return 500;
    }
  }

  /**
   * This method is used to login to the system. Primarily, it checks the validity of the
   * Username/Password
   *
   * @param realUserName: Username to be checked
   * @param realPassword: Password to be checked
   * @param loginSelection: Can be client, manager, or instructor. Use to determine who are you
   *     logging in as
   * @throws IllegalArgumentException: when the loginSelection is invalid. It needs to be Client,
   *     Manager or Instructor
   * @return: 200 for successful found. 401 for incorrect password. 404 for not found
   */
  public int systemLogin(String realUserName, String realPassword, String loginSelection)
      throws IllegalArgumentException {

    try {
      JSONArray CIMdata = new JSONArray();

      switch (loginSelection) {
        case "Client":
          CIMdata = database.getAllClients();
          break;
        case "Instructor":
          CIMdata = database.getAllInstructors();
          break;
        case "Manager":
          CIMdata = database.getAllManagers();
          break;
        default:
          throw new IllegalArgumentException();
      }

      for (Object eachElement : CIMdata) {

        if (((JSONObject) eachElement).get("UserName").equals(realUserName)
            && ((JSONObject) eachElement).get("PassWord").equals(realPassword)) {
          return 200;
        } else if (((JSONObject) eachElement).get("UserName").equals(realUserName)
            && !((JSONObject) eachElement).get("PassWord").equals(realPassword)) {
          return 401;
        }
      }
      return 404; // if we cant find the data
    } catch (MongoException me) {
      return 500;
    } catch (Exception e) {
      return -1;
    }
  }

  /**
   * Creates the Organization with a specified unique organization name and jsonOrgData
   *
   * @param uniqueOrgName: Unique Organization Name
   * @param jsonOrgData: Json data involving the organization
   */
  public int createOrganization(String uniqueOrgName, JSONObject jsonOrgData) {

    try {
      database.createOrganization(uniqueOrgName, jsonOrgData);
      return 200;
    } catch (MongoException me) {
      return 500;
    }
  }
}
