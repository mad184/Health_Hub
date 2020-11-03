package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.json.JSONArray;
import org.bson.Document;
import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;

public class Reader implements ReadWriteInterface {

  private final MongoClient SERVER; // server initiation
  private MongoDatabase mongoDb; // database initiation
  private MongoCollection<Document>
      collectionTable; // collection initiation ( i.e Table to initiate )

  /**
   * Constructs the Reader class by instantiating connection to a uriString.
   *
   * @param uriString: uriString to connect to
   * @param dbName: Name of the database to connect to
   * @param tableName: Name of the collection/table to connect to
   */
  public Reader(String uriString, String dbName, String tableName) {
    Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
    mongoLogger.setLevel(Level.SEVERE); // set logging to only severe conditions
    SERVER = MongoClients.create(uriString);
    mongoDb = SERVER.getDatabase(dbName);
    collectionTable = mongoDb.getCollection(tableName);
  }

  @Override
  public MongoClient getServerObj() {
    return SERVER;
  }

  @Override
  public MongoDatabase getDbObject() {
    return mongoDb;
  }

  @Override
  public MongoCollection<Document> getCollectionTable() {
    return collectionTable;
  }

  @Override
  public void setDbObject(String dbName) {
    mongoDb = SERVER.getDatabase(dbName);
  }

  @Override
  public void setCollectionTable(String newTable) {
    collectionTable = mongoDb.getCollection(newTable);
  }

  @Override
  public void setCollectionTable(MongoCollection<Document> newMongoCollection) {
    collectionTable = newMongoCollection;
  }

  /**
   * This function creates a JSONObject from the Document inputted. This acts similar to conversion
   * from Document to JSONObject type. This should enable developers to parse JSONObject it returned
   *
   * @param newJson: JSONObject to append to
   * @param documentData: document object used to convert to JSONObject
   * @return JSONObject version of the documentData
   */
  private JSONObject createJsonData(JSONObject newJson, Document documentData) {

    for (String eachKey : documentData.keySet()) {
      newJson.put(eachKey, documentData.get(eachKey));
    }

    return newJson;
  }

  /**
   * Looks for the unique Id within the specified Collection inside the database. The iteration will
   * only return the first result as ID are suppose to be unique within Collections
   *
   * @param uniqueId unique id to search/read
   * @param readCollection collection to find the unique id data
   * @return Document object that corresponds to the specified unique id
   * @throws EmptyQueryException when the unique id does not exist within the collection, this
   *     exception is thrown
   */
  private Document readData(int uniqueId, String readCollection) throws EmptyQueryException {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable(readCollection);
    try {
      Document readResult = collectionTable.find(eq("_id", uniqueId)).first();
      assert readResult != null;
      return readResult;
    } catch (AssertionError eqe) {
      throw new EmptyQueryException();
    } finally {
      setCollectionTable(previousCollection);
    }
  }

  /**
   * Retrieve all data within the collection
   *
   * @param collectionRetrieve: collection where we retrieve all data
   * @return JSONArray of JSONObjects within the collection
   */
  private JSONArray retrieveAllData(String collectionRetrieve) {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable(collectionRetrieve);

    JSONArray allDataArray = new JSONArray();
    FindIterable<Document> allCollectionData = collectionTable.find();

    for (Document eachData : allCollectionData) {
      allDataArray.put(eachData);
    }
    setCollectionTable(previousCollection);

    if (allDataArray.isEmpty()) {
      return null;
    } else {
      return allDataArray;
    }
  }

  /**
   * Reads the client data for the specified unique client id
   *
   * @param uniqueCid: unique client id to read
   * @return JSONObject of the client data.
   * @throws EmptyQueryException when the user attempts to read data from nonexisting client, this
   *     exception is thrown
   */
  public JSONObject readClientData(int uniqueCid) throws EmptyQueryException {

    return createJsonData(new JSONObject(), readData(uniqueCid, "ClientCollection"));
  }

  /**
   * Reads the instructor data for the specified unique instructor id
   *
   * @param uniqueIid : unique instructor id to read
   * @return JSONObject of the Instructor data.
   */
  public JSONObject readInstructorData(int uniqueIid) throws EmptyQueryException {
    return createJsonData(new JSONObject(), readData(uniqueIid, "InstructorCollection"));
  }

  /**
   * Reads the Manager data for the specified unique manager id
   *
   * @param uniqueMid: unique manager id to read
   * @return JSONObject of the Manager data.
   */
  public JSONObject readManagerData(int uniqueMid) throws EmptyQueryException {
    return createJsonData(new JSONObject(), readData(uniqueMid, "ManagerCollection"));
  }

  /**
   * Gets all the clients available in the client table
   *
   * @return returns a JSONArray of all the clients and their data, null if no data
   */
  public JSONArray getAllClients() {
    return retrieveAllData("ClientCollection");
  }

  /**
   * Gets all the instructors available in the instructor table
   *
   * @return returns a JSONarray of all the instructors and their data, null if no data
   */
  public JSONArray getAllInstructors() {
    return retrieveAllData("InstructorCollection");
  }

  /**
   * Gets all the Managers available in the manager table
   *
   * @return returns a JSONarray of all the managers and their data, null if no data
   */
  public JSONArray getAllManagers() {
    return retrieveAllData("ManagerCollection");
  }
}
