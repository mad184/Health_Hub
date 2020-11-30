package staff;

import org.junit.jupiter.api.Test;
import staff.Controllers.ManagerController;
import staff.Models.ManagerModel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerModelControllerTest {

  ManagerModel model =
      new ManagerModel(
          "Steff",
          33,
          "steff@usask.ca",
          "306-888-8888",
          168,
          110,
          "One Organization",
          3,
          new ArrayList<>(),
          "Marcos",
          "Manager1",
          "Dev-Marcos-Db",
          "Instructors");

  ManagerController controller = new ManagerController(model);

  @Test
  void ModelTest() {
    assertNotNull(model);
  }

  @Test
  void ControllerTest() {
    assertNotNull(controller);
  }

  @Test
  void testName() {
    assertEquals(controller.getName(), "Steff");
    controller.setName("Stef");
    assertEquals(controller.getName(), "Stef");
  }

  @Test
  void testAge() {
    assertEquals(controller.getAge(), 33);
    controller.setAge(32);
    assertEquals(controller.getAge(), 32);
  }

  @Test
  void testPhoneNumber() {
    assertEquals(controller.getPhoneNumber(), "306-888-8888");
    controller.setPhoneNumber("306-777-7777");
    assertEquals(controller.getPhoneNumber(), "306-777-7777");
  }

  @Test
  void testEmail() {
    assertEquals(controller.getEmail(), "steff@usask.ca");
    controller.setEmail("stef@usask.ca");
    assertEquals(controller.getEmail(), "stef@usask.ca");
  }

  @Test
  void testHeight() {
    assertEquals(controller.getHeight(), 168);
    controller.setHeight(167);
    assertEquals(controller.getHeight(), 167);
  }

  @Test
  void testWeight() {
    assertEquals(controller.getWeight(), 110);
    controller.setWeight(108);
    assertEquals(controller.getWeight(), 108);
  }

  @Test
  void testOrganization() {
    assertEquals(controller.getOrganization(), "One Organization");
    controller.setOrganization("Smarty");
    assertEquals(controller.getOrganization(), "Smarty");
  }

  @Test
  void testId() {
    assertEquals(controller.getId(), 3);
  }
}
