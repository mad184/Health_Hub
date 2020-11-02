package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.json.JSONArray;
import org.bson.Document;

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
   * Reads the client data for the specified unique client id This function iterates over the
   * Clients tables but only take the first result It is expected that every client will have unique
   * id so only first result is outputted
   *
   * @param uniqueCid: unique client id to read
   * @return returns JSON string of the client data, If no data found, returns null
   */
  public String readClientData(int uniqueCid) {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("ClientCollection");

    try {
      Document clientData = collectionTable.find(eq("_id", uniqueCid)).first();
      assert clientData != null;
      return clientData.toJson();
    } catch (AssertionError npe) {
      return null;
    } finally {
      setCollectionTable(previousCollection);
    }
  }

  /**
   * Reads the instructor data for the specified unique instructor id This function iterates over
   * the Instructor tables but only take the first result It is expected that every instructor will
   * have unique id so only first result is outputted
   *
   * @param uniqueIid: unique instructor id to read
   * @return returns JSON string of the instructor data, If no data found, returns null
   */
  public String readInstructorData(int uniqueIid) {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("InstructorCollection");

    try {
      Document instructorData = collectionTable.find(eq("_id", uniqueIid)).first();
      assert instructorData != null;
      return instructorData.toJson();
    } catch (AssertionError npe) {
      return null;
    } finally {
      setCollectionTable(previousCollection);
    }
  }

  /**
   * Reads the Manager data for the specified unique manager id This function iterates over the
   * Manager tables but only take the first result It is expected that every manager will have
   * unique id so only first result is outputted
   *
   * @param uniqueMid: unique manager id to read
   * @return returns JSON string of the manager data. If no data found, returns null
   */
  public String readManagerData(int uniqueMid) {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("ManagerCollection");

    try {
      Document managerData = collectionTable.find(eq("_id", uniqueMid)).first();
      assert managerData != null;
      return managerData.toJson();

    } catch (AssertionError npe) {
      return null;
    } finally {
      setCollectionTable(previousCollection);
    }
  }

  /**
   * Gets all the clients available in the client table
   *
   * @return returns a JSONArray of all the clients and their data, null if no data
   */
  public JSONArray getAllClients() {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("ClientCollection");

    JSONArray allClientArr = new JSONArray();
    FindIterable<Document> allClientDoc = collectionTable.find();

    for (Document eachClient : allClientDoc) {
      allClientArr.put(eachClient);
    }
    setCollectionTable(previousCollection);

    if (allClientArr.isEmpty()) {
      return null;
    } else {
      return allClientArr;
    }
  }

  /**
   * Gets all the instructors available in the instructor table
   *
   * @return returns a JSONarray of all the instructors and their data, null if no data
   */
  public JSONArray getAllInstructors() {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("InstructorCollection");

    JSONArray allInstructorArr = new JSONArray();
    FindIterable<Document> allInstructorDoc = collectionTable.find();

    for (Document eachInstructor : allInstructorDoc) {
      allInstructorArr.put(eachInstructor);
    }
    setCollectionTable(previousCollection);

    if (allInstructorArr.isEmpty()) {
      return null;
    } else {
      return allInstructorArr;
    }
  }

  /**
   * Gets all the Managers available in the manager table
   *
   * @return returns a JSONarray of all the managers and their data, null if no data
   */
  public JSONArray getAllManagers() {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("ManagerCollection");

    JSONArray allManagerArr = new JSONArray();
    FindIterable<Document> allManagerDoc = collectionTable.find();

    for (Document eachManager : allManagerDoc) {
      allManagerArr.put(eachManager);
    }
    setCollectionTable(previousCollection);

    if (allManagerArr.isEmpty()) {
      return null;
    } else {
      return allManagerArr;
    }
  }
}
