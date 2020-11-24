package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.json.JSONObject;
import org.bson.Document;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;

public class Writer implements ServerInterface, WriteInterface {

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
  public Writer(String uriString, String dbName, String tableName) {
    Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
    mongoLogger.setLevel(Level.SEVERE); // set logging to only severe conditions
    SERVER = MongoClients.create(uriString);
    mongoDb = SERVER.getDatabase(dbName);
    collectionTable = mongoDb.getCollection(tableName);
  }

  public MongoClient getServerObj() {
    return SERVER;
  }

  public MongoDatabase getDbObject() {
    return mongoDb;
  }

  public MongoCollection<Document> getCollectionTable() {
    return collectionTable;
  }

  public void setDbObject(String dbName) {
    mongoDb = SERVER.getDatabase(dbName);
  }

  /**
   * Within the database, change the collection( or table ) to work on. Note that if the collection
   * table does not exist, this creates a new collection( or table )
   *
   * @param newTable new collection table to create
   */
  public void setCollectionTable(String newTable) {
    collectionTable = mongoDb.getCollection(newTable);
  }

  /**
   * Within the database, change the collection( or table to work on ). Note that the mongo
   * Collection has to exist for this one.
   *
   * @param newMongoCollection Mongo Collection that we need to set
   */
  public void setCollectionTable(MongoCollection<Document> newMongoCollection) {
    collectionTable = newMongoCollection;
  }

  /**
   * This function creates a document from the JsonData inputted. This acts similar to conversion
   * from JSONObject to Document type Enables the database to parse the JSONObject into parseable
   * Document
   *
   * @param JsonData: JSONObject data to convert
   * @param newDocument: Document object to append to
   * @return document version of the JSonData JSONObject
   */
  private Document createDocumentData(JSONObject JsonData, Document newDocument) {

    for (String eachKey : JsonData.keySet()) {
      newDocument.put(eachKey, JsonData.get(eachKey));
    }

    return newDocument;
  }

  /**
   * Creates data based on the unique id within the specified collection It takes a JSONObject value
   * which it uses to create data
   *
   * @param uniqueId unique id within a collection
   * @param collectionInsert collection to create/insert new data
   * @param dataValue JSONObject value that will be stored within the document
   * @throws NullPointerException when dataValue is null, it will throw this exception
   */
  private void createData(int uniqueId, String collectionInsert, JSONObject dataValue)
      throws NullPointerException {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable(collectionInsert);
    Document newDocument = new Document("_id", uniqueId);
    Document newData = createDocumentData(dataValue, newDocument);
    collectionTable.insertOne(newData);
    setCollectionTable(previousCollection);
  }

  /**
   * Updates data based on the unique id within the specified collection. It takes a JSONObject
   * value which it writes as an updated data for the specified unique id It will throw an exception
   * if unique id does not exist
   *
   * @param uniqueId unique id to update
   * @param collectionUpdate collection where the unique id is located
   * @param updatedData replace the old data with this updated data within the specified unique id
   *     data
   * @throws NullPointerException when updatedData is null ( not empty ), this exception will be
   *     thrown
   * @throws JsonObjectException when the updatedData is empty ( not null ), this exception will be
   *     thrown to prevent users from updating empty data to Manager
   * @throws EmptyQueryException when the unique Id does not exist within the list of instructors
   *     this exception will be thrown
   */
  private void updateData(int uniqueId, String collectionUpdate, JSONObject updatedData)
      throws JsonObjectException, EmptyQueryException, NullPointerException {
    if (updatedData.isEmpty()) {
      throw new JsonObjectException();
    }
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable(collectionUpdate);

    Document updatedDocument = new Document();
    Document updatedDoc = createDocumentData(updatedData, updatedDocument);

    try {
      Document checkResult = collectionTable.findOneAndReplace(eq("_id", uniqueId), updatedDoc);
      assert checkResult != null;
    } catch (AssertionError ae) {
      throw new EmptyQueryException();
    } finally {
      setCollectionTable(previousCollection);
    }
  }

  /**
   * deletes everything ( including the unique id ) of the the specified unique id within a
   * collection. If the unique id does not exist within the database, no update will be performed on
   * the database
   *
   * @param uniqueId unique id to delete
   * @param collectionDelete collection to where the unique id exist
   */
  private void deleteData(int uniqueId, String collectionDelete) {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable(collectionDelete);
    collectionTable.deleteOne(eq("_id", uniqueId));
    setCollectionTable(previousCollection);
  }

  /**
   * Create a client data within the client collection
   *
   * @param uniqueCid unique client id
   * @param value JSON data that refers to the specific client id
   * @throws NullPointerException when value is null ( not empty ), this exception will be thrown
   */
  public void createClient(int uniqueCid, JSONObject value) throws NullPointerException {
    createData(uniqueCid, "ClientCollection", value);
  }

  /**
   * Create an instructor data within the Instructor collection
   *
   * @param uniqueIid unique instructor id
   * @param value JSON data that refers to the specific Instructor id
   * @throws NullPointerException when value is null ( not empty ), this exception will be thrown
   */
  public void createInstructor(int uniqueIid, JSONObject value) throws NullPointerException {

    createData(uniqueIid, "InstructorCollection", value);
  }

  /**
   * Create an Manager data within the Manager collection
   *
   * @param uniqueMid unique manager id
   * @param value JSON data that refers to the specific Manager id
   * @throws NullPointerException when value is null ( not empty ), this exception will be thrown
   */
  public void createManager(int uniqueMid, JSONObject value) throws NullPointerException {

    createData(uniqueMid, "ManagerCollection", value);
  }

  /**
   * Create an Organization data within the Organization collection
   *
   * @param uniqueOrgName unique organization name to be added within the database
   * @param value JSON data that refers to the specific Organization
   * @throws NullPointerException when value is null ( not empty ), this exception will be thrown
   */
  public void createOrganization(String uniqueOrgName, JSONObject value) throws NullPointerException {

    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("OrganizationCollection");
    Document newDocument = new Document("_id", uniqueOrgName);
    Document newData = createDocumentData(value, newDocument);
    collectionTable.insertOne(newData);
    setCollectionTable(previousCollection);
  }

  /**
   * Update Client Information inside the Client Collection Does not allow updates for non-existing
   * clients within the database
   *
   * @param uniqueCid: Unique Client Id
   * @param updatedData: Updated data to use in replacing
   * @throws NullPointerException when value is null ( not empty ), this exception will be thrown
   * @throws JsonObjectException when the updatedData is empty ( not null ), this exception will be
   *     thrown to prevent users from updating empty data to Clients
   * @throws EmptyQueryException when the unique Client Id does not exist within the list of clients
   *     this exception will be thrown
   */
  public void updateClient(int uniqueCid, JSONObject updatedData)
      throws NullPointerException, JsonObjectException, EmptyQueryException {

    updateData(uniqueCid, "ClientCollection", updatedData);
  }

  /**
   * Update Instructor Information inside the Instructor Collection Does not allow updates for
   * non-existing instructors within the database
   *
   * @param uniqueIid: Unique id for instructors
   * @param updatedData: New updated data
   * @throws NullPointerException when value is null ( not empty ), this exception will be thrown
   * @throws JsonObjectException when the updatedData is empty ( not null ), this exception will be
   *     thrown to prevent users from updating empty data to Instructor
   * @throws EmptyQueryException when the unique Instructor Id does not exist within the list of
   *     instructors this exception will be thrown
   */
  public void updateInstructor(int uniqueIid, JSONObject updatedData)
      throws NullPointerException, JsonObjectException, EmptyQueryException {

    updateData(uniqueIid, "InstructorCollection", updatedData);
  }

  /**
   * Update Manager data inside the Manager Collection Does not allow updates for non-existing
   * instructors within the database
   *
   * @param uniqueMid: unique manager id to find
   * @param updatedData: updated data to store
   * @throws NullPointerException when value is null ( not empty ), this exception will be thrown
   * @throws JsonObjectException when the updatedData is empty ( not null ), this exception will be
   *     thrown to prevent users from updating empty data to Manager
   * @throws EmptyQueryException when the unique Manager Id does not exist within the list of
   *     managers this exception will be thrown
   */
  public void updateManager(int uniqueMid, JSONObject updatedData)
      throws NullPointerException, JsonObjectException, EmptyQueryException {
    updateData(uniqueMid, "ManagerCollection", updatedData);
  }

  /**
   * Removes Client Information inside the Client Collection If the client id does not exist within
   * the database, no update will be performed on the database
   *
   * @param uniqueCid: Unique Client Id to remove
   */
  public void removeClient(int uniqueCid) {
    deleteData(uniqueCid, "ClientCollection");
  }

  /**
   * Removes Instructor Information inside the Instructor Collection If the instructor id does not
   * exist within the database, no update will be performed on the database
   *
   * @param uniqueIid: unique instructor id to remove
   */
  public void removeInstructor(int uniqueIid) {
    deleteData(uniqueIid, "InstructorCollection");
  }

  /**
   * Removes Manager Information inside the Manager Collection If the manager id does not exist
   * within the database, no update will be performed on the database
   *
   * @param uniqueMid: unique manager id to remove
   */
  public void removeManager(int uniqueMid) {
    deleteData(uniqueMid, "ManagerCollection");
  }
}
