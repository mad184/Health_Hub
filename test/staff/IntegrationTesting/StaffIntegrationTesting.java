package staff.IntegrationTesting;

import database.EmptyQueryException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import staff.Controllers.InstructorController;
import staff.Controllers.ManagerController;
import staff.Controllers.OwnerController;
import staff.InstructorViews.InstructorView;
import staff.Models.InstructorModel;
import staff.Models.ManagerModel;
import staff.Models.OwnerModel;
import staff.StaffToDB;
import staff.UserID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class StaffIntegrationTesting {

  StaffToDB dbAccess = new StaffToDB();

  InstructorModel instructorModel =
      new InstructorModel(
          "John",
          "john1",
          21,
          "john@usask.ca",
          "306-555-5555",
          175,
          160,
          "Average Joes",
          1,
              "test-user",
              "healthhub1",
              "Test-General-Database",
              "testCollection");

  ManagerModel managerModel =
      new ManagerModel(
          "Steff",
          "steff1",
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

  OwnerModel ownerModel =
      new OwnerModel(
          "Andrew",
          "andrew1",
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

  InstructorController instructorController = new InstructorController(instructorModel);

  ManagerController managerController = new ManagerController(managerModel);

  OwnerController ownerController = new OwnerController(ownerModel);

  UserID Instructor_1 = new UserID(instructorController.getId(), instructorController.getName());

  UserID Manager_1 = new UserID(managerController.getId(), managerController.getName());

  UserID Owner_1 = new UserID(ownerController.getId(), ownerController.getName());

  @Test
  void testStaffToDb() {
    assertNotNull(dbAccess);
  }

  @Test
  void testInstructorModel() {
    assertNotNull(instructorModel);
  }

  @Test
  void testManagerModel() {
    assertNotNull(managerModel);
  }

  @Test
  void testOwnerModel() {
    assertNotNull(ownerModel);
  }

  @Test
  void testAddInstructorToDb() throws EmptyQueryException {
    //REMOVE FROM DB PLEASE
    assertNotNull(instructorController.toJson());
    dbAccess.createInstructor(instructorController.getId(), instructorController.toJson());
    assertNotNull(dbAccess.getInstructor(instructorController.getId()));
    //dbAccess.removeInstructor(instructorController.getId());
  }

  @Test
  void testAddManagerToDb() throws EmptyQueryException {
    assertNotNull(managerModel.toJson());
    dbAccess.createManager(managerController.getId(), managerController.toJson());
    assertNotNull(dbAccess.getManager(managerController.getId()));
    dbAccess.removeInstructor(instructorController.getId());
  }

  @Test
  void testManagerAddInstructor() {
    assertNotNull(Instructor_1);
    managerController.addInstructor(Instructor_1);
  }
}
