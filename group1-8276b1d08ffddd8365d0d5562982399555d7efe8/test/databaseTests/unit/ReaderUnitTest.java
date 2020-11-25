package databaseTests.unit;

import com.mongodb.MongoSecurityException;
import database.EmptyQueryException;
import database.Reader;
import database.Writer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.ArrayList;

@TestMethodOrder(OrderAnnotation.class)
public class ReaderUnitTest {
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

  static Reader realCon = new Reader(realUriString, dbName, tableName);

  // sets up the data we need to read
  @BeforeAll
  static void preWriteData() {

    // necessary data to write
    Writer preWriter = new Writer(realUriString, dbName, tableName);
    JSONObject testAdd = new JSONObject();
    testAdd.put("Name", "Gawr Gura");
    testAdd.put("Age", 7);
    JSONArray testArray = new JSONArray();
    testArray.put("Kiara");
    testArray.put("Ina");
    testArray.put("Amelia");
    testAdd.put("Hololive Friends", testArray);

    // PreWrite of data
    Assertions.assertDoesNotThrow(
        () -> {
          preWriter.createClient(1, testAdd);
          preWriter.createManager(1, testAdd);
          preWriter.createInstructor(1, testAdd);
          preWriter.createOrganization("HololiveEN", testAdd);
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

    Reader fakeTest = new Reader(fakeUriString, dbName, tableName);

    // Test for fake credentials.
    Assertions.assertThrows(MongoSecurityException.class, () -> fakeTest.readClientData(1));

    // Test for real credentials.
    Assertions.assertDoesNotThrow(() -> realCon.readClientData(1));
  }

  // Tests that EmptyQueryException thrown when reading non-existing unique id
  @Test
  @Order(2)
  void testDnExistReads() {
    Assertions.assertThrows(EmptyQueryException.class, () -> realCon.readClientData(2));
    Assertions.assertThrows(EmptyQueryException.class, () -> realCon.readInstructorData(2));
    Assertions.assertThrows(EmptyQueryException.class, () -> realCon.readManagerData(2));
    Assertions.assertThrows(
        EmptyQueryException.class, () -> realCon.readOrganizationData("HololivePH"));
  }

  // Tests the actual reading by comparing to pre-created data for client, manager and instructor
  @Test
  @Order(3)
  void testCIMDataReading() throws EmptyQueryException {
    Assertions.assertDoesNotThrow(
        () -> {
          realCon.readClientData(1);
          realCon.readInstructorData(1);
          realCon.readManagerData(1);
          realCon.readOrganizationData("HololiveEN");
        });

    // Checking the expected and actual data created in precondition
    JSONObject clientData = realCon.readClientData(1);
    JSONObject InstructorData = realCon.readInstructorData(1);
    JSONObject ManagerData = realCon.readManagerData(1);
    JSONObject OrganizationData = realCon.readOrganizationData("HololiveEN");
    ArrayList<String> testArray = new ArrayList<>();
    testArray.add("Kiara");
    testArray.add("Ina");
    testArray.add("Amelia");
    Assertions.assertEquals(clientData.get("Age"), 7);
    Assertions.assertEquals(clientData.get("Name"), "Gawr Gura");
    Assertions.assertEquals(clientData.get("Hololive Friends"), testArray);

    Assertions.assertEquals(InstructorData.get("Age"), 7);
    Assertions.assertEquals(InstructorData.get("Name"), "Gawr Gura");
    Assertions.assertEquals(InstructorData.get("Hololive Friends"), testArray);

    Assertions.assertEquals(ManagerData.get("Age"), 7);
    Assertions.assertEquals(ManagerData.get("Name"), "Gawr Gura");
    Assertions.assertEquals(ManagerData.get("Hololive Friends"), testArray);

    Assertions.assertEquals(OrganizationData.get("Age"), 7);
    Assertions.assertEquals(OrganizationData.get("Name"), "Gawr Gura");
    Assertions.assertEquals(OrganizationData.get("Hololive Friends"), testArray);
  }

  // function that createsTestElement used for the next case
  JSONObject createTestElement() {
    JSONObject testAdd = new JSONObject();
    testAdd.put("_id", "1");
    testAdd.put("Name", "Gawr Gura");
    testAdd.put("Age", 7);

    JSONArray testArray = new JSONArray();
    testArray.put("Kiara");
    testArray.put("Ina");
    testArray.put("Amelia");
    testAdd.put("Hololive Friends", testArray);

    return testAdd;
  }

  // Tests the get all methods and compare with pre-created data
  @Test
  @Order(4)
  void testGetAllCIM() {
    Assertions.assertDoesNotThrow(
        () -> {
          realCon.getAllClients();
          realCon.getAllInstructors();
          realCon.getAllManagers();
          realCon.getAllOrganization();
        });

    JSONArray testClient = realCon.getAllClients();
    JSONArray testInstructors = realCon.getAllInstructors();
    JSONArray testManagers = realCon.getAllManagers();
    JSONArray testOrganization = realCon.getAllOrganization();

    JSONObject actualClientData = testClient.getJSONObject(0);
    JSONObject actualInstructorsData = testInstructors.getJSONObject(0);
    JSONObject actualManagerData = testManagers.getJSONObject(0);
    JSONObject actualOrganizationData = testOrganization.getJSONObject(0);

    JSONObject expectedData = createTestElement();

    Assertions.assertEquals(expectedData.get("Age"), actualClientData.get("Age"));
    Assertions.assertEquals(expectedData.get("Name"), actualClientData.get("Name"));
    Assertions.assertEquals(
        expectedData.get("Hololive Friends").toString(),
        actualClientData.get("Hololive Friends").toString());

    Assertions.assertEquals(expectedData.get("Age"), actualInstructorsData.get("Age"));
    Assertions.assertEquals(expectedData.get("Name"), actualInstructorsData.get("Name"));
    Assertions.assertEquals(
        expectedData.get("Hololive Friends").toString(),
        actualInstructorsData.get("Hololive Friends").toString());

    Assertions.assertEquals(expectedData.get("Age"), actualManagerData.get("Age"));
    Assertions.assertEquals(expectedData.get("Name"), actualManagerData.get("Name"));
    Assertions.assertEquals(
        expectedData.get("Hololive Friends").toString(),
        actualManagerData.get("Hololive Friends").toString());

    Assertions.assertEquals(expectedData.get("Age"), actualOrganizationData.get("Age"));
    Assertions.assertEquals(expectedData.get("Name"), actualOrganizationData.get("Name"));
    Assertions.assertEquals(
        expectedData.get("Hololive Friends").toString(),
        actualOrganizationData.get("Hololive Friends").toString());
  }

  // Delete everything
  @AfterAll
  static void postDelete() {

    Writer postWriter = new Writer(realUriString, dbName, tableName);
    Assertions.assertDoesNotThrow(
        () -> {
          postWriter.removeClient(1);
          postWriter.removeManager(1);
          postWriter.removeInstructor(1);
          postWriter.removeOrganization("HololiveEN");
        });
  }
}
