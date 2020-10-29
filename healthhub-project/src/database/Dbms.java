package database;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import healthhub.Client;
import org.bson.Document;
import org.json.JSONObject;
import healthhub.HealthHubModel;
// import client.ClientModel;
// import staff.InstructorModel;

public class Dbms {
  Writer db_writer;
  Reader db_reader;
  private final String uriString;

  /**
   * Initializes the cloud database to be used for the healthhub application.
   * if db does not exist, it will not work
   * if table does not exist, it will create a new table
   * @param userName: username credential to connect to cloud
   * @param passWord: passWord credential to connect to cloud(NOT SECURE!)
   * @param dbName: cloud database to connect. Need to exist
   * @param tableName: table inside the cloud database to connect
   */
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

  /**
   * Calls the createClient from the writer initialization.
   * This should be called when creating a new client within the healthhub application
   * @param uniqueCid: unique client id to create
   * @param newClientInfo: new client information to create
   */
  public void addClient( int uniqueCid,JSONObject newClientInfo) {
    db_writer.createClient(uniqueCid,newClientInfo);
  }

  /**
   * Calls the createInstructor from the writer initialization.
   * This should be called when creating a new instructor within the healthhub application
   * @param uniqueIid: unique instructor id to create
   * @param newInstructorInfo: new instructor information to create
   */
  public void addInstructor( int uniqueIid, JSONObject newInstructorInfo){
    db_writer.createInstructor(uniqueIid,newInstructorInfo);
  }

  /**
   * Calls the createManager from the writer initialization.
   * This should be called when creating a new Manager within the healthhub application
   * @param uniqueMid: unique manager id to create
   * @param newManagerInfo: new manager information to create
   */
  public void addManager(int uniqueMid, JSONObject newManagerInfo){
    db_writer.createManager(uniqueMid,newManagerInfo);
  }

  /**
   * Calls the removeClient from the writer initialization.
   * This should be called when removing a client from the healthhub application
   * @param uniqueCid: unique client id to remove
   */
  public void removeClient(int uniqueCid){
    db_writer.removeClient(uniqueCid);
  }

  /**
   * Calls the removeInstructor from the writer initialization
   * This should be called when removing an instructor from the healthhub application
   * @param uniqueIid: unique instructor id to remove
   */
  public void removeInstructor(int uniqueIid){
    db_writer.removeInstructor(uniqueIid);
  }

  /**
   * calls the updateClient from the writer initialization.
   * This should be called when updating client information inside the database.
   * @param uniqueCid: Unique Client Id
   * @param clientUpdatedData: client updated data to use in replacing
   */
  public void updateClient(int uniqueCid, JSONObject clientUpdatedData) {
    db_writer.updateClient(uniqueCid, clientUpdatedData);
  }

  /**
   * calls the updateInstructor from the writer initialization.
   * This should be called when updating instructor information inside the database.
   * @param uniqueIid: Unique id for instructors
   * @param instrUpdatedData: Instructor updated to be use in replacing
   */
  protected void updateInstructor(int uniqueIid, JSONObject instrUpdatedData) {
    db_writer.updateInstructor(uniqueIid, instrUpdatedData);
  }

  /**
   * calls the updateManager from the writer initialization.
   * This should be called when updating Manager information inside the database.
   * @param uniqueMid: unique manager id to find
   * @param managerUpdatedData: Manager updated data to store
   */
  protected void updateManager(int uniqueMid, JSONObject managerUpdatedData) {
    db_writer.updateManager(uniqueMid,managerUpdatedData);
  }
}
