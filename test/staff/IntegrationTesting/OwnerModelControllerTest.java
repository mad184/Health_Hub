package staff.IntegrationTesting;

import org.junit.jupiter.api.Test;

import staff.Controllers.OwnerController;
import staff.Models.OwnerModel;
import staff.UserID;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OwnerModelControllerTest {

  OwnerModel model =
      new OwnerModel(
          "Andrew",
          38,
          "Andrew@usask.ca",
          "306-123-4567",
          180,
          180,
          "Smarty",
          8,
          new ArrayList<>(),
          new ArrayList<>(),
          "Marcos",
          "Manager1",
          "Dev-Marcos-Db",
          "Instructors");

  OwnerController controller = new OwnerController(model);

  @Test
  void testModel() {
    assertNotNull(model);
  }

  @Test
  void testController() {
    assertNotNull(controller);
  }

  @Test
  void testName() {
    assertEquals(controller.getName(), "Andrew");
    controller.setName("Andre");
    assertEquals(controller.getName(), "Andre");
  }

  @Test
  void testAge() {
    assertEquals(controller.getAge(), 38);
    controller.setAge(39);
    assertEquals(controller.getAge(), 39);
  }

  @Test
  void testEmail() {
    assertEquals(controller.getEmail(), "Andrew@usask.ca");
    controller.setEmail("Andre@usask.ca");
    assertEquals(controller.getEmail(), "Andre@usask.ca");
  }

  @Test
  void testOrganization() {
    assertEquals(controller.getOrganization(), "Smarty");
    controller.setOrganization("Smarties");
    assertEquals(controller.getOrganization(), "Smarties");
  }

  @Test
  void testId() {
    assertEquals(controller.getId(), 8);
  }

  @Test
  void testHeight() {
    assertEquals(controller.getHeight(), 180);
    controller.setHeight(181);
    assertEquals(controller.getHeight(), 181);
  }

  @Test
  void testWeight() {
    assertEquals(controller.getWeight(), 180);
    controller.setWeight(190);
    assertEquals(controller.getWeight(), 190);
  }

  /**
   * @Test void testInstructors(){ UserID instructor_1 = new UserID(1, "Jimmy"); UserID instructor_2
   * = new UserID(2, "Pam"); UserID instructor_3 = new UserID(3, "Nick");
   *
   * <p>ArrayList<UserID> instructors = new ArrayList<>(); instructors.add(instructor_1);
   * instructors.add(instructor_2); instructors.add(instructor_3);
   *
   * <p>TODO: controller and model should have a method to add/remove/get instructors since the
   * model constructor TODO: does require a ArrayLists<>() for instructors }
   */
  @Test
  void testManagers() {
    UserID manager_1 = new UserID(1, "Jeff");
    UserID manager_2 = new UserID(2, "Mike");
    UserID manager_3 = new UserID(3, "Casey");

    ArrayList<UserID> managers = new ArrayList<>();
    managers.add(manager_1);
    managers.add(manager_2);
    managers.add(manager_3);

    assertNotNull(controller.getManagers());
    assertTrue(controller.getManagers().isEmpty());

    controller.addManager(manager_1);
    assertFalse(controller.getManagers().isEmpty());

    controller.addManager(manager_2);
    controller.addManager(manager_3);
    assertEquals(controller.getManagers(), managers);

    controller.removeManager(manager_3);
    assertNotEquals(controller.getManagers(), managers);

    controller.removeManager(manager_1);
    assertEquals(controller.getManagers().get(0), manager_2);

    controller.removeManager(manager_2);
    assertTrue(controller.getManagers().isEmpty());
  }
}
