package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.json.JSONArray;
import org.bson.Document;

import java.util.function.Consumer;
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

  public String readClientData(int uniqueCid) {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("ClientCollection");

    try {
      Document clientData = collectionTable.find(eq("_id", uniqueCid)).first();
      assert clientData != null;
      return clientData.toJson();
    } catch (NullPointerException npe) {
      return null;
    } finally {
      setCollectionTable(previousCollection);
    }
  }

  public String readInstructorData(int uniqueIid) {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("InstructorCollection");

    try {
      Document instructorData = collectionTable.find(eq("_id", uniqueIid)).first();
      assert instructorData != null;
      return instructorData.toJson();
    } catch (NullPointerException npe) {
      return null;
    } finally {
      setCollectionTable(previousCollection);
    }
  }

  public String readManagerData(int uniqueMid) {
    MongoCollection<Document> previousCollection = getCollectionTable();
    setCollectionTable("ManagerCollection");

    try {
      Document managerData = collectionTable.find(eq("_id", uniqueMid)).first();
      assert managerData != null;
      return managerData.toJson();

    } catch (NullPointerException npe) {
      return null;
    } finally {
      setCollectionTable(previousCollection);
    }
  }

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

  public static void main(String[] args) {
    final String realUserName = "test-user";
    final String realPassWord = "healthhub1";
    final String dbName = "Dev-General-Database";
    final String tableName = "testCollection";

    String realUriString =
        "mongodb+srv://"
            + realUserName
            + ":"
            + realPassWord
            + "@healthhub-cluster.7y7j0.mongodb.net/"
            + dbName
            + "?retryWrites=true&w=majority";

    Reader newReader = new Reader(realUriString, dbName, tableName);
    // System.out.println(newReader.readClientData(1));
    System.out.println(newReader.getAllClients().toString());
  }
}
