package healthhub;

import client.Client;
import com.mongodb.MongoException;
import database.main.Dbms;
import org.json.JSONArray;
import org.json.JSONObject;
import staff.Instructor;
import staff.Manager;

public class HealthHubModel {
  private final Dbms database; // actual database that the healthhub model connects

  public HealthHubModel() {
    // Need to be changed in the future. This is for Development
    database = new Dbms("test-user", "healthhub1", "Test-General-Database", "testCollection");
  }

  /**
   * This method adds/creates the client within the database
   *
   * @param client: Client Object to be added within the database
   */
  public void addClient(Client client) {
    database.createClient(client.getUniqueId(), client.getJSONData());
  }

  /**
   * This method adds/creates the instructor within the database
   *
   * @param instructor: Instructor Object to be added within the database
   */
  public void addInstructor(Instructor instructor) {
    database.createInstructor(instructor.getUniqueId(), instructor.getJSONData());
  }

  /**
   * This method adds/creates the manager within the database
   *
   * @param manager: Manager Object to be added within the database
   */
  public void addManager(Manager manager) {
    database.createManager(manager.getUniqueId(), manager.getJSONData());
  }

  /**
   * This method is used to login to the system. Primarily, it checks the validity of the
   * Username/Password
   *
   * @param realUserName: Username to be checked
   * @param realPassword: Password to be checked
   * @param loginSelection: Can be client, manager, or instructor. Use to determine who are you
   *     logging in as
   * @return: 200 for successful found. 401 for incorrect password. 404 for not found
   * @throws IllegalArgumentException: when the loginSelection is invalid. It needs to be Client,
   *     Manager or Instructor
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
          CIMdata = database.getAllInstructor();
          break;
        case "Manager":
          CIMdata = database.getAllManager();
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
      return 404;
    } catch (MongoException me) {
      return 500;
    } catch (Exception e) {
      return -1;
    }
  }

  /**
   * A method to create the Organization,
   *
   * @param name: the name of the organization we want to create
   * @param owner: the owner of the organization
   * @return
   *     true -> the organization has been created
   *     false -> the organziation has already been created
   */
  public boolean CreateOrgnaization(String name, Manager owner) {
    try {
      HealthHubAccessSingleton.newOrganziation(name, owner);
      return true;
    }
    catch (RuntimeException e) {
      return false;
    }
  }
}
