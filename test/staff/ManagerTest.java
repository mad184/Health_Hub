package staff;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ManagerTest {

  Manager manager =
      new Manager(
          "Jim",
          "jim456@usask.ca",
          "Rick",
          "Gold",
          "3061234556",
          "manager",
          36,
          1225,
          170,
          180,
          3000,
          2000,
          null);

  /** Test Manager when the object is created. It focus on the correctness of its creation */
  @Test
  void testObjectCreate() {

    assertEquals("Jim", manager.getName());
    assertEquals("jim456@usask.ca", manager.getEmail());
    assertEquals("Rick", manager.getInstructor());
    assertEquals("3061234556", manager.getPhoneNum());
    assertEquals("manager", manager.getPermission());
    assertEquals(36, manager.getAge());
    assertEquals(1225, manager.getId());
    assertEquals(170, manager.getWeight());
    assertEquals(180, manager.getHeight());
    assertEquals(3000, manager.getGoal(), 0.0001);
    assertEquals(2000, manager.getCalories(), 0.001);
    assertNull(manager.getComment());
  }

  /**
   * Test changing attributes on manager by calling setters. It focus in the correctness on the
   * setters
   */
  @Test
  void testObjectChange() {

    manager.setName("Jimmy");
    manager.setEmail("jimmy456@usask.ca");
    manager.setInstructor("Jessica");
    manager.setPhoneNum("3061233211");
    manager.setAge(37);
    manager.setGoal(3500);
    manager.setCalories(2500);
    manager.setComment("Testing a comment");

    assertEquals("Jimmy", manager.getName());
    assertEquals("jimmy456@usask.ca", manager.getEmail());
    assertEquals("Jessica", manager.getInstructor());
    assertEquals(37, manager.getAge());
    assertEquals(3500, manager.getGoal());
    assertEquals(2500, manager.getCalories());
    assertEquals("Testing a comment", manager.getComment());
  }

  /** Test removing a calorie on manager */
  @Test
  void testRemoveCalories() {

    manager.removeCalories(2500);
    assertEquals(0, manager.getCalories(), 0.0001);
  }

  /** Test the previous tests all at once. Notice that order of function call DO MATTER */
  @Test
  void testObject() {

    testObjectCreate();
    testObjectChange();
    testRemoveCalories();
  }
}
