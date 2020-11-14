package unit;

import client.Client;
import healthhub.HealthHubModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import staff.Instructor;
import staff.Manager;

@TestMethodOrder(OrderAnnotation.class)
public class HealthHubModelUnitTest {

  static HealthHubModel testHHM = new HealthHubModel();
  Client testClient = new Client();
  Manager testManager = new Manager();
  Instructor testInstructor = new Instructor();

  @Test
  @Order(1)
  void testAddClient() {

    Assertions.assertDoesNotThrow(
        () -> {
          testHHM.addClient(testClient);
        });
  }

  @Test
  @Order(2)
  void testAddInstructor() {

    Assertions.assertDoesNotThrow(
        () -> {
          testHHM.addInstructor(testInstructor);
        });
  }

  @Test
  @Order(3)
  void testAddManager() {
    Assertions.assertDoesNotThrow(
        () -> {
          testHHM.addManager(testManager);
        });
  }

  @Test
  @Order(4)
  void testLoginRunning() {
    Assertions.assertDoesNotThrow(
        () -> {
          testHHM.systemLogin("usertest", "userpw", "Client");
          testHHM.systemLogin("usertest", "userpw", "Instructor");
          testHHM.systemLogin("usertest", "userpw", "Manager");
        });
  }

  @Test
  // @Disabled
  @Order(5)
  void testLoginValidCredentials() {
    System.out.println("Expected Error if no database connected");
    int returnedClientCode = testHHM.systemLogin("validLogin", "validPassword", "Client");
    int returnedInstrCode = testHHM.systemLogin("validLogin", "validPassword", "Instructor");
    int returnedManagerCode = testHHM.systemLogin("validLogin", "validPassword", "Manager");

    Assertions.assertEquals(200, returnedClientCode);
    Assertions.assertEquals(200, returnedInstrCode);
    Assertions.assertEquals(200, returnedManagerCode);
  }

  @Test
  // @Disabled
  @Order(6)
  void testLoginInvalidCredentials() {
    System.out.println("Expected Error if no database connected");
    int returnedClientCode = testHHM.systemLogin("InvalidLogin", "InvalidPassword", "Client");
    int returnedInstrCode = testHHM.systemLogin("InvalidLogin", "InvalidPassword", "Instructor");
    int returnedManagerCode = testHHM.systemLogin("InvalidLogin", "InvalidPassword", "Manager");

    Assertions.assertEquals(401, returnedClientCode);
    Assertions.assertEquals(401, returnedInstrCode);
    Assertions.assertEquals(401, returnedManagerCode);
  }

  @Test
  // @Disabled
  @Order(7)
  void testLoginCIMNotFound() {
    int returnedClientCode = testHHM.systemLogin("Who is this guy?", "validPassword", "Client");
    int returnedInstrCode = testHHM.systemLogin("Who is this guy", "validPassword", "Instructor");
    int returnedManagerCode = testHHM.systemLogin("Who is this guy", "validPassword", "Manager");

    Assertions.assertEquals(404, returnedClientCode);
    Assertions.assertEquals(404, returnedInstrCode);
    Assertions.assertEquals(404, returnedManagerCode);
  }

  @Test
  // @Disabled
  @Order(8)
  void testLoginSelectionInvalid() {
    System.out.println("Expected Error if no database connected");
    int returnedClientCode = testHHM.systemLogin("Who is this guy?", "validPassword", "Hacker");
    int returnedInstrCode = testHHM.systemLogin("Who is this guy", "validPassword", "Vtuber");
    int returnedManagerCode = testHHM.systemLogin("Who is this guy", "validPassword", "Shrimp");

    Assertions.assertEquals(-1, returnedClientCode);
    Assertions.assertEquals(-1, returnedInstrCode);
    Assertions.assertEquals(-1, returnedManagerCode);
  }

  @Test
  // @Disabled
  @Order(9)
  void testLoginServerIssues() {
    // You can test this by removing your internet connection. That should give you a server issue.
    // Although database needs to be connected
    System.out.println("Expected Error if no database connected");
    int returnedClientCode = testHHM.systemLogin("Who is this guy?", "validPassword", "Client");
    int returnedInstrCode = testHHM.systemLogin("Who is this guy", "validPassword", "Instructor");
    int returnedManagerCode = testHHM.systemLogin("Who is this guy", "validPassword", "Manager");

    Assertions.assertEquals(500, returnedClientCode);
    Assertions.assertEquals(500, returnedInstrCode);
    Assertions.assertEquals(500, returnedManagerCode);
  }
}
