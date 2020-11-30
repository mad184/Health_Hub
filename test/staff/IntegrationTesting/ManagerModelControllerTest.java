package staff.IntegrationTesting;

import org.junit.jupiter.api.Test;
import staff.Controllers.ManagerController;
import staff.Models.ManagerModel;
import staff.UserID;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerModelControllerTest {

  UserID Instructor_1 = new UserID(147, "Izzy");
  UserID Instructor_2 = new UserID(258, "Carl");
  UserID Instructor_3 = new UserID(258, "Jessy");
  UserID Instructor_4 = new UserID(258, "Aubrey");

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

  @Test
  void ManagerInstructors() {
    ArrayList<UserID> instructorTestList = new ArrayList<>();
    instructorTestList.add(Instructor_1);
    instructorTestList.add(Instructor_2);
    instructorTestList.add(Instructor_3);
    instructorTestList.add(Instructor_4);

    controller.addInstructor(Instructor_1);
    controller.addInstructor(Instructor_2);
    assertNotNull(controller);
    assertNotEquals(controller.getInstructors(), instructorTestList);

    controller.addInstructor(Instructor_3);
    controller.addInstructor(Instructor_4);
    assertEquals(controller.getInstructors(), instructorTestList);

    controller.removeInstructor(Instructor_1);
    assertNotEquals(controller.getInstructors(), instructorTestList);

    instructorTestList.remove(Instructor_1);
    assertEquals(controller.getInstructors(), instructorTestList);

    controller.removeInstructor(Instructor_3);
    instructorTestList.remove(Instructor_3);
    assertEquals(controller.getInstructors(), instructorTestList);

    controller.removeInstructor(Instructor_2);
    assertFalse(controller.getInstructors().contains(Instructor_2));
    controller.removeInstructor(Instructor_4);

    assertTrue(controller.getInstructors().isEmpty());
  }
}
