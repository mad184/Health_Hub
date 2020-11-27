package databaseTests.integration;

import com.mongodb.MongoWriteException;
import database.Dbms;
import database.JsonObjectException;
import database.EmptyQueryException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@TestMethodOrder(OrderAnnotation.class)
public class DbmsIntegrationTest {

  private static final String realUserName = "test-user";
  private static final String realPassWord = "healthhub1";
  private static final String dbName = "Test-General-Database";
  private static final String tableName = "testCollection";
  private static JSONArray expectedFullData = new JSONArray();

  static Dbms realDbms = new Dbms(realUserName, realPassWord, dbName, tableName);
  static Random generator = new Random();

  // Initially create the complex data to use in testing
  @BeforeAll
  static void initialCreate() {

    // All elements within JSONObject are arrays to iterate
    JSONArray expectedNicknames = new JSONArray();
    JSONArray expectedAges = new JSONArray();
    JSONArray expectedNames = new JSONArray();
    String expectedManager = "Yagoo";
    String[] createNames = {
      "Matsuri", "Fubuki", "Haato", "Subaru", "Shion", "Aqua", "Korone", "Pekora", "Rushia",
      "Marine", "Kanata", "Coco", "Miko", "Yagoo", "Kiara", "Amelia", "Gura", "", "EnMa"
    };
    int[] createAges = {
      18, 16, 9000, 50, 24, 22, 21, 20, 0, 10, 17, 15, 25, 3501, 27, 28, 29, 111, 60
    };

    // Special: You can use any List<> type, but preferably JSONArray
    ArrayList<List<String>> createNicknames = new ArrayList<>();
    createNicknames.add(Arrays.asList("City Pop Shark", "Apex Predator", "Same-Chan"));
    createNicknames.add(Arrays.asList("Koronen", "Doggo", "Doog"));
    createNicknames.add(Arrays.asList("Miko-chi", "elite-Miko"));
    createNicknames.add(Arrays.asList("Haato", "Haachama"));
    createNicknames.add(Arrays.asList("GOD"));
    createNicknames.add(Arrays.asList("Fubuking", "FBK", ""));
    createNicknames.add(Arrays.asList("Senchou", "Maririn"));
    createNicknames.add(Arrays.asList());
    createNicknames.add(Arrays.asList("Rushifer", "Ruu", "Boing Boing Rushia"));
    createNicknames.add(Arrays.asList("Peko-chan"));
    createNicknames.add(Arrays.asList("PP Tenshi", "Kanatan", "Kabaya"));
    createNicknames.add(Arrays.asList("Aka-chan", "Luna-Hime"));
    createNicknames.add(Arrays.asList("Adidas Lion"));
    createNicknames.add(Arrays.asList("CallioP", "Miss Mori"));
    createNicknames.add(Arrays.asList("Kusotori", "Tenchou"));
    createNicknames.add(Arrays.asList("Ina"));
    createNicknames.add(Arrays.asList("Ame", "Amechan"));
    createNicknames.add(Arrays.asList("Kaichou", "Coco-chi", "Coco-chin"));
    createNicknames.add(Arrays.asList("Sololive", "Baqua", "Akutan"));

    // for loop to create the data for JSONArrays
    for (int i = 0; i < 19; i++) {
      expectedNames.put(createNames[i]);
      expectedAges.put(createAges[i]);
      expectedNicknames.put(createNicknames.get(i));
    }

    // for loop to create the final expected data
    int counter = 0;
    for (Object name : expectedNames) {
      JSONObject expectedJson = new JSONObject();
      expectedJson.put("Name", name);
      expectedJson.put("Age", expectedAges.get(counter));
      expectedJson.put("Manager", expectedManager);
      expectedJson.put("Active", true);
      expectedJson.put("Retired", false);
      expectedJson.put("Nicknames", expectedNicknames.get(counter));
      expectedFullData.put(expectedJson);
      counter++;
    }
  }

  @Test
  @Order(1)
  void testInitialUniqueIdSystem() {

    // No Id exist in testing yet therefore it should be zero
    Assertions.assertEquals(1, realDbms.getUniqueId());

    // Reset the counter of the unique id system
  }

  // Test for creating data within the Dbms
  @Test
  @Order(2)
  void testCIMCreate() {

    for (int uniqueId = 0; uniqueId < 19; uniqueId++) {
      int finalUniqueId = uniqueId;
      Assertions.assertDoesNotThrow(
          () -> {
            realDbms.createClient(finalUniqueId, expectedFullData.getJSONObject(finalUniqueId));
            realDbms.createInstructor(finalUniqueId, expectedFullData.getJSONObject(finalUniqueId));
            realDbms.createManager(finalUniqueId, expectedFullData.getJSONObject(finalUniqueId));
          });
    }

    Assertions.assertDoesNotThrow(
        () -> {
          realDbms.createOrganization("Hololive", expectedFullData.getJSONObject(0));
          realDbms.createOrganization("Hololive2", expectedFullData.getJSONObject(1));
        });
  }

  // Tests for duplicates. Repeated tests are done as different random combination will be used
  @Order(3)
  @RepeatedTest(50)
  void testDuplicateClientCreate() {

    Assertions.assertThrows(
        MongoWriteException.class,
        () ->
            realDbms.createClient(
                generator.nextInt(19), expectedFullData.getJSONObject(generator.nextInt(19))));
  }

  // Tests for duplicates. Repeated tests are done as different random combination will be used
  @Order(4)
  @RepeatedTest(50)
  void testDuplicateInstrCreate() {

    Assertions.assertThrows(
        MongoWriteException.class,
        () ->
            realDbms.createInstructor(
                generator.nextInt(19), expectedFullData.getJSONObject(generator.nextInt(19))));
  }

  // Tests for duplicates. Repeated tests are done as different random combination will be used
  @Order(5)
  @RepeatedTest(50)
  void testDuplicateManagerCreate() {

    Assertions.assertThrows(
        MongoWriteException.class,
        () ->
            realDbms.createManager(
                generator.nextInt(19), expectedFullData.getJSONObject(generator.nextInt(19))));
  }

  // Tests for duplicates. Repeated tests are done as different random combination will be used
  @Order(6)
  void testDuplicateOrganizationCreate() {

    Assertions.assertThrows(
        MongoWriteException.class,
        () -> realDbms.createOrganization("Hololive", expectedFullData.getJSONObject(0)));
  }

  // Test that reading data does not throw unnecessary exceptions
  @Order(7)
  @Test
  void testCIMRead() {

    for (int uniqueId = 0; uniqueId < 19; uniqueId++) {
      int finalUniqueId = uniqueId;
      Assertions.assertDoesNotThrow(
          () -> {
            realDbms.readClientData(finalUniqueId);
            realDbms.readInstructorData(finalUniqueId);
            realDbms.readManagerData(finalUniqueId);
          });
    }

    Assertions.assertDoesNotThrow(() -> realDbms.readOrganizationData("Hololive"));
  }

  // Test data reading for Client, Instructor and Manager
  @Order(8)
  @Test
  void testDataRead() throws EmptyQueryException {

    // for loop is necessary to loop through all the data pre-created
    for (int uniqueId = 0; uniqueId < 19; uniqueId++) {

      JSONObject expectedData = expectedFullData.getJSONObject(uniqueId);
      JSONObject actualClientData = realDbms.readClientData(uniqueId);
      JSONObject actualInstrData = realDbms.readInstructorData(uniqueId);
      JSONObject actualManagerData = realDbms.readManagerData(uniqueId);

      // Checking the client data
      Assertions.assertEquals(expectedData.get("Name"), actualClientData.get("Name"));
      Assertions.assertEquals(expectedData.get("Age"), actualClientData.get("Age"));
      Assertions.assertEquals(expectedData.get("Manager"), actualClientData.get("Manager"));
      Assertions.assertEquals(expectedData.get("Active"), actualClientData.get("Active"));
      Assertions.assertEquals(expectedData.get("Retired"), actualClientData.get("Retired"));

      // Checking the instructor data
      Assertions.assertEquals(expectedData.get("Name"), actualInstrData.get("Name"));
      Assertions.assertEquals(expectedData.get("Age"), actualInstrData.get("Age"));
      Assertions.assertEquals(expectedData.get("Manager"), actualInstrData.get("Manager"));
      Assertions.assertEquals(expectedData.get("Active"), actualInstrData.get("Active"));
      Assertions.assertEquals(expectedData.get("Retired"), actualInstrData.get("Retired"));

      // Checking the Manager Data
      Assertions.assertEquals(expectedData.get("Name"), actualManagerData.get("Name"));
      Assertions.assertEquals(expectedData.get("Age"), actualManagerData.get("Age"));
      Assertions.assertEquals(expectedData.get("Manager"), actualManagerData.get("Manager"));
      Assertions.assertEquals(expectedData.get("Active"), actualManagerData.get("Active"));
      Assertions.assertEquals(expectedData.get("Retired"), actualManagerData.get("Retired"));

      // Testing the array within array. I recommend using JSONArrays for arrays
      if (((JSONArray) expectedData.get("Nicknames")).isEmpty()) {
        Assertions.assertEquals(
            expectedData.get("Nicknames").toString(), actualClientData.get("Nicknames").toString());

        Assertions.assertEquals(
            expectedData.get("Nicknames").toString(), actualInstrData.get("Nicknames").toString());

        Assertions.assertEquals(
            expectedData.get("Nicknames").toString(),
            actualManagerData.get("Nicknames").toString());

      } else {

        // Special for loop for nicknames which is an array within an array
        for (int each = 0; each < ((JSONArray) expectedData.get("Nicknames")).length(); each++) {
          Assertions.assertEquals(
              ((JSONArray) expectedData.get("Nicknames")).get(each),
              ((List<String>) actualClientData.get("Nicknames")).get(each));

          Assertions.assertEquals(
              ((JSONArray) expectedData.get("Nicknames")).get(each),
              ((List<String>) actualInstrData.get("Nicknames")).get(each));

          Assertions.assertEquals(
              ((JSONArray) expectedData.get("Nicknames")).get(each),
              ((List<String>) actualManagerData.get("Nicknames")).get(each));
        }
      }
    }
  }

  // Test data reading for Organization
  @Order(9)
  @Test
  void testDataOrganizationRead() throws EmptyQueryException {

    JSONObject expectedData = expectedFullData.getJSONObject(0);
    JSONObject actualOrganizationData = realDbms.readOrganizationData("Hololive");

    // Checking the client data
    Assertions.assertEquals(expectedData.get("Name"), actualOrganizationData.get("Name"));
    Assertions.assertEquals(expectedData.get("Age"), actualOrganizationData.get("Age"));
    Assertions.assertEquals(expectedData.get("Manager"), actualOrganizationData.get("Manager"));
    Assertions.assertEquals(expectedData.get("Active"), actualOrganizationData.get("Active"));
    Assertions.assertEquals(expectedData.get("Retired"), actualOrganizationData.get("Retired"));

    // Testing the array within array. I recommend using JSONArrays for arrays
    if (((JSONArray) expectedData.get("Nicknames")).isEmpty()) {
      Assertions.assertEquals(
          expectedData.get("Nicknames").toString(),
          actualOrganizationData.get("Nicknames").toString());
    } else {

      // Special for loop for nicknames which is an array within an array
      for (int each = 0; each < ((JSONArray) expectedData.get("Nicknames")).length(); each++) {
        Assertions.assertEquals(
            ((JSONArray) expectedData.get("Nicknames")).get(each),
            ((List<String>) actualOrganizationData.get("Nicknames")).get(each));
      }
    }
  }

  // Tests on getting all data for clients, instructors and managers
  @Order(10)
  @Test
  void testGetAllData() {

    JSONArray actualAllClients = realDbms.getAllClients();
    JSONArray actualAllInstr = realDbms.getAllInstructors();
    JSONArray actualAllManagers = realDbms.getAllManagers();

    // Loop through the expected data
    for (int i = 0; i < expectedFullData.length(); i++) {

      JSONObject expectedArrayElement = expectedFullData.getJSONObject(i);
      JSONObject actualAllClientElements = actualAllClients.getJSONObject(i);
      JSONObject actualAllInstrElements = actualAllInstr.getJSONObject(i);
      JSONObject actualAllManagerElements = actualAllManagers.getJSONObject(i);

      // Compare each key value with expected data key-value
      for (String eachKey : expectedArrayElement.keySet()) {
        Assertions.assertEquals(
            expectedArrayElement.get(eachKey).toString(),
            actualAllClientElements.get(eachKey).toString());
        Assertions.assertEquals(
            expectedArrayElement.get(eachKey).toString(),
            actualAllInstrElements.get(eachKey).toString());
        Assertions.assertEquals(
            expectedArrayElement.get(eachKey).toString(),
            actualAllManagerElements.get(eachKey).toString());
      }
    }
  }

  // Tests on getting all data for organization
  @Order(11)
  @Test
  void testGetAllOrgData() {

    JSONObject expectedArrayElement = expectedFullData.getJSONObject(0);
    JSONObject expectedArrayElement1 = expectedFullData.getJSONObject(1);

    JSONObject actualAllOrganization = realDbms.getAllOrganization().getJSONObject(0);
    JSONObject actualAllOrganization1 = realDbms.getAllOrganization().getJSONObject(1);

    // Compare each key value with expected data key-value
    for (String eachKey : expectedArrayElement.keySet()) {
      Assertions.assertEquals(
          expectedArrayElement.get(eachKey).toString(),
          actualAllOrganization.get(eachKey).toString());
      Assertions.assertEquals(
          expectedArrayElement1.get(eachKey).toString(),
          actualAllOrganization1.get(eachKey).toString());
    }
  }

  // Testing for updating client, manager and instructor
  @Order(12)
  @Test
  void testDataUpdate() throws EmptyQueryException, JsonObjectException {

    // Create the expected updated data
    JSONObject expectedUpdatedClientData = new JSONObject();
    expectedUpdatedClientData.put("Client Contract Breached", true);

    JSONObject expectedUpdatedInstrData = new JSONObject();
    expectedUpdatedInstrData.put("Instructor Removed", true);

    JSONObject expectedUpdatedManagerData = new JSONObject();
    expectedUpdatedManagerData.put("Manager Removed", true);

    JSONObject expectedUpdatedOrgData = new JSONObject();
    expectedUpdatedOrgData.put("Organization Removed", true);

    // perform the updates
    realDbms.updateClient(0, expectedUpdatedClientData);
    realDbms.updateInstructor(1, expectedUpdatedInstrData);
    realDbms.updateManager(2, expectedUpdatedManagerData);
    realDbms.updateOrganization("Hololive", expectedUpdatedOrgData);

    // read the actual data updated
    JSONObject actualUpdatedClientData = realDbms.readClientData(0);
    JSONObject actualInstrData = realDbms.readInstructorData(1);
    JSONObject actualManagerData = realDbms.readManagerData(2);
    JSONObject actualOrgData = realDbms.readOrganizationData("Hololive");

    // Testing the asserted data not equal to previous value
    Assertions.assertThrows(
        JSONException.class,
        () -> {
          Assertions.assertNotEquals(
              expectedFullData.getJSONObject(0).get("Client Contract Breached"),
              actualUpdatedClientData);
          Assertions.assertNotEquals(
              expectedFullData.getJSONObject(1).get("Instructor Removed"), actualInstrData);
          Assertions.assertNotEquals(
              expectedFullData.getJSONObject(2).get("Manager Removed"), actualManagerData);
          Assertions.assertNotEquals(expectedFullData.getJSONObject(3).get("ENma"), actualOrgData);
        });

    // Testing it that it is equal now to the updated data
    Assertions.assertEquals(
        expectedUpdatedClientData.get("Client Contract Breached"),
        actualUpdatedClientData.get("Client Contract Breached"));
    Assertions.assertEquals(
        expectedUpdatedInstrData.get("Instructor Removed"),
        actualInstrData.get("Instructor Removed"));
    Assertions.assertEquals(
        expectedUpdatedManagerData.get("Manager Removed"),
        actualManagerData.get("Manager Removed"));
    Assertions.assertEquals(
        expectedUpdatedOrgData.get("Organization Removed"),
        actualOrgData.get("Organization Removed"));
  }

  // Test of Client, Instructor, Manager and Organization deletion
  @Test
  @Order(13)
  void testCIMODeletion() {
    for (int uniqueId = 0; uniqueId < 19; uniqueId++) {
      int finalUniqueId = uniqueId;
      Assertions.assertDoesNotThrow(
          () -> {
            realDbms.removeClient(finalUniqueId);
            realDbms.removeInstructor(finalUniqueId);
            realDbms.removeManager(finalUniqueId);
          });
    }
    Assertions.assertDoesNotThrow(
        () -> {
          realDbms.removeOrganization("Hololive");
          realDbms.removeOrganization("Hololive2");
        });
  }

  // Test of post Client, Instructor, Manager and Organization deletion
  @Test
  @Order(14)
  void testPostCIMODeletion() {
    for (int uniqueId = 0; uniqueId < 19; uniqueId++) {
      int finalUniqueId = uniqueId;
      Assertions.assertThrows(
          EmptyQueryException.class,
          () -> {
            realDbms.readClientData(finalUniqueId);
            realDbms.readInstructorData(finalUniqueId);
            realDbms.readManagerData(finalUniqueId);
          });
    }
    Assertions.assertThrows(
        EmptyQueryException.class,
        () -> {
          realDbms.readOrganizationData("Hololive");
          realDbms.readOrganizationData("Hololive2");
        });
  }

  @Test
  @Order(15)
  void testFinalUniqueIdSystem() {

    // There is only 19*3 creation so it is expected to have 58 as the next unique id
    Assertions.assertEquals(58, realDbms.getUniqueId());

    // Reset the counter of the unique id system
    realDbms.resetUniqueId();
  }

  // post delete all the created Data
  @AfterAll
  static void postDataDeletion() {
    for (int uniqueId = 0; uniqueId < 19; uniqueId++) {
      int finalUniqueId = uniqueId;
      realDbms.removeClient(finalUniqueId);
      realDbms.removeInstructor(finalUniqueId);
      realDbms.removeManager(finalUniqueId);
    }
    realDbms.removeOrganization("Hololive");
    realDbms.removeOrganization("Hololive2");
  }
}
