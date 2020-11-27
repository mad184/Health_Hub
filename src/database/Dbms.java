package database;

import org.json.JSONArray;
import org.json.JSONObject;

public class Dbms implements WriteInterface, ReadInterface {
  private final Writer DBWRITER; // Writer initialization
  private final Reader DBREADER; // Reader initialization

  /**
   * Initializes the cloud database to be used for the healthhub application. if db does not exist,
   * it will not work if table does not exist, it will create a new table
   *
   * @param userName: username credential to connect to cloud
   * @param passWord: passWord credential to connect to cloud(NOT SECURE!)
   * @param dbName: cloud database to connect. Need to exist
   * @param tableName: table inside the cloud database to connect
   */
  public Dbms(String userName, String passWord, String dbName, String tableName) {
    String uriString =
        "mongodb+srv://"
            + userName
            + ":"
            + passWord
            + "@healthhub-cluster.7y7j0.mongodb.net/"
            + dbName
            + "?retryWrites=true&w=majority";
    DBWRITER = new Writer(uriString, dbName, tableName);
    DBREADER = new Reader(uriString, dbName, tableName);
  }

  /**
   * Calls the createClient from the writer initialization. This should be called when creating a
   * new client within the healthhub application
   *
   * @param uniqueCid: unique client id to create
   * @param newClientInfo: new client information to create
   */
  public void createClient(int uniqueCid, JSONObject newClientInfo) {
    DBWRITER.createClient(uniqueCid, newClientInfo);
    DBWRITER.setNextUniqueId();
  }

  /**
   * Calls the createInstructor from the writer initialization. This should be called when creating
   * a new instructor within the healthhub application
   *
   * @param uniqueIid: unique instructor id to create
   * @param newInstructorInfo: new instructor information to create
   */
  public void createInstructor(int uniqueIid, JSONObject newInstructorInfo) {
    DBWRITER.createInstructor(uniqueIid, newInstructorInfo);
    DBWRITER.setNextUniqueId();
  }

  /**
   * Calls the createManager from the writer initialization. This should be called when creating a
   * new Manager within the healthhub application
   *
   * @param uniqueMid: unique manager id to create
   * @param newManagerInfo: new manager information to create
   */
  public void createManager(int uniqueMid, JSONObject newManagerInfo) {
    DBWRITER.createManager(uniqueMid, newManagerInfo);
    DBWRITER.setNextUniqueId();
  }

  /**
   * Calls the createOrganization from the writer initialization. This should be called when
   * creating a new Organization within the healthhub application
   *
   * @param uniqueOrgName: unique Organization name to create
   * @param newOrgInfo: new Organization information to create
   */
  public void createOrganization(String uniqueOrgName, JSONObject newOrgInfo) {
    DBWRITER.createOrganization(uniqueOrgName, newOrgInfo);
  }

  /**
   * Calls the removeClient from the writer initialization. This should be called when removing a
   * client from the healthhub application. Take note that no deletion will be made if the client
   * does not exist
   *
   * @param uniqueCid: unique client id to remove
   */
  public void removeClient(int uniqueCid) {
    DBWRITER.removeClient(uniqueCid);
  }

  /**
   * Calls the removeInstructor from the writer initialization This should be called when removing
   * an instructor from the healthhub application. Take note that no deletion will be made if the
   * instructor does not exist
   *
   * @param uniqueIid: unique instructor id to remove
   */
  public void removeInstructor(int uniqueIid) {
    DBWRITER.removeInstructor(uniqueIid);
  }

  /**
   * Calls the removeInstructor from the writer initialization. This method should be called when
   * removing a Manager from the healthhub-application Take note that no deletion will be made if
   * the manager does not exist
   *
   * @param uniqueMid: unique Manager id to remove
   */
  public void removeManager(int uniqueMid) {
    DBWRITER.removeManager(uniqueMid);
  }

  /**
   * Calls the removeOrganization from the writer initialization. This method should be called when
   * removing an Organization from the healthhub-application Take note that no deletion will be made
   * if the Organization does not exist
   *
   * @param uniqueOrgName: unique Organization Name to remove
   */
  public void removeOrganization(String uniqueOrgName) {
    DBWRITER.removeOrganization(uniqueOrgName);
  }

  /**
   * calls the updateClient from the writer initialization. This should be called when updating
   * client information inside the database.
   *
   * @param uniqueCid: Unique Client Id
   * @param clientUpdatedData: client updated data to use in replacing
   * @throws NullPointerException when value is null ( not empty ), this exception will be thrown
   * @throws JsonObjectException when the clientUpdatedData is empty ( not null ), this exception
   *     will be thrown to prevent users from updating empty data to Client
   * @throws EmptyQueryException when the unique Client Id does not exist within the list of clients
   *     this exception will be thrown
   */
  public void updateClient(int uniqueCid, JSONObject clientUpdatedData)
      throws JsonObjectException, EmptyQueryException, NullPointerException {
    DBWRITER.updateClient(uniqueCid, clientUpdatedData);
  }

  /**
   * calls the updateInstructor from the writer initialization. This should be called when updating
   * instructor information inside the database.
   *
   * @param uniqueIid: Unique id for instructors
   * @param instrUpdatedData: Instructor updated to be use in replacing
   * @throws NullPointerException when value is null ( not empty ), this exception will be thrown
   * @throws JsonObjectException when the instrUpdatedData is empty ( not null ), this exception
   *     will be thrown to prevent users from updating empty data to Instructor
   * @throws EmptyQueryException when the unique Instructor Id does not exist within the list of
   *     instructors this exception will be thrown
   */
  public void updateInstructor(int uniqueIid, JSONObject instrUpdatedData)
      throws JsonObjectException, EmptyQueryException, NullPointerException {
    DBWRITER.updateInstructor(uniqueIid, instrUpdatedData);
  }

  /**
   * calls the updateManager from the writer initialization. This should be called when updating
   * Manager information inside the database.
   *
   * @param uniqueMid: unique manager id to find
   * @param managerUpdatedData: Manager updated data to store
   * @throws NullPointerException when value is null ( not empty ), this exception will be thrown
   * @throws JsonObjectException when the managerUpdatedData is empty ( not null ), this exception
   *     will be thrown to prevent users from updating empty data to Manager
   * @throws EmptyQueryException when the unique Manager Id does not exist within the list of
   *     managers this exception will be thrown
   */
  public void updateManager(int uniqueMid, JSONObject managerUpdatedData)
      throws JsonObjectException, EmptyQueryException, NullPointerException {
    DBWRITER.updateManager(uniqueMid, managerUpdatedData);
  }

  /**
   * calls the updateOrganization from the writer initialization. This should be called when
   * updating Organization information inside the database.
   *
   * @param uniqueOrgName: unique Organization Name to find
   * @param orgUpdatedData: Manager updated data to store
   * @throws NullPointerException when value is null ( not empty ), this exception will be thrown
   * @throws JsonObjectException when the managerUpdatedData is empty ( not null ), this exception
   *     will be thrown to prevent users from updating empty data to Organization
   * @throws EmptyQueryException when the unique Organization Name does not exist within the list of
   *     organization, this exception will be thrown
   */
  public void updateOrganization(String uniqueOrgName, JSONObject orgUpdatedData)
      throws JsonObjectException, EmptyQueryException, NullPointerException {
    DBWRITER.updateOrganization(uniqueOrgName, orgUpdatedData);
  }

  /**
   * calls the readClientData from the reader initialization. This should be called when reading
   * client data of specified unique client id
   *
   * @param uniqueCid: unique client it to read
   * @return JSONObject of the client data.
   * @throws EmptyQueryException when the unique client id does not exist within the list of
   *     clients, this exception is thrown
   */
  public JSONObject readClientData(int uniqueCid) throws EmptyQueryException {
    return DBREADER.readClientData(uniqueCid);
  }

  /**
   * calls the readInstructorData from the reader initialization. This should be called when reading
   * instructor data of specified unique instructor id
   *
   * @param uniqueIid Unique instructor id to read
   * @return JSONObject of the instructor data
   * @throws EmptyQueryException when the unique instructor id does not exist within the list of
   *     instructors, this exception is thrown
   */
  public JSONObject readInstructorData(int uniqueIid) throws EmptyQueryException {
    return DBREADER.readInstructorData(uniqueIid);
  }

  /**
   * calls the readManagerData from the reader initialization. This should be called when reading
   * manager data of specified unique manager id
   *
   * @param uniqueMid Unique manager id to read
   * @return JSONObject of the manager data
   * @throws EmptyQueryException when the unique manager id does not exist within the list of
   *     managers, this exception is thrown
   */
  public JSONObject readManagerData(int uniqueMid) throws EmptyQueryException {
    return DBREADER.readManagerData(uniqueMid);
  }

  /**
   * calls the readOrganization Data from the reader initialization. This should be called when
   * reading organization data of specified unique organization id
   *
   * @param uniqueOrgString Unique organization String used to read
   * @return JSONObject of the organization data
   * @throws EmptyQueryException when the unique string does not exist within the list of
   *     organization, this exception is thrown
   */
  public JSONObject readOrganizationData(String uniqueOrgString) throws EmptyQueryException {
    return DBREADER.readOrganizationData(uniqueOrgString);
  }

  /**
   * Gets all the clients available in the client collection
   *
   * @return returns a JSONArray of all the clients and their data, null if no data
   */
  public JSONArray getAllClients() {
    return DBREADER.getAllClients();
  }

  /**
   * Gets all the instructors available in the instructor collection
   *
   * @return returns a JSONArray of all the instructor and their data, null if no data
   */
  public JSONArray getAllInstructors() {
    return DBREADER.getAllInstructors();
  }

  /**
   * Gets all the managers available in the manager collection
   *
   * @return returns a JSONArray of all the manager and their data, null if no data
   */
  public JSONArray getAllManagers() {
    return DBREADER.getAllManagers();
  }

  /**
   * Gets all the organization available in the organization collection
   *
   * @return returns a JSONArray of all the organization and their data, null if no data
   */
  public JSONArray getAllOrganization() {
    return DBREADER.getAllOrganization();
  }

  /**
   * This method is used to get the unique id from the database
   *
   * @return unique id from the database.
   */
  public int getUniqueId() {
    return DBREADER.getUniqueId();
  }

  /**
   * EXPERIMENTAL: This method can be used to reset the Unique ID into 0, Be careful as it only
   * reset the unique Id counter to 0. To ensure, it will work. I heavily recommend to delete all
   * the client, instructor and manager data
   */
  public void resetUniqueId() {
    DBWRITER.deleteData(0, "CounterCollection");
  }
}
