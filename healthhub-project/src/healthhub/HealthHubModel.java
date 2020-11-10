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
   */
  private int CIMLogin(String realUserName, String realPassword, String loginSelection) {

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
      default: // Do nothing
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
  }

  /**
   * This function is the primary entrance to system login. Calls the CIM login that verifies the
   * validity of username and password
   *
   * @param realUserName: Username to be checked
   * @param realPassWord: Password to be checked
   * @param loginSelection: Can be client, manager, or instructor. Use to determine who are you
   *     logging in as.
   * @return: 200 for successful found. 401 for incorrect password. 404 for not found. 500 for
   *     server-client issues. -1 for anything that is unexpected
   */
  public int systemLogin(String realUserName, String realPassWord, String loginSelection) {

    try {
      return CIMLogin(realUserName, realPassWord, loginSelection);
    } catch (MongoException mse) {
      return 500;
    } catch (Exception ex) {
      return -1;
    }
  }
}
