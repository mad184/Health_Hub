package healthhub;

import database.Dbms;
import org.json.JSONObject;

public class HealthHubModel {

  private HealthHubController controller;
  private Dbms database;

  private void addClient(int uniqueCid,JSONObject newClient) {
    database.addClient(uniqueCid,newClient);
  }

  private void addInstructor(Instructor newInstructor) {
    // db_writer.createInstructor(newInstructor);
  }

  private void addManager(Manager newManager) {
    // db_writer.createManager(newManager);
  }

  private void addOwner(Owner newOwner) {
    // db_writer.createOwner(newOwner);
  }

  private int login(String userName, String passWord) {

    // int authValue = db_reader.readCredentials(userName, passWord);

    //    switch (authValue) {
    //      case 404:
    //        return 404;
    //        break;
    //      case 409:
    //        return 409;
    //        break;
    //      case 408:
    //        return 408;
    //        break;
    //      default:
    //        return 200;
    //    }
    //  }
    return 1;
  }
}
