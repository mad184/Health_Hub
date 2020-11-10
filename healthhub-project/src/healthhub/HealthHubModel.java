package healthhub;

import client.Client;
import com.mongodb.MongoException;
import database.main.Dbms;
import org.json.JSONArray;
import org.json.JSONObject;
import staff.Instructor;
import staff.Manager;

public class HealthHubModel {
  private final Dbms database;

  public HealthHubModel() {
    // Need to be changed in the future. This is for Development
    database = new Dbms("test-user", "healthhub1", "Test-General-Database", "testCollection");
  }

  protected void addClient(Client client) {
    database.createClient(client.getUniqueId(), client.getJSONData());
  }

  protected void addInstructor(Instructor instructor) {
    database.createInstructor(instructor.getUniqueId(), instructor.getJSONData());
  }

  protected void addManager(Manager manager) {
    database.createManager(manager.getUniqueId(), manager.getJSONData());
  }

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

  protected int systemLogin(String realUserName, String realPassWord, String loginSelection) {

    try {
      return CIMLogin(realUserName, realPassWord, loginSelection);
    }
    // Not actual exception yet
    catch (MongoException mse) {
      return 500;
    } catch (Exception ex) {
      return -1;
    }
  }
}
