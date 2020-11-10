package unit;

import client.Client;
import healthhub.HealthHubModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.jmock.Mockery;
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
}
