package unit;

import client.Client;
import healthhub.HealthHubModel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
                    testHHM.systemLogin("usertest","userpw","Client");
                    testHHM.systemLogin("usertest","userpw","Instructor");
                    testHHM.systemLogin("usertest","userpw","Manager");
                });
    }

    @Test
    //@Disabled
    @Order(5)
    void testLoginValidCredentials(){
        int returnedClientCode = testHHM.systemLogin("validLogin","validPassword","Client");
        int returnedInstrCode = testHHM.systemLogin("validLogin","validPassword","Instructor");
        int returnedManagerCode = testHHM.systemLogin("validLogin","validPassword","Manager");

        Assertions.assertEquals(returnedClientCode,200);
        Assertions.assertEquals(returnedInstrCode,200);
        Assertions.assertEquals(returnedManagerCode,200);
    }

    @Test
    //@Disabled
    @Order(6)
    void testLoginInvalidCredentials(){
        int returnedClientCode = testHHM.systemLogin("InvalidLogin","InvalidPassword","Client");
        int returnedInstrCode = testHHM.systemLogin("InvalidLogin","InvalidPassword","Instructor");
        int returnedManagerCode = testHHM.systemLogin("InvalidLogin","InvalidPassword","Manager");

        Assertions.assertEquals(returnedClientCode,401);
        Assertions.assertEquals(returnedInstrCode,401);
        Assertions.assertEquals(returnedManagerCode,401);
    }

    @Test
    //@Disabled
    @Order(7)
    void testLoginCIMNotFound(){
        int returnedClientCode = testHHM.systemLogin("Who is this guy?","validPassword","Client");
        int returnedInstrCode = testHHM.systemLogin("Who is this guy","validPassword","Instructor");
        int returnedManagerCode = testHHM.systemLogin("Who is this guy","validPassword","Manager");

        Assertions.assertEquals(returnedClientCode,404);
        Assertions.assertEquals(returnedInstrCode,404);
        Assertions.assertEquals(returnedManagerCode,404);
    }

  @Test
  // @Disabled
  @Order(8)
  void testLoginSelectionInvalid() {
    int returnedClientCode = testHHM.systemLogin("Who is this guy?", "validPassword", "Hacker");
    int returnedInstrCode = testHHM.systemLogin("Who is this guy", "validPassword", "Vtuber");
    int returnedManagerCode = testHHM.systemLogin("Who is this guy", "validPassword", "Shrimp");

    Assertions.assertEquals(returnedClientCode, -1);
    Assertions.assertEquals(returnedInstrCode, -1);
    Assertions.assertEquals(returnedManagerCode, -1);
    }
}
