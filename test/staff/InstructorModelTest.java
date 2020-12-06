package staff;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.jmock.Mockery;
import staff.Interfaces.Controller;
import staff.Models.InstructorModel;

public class InstructorModelTest {
  Mockery context = new Mockery();

  //final Database database = context.mock(Database.class);
  final Controller controller = context.mock(Controller.class);

  // Please not that the database name is just for testing purpose - Dev-Marcos-Db = personal testing Marcos db
  // It can be changed
  InstructorModel model = new InstructorModel(
          "John",
          "john1",
          21,
          "john@usask.ca",
          "306-555-5555",
          175,
          160,
          "Average Joes",
          1,
          "JhonWick",
          "Jhon123",
          "Dev-Marcos-Db",
          "Instructors"
          );

  /** Tests the constructor of the InstructorModel */
  @Test
  void testConstructor() {
    assertNotNull(model);
  }

  /** Tests deleteItself method */
  @Test
  void testDeleteItself() {
    // TODO: Figure out how to test this
  }

  /** Tests removeCal method */
  @Test
  void testRemoveCal() {
    // TODO: Implement this test
  }

  /** Tests removeClient method, when the client is assigned to the Instructor */
  @Test
  void testRemoveClient() {
    // TODO: Implement this test
  }

  /** Tests removeClient method, when the Client is not assigned to the Instructor */
  @Test
  void testRemoveClientNotAssigned() {
    // TODO: Implement this test
  }

  /** Tests addClient method when the client is not assigned to the Instructor */
  @Test
  void testAddClient() {
    // TODO: Implement this test
  }

  /** Tests addClient method when the client is already assigned to the Instructor */
  @Test
  void testAddClientAlreadyAssigned() {
    // TODO: Implement this test
  }

  /** Tests addComment method */
  @Test
  void testAddComment() {
    // TODO: Implement this test
  }

  /** Tests removeComment method */
  @Test
  void testRemoveComment() {
    // TODO: Implement this test
  }

  /** Tests getClientInfo method */
  @Test
  void testGetClientInfo() {
    // TODO: Implement this test
  }

  /** Tests getInstructorInfo method */
  @Test
  void testGetInstructorInfo() {
    // TODO: Implement this test
  }

  /** Tests getInstructorID method */
  @Test
  void testGetInstructorID() {
    // TODO: Implement this class
  }

  /** Tests removeInstructor method */
  @Test
  void testRemoveInstructor() {
    // TODO: Implement this class
  }

  /** Tests removeInstructor method when Instructor is not in system */
  @Test
  void testRemoveInstructorNotAssigned() {
    // TODO: Implement this test
  }

  /** Tests removeManager method */
  @Test
  void testRemoveManager() {
    // TODO: Implement this test
  }

  /** Tests addManager method */
  @Test
  void testAddManager() {
    // TODO: Implement this test
  }

  /** Tests instructorToJson method */
  @Test
  void testInstructorToJson() {
    // TODO: Implement this test
  }

  /** Tests jsonToInstructor method */
  void testJsonToInstructor() {
    // TODO: Implement this test
  }
}
