package healthhubTests.integration;


import database.EmptyQueryException;
import healthhub.HealthHubModel;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.json.JSONObject;

import java.util.ArrayList;

@TestMethodOrder(OrderAnnotation.class)
public class HealthHubModelIntegrationTest {

  static HealthHubModel testHHM = new HealthHubModel();
  static ArrayList<Integer> createdUniqueIds =
      new ArrayList<>(); // Storage of all uniqueIds to be deleted

  // Test if creating the client will not result to any of the error codes
  @Test
  @Order(1)
  void testNewAddClient() {

    JSONObject testClient = new JSONObject();
    testClient.put("email", "Gura@Hololive.en");

    int testValue = testHHM.addClient(testClient);
    createdUniqueIds.add(testValue);

    Assertions.assertNotEquals(500, testValue);
    Assertions.assertNotEquals(404, testValue);
    Assertions.assertNotEquals(403, testValue);
    Assertions.assertNotEquals(401, testValue);
  }

  // Test if creating email duplicated client will result to 403 error codes
  @Order(2)
  @RepeatedTest(5)
  void testDuplicateEmailAddClient() {

    JSONObject testClient = new JSONObject();
    testClient.put("email", "Gura@Hololive.en");

    int testValue = testHHM.addClient(testClient);

    createdUniqueIds.add(testValue);

    Assertions.assertEquals(403, testValue);
  }

  // Test whether adding a client that does not have an email will throw exceptions
  @Test
  @Order(3)
  void testInvalidKeyAddClient() {
    JSONObject testClient = new JSONObject();
    testClient.put("NoEmail", "Gura@Hololive.en");

    Assertions.assertThrows(Exception.class, () -> testHHM.addClient(testClient));
  }

  // Test if creating the instructor will not result to any of the error codes
  @Test
  @Order(4)
  void testNewAddInstructor() {

    JSONObject testInstructor = new JSONObject();
    testInstructor.put("email", "Kiara@Hololive.en");

    int testValue = testHHM.addInstructor(testInstructor);

    createdUniqueIds.add(testValue);

    Assertions.assertNotEquals(500, testValue);
    Assertions.assertNotEquals(404, testValue);
    Assertions.assertNotEquals(403, testValue);
    Assertions.assertNotEquals(401, testValue);
  }

  // Test if creating email duplicated instructor will result to 403 error codes
  @Order(5)
  @RepeatedTest(5)
  void testDuplicateEmailAddInstructor() {

    JSONObject testInstructor = new JSONObject();
    testInstructor.put("email", "Kiara@Hololive.en");

    int testValue = testHHM.addInstructor(testInstructor);

    createdUniqueIds.add(testValue);

    Assertions.assertEquals(403, testValue);
  }

  // Test whether adding an instructor that does not have an email will throw exceptions
  @Test
  @Order(6)
  void testInvalidKeyAddInstructor() {
    JSONObject testInstructor = new JSONObject();
    testInstructor.put("NoEmail", "Gura@Hololive.en");

    Assertions.assertThrows(Exception.class, () -> testHHM.addInstructor(testInstructor));
  }

  // Test if creating the manager will not result to any of the error codes
  @Test
  @Order(7)
  void testNewAddManager() {

    JSONObject testManager = new JSONObject();
    testManager.put("email", "Ina@Hololive.en");

    int testValue = testHHM.addManager(testManager);

    createdUniqueIds.add(testValue);

    Assertions.assertNotEquals(500, testValue);
    Assertions.assertNotEquals(404, testValue);
    Assertions.assertNotEquals(403, testValue);
    Assertions.assertNotEquals(401, testValue);
  }

  // Test if creating email duplicated manager will result to 403 error codes
  @Order(8)
  @RepeatedTest(5)
  void testDuplicateEmailAddManager() {

    JSONObject testManager = new JSONObject();
    testManager.put("email", "Ina@Hololive.en");

    int testValue = testHHM.addManager(testManager);

    createdUniqueIds.add(testValue);

    Assertions.assertEquals(403, testValue);
  }

  // Test whether adding a manager that does not have an email will throw exceptions
  @Test
  @Order(9)
  void testInvalidKeyAddManager() {
    JSONObject testManager = new JSONObject();
    testManager.put("NoEmail", "Ina@Hololive.en");

    Assertions.assertThrows(Exception.class, () -> testHHM.addManager(testManager));
  }

  // Testing of CIM Adding where the user attempts to use an email that exists within different
  // table
  // i.e an instructor wants to register with a pre-existing email already
  @Test
  @Order(10)
  void testCIMDuplicatedAdding() {

    // At this stage, the DB should have Gura - Client, Kiara - Instructor and Ina - Manager
    JSONObject existingClient = new JSONObject();
    existingClient.put("email", "Gura@Hololive.en");
    JSONObject existingInstructor = new JSONObject();
    existingInstructor.put("email", "Kiara@Hololive.en");
    JSONObject existingManager = new JSONObject();
    existingManager.put("email", "Ina@Hololive.en");

    // We are going to mix up the adds to make sure it is still checking that email is unique
    Assertions.assertEquals(403, testHHM.addClient(existingInstructor));
    Assertions.assertEquals(403, testHHM.addClient(existingManager));

    Assertions.assertEquals(403, testHHM.addInstructor(existingClient));
    Assertions.assertEquals(403, testHHM.addInstructor(existingManager));

    Assertions.assertEquals(403, testHHM.addManager(existingClient));
    Assertions.assertEquals(403, testHHM.addManager(existingInstructor));
  }

  // Testing of unique ID system
  @Test
  @Order(11)
  void testCIMUniqueIdSystem() {

    // At this stage, the DB should have Gura - Client, Kiara - Instructor and Ina - Manager
    testHHM.setProductionRandomBound(); // Make sure we are at production Random Bound

    // Loop with the range of the Production Random Bound
    for (int iteration = 1; iteration <= 100; iteration++) {

      JSONObject testClientData = new JSONObject();
      testClientData.put("email", iteration + "@client.com");

      JSONObject testInstructorData = new JSONObject();
      testInstructorData.put("email", iteration + "@instructor.com");

      JSONObject testManagerData = new JSONObject();
      testManagerData.put("email", iteration + "@manager.com");

      // Test the creation of data whether it will fail or not
      int clientStatusValue = testHHM.addClient(testClientData);
      createdUniqueIds.add(clientStatusValue);
      Assertions.assertNotEquals(500, clientStatusValue);

      int instructorStatusValue = testHHM.addInstructor(testInstructorData);
      createdUniqueIds.add(instructorStatusValue);
      Assertions.assertNotEquals(500, testHHM.addInstructor(testInstructorData));

      int managerStatusValue = testHHM.addManager(testManagerData);
      createdUniqueIds.add(managerStatusValue);
      Assertions.assertNotEquals(500, testHHM.addManager(testManagerData));
    }
    testHHM.testRevertRandomBound(); // revert to the safest RANDOMBOUND for this test code
  }

  /** This method is called for deleting all within the database */
  void deleteAll() {
    for (Integer each : createdUniqueIds) {
      testHHM.testGetDatabase().removeClient(each);
      testHHM.testGetDatabase().removeInstructor(each);
      testHHM.testGetDatabase().removeManager(each);
    }
  }

  // Testing of unique email system. Really slow testing by the way
  @Test
  @Order(12)
  void testUniqueEmailSystem() {

    deleteAll(); // delete all pre-existing data

    testHHM.setProductionRandomBound();

    // Create the unique emails
    JSONObject testClientData = new JSONObject();
    testClientData.put("email", "gura@client.com");

    JSONObject testInstructorData = new JSONObject();
    testInstructorData.put("email", "Kiara@instructor.com");

    JSONObject testManagerData = new JSONObject();
    testManagerData.put("email", "Ina@manager.com");

    // Test the Unique Emails with different Combinations of how it will be added
    int testManagerValueM1 = testHHM.addManager(testManagerData);
    createdUniqueIds.add(testManagerValueM1);
    Assertions.assertNotEquals(403, testManagerValueM1);

    int testInstructorValueI1 = testHHM.addInstructor(testInstructorData);
    createdUniqueIds.add(testInstructorValueI1);
    Assertions.assertNotEquals(403, testInstructorValueI1);

    int testClientValueC1 = testHHM.addClient(testClientData);
    createdUniqueIds.add(testClientValueC1);
    Assertions.assertNotEquals(403, testClientValueC1);

    deleteAll();

    int testInstructorValueI2 = testHHM.addInstructor(testInstructorData);
    createdUniqueIds.add(testInstructorValueI2);
    Assertions.assertNotEquals(403, testInstructorValueI2);

    int testManagerValueM2 = testHHM.addManager(testManagerData);
    createdUniqueIds.add(testManagerValueM2);
    Assertions.assertNotEquals(403, testManagerValueM2);

    int testClientValueC2 = testHHM.addClient(testClientData);
    createdUniqueIds.add(testClientValueC2);
    Assertions.assertNotEquals(403, testClientValueC2);

    deleteAll();

    int testInstructorValueI3 = testHHM.addInstructor(testInstructorData);
    createdUniqueIds.add(testInstructorValueI3);
    Assertions.assertNotEquals(403, testInstructorValueI3);

    int testClientValueC3 = testHHM.addClient(testClientData);
    createdUniqueIds.add(testClientValueC3);
    Assertions.assertNotEquals(403, testClientValueC3);

    int testManagerValueM3 = testHHM.addManager(testManagerData);
    createdUniqueIds.add(testManagerValueM3);
    Assertions.assertNotEquals(403, testManagerValueM3);

    deleteAll();

    testHHM.testRevertRandomBound(); // revert to the safest RANDOMBOUND for this test code
  }

  // Test that adding organization has no issues
  @Test
  @Order(13)
  void testAddOrganization() throws EmptyQueryException {

    JSONObject expectedOrgData = new JSONObject();
    expectedOrgData.put("Owner", "Yagoo");

    Assertions.assertEquals(200, testHHM.createOrganization("Hololive", expectedOrgData));

    JSONObject actualData = testHHM.testGetDatabase().readOrganizationData("Hololive");

    Assertions.assertEquals(
        expectedOrgData.get("Owner").toString(), actualData.get("Owner").toString());
    Assertions.assertEquals("Hololive", actualData.get("_id").toString());
  }

  // Test for duplication any organization
  @Test
  @Order(14)
  void testDuplicateAddOrganization() {
    JSONObject expectedOrgData = new JSONObject();
    expectedOrgData.put("Owner", "Yagoo");

    Assertions.assertEquals(500, testHHM.createOrganization("Hololive", expectedOrgData));
  }

  /**
   * Special method the preCreates Data for systemLogin
   *
   * @return JSONArray containing valid credentials
   */
  JSONArray preCreateSystemLogin() {

    testHHM.setProductionRandomBound();

    // Prepare the necessary data
    JSONObject clientShrimpUser1 = new JSONObject();
    clientShrimpUser1.put("email", "clientShrimp1@mail.com");
    clientShrimpUser1.put("password", "cs1");

    JSONObject clientShrimpUser2 = new JSONObject();
    clientShrimpUser2.put("email", "clientShrimp2@mail.com");
    clientShrimpUser2.put("password", "cs2");

    JSONObject instrShrimpUser1 = new JSONObject();
    instrShrimpUser1.put("email", "instrShrimp1@mail.com");
    instrShrimpUser1.put("password", "is1");

    JSONObject instrShrimpUser2 = new JSONObject();
    instrShrimpUser2.put("email", "instrShrimp2@mail.com");
    instrShrimpUser2.put("password", "is2");

    JSONObject managerShrimpUser1 = new JSONObject();
    managerShrimpUser1.put("email", "managerShrimp1@mail.com");
    managerShrimpUser1.put("password", "ms1");

    JSONObject managerShrimpUser2 = new JSONObject();
    managerShrimpUser2.put("email", "managerShrimp2@mail.com");
    managerShrimpUser2.put("password", "ms2");

    JSONArray validCredentials = new JSONArray();

    // Create the data within the database
    int clientUserId1 = testHHM.addClient(clientShrimpUser1);
    validCredentials.put(new JSONObject().put("clientShrimpUserId1", clientUserId1));
    createdUniqueIds.add(clientUserId1);

    int clientUserId2 = testHHM.addClient(clientShrimpUser2);
    validCredentials.put(new JSONObject().put("clientShrimpUserId2", clientUserId2));
    createdUniqueIds.add(clientUserId2);

    int instrUserId1 = testHHM.addInstructor(instrShrimpUser1);
    validCredentials.put(new JSONObject().put("instrShrimpUserId1", instrUserId1));
    createdUniqueIds.add(instrUserId1);

    int instrUserId2 = testHHM.addInstructor(instrShrimpUser2);
    validCredentials.put(new JSONObject().put("instrShrimpUserId2", instrUserId2));
    createdUniqueIds.add(instrUserId2);

    int managerUserId1 = testHHM.addManager(managerShrimpUser1);
    validCredentials.put(new JSONObject().put("managerShrimpUserId1", managerUserId1));
    createdUniqueIds.add(managerUserId1);

    int managerUserId2 = testHHM.addManager(managerShrimpUser2);
    validCredentials.put(new JSONObject().put("managerShrimpUserId2", managerUserId2));
    createdUniqueIds.add(managerUserId2);

    testHHM.testRevertRandomBound();

    return validCredentials;
  }

  // Testing when attempting login with literally no data within the database
  @Test
  @Order(15)
  void testNonExistentSystem() {

    Assertions.assertEquals(
        404, testHHM.systemLogin("I do not exist yet", "I do not exist yet either", "Client"));
    Assertions.assertEquals(
        404, testHHM.systemLogin("I do not exist yet", "I do not exist yet either", "Instructor"));
    Assertions.assertEquals(
        404, testHHM.systemLogin("I do not exist yet", "I do not exist yet either", "Manager"));
  }

  // Test Valid Credentials
  @Test
  @Order(16)
  void testValidCredentialLogin() {
    JSONArray validCredentials = preCreateSystemLogin();

    Assertions.assertEquals(
        validCredentials.getJSONObject(0).get("clientShrimpUserId1"),
        testHHM.systemLogin("clientShrimp1@mail.com", "cs1", "Client"));
    Assertions.assertEquals(
        validCredentials.getJSONObject(1).get("clientShrimpUserId2"),
        testHHM.systemLogin("clientShrimp2@mail.com", "cs2", "Client"));
    Assertions.assertEquals(
        validCredentials.getJSONObject(2).get("instrShrimpUserId1"),
        testHHM.systemLogin("instrShrimp1@mail.com", "is1", "Instructor"));
    Assertions.assertEquals(
        validCredentials.getJSONObject(3).get("instrShrimpUserId2"),
        testHHM.systemLogin("instrShrimp2@mail.com", "is2", "Instructor"));
    Assertions.assertEquals(
        validCredentials.getJSONObject(4).get("managerShrimpUserId1"),
        testHHM.systemLogin("managerShrimp1@mail.com", "ms1", "Manager"));
    Assertions.assertEquals(
        validCredentials.getJSONObject(5).get("managerShrimpUserId2"),
        testHHM.systemLogin("managerShrimp2@mail.com", "ms2", "Manager"));
  }

  // Test Invalid Credentials
  @Test
  @Order(17)
  void testInValidCredentialLogin() {
    // preCreateSystemLogin(); // Uncomment if standalone running the test Case

    Assertions.assertEquals(
        401, testHHM.systemLogin("clientShrimp1@mail.com", "fakePassword", "Client"));
    Assertions.assertEquals(
        401, testHHM.systemLogin("instrShrimp1@mail.com", "fakepassword", "Instructor"));
    Assertions.assertEquals(
        401, testHHM.systemLogin("managerShrimp1@mail.com", "fakepassword", "Manager"));
  }

  // Testing of attempting to login for non existent Credential when the database has data
  @Test
  @Order(18)
  void testNonExistentCredentials() {

    // preCreateSystemLogin(); // Uncomment if standalone running the test Case

    Assertions.assertEquals(404, testHHM.systemLogin("I do not exist", "cs1", "Client"));
    Assertions.assertEquals(404, testHHM.systemLogin("I do not exist", "is2", "Instructor"));
    Assertions.assertEquals(404, testHHM.systemLogin("I do not exist", "ms1", "Manager"));
  }

  @AfterAll
  static void postDelete() {

    for (Integer each : createdUniqueIds) {
      testHHM.testGetDatabase().removeClient(each);
      testHHM.testGetDatabase().removeInstructor(each);
      testHHM.testGetDatabase().removeManager(each);
    }

    testHHM.testGetDatabase().removeOrganization("Hololive");
  }
}
