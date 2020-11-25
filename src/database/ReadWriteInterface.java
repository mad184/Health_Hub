package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

interface ReadWriteInterface {

  MongoClient getServerObj();

  MongoDatabase getDbObject();

  MongoCollection<Document> getCollectionTable();

  void setDbObject(String dbName);

  void setCollectionTable(String newTable);

  void setCollectionTable(MongoCollection<Document> newMongoCollection);
}
