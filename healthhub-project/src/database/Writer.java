package database;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.json.JSONObject;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class Writer {

  private final MongoClient SERVER; // server initiation
  private MongoDatabase mongoDb; // database initiation
  private MongoCollection<Document>
      collectionTable; // collection initiation ( i.e Table to initiate )

  /**
   * Construct a writer connection to the mongoDb local database
   *
   * @param conString chosen string connection to mongoDb
   * @param conPort chosen port connection to mongoDb
   * @param dbName chosen database connection within the server
   * @param tableName chosen collection(or table) to write
   * @deprecated This is used for local mongoDB. Has not been maintained
   */
  Writer(String conString, int conPort, String dbName, String tableName) {
    SERVER = MongoClients.create(conString);
    mongoDb = SERVER.getDatabase(dbName);
    collectionTable = mongoDb.getCollection(tableName);
  }

  /**
   * Constructs the writer class by instantiating connection to a uriString.
   *
   * @param uriString: uriString to connect to
   * @param dbName: Name of the database to connect to
   * @param tableName: Name of the collection/table to connect to
   */
  Writer(String uriString, String dbName, String tableName) {

    SERVER = MongoClients.create(uriString);
    mongoDb = SERVER.getDatabase(dbName);
    collectionTable = mongoDb.getCollection(tableName);
  }

  protected MongoClient getServerObj() {
    return SERVER;
  }

  protected MongoDatabase getDbObject() {
    return mongoDb;
  }

  protected MongoCollection<Document> getCollectionTable() {
    return collectionTable;
  }

  private void setDbObject(String dbName) {
    mongoDb = SERVER.getDatabase(dbName);
  }

  /**
   * Within the database, change the collection( or table ) to work on. Note that if the collection
   * table does not exist, this creates a new collection( or table )
   *
   * @param newTable new collection table to create
   */
  private void setCollectionTable(String newTable) {
    collectionTable = mongoDb.getCollection(newTable);
  }

  /**
   * Within the database, change the collection( or table to work on ). Note that the mongo
   * Collection has to exist for this one.
   *
   * @param newMongoCollection Mongo Collection that we need to set
   */
  private void setCollectionTable(MongoCollection<Document> newMongoCollection) {
    collectionTable = newMongoCollection;
  }

  /**
   * Create a client data within the client collection
   *
   * @param uniqueCid unique client id
   * @param value JSON data that refers to the specific client id
   */
  protected void createClient(int uniqueCid, JSONObject value) {

    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("ClientCollection");
    Document newClient = new Document("_id", uniqueCid);
    newClient.append("data", BasicDBObject.parse(value.toString()));
    collectionTable.insertOne(newClient);
    setCollectionTable(previousCollection);
  }

  /**
   * Create an instructor data within the Instructor collection
   *
   * @param uniqueIid unique instructor id
   * @param value JSON data that refers to the specific Instructor id
   */
  protected void createInstructor(int uniqueIid, JSONObject value) {

    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("InstructorCollection");
    Document newInstructor = new Document("_id", uniqueIid);
    newInstructor.append("data", BasicDBObject.parse(value.toString()));
    collectionTable.insertOne(newInstructor);
    setCollectionTable(previousCollection);
  }

  /**
   * Create an Manager data within the Manager collection
   *
   * @param uniqueMid unique manager id
   * @param value JSON data that refers to the specific Manager id
   */
  protected void createManager(int uniqueMid, JSONObject value) {

    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("ManagerCollection");
    Document newManager = new Document("_id", uniqueMid);
    newManager.append("data", BasicDBObject.parse(value.toString()));
    collectionTable.insertOne(newManager);
    setCollectionTable(previousCollection);
  }

  /**
   * Update Client Information inside the Client Collection
   *
   * @param uniqueCid: Unique Client Id
   * @param updatedData: Updated data to use in replacing
   */
  protected void updateClient(int uniqueCid, JSONObject updatedData) {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("ClientCollection");
    Document findData = new Document("_id", uniqueCid);
    collectionTable.findOneAndReplace(
        findData, new Document("data", BasicDBObject.parse(updatedData.toString())));
    setCollectionTable(previousCollection);
  }

  /**
   * Update Instructor Information inside the Instructor Collection
   *
   * @param uniqueIid: Unique id for instructors
   * @param updatedData: New updated data
   */
  protected void updateInstructor(int uniqueIid, JSONObject updatedData) {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("InstructorCollection");
    Document findData = new Document("_id", uniqueIid);
    collectionTable.findOneAndReplace(
        findData, new Document("data", BasicDBObject.parse(updatedData.toString())));
    setCollectionTable(previousCollection);
  }

  /**
   * Update Manager data inside the Manager Collection
   *
   * @param uniqueMid: unique manager id to find
   * @param updatedData: updated data to store
   */
  protected void updateManager(int uniqueMid, JSONObject updatedData) {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("ManagerCollection");
    Document findData = new Document("_id", uniqueMid);
    collectionTable.findOneAndReplace(
        findData, new Document("data", BasicDBObject.parse(updatedData.toString())));
    setCollectionTable(previousCollection);
  }

  /**
   * Removes Client Information inside the Client Collection
   *
   * @param uniqueCid: Unique Client Id to remove
   */
  protected void removeClient(int uniqueCid) {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("ClientCollection");
    collectionTable.deleteOne(eq("_id", uniqueCid));
    setCollectionTable(previousCollection);
  }

  /**
   * Removes Instructor Information inside the Instructor Collection
   *
   * @param uniqueIid: unique instructor id to remove
   */
  protected void removeInstructor(int uniqueIid) {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("InstructorCollection");
    collectionTable.deleteOne(eq("_id", uniqueIid));
    setCollectionTable(previousCollection);
  }

  /**
   * Removes Manager Information inside the Manager Collection
   *
   * @param uniqueMid: unique manager id to remove
   */
  protected void removeManager(int uniqueMid) {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("ManagerCollection");
    collectionTable.deleteOne(eq("_id", uniqueMid));
    setCollectionTable(previousCollection);
  }

  public static void main(String[] args) {

    // do not change this. This is the cloud connection. Not Secure as username and password is
    // seen. Need Authentication
    // database chosen. See me if you interested on creating your own database
    String userName = "test-user";
    String passWord = "healthhub1";
    String dbName = "Dev-General-Database";
    String tableName = "testCollection";

    String uriString =
        "mongodb+srv://"
            + userName
            + ":"
            + passWord
            + "@healthhub-cluster.7y7j0.mongodb.net/"
            + dbName
            + "?retryWrites=true&w=majority";

    // Instantiation of all JSON objects to play around
    Writer testWriter = new Writer(uriString, dbName, tableName);
    JSONObject newClient = new JSONObject();
    newClient.append("Name", "Shrimp");
    JSONObject newInstructor = new JSONObject();
    newInstructor.append("Name", "GawrGura");
    JSONObject newManager = new JSONObject();
    newManager.append("Name", "Yagoo");

    // This remove test removes specified id within the cloud database
    testWriter.removeClient(1);
    System.out.println("Succesfully deleted Client");
    testWriter.removeInstructor(1);
    System.out.println("Succesfully deleted Instructor");
    testWriter.removeManager(1);
    System.out.println("Succesfully deleted Manager");

    // This creation test is meant for you to test whether you can write into the Cloud. Cid, Iid
    // and Mid has to be unique.
    testWriter.createClient(1, newClient);
    System.out.println("Succesfully created Client");
    testWriter.createInstructor(1, newInstructor);
    System.out.println("Succesfully created Instructor");
    testWriter.createManager(1, newManager);
    System.out.println("Succesfully created Manager");

    // This update test is meant to test whether updating will work within the cloud
    newClient.append("Name", "Kiara");
    newInstructor.append("Name", "Coco");
    newManager.append("Name", "Coco");

    testWriter.updateClient(1, newClient);
    System.out.println("Succesfully updated Client");
    testWriter.updateInstructor(1, newInstructor);
    System.out.println("Succesfully updated Instructor");
    testWriter.updateManager(1, newManager);
    System.out.println("Succesfully updated Manager");
  }
}
