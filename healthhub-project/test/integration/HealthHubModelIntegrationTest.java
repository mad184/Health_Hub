package integration;

import com.mongodb.MongoException;
import database.EmptyQueryException;
import healthhub.HealthHubModel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.json.JSONObject;

import java.util.ArrayList;

@TestMethodOrder(OrderAnnotation.class)
public class HealthHubModelIntegrationTest {

  static HealthHubModel testHHM = new HealthHubModel();
  static ArrayList<Integer> createdUniqueIds = new ArrayList<>();

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
  @Disabled
  @Order(11)
  void testCIMUniqueIdSystem() {

    // At this stage, the DB should have Gura - Client, Kiara - Instructor and Ina - Manager
    testHHM.setProductionRandomBound();

    // Loop with the range of the Production Random Bound
    for (int iteration = 1; iteration <= 100; iteration++) {

      JSONObject testClientData = new JSONObject();
      testClientData.put("email", iteration + "@client.com");

      JSONObject testInstructorData = new JSONObject();
      testInstructorData.put("email", iteration + "@instructor.com");

      JSONObject testManagerData = new JSONObject();
      testManagerData.put("email", iteration + "@manager.com");

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
    testHHM.testRevertRandomBound();
  }

  void deleteAll() {
    for (Integer each : createdUniqueIds) {
      testHHM.testGetDatabase().removeClient(each);
      testHHM.testGetDatabase().removeInstructor(each);
      testHHM.testGetDatabase().removeManager(each);
    }
  }

  // Testing of unique email system. Really slow testing by the way
  @Test
  @Disabled
  @Order(12)
  void testUniqueEmailSystem() {

    deleteAll();

    testHHM.setProductionRandomBound();

    JSONObject testClientData = new JSONObject();
    testClientData.put("email", "gura@client.com");

    JSONObject testInstructorData = new JSONObject();
    testInstructorData.put("email", "Kiara@instructor.com");

    JSONObject testManagerData = new JSONObject();
    testManagerData.put("email", "Ina@manager.com");

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

    testHHM.testRevertRandomBound();
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

  @Test
  @Order(14)
  void testDuplicateAddOrganization() {
    JSONObject expectedOrgData = new JSONObject();
    expectedOrgData.put("Owner", "Yagoo");

    Assertions.assertEquals(500, testHHM.createOrganization("Hololive", expectedOrgData));
  }
  //  @Test
  //  @Order(2)
  //  void testAddInstructor() {
  //
  //    Assertions.assertDoesNotThrow(
  //        () -> {
  //          testHHM.addInstructor(testInstructor);
  //        });
  //  }
  //
  //  @Test
  //  @Order(3)
  //  void testAddManager() {
  //    Assertions.assertDoesNotThrow(
  //        () -> {
  //          testHHM.addManager(testManager);
  //        });
  //  }
  //
  //  @Test
  //  @Order(4)
  //  void testLoginRunning() {
  //    Assertions.assertDoesNotThrow(
  //        () -> {
  //          testHHM.systemLogin("usertest", "userpw", "Client");
  //          testHHM.systemLogin("usertest", "userpw", "Instructor");
  //          testHHM.systemLogin("usertest", "userpw", "Manager");
  //        });
  //  }
  //
  //  @Test
  //  // @Disabled
  //  @Order(5)
  //  void testLoginValidCredentials() {
  //    System.out.println("Expected Error if no database connected");
  //    int returnedClientCode = testHHM.systemLogin("validLogin", "validPassword", "Client");
  //    int returnedInstrCode = testHHM.systemLogin("validLogin", "validPassword", "Instructor");
  //    int returnedManagerCode = testHHM.systemLogin("validLogin", "validPassword", "Manager");
  //
  //    Assertions.assertEquals(200, returnedClientCode);
  //    Assertions.assertEquals(200, returnedInstrCode);
  //    Assertions.assertEquals(200, returnedManagerCode);
  //  }
  //
  //  @Test
  //  // @Disabled
  //  @Order(6)
  //  void testLoginInvalidCredentials() {
  //    System.out.println("Expected Error if no database connected");
  //    int returnedClientCode = testHHM.systemLogin("InvalidLogin", "InvalidPassword", "Client");
  //    int returnedInstrCode = testHHM.systemLogin("InvalidLogin", "InvalidPassword",
  // "Instructor");
  //    int returnedManagerCode = testHHM.systemLogin("InvalidLogin", "InvalidPassword", "Manager");
  //
  //    Assertions.assertEquals(401, returnedClientCode);
  //    Assertions.assertEquals(401, returnedInstrCode);
  //    Assertions.assertEquals(401, returnedManagerCode);
  //  }
  //
  //  @Test
  //  // @Disabled
  //  @Order(7)
  //  void testLoginCIMNotFound() {
  //    int returnedClientCode = testHHM.systemLogin("Who is this guy?", "validPassword", "Client");
  //    int returnedInstrCode = testHHM.systemLogin("Who is this guy", "validPassword",
  // "Instructor");
  //    int returnedManagerCode = testHHM.systemLogin("Who is this guy", "validPassword",
  // "Manager");
  //
  //    Assertions.assertEquals(404, returnedClientCode);
  //    Assertions.assertEquals(404, returnedInstrCode);
  //    Assertions.assertEquals(404, returnedManagerCode);
  //  }
  //
  //  @Test
  //  // @Disabled
  //  @Order(8)
  //  void testLoginSelectionInvalid() {
  //    System.out.println("Expected Error if no database connected");
  //    int returnedClientCode = testHHM.systemLogin("Who is this guy?", "validPassword", "Hacker");
  //    int returnedInstrCode = testHHM.systemLogin("Who is this guy", "validPassword", "Vtuber");
  //    int returnedManagerCode = testHHM.systemLogin("Who is this guy", "validPassword", "Shrimp");
  //
  //    Assertions.assertEquals(-1, returnedClientCode);
  //    Assertions.assertEquals(-1, returnedInstrCode);
  //    Assertions.assertEquals(-1, returnedManagerCode);
  //  }
  //
  //  @Test
  //  // @Disabled
  //  @Order(9)
  //  void testLoginServerIssues() {
  //    // You can test this by removing your internet connection. That should give you a server
  // issue.
  //    // Although database needs to be connected
  //    System.out.println("Expected Error if no database connected");
  //    int returnedClientCode = testHHM.systemLogin("Who is this guy?", "validPassword", "Client");
  //    int returnedInstrCode = testHHM.systemLogin("Who is this guy", "validPassword",
  // "Instructor");
  //    int returnedManagerCode = testHHM.systemLogin("Who is this guy", "validPassword",
  // "Manager");
  //
  //    Assertions.assertEquals(500, returnedClientCode);
  //    Assertions.assertEquals(500, returnedInstrCode);
  //    Assertions.assertEquals(500, returnedManagerCode);
  //  }
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
