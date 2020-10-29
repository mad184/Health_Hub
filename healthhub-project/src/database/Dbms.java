package database;

import healthhub.Client;
import org.json.JSONObject;
import healthhub.HealthHubModel;
// import client.ClientModel;
// import staff.InstructorModel;

public class Dbms {
  Writer db_writer;
  Reader db_reader;
  private final String uriString;

  Dbms(String userName, String passWord, String dbName, String tableName) {
    uriString =
        "mongodb+srv://"
            + userName
            + ":"
            + passWord
            + "@healthhub-cluster.7y7j0.mongodb.net/"
            + dbName
            + "?retryWrites=true&w=majority";
    db_writer = new Writer(uriString, dbName, tableName);
    // Insert Reader instance here
  }

  public void addClient( int uniqueCid,JSONObject newClientInfo) {
    db_writer.createClient(uniqueCid,newClientInfo);
  }
}
