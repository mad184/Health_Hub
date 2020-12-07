package healthhubTests.integration;

import database.EmptyQueryException;
import healthhub.HealthHubModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.json.JSONObject;

@TestMethodOrder(OrderAnnotation.class)
public class HealthHubModelIntegrationTest {

  private static HealthHubModel testHHM =
      new HealthHubModel("production_user", "healthhub1", "Test-Production-Database");

  // Test if creating the client will not result to any of the error codes
  @Test
  @Order(1)
  void testNewAddClient() {
    // testHHM.resetUniqueID(); // Reset the system
    JSONObject testClient = new JSONObject();
    testClient.put("email", "Gura@Hololive.en");

    int testValue = testHHM.addClient(testHHM.getUniqueId(), testClient);

    // None of the status code should be triggered
    Assertions.assertNotEquals(500, testValue);
    Assertions.assertNotEquals(404, testValue);
    Assertions.assertNotEquals(403, testValue);
    Assertions.assertNotEquals(401, testValue);

    // The only status code that should be triggered
    Assertions.assertEquals(200, testValue);
  }

  // Test if creating email duplicated client will result to 403 error codes
  @Order(2)
  @RepeatedTest(5)
  void testDuplicateEmailAddClient() {

    JSONObject testClient = new JSONObject();
    testClient.put("email", "Gura@Hololive.en");

    int testValue = testHHM.addClient(testHHM.getUniqueId(), testClient);

    Assertions.assertEquals(403, testValue);
  }

  // Test whether adding a client that does not have an email will throw exceptions
  @Test
  @Order(3)
  void testInvalidKeyAddClient() {
    JSONObject testClient = new JSONObject();
    testClient.put("NoEmail", "Gura@Hololive.en");

    Assertions.assertThrows(
        Exception.class, () -> testHHM.addClient(testHHM.getUniqueId(), testClient));
  }

  // Test if creating the instructor will not result to any of the error codes
  @Test
  @Order(4)
  void testNewAddInstructor() {

    JSONObject testInstructor = new JSONObject();
    testInstructor.put("email", "Kiara@Hololive.en");

    int testValue = testHHM.addInstructor(testHHM.getUniqueId(), testInstructor);

    // None of the status code should be triggered
    Assertions.assertNotEquals(500, testValue);
    Assertions.assertNotEquals(404, testValue);
    Assertions.assertNotEquals(403, testValue);
    Assertions.assertNotEquals(401, testValue);

    // Only successful write should happen
    Assertions.assertEquals(200, testValue);
  }

  // Test if creating email duplicated instructor will result to 403 error codes
  @Order(5)
  @RepeatedTest(5)
  void testDuplicateEmailAddInstructor() {

    JSONObject testInstructor = new JSONObject();
    testInstructor.put("email", "Kiara@Hololive.en");

    int testValue = testHHM.addInstructor(testHHM.getUniqueId(), testInstructor);

    Assertions.assertEquals(403, testValue);
  }

  // Test whether adding an instructor that does not have an email will throw exceptions
  @Test
  @Order(6)
  void testInvalidKeyAddInstructor() {
    JSONObject testInstructor = new JSONObject();
    testInstructor.put("NoEmail", "Gura@Hololive.en");

    Assertions.assertThrows(
        Exception.class, () -> testHHM.addInstructor(testHHM.getUniqueId(), testInstructor));
  }

  // Test if creating the manager will not result to any of the error codes
  @Test
  @Order(7)
  void testNewAddManager() {

    JSONObject testManager = new JSONObject();
    testManager.put("email", "Ina@Hololive.en");

    int testValue = testHHM.addManager(testHHM.getUniqueId(), testManager);

    // No status code should be returned
    Assertions.assertNotEquals(500, testValue);
    Assertions.assertNotEquals(404, testValue);
    Assertions.assertNotEquals(403, testValue);
    Assertions.assertNotEquals(401, testValue);

    // Only status code should be returned
    Assertions.assertEquals(200, testValue);
  }

  // Test if creating email duplicated manager will result to 403 error codes
  @Order(8)
  @RepeatedTest(5)
  void testDuplicateEmailAddManager() {

    JSONObject testManager = new JSONObject();
    testManager.put("email", "Ina@Hololive.en");

    int testValue = testHHM.addManager(testHHM.getUniqueId(), testManager);

    Assertions.assertEquals(403, testValue);
  }

  // Test whether adding a manager that does not have an email will throw exceptions
  @Test
  @Order(9)
  void testInvalidKeyAddManager() {
    JSONObject testManager = new JSONObject();
    testManager.put("NoEmail", "Ina@Hololive.en");

    Assertions.assertThrows(
        Exception.class, () -> testHHM.addManager(testHHM.getUniqueId(), testManager));
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
    Assertions.assertEquals(403, testHHM.addClient(testHHM.getUniqueId(), existingInstructor));
    Assertions.assertEquals(403, testHHM.addClient(testHHM.getUniqueId(), existingManager));

    Assertions.assertEquals(403, testHHM.addInstructor(testHHM.getUniqueId(), existingClient));
    Assertions.assertEquals(403, testHHM.addInstructor(testHHM.getUniqueId(), existingManager));

    Assertions.assertEquals(403, testHHM.addManager(testHHM.getUniqueId(), existingClient));
    Assertions.assertEquals(403, testHHM.addManager(testHHM.getUniqueId(), existingInstructor));
  }

  /** This method is called for deleting all within the database */
  void deleteAll() {
    for (int i = 0; i <= 50; i++) {
      testHHM.testGetDatabase().removeClient(i);
      testHHM.testGetDatabase().removeInstructor(i);
      testHHM.testGetDatabase().removeManager(i);
    }
  }

  // Testing of unique email system. Really slow testing by the way
  @Test
  @Order(11)
  void testUniqueEmailSystem() {

    deleteAll(); // delete all pre-existing data

    // Create the unique emails
    JSONObject testClientData = new JSONObject();
    testClientData.put("email", "gura@client.com");

    JSONObject testInstructorData = new JSONObject();
    testInstructorData.put("email", "Kiara@instructor.com");

    JSONObject testManagerData = new JSONObject();
    testManagerData.put("email", "Ina@manager.com");

    // Test the Unique Emails with different Combinations of how it will be added
    int testManagerValueM1 = testHHM.addManager(testHHM.getUniqueId(), testManagerData);
    Assertions.assertNotEquals(403, testManagerValueM1);

    int testInstructorValueI1 = testHHM.addInstructor(testHHM.getUniqueId(), testInstructorData);
    Assertions.assertNotEquals(403, testInstructorValueI1);

    int testClientValueC1 = testHHM.addClient(testHHM.getUniqueId(), testClientData);
    Assertions.assertNotEquals(403, testClientValueC1);

    deleteAll();

    int testInstructorValueI2 = testHHM.addInstructor(testHHM.getUniqueId(), testInstructorData);
    Assertions.assertNotEquals(403, testInstructorValueI2);

    int testManagerValueM2 = testHHM.addManager(testHHM.getUniqueId(), testManagerData);
    Assertions.assertNotEquals(403, testManagerValueM2);

    int testClientValueC2 = testHHM.addClient(testHHM.getUniqueId(), testClientData);
    Assertions.assertNotEquals(403, testClientValueC2);

    deleteAll();

    int testInstructorValueI3 = testHHM.addInstructor(testHHM.getUniqueId(), testInstructorData);
    Assertions.assertNotEquals(403, testInstructorValueI3);

    int testClientValueC3 = testHHM.addClient(testHHM.getUniqueId(), testClientData);
    Assertions.assertNotEquals(403, testClientValueC3);

    int testManagerValueM3 = testHHM.addManager(testHHM.getUniqueId(), testManagerData);
    Assertions.assertNotEquals(403, testManagerValueM3);

    deleteAll();
  }

  // Test that adding organization has no issues
  @Test
  @Order(12)
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
  @Order(13)
  void testDuplicateAddOrganization() {
    JSONObject expectedOrgData = new JSONObject();
    expectedOrgData.put("Owner", "Yagoo");

    Assertions.assertEquals(500, testHHM.createOrganization("Hololive", expectedOrgData));
  }

  /** Special method the preCreates Data for systemLogin */
  void preCreateSystemLogin() {

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

    // Create the data within the database
    testHHM.addClient(testHHM.getUniqueId(), clientShrimpUser1);

    testHHM.addClient(testHHM.getUniqueId(), clientShrimpUser2);

    testHHM.addInstructor(testHHM.getUniqueId(), instrShrimpUser1);

    testHHM.addInstructor(testHHM.getUniqueId(), instrShrimpUser2);

    testHHM.addManager(testHHM.getUniqueId(), managerShrimpUser1);

    testHHM.addManager(testHHM.getUniqueId(), managerShrimpUser2);
  }

  // Testing when attempting login with literally no data within the database
  @Test
  @Order(14)
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
  @Order(15)
  void testValidCredentialLogin() {
    preCreateSystemLogin();

    Assertions.assertNotEquals(401, testHHM.systemLogin("clientShrimp1@mail.com", "cs1", "Client"));
    Assertions.assertNotEquals(401, testHHM.systemLogin("clientShrimp2@mail.com", "cs2", "Client"));
    Assertions.assertNotEquals(
        401, testHHM.systemLogin("instrShrimp1@mail.com", "is1", "Instructor"));
    Assertions.assertNotEquals(
        401, testHHM.systemLogin("instrShrimp2@mail.com", "is2", "Instructor"));
    Assertions.assertNotEquals(
        401, testHHM.systemLogin("managerShrimp1@mail.com", "ms1", "Manager"));
    Assertions.assertNotEquals(
        401, testHHM.systemLogin("managerShrimp2@mail.com", "ms2", "Manager"));
  }

  // Test Invalid Credentials
  @Test
  @Order(16)
  void testInValidCredentialLogin() {

    Assertions.assertEquals(
        401, testHHM.systemLogin("clientShrimp1@mail.com", "fakePassword", "Client"));
    Assertions.assertEquals(
        401, testHHM.systemLogin("instrShrimp1@mail.com", "fakepassword", "Instructor"));
    Assertions.assertEquals(
        401, testHHM.systemLogin("managerShrimp1@mail.com", "fakepassword", "Manager"));
  }

  // Testing of attempting to login for non existent Credential when the database has data
  @Test
  @Order(17)
  void testNonExistentCredentials() {

    // preCreateSystemLogin(); // Uncomment if standalone running the test Case

    Assertions.assertEquals(404, testHHM.systemLogin("I do not exist", "cs1", "Client"));
    Assertions.assertEquals(404, testHHM.systemLogin("I do not exist", "is2", "Instructor"));
    Assertions.assertEquals(404, testHHM.systemLogin("I do not exist", "ms1", "Manager"));
  }

  @AfterAll
  static void postDelete() {

    for (int i = 0; i < 50; i++) {
      testHHM.testGetDatabase().removeClient(i);
      testHHM.testGetDatabase().removeInstructor(i);
      testHHM.testGetDatabase().removeManager(i);
    }

    testHHM.resetUniqueID();
    testHHM.testGetDatabase().removeOrganization("Hololive");
  }
}
