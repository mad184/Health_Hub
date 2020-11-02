package database.test;

import com.mongodb.MongoSecurityException;
import com.mongodb.MongoWriteException;
import database.JsonObjectException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import database.Writer;

@TestMethodOrder(OrderAnnotation.class)
public class WriterUnitTest {

  private static final String realUserName = "test-user";
  private static final String realPassWord = "healthhub1";
  private static final String dbName = "Test-General-Database";
  private static final String tableName = "testCollection";
  private static final String realUriString =
      "mongodb+srv://"
          + realUserName
          + ":"
          + realPassWord
          + "@healthhub-cluster.7y7j0.mongodb.net/"
          + dbName
          + "?retryWrites=true&w=majority";

  static Writer realCon = new Writer(realUriString, dbName, tableName);

  @BeforeAll
  static void preDelete() {
    Assertions.assertDoesNotThrow(
        () -> {
          realCon.removeClient(1);
          realCon.removeManager(1);
          realCon.removeInstructor(1);
        });
  }

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

  @RepeatedTest(3)
  @Order(2)
  void testDeletion() {

    // Test deletion of data
    Assertions.assertDoesNotThrow(
        () -> {
          realCon.removeClient(1);
          realCon.removeManager(1);
          realCon.removeInstructor(1);
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
        });
  }

  @Test
  @Order(4)
  void testCreation() {
    JSONObject testAdd = new JSONObject();
    testAdd.append("Data", "Client/Manager/Instructor Data");

    // Test Creation of data
    Assertions.assertDoesNotThrow(
        () -> {
          realCon.createClient(1, testAdd);
          realCon.createManager(1, testAdd);
          realCon.createInstructor(1, testAdd);
        });
  }

  @Test
  @Order(5)
  void testCreationDuplicate() {
    JSONObject test_add = new JSONObject();
    test_add.append("Data", "Client/Manager/Instructor Data");

    // Test Creation of duplicated data
    Assertions.assertThrows(MongoWriteException.class, () -> realCon.createClient(1, test_add));
    Assertions.assertThrows(MongoWriteException.class, () -> realCon.createManager(1, test_add));
    Assertions.assertThrows(MongoWriteException.class, () -> realCon.createInstructor(1, test_add));
  }

  @Test
  @Order(6)
  void testUpdateNull() {

    // Test of updating a client with null updated data
    Assertions.assertThrows(
        NullPointerException.class,
        () -> {
          realCon.updateClient(1, null);
          realCon.updateManager(1, null);
          realCon.updateInstructor(1, null);
        });
  }

  @Test
  @Order(7)
  void testUpdateEmpty() {

    JSONObject emptyUpdate = new JSONObject();

    // Test of updating a client with null updated data
    Assertions.assertThrows(
        JsonObjectException.class,
        () -> {
          realCon.updateClient(1, emptyUpdate);
          realCon.updateManager(1, emptyUpdate);
          realCon.updateInstructor(1, emptyUpdate);
        });
  }

  @Test
  @Order(8)
  void testUpdate() {
    JSONObject testUpdate = new JSONObject();
    testUpdate.append("Another Data", "Another Client/Manager/Instructor Data");

    // Test update of already created data
    Assertions.assertDoesNotThrow(
        () -> {
          realCon.updateClient(1, testUpdate);
          realCon.updateManager(1, testUpdate);
          realCon.updateInstructor(1, testUpdate);
        });
  }

  @AfterAll
  static void postDelete() {
    Assertions.assertDoesNotThrow(
        () -> {
          realCon.removeClient(1);
          realCon.removeManager(1);
          realCon.removeInstructor(1);
        });
  }
}
