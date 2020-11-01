package database.test;

import com.mongodb.MongoSecurityException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import database.Writer;

/** Note the order of test call should be Credentials > Deletion > Creation > Update */
public class WriterUnitTest {

  final String realUserName = "test-user";
  final String realPassWord = "healthhub1";
  final String dbName = "Test-General-Database";
  final String tableName = "testCollection";

  String realUriString =
      "mongodb+srv://"
          + realUserName
          + ":"
          + realPassWord
          + "@healthhub-cluster.7y7j0.mongodb.net/"
          + dbName
          + "?retryWrites=true&w=majority";

  Writer realCon = new Writer(realUriString, dbName, tableName);

  @Test
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

  @Test
  void testDeletion() {
    JSONObject test_add = new JSONObject();
    test_add.append("Data", "Client/Manager/Instructor Data");

    Assertions.assertDoesNotThrow(
        () -> {
          realCon.removeClient(1);
          realCon.removeManager(1);
          realCon.removeInstructor(1);
        });
  }

  @Test
  void testCreation() {
    JSONObject test_add = new JSONObject();
    test_add.append("Data", "Client/Manager/Instructor Data");

    Assertions.assertDoesNotThrow(
        () -> {
          realCon.createClient(1, test_add);
          realCon.createManager(1, test_add);
          realCon.createInstructor(1, test_add);
        });
  }

  @Test
  void testUpdate() {
    JSONObject test_update = new JSONObject();
    test_update.append("Another Data", "Another Client/Manager/Instructor Data");

    Assertions.assertDoesNotThrow(
        () -> {
          realCon.updateClient(1, test_update);
          realCon.updateManager(1, test_update);
          realCon.updateInstructor(1, test_update);
        });
  }
}
