package integration;

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
    testClient.put("email","Gura@Hololive.en");

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
        testClient.put("email","Gura@Hololive.en");

        int testValue = testHHM.addClient(testClient);

        createdUniqueIds.add(testValue);

        Assertions.assertEquals(403, testValue);
    }

    // Test whether adding a client that does not have an email will throw exceptions
    @Test
    @Order(3)
    void testInvalidKeyAddClient(){
        JSONObject testClient = new JSONObject();
        testClient.put("NoEmail","Gura@Hololive.en");

        Assertions.assertThrows(Exception.class, () ->testHHM.addClient(testClient));
    }

    // Test if creating the instructor will not result to any of the error codes
    @Test
    @Order(4)
    void testNewAddInstructor() {

        JSONObject testInstructor = new JSONObject();
        testInstructor.put("email","Kiara@Hololive.en");

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
        testInstructor.put("email","Kiara@Hololive.en");

        int testValue = testHHM.addInstructor(testInstructor);

        createdUniqueIds.add(testValue);

        Assertions.assertEquals(403, testValue);
    }

    // Test whether adding an instructor that does not have an email will throw exceptions
    @Test
    @Order(6)
    void testInvalidKeyAddInstructor(){
        JSONObject testInstructor = new JSONObject();
        testInstructor.put("NoEmail","Gura@Hololive.en");

        Assertions.assertThrows(Exception.class, () ->testHHM.addInstructor(testInstructor));
    }

    // Test if creating the manager will not result to any of the error codes
    @Test
    @Order(7)
    void testNewAddManager() {

        JSONObject testManager = new JSONObject();
        testManager.put("email","Ina@Hololive.en");

        int testValue = testHHM.addInstructor(testManager);

        createdUniqueIds.add(testValue);

        Assertions.assertNotEquals(500, testValue);
        Assertions.assertNotEquals(404, testValue);
        Assertions.assertNotEquals(403, testValue);
        Assertions.assertNotEquals(401, testValue);
    }

//    // Test if creating email duplicated instructor will result to 403 error codes
//    @Order(5)
//    @RepeatedTest(5)
//    void testDuplicateEmailAddInstructor() {
//
//        JSONObject testInstructor = new JSONObject();
//        testInstructor.put("email","Kiara@Hololive.en");
//
//        int testValue = testHHM.addInstructor(testInstructor);
//
//        createdUniqueIds.add(testValue);
//
//        Assertions.assertEquals(403, testValue);
//    }
//
//    // Test whether adding an instructor that does not have an email will throw exceptions
//    @Test
//    @Order(6)
//    void testInvalidKeyAddInstructor(){
//        JSONObject testInstructor = new JSONObject();
//        testInstructor.put("NoEmail","Gura@Hololive.en");
//
//        Assertions.assertThrows(Exception.class, () ->testHHM.addInstructor(testInstructor));
//    }

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
//    int returnedInstrCode = testHHM.systemLogin("InvalidLogin", "InvalidPassword", "Instructor");
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
//    int returnedInstrCode = testHHM.systemLogin("Who is this guy", "validPassword", "Instructor");
//    int returnedManagerCode = testHHM.systemLogin("Who is this guy", "validPassword", "Manager");
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
//    // You can test this by removing your internet connection. That should give you a server issue.
//    // Although database needs to be connected
//    System.out.println("Expected Error if no database connected");
//    int returnedClientCode = testHHM.systemLogin("Who is this guy?", "validPassword", "Client");
//    int returnedInstrCode = testHHM.systemLogin("Who is this guy", "validPassword", "Instructor");
//    int returnedManagerCode = testHHM.systemLogin("Who is this guy", "validPassword", "Manager");
//
//    Assertions.assertEquals(500, returnedClientCode);
//    Assertions.assertEquals(500, returnedInstrCode);
//    Assertions.assertEquals(500, returnedManagerCode);
//  }
    @AfterAll
    static void postDelete(){

      for(Integer each: createdUniqueIds){
          testHHM.getDatabase().removeClient(each);
          testHHM.getDatabase().removeInstructor(each);
          testHHM.getDatabase().removeManager(each);
      }

    }
}
