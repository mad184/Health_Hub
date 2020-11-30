package staff;

import org.junit.jupiter.api.Test;

import staff.Controllers.InstructorController;
import staff.Models.InstructorModel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InstructorModelControllerTest {

  UserID client_1 = new UserID(123, "Jimmy");
  UserID client_2 = new UserID(456, "Pam");
  UserID client_3 = new UserID(789, "Nick");

  InstructorModel model =
      new InstructorModel(
          "John",
          21,
          "john@usask.ca",
          "306-555-5555",
          175,
          160,
          "Average Joes",
          1,
          new ArrayList<>(),
          "Marcos",
          "Manager1",
          "Dev-Marcos-Db",
          "Instructors");

  InstructorController controller = new InstructorController(model);

  @Test
  void testModel() {
    assertNotNull(model);
  }

  @Test
  void testController() {
    assertNotNull(controller);
  }

  @Test
  void InstructorSetters() {
    controller.setGoalCal(120);
    controller.setCalories(100);
    controller.setAge(20);
    controller.setName("Jones");
    controller.setEmail("Jones@usask.ca");
    controller.setOrganization("");
    controller.setPhoneNumber("306-444-4444");

    assertEquals(controller.getCaloriesGoal(), 120);
    assertEquals(controller.getCalories(), 100);
    assertEquals(controller.getAge(), 20);
    assertEquals(controller.getName(), "Jones");
    assertEquals(controller.getEmail(), "Jones@usas.ca");
    assertEquals(controller.getOrganization(), "");
    assertEquals(controller.getPhoneNumber(), "306-444-4444");
  }

  @Test
  void testGetters() {
    assertEquals(controller.getName(), "John");
    assertEquals(controller.getAge(), 21);
    assertEquals(controller.getEmail(), "john@usask.ca");
    assertEquals(controller.getPhoneNumber(), "306-555-5555");
    assertEquals(controller.getHeight(), 175);
    assertEquals(controller.getWeight(), 160);
    assertEquals(controller.getOrganization(), "Average Joes");
    assertEquals(controller.getId(), 1);
  }

  @Test
  void InstructorClients() {
    ArrayList<UserID> emptyClients = new ArrayList<>();
    ArrayList<UserID> clients = new ArrayList<>();
    clients.add(client_1);
    clients.add(client_3);

    controller.addClient(client_1);
    controller.addClient(client_3);
    assertEquals(controller.getClients(), clients);

    controller.addClient(client_2);
    assertNotEquals(controller.getClients(), clients);
    assertEquals(controller.getClients().get(0), client_1);
    assertEquals(controller.getClients().get(1), client_3);
    assertEquals(controller.getClients().get(2), client_2);

    controller.removeClient(client_2);
    assertEquals(controller.getClients(), clients);

    controller.removeClient(client_3);
    controller.removeClient(client_1);
    assertEquals(controller.getClients(), emptyClients);
  }

  @Test
  void UserIDTest() {
    assertEquals(client_1.getId(), 123);
    assertEquals(client_2.getId(), 456);
    assertEquals(client_3.getId(), 789);
    assertEquals(client_1.getName(), "Jimmy");
    assertEquals(client_2.getName(), "Pam");
    assertEquals(client_3.getName(), "Nick");
  }
}
