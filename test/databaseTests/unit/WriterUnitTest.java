package databaseTests.unit;

import com.mongodb.MongoSecurityException;
import com.mongodb.MongoWriteException;
import database.JsonObjectException;
import database.EmptyQueryException;
import database.Writer;
import org.bson.codecs.configuration.CodecConfigurationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(OrderAnnotation.class)
public class WriterUnitTest {

  // Creadential related fields
  private static final String realUserName = "production_user";
  private static final String realPassWord = "healthhub1";
  private static final String dbName = "Test-Production-Database";
  private static final String tableName = "testCollection";
  private static final String realUriString =
      "mongodb+srv://"
          + realUserName
          + ":"
          + realPassWord
          + "@healthhub-cluster.7y7j0.mongodb.net/"
          + dbName
          + "?retryWrites=true&w=majority";

  // Writer initialization
  static Writer realCon = new Writer(realUriString, dbName, tableName);

  // preDelete is necessary to make sure that no data exists initially in db
  @BeforeAll
  static void preDelete() {
    Assertions.assertDoesNotThrow(
        () -> {
          realCon.removeClient(1);
          realCon.removeClient(2);
          realCon.removeClient(3);
          realCon.removeClient(4);
          realCon.removeClient(5);
          realCon.removeClient(6);
          realCon.removeClient(7);

          realCon.removeManager(1);
          realCon.removeManager(2);
          realCon.removeManager(3);
          realCon.removeManager(4);
          realCon.removeManager(5);
          realCon.removeManager(6);
          realCon.removeManager(7);

          realCon.removeInstructor(1);
          realCon.removeInstructor(2);
          realCon.removeInstructor(3);
          realCon.removeInstructor(4);
          realCon.removeInstructor(5);
          realCon.removeInstructor(6);
          realCon.removeInstructor(7);

          realCon.removeOrganization("HololiveJP");
          realCon.removeOrganization("HololiveEN");
          realCon.removeOrganization("HololiveID");
          realCon.removeOrganization("HololiveCN");
        });
  }

  // Tests fake and real credentials for writer
  @Test
  @Order(1)
  void testCredentials() {

    String fakeUserName = "fakeUdesu";
    String fakePassWord = "fakePdesu";
    String fakeUriString =
        "mongodb+srv://"
            + fakeUserName
            + ":"
            + fakePassWord
            + "@healthhub-cluster.7y7j0.mongodb.net/"
            + dbName
            + "?retryWrites=true&w=majority";

    Writer fakeTest = new Writer(fakeUriString, dbName, tableName);

    // Test for fake credentials.
    Assertions.assertThrows(MongoSecurityException.class, () -> fakeTest.removeClient(1));

    // Test for real credentials.
    Assertions.assertDoesNotThrow(() -> realCon.removeClient(1));
  }

  // Tests deletion of client, manager and instructor
  @RepeatedTest(3)
  @Order(2)
  void testDeletion() {

    // Test deletion of data
    Assertions.assertDoesNotThrow(
        () -> {
          realCon.removeClient(1);
          realCon.removeManager(1);
          realCon.removeInstructor(1);
          realCon.removeOrganization("HololiveJP");
        });
  }

  @Test
  @Order(3)
  void testCreationNull() {

    // Test Creation of data with nulls
    Assertions.assertThrows(
        NullPointerException.class,
        () -> {
          realCon.createClient(1, null);
          realCon.createManager(1, null);
          realCon.createInstructor(1, null);
          realCon.createOrganization("HololiveJP", null);
        });
  }

  @Test
  @Order(4)
  void testStrStrCreate() {

    // JSONObject with String key - String value
    JSONObject testStrStr = new JSONObject();
    testStrStr.append("Data", "Client/Manager/Instructor Data");
    testStrStr.append("OrganizationData", "Hololive Organization Data");

    // Test Creation of data
    Assertions.assertDoesNotThrow(
        () -> {
          realCon.createClient(1, testStrStr);
          realCon.createManager(1, testStrStr);
          realCon.createInstructor(1, testStrStr);
          realCon.createOrganization("HololiveJP", testStrStr);
        });
  }

  @Test
  @Order(5)
  void testCreationDuplicate() {
    JSONObject test_add = new JSONObject();
    test_add.append("Data", "Client/Manager/Instructor Data");
    test_add.append("OrganizationData", "Hololive Organization Data");

    // Test Creation of duplicated data
    Assertions.assertThrows(MongoWriteException.class, () -> realCon.createClient(1, test_add));
    Assertions.assertThrows(MongoWriteException.class, () -> realCon.createManager(1, test_add));
    Assertions.assertThrows(MongoWriteException.class, () -> realCon.createInstructor(1, test_add));
    Assertions.assertThrows(
        MongoWriteException.class, () -> realCon.createOrganization("HololiveJP", test_add));
  }

  @Test
  @Order(6)
  void TestStrJArrCreate() {

    // JSONObject with String key - JSONArray value
    JSONArray jArray = new JSONArray();
    jArray.put(5);
    jArray.put("");
    jArray.put(true);
    jArray.put(false);
    JSONObject testStrJarr = new JSONObject();
    testStrJarr.put("value", jArray);

    // System.out.println(testStrJarr.toString());

    Assertions.assertDoesNotThrow(
        () -> {
          realCon.createClient(2, testStrJarr);
          realCon.createInstructor(2, testStrJarr);
          realCon.createManager(2, testStrJarr);
          realCon.createOrganization("HololiveCN", testStrJarr);
        });
  }

  @Test
  @Order(7)
  void TestStrJArrNullCreate() {

    // JSONObject with String key - JSONArray JSONObject.Null value
    JSONArray jNullArray = new JSONArray();
    jNullArray.put(JSONObject.NULL); // Any form of nulls, the mongodb cant accept it
    JSONObject testStrJArrNull = new JSONObject();
    testStrJArrNull.put("value", jNullArray);

    // System.out.println(testStrJarr.toString());

    Assertions.assertThrows(
        CodecConfigurationException.class,
        () -> {
          realCon.createClient(3, testStrJArrNull);
          realCon.createInstructor(3, testStrJArrNull);
          realCon.createManager(3, testStrJArrNull);
          realCon.createOrganization("HololiveCN", testStrJArrNull);
        });
  }

  @Test
  @Order(8)
  void TestStrBoolCreate() {

    // JSONObject with String key - Bool value
    JSONObject testStrBool = new JSONObject();
    testStrBool.put("trueValue", true);
    testStrBool.put("falseValue", false);

    // System.out.println(testStrBool.toString());

    Assertions.assertDoesNotThrow(
        () -> {
          realCon.createClient(4, testStrBool);
          realCon.createInstructor(4, testStrBool);
          realCon.createManager(4, testStrBool);
          realCon.createOrganization("HololiveID", testStrBool);
        });
  }

  @Test
  @Order(9)
  void TestStrNullCreate() {

    /*
     * DESIGN ISSUE: JSON does accept null values however the main system (i.e mongoDB's library)
     * cannot insert the value when there is a null value within the JSONObject or even array
     */

    // JSONObject with String key - JSONObject.Null value
    JSONObject testStrBool = new JSONObject();
    testStrBool.put("NullValue", JSONObject.NULL);

    // System.out.println(testStrBool.toString());

    Assertions.assertThrows(
        CodecConfigurationException.class,
        () -> {
          realCon.createClient(5, testStrBool);
          realCon.createInstructor(5, testStrBool);
          realCon.createManager(5, testStrBool);
          realCon.createOrganization("HololiveEN", testStrBool);
        });
  }

  @Test
  @Order(10)
  void TestStrArrayCreate() {

    /*
     * DESIGN ISSUE: JSON does accept arrays values however the main system (i.e mongoDB's library)
     * cannot insert the value when there is a null value within the JSONObject or even array
     */

    // JSONObject with String key - Array value
    JSONObject testJavaArray = new JSONObject();
    String[] javaArray = {"String 1"};
    testJavaArray.put("JavaArray", javaArray);
    // System.out.println(testJavaArray.toString());

    Assertions.assertThrows(
        CodecConfigurationException.class,
        () -> {
          realCon.createClient(6, testJavaArray);
          realCon.createInstructor(6, testJavaArray);
          realCon.createManager(6, testJavaArray);
          realCon.createOrganization("HololiveEN", testJavaArray);
        });
  }

  @Test
  @Order(11)
  void TestStrListCreate() {

    /*
     * DESIGN ISSUE: JSON does accept arrays values however the main system (i.e mongoDB's library)
     * cannot insert the value when there is a null value within the JSONObject or even array
     */

    // JSONObject with String key - List<> value
    JSONObject testStrList = new JSONObject();
    List<Object> testList = new ArrayList<>();

    testList.add(5);
    testList.add("");
    testList.add(null);
    testList.add("String Value");

    testStrList.put("JavaArray", testList);
    // System.out.println(testStrList.toString());

    Assertions.assertThrows(
        CodecConfigurationException.class,
        () -> {
          realCon.createClient(7, testStrList);
          realCon.createInstructor(7, testStrList);
          realCon.createManager(7, testStrList);
          realCon.createOrganization("HololiveEN", testStrList);
        });
  }

  @Test
  @Order(12)
  void testStrStrUpdate() {

    // JSONObject with String key - String value
    JSONObject testStrStr = new JSONObject();
    testStrStr.append("UpdatedData", "Updated Client/Manager/Instructor Data");

    // Test Update of data
    Assertions.assertDoesNotThrow(
        () -> {
          realCon.updateClient(1, testStrStr);
          realCon.updateManager(1, testStrStr);
          realCon.updateInstructor(1, testStrStr);
          realCon.updateOrganization("HololiveJP", testStrStr);
        });
  }

  @Test
  @Order(13)
  void TestStrJArrUpdate() {

    // JSONObject with String key - JSONArray value
    JSONArray jArray = new JSONArray();
    jArray.put(6);
    jArray.put("");
    jArray.put(false);
    jArray.put(false);
    JSONObject testStrJarr = new JSONObject();
    testStrJarr.put("value", jArray);

    // System.out.println(testStrJarr.toString());

    Assertions.assertDoesNotThrow(
        () -> {
          realCon.updateClient(2, testStrJarr);
          realCon.updateInstructor(2, testStrJarr);
          realCon.updateManager(2, testStrJarr);
          realCon.updateOrganization("HololiveCN", testStrJarr);
        });
  }

  @Test
  @Order(14)
  void TestStrJArrNullUpdate() {

    // JSONObject with String key - JSONArray JSONObject.Null value
    JSONArray jNullArray = new JSONArray();
    jNullArray.put(JSONObject.NULL); // Any form of nulls, the mongodb cant accept it
    JSONObject testStrJArrNull = new JSONObject();
    testStrJArrNull.put("value", jNullArray);

    // System.out.println(testStrJarr.toString());

    Assertions.assertThrows(
        CodecConfigurationException.class,
        () -> {
          realCon.updateClient(3, testStrJArrNull);
          realCon.updateInstructor(3, testStrJArrNull);
          realCon.updateManager(3, testStrJArrNull);
          realCon.updateOrganization("HololiveID", testStrJArrNull);
        });
  }

  @Test
  @Order(14)
  void TestStrBoolUpdate() {

    // JSONObject with String key - Bool value
    JSONObject testStrBool = new JSONObject();
    testStrBool.put("trueValue", false);
    testStrBool.put("falseValue", true);

    // System.out.println(testStrBool.toString());

    Assertions.assertDoesNotThrow(
        () -> {
          realCon.updateClient(4, testStrBool);
          realCon.updateInstructor(4, testStrBool);
          realCon.updateManager(4, testStrBool);
          realCon.updateOrganization("HololiveID", testStrBool);
        });
  }

  @Test
  @Order(15)
  void testUpdateNull() {

    // Test of updating a client with null updated data
    Assertions.assertThrows(
        NullPointerException.class,
        () -> {
          realCon.updateClient(1, null);
          realCon.updateManager(1, null);
          realCon.updateInstructor(1, null);
          realCon.updateOrganization("HololiveJP", null);
        });
  }

  @Test
  @Order(16)
  void testUpdateEmpty() {

    JSONObject emptyUpdate = new JSONObject();

    // Test of updating a client with null updated data
    Assertions.assertThrows(
        JsonObjectException.class,
        () -> {
          realCon.updateClient(1, emptyUpdate);
          realCon.updateManager(1, emptyUpdate);
          realCon.updateInstructor(1, emptyUpdate);
          realCon.updateOrganization("HololiveJP", emptyUpdate);
        });
  }

  @Test
  @Order(17)
  void testDneUpdate() {
    JSONObject testUpdate = new JSONObject();
    testUpdate.append("Another Data", "Another Client/Manager/Instructor Data");

    // Test of updating a client that does not exist
    Assertions.assertThrows(
        EmptyQueryException.class,
        () -> {
          realCon.updateClient(50, testUpdate);
          realCon.updateManager(51, testUpdate);
          realCon.updateInstructor(52, testUpdate);
          realCon.updateOrganization("HololivePH", testUpdate);
        });
  }

  // Test if initializing and setting the next ID throws exception
  @Test
  @Order(18)
  void testSetUniqueId() {
    realCon.deleteData(0, "CounterCollection");

    Assertions.assertDoesNotThrow(
        () -> {
          realCon.initializeUniqueId();
          realCon.setNextUniqueId();
        });

    realCon.deleteData(0, "CounterCollection");
  }

  @AfterAll
  static void postDelete() {
    Assertions.assertDoesNotThrow(
        () -> {
          realCon.removeClient(1);
          realCon.removeClient(2);
          realCon.removeClient(3);
          realCon.removeClient(4);
          realCon.removeClient(5);
          realCon.removeManager(1);
          realCon.removeManager(2);
          realCon.removeManager(3);
          realCon.removeManager(4);
          realCon.removeManager(5);
          realCon.removeInstructor(1);
          realCon.removeInstructor(2);
          realCon.removeInstructor(3);
          realCon.removeInstructor(4);
          realCon.removeInstructor(5);
          realCon.removeOrganization("HololiveJP");
          realCon.removeOrganization("HololiveEN");
          realCon.removeOrganization("HololiveID");
          realCon.removeOrganization("HololiveCN");
        });
  }
}
