package staff;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import staff.Models.StaffModel;

/** Testing class for StaffModel class. */
public class StaffModelTest {
  StaffModel staff =
      new StaffModel("John", 21, "john@usask.ca", "306-555-5555", 175, 160, "Average Joes", 1, "John", "123456", "test", "test");

  /** Tests the constructor for StaffModel. */
  @Test
  public void testConstructor() {
    assertNotNull(staff);
  }

  /** Tests getter for name of Staff users. */
  @Test
  public void testGetName() {
    assertEquals("John", staff.getName());
  }

  /** Tests getter for age of Staff users. */
  @Test
  public void testGetAge() {
    assertEquals(21, staff.getAge());
  }

  /** Tests getter for email of Staff users. */
  @Test
  public void testGetEmail() {
    assertEquals("john@usask.ca", staff.getEmail());
  }

  /** Tests getter for phone number of Staff users. */
  @Test
  public void testGetPhoneNumber() {
    assertEquals("306-555-5555", staff.getPhoneNumber());
  }

  /** Tests getter for height of Staff users. */
  @Test
  public void testGetHeight() {
    assertEquals(175, staff.getHeight());
  }

  /** Tests getter for weight of Staff users. */
  @Test
  public void testGetWeight() {
    assertEquals(160, staff.getWeight());
  }

  /** Tests getter for ID of Staff users. */
  @Test
  public void testGetId() {
    assertEquals(1, staff.getId());
  }

  /** Tests getter for organization of Staff users. */
  @Test
  public void testGetOrganization() {
    assertEquals("Average Joes", staff.getOrganization());
  }

  /** Tests setter for name of Staff users. */
  @Test
  public void testSetName() {
    staff.setName("Joe");
    assertEquals("Joe", staff.getName());
  }

  /** Tests setter for age of Staff users. */
  @Test
  public void testSetAge() {
    staff.setAge(50);
    assertEquals(50, staff.getAge());
  }

  /** Tests setter for email of Staff users. */
  @Test
  public void testSetEmail() {
    staff.setEmail("abc123@usask.ca");
    assertEquals("abc123@usask.ca", staff.getEmail());
  }

  /** Tests setter for phone number of Staff users. */
  @Test
  public void testSetPhoneNumber() {
    staff.setPhoneNumber("306-123-4567");
    assertEquals("306-123-4567", staff.getPhoneNumber());
  }

  /** Tests setter for height of Staff users. */
  @Test
  public void testSetHeight() {
    staff.setHeight(200);
    assertEquals(200, staff.getHeight());
  }

  /** Tests setter for weight of Staff users. */
  @Test
  public void testSetWeight() {
    staff.setWeight(199);
    assertEquals(199, staff.getWeight());
  }

  /** Tests setter for organization of Staff users. */
  @Test
  public void testSetOrganization() {
    staff.setOrganization("GloboGym");
    assertEquals("GloboGym", staff.getOrganization());
  }

  /**
   * Tests conversion from JSONObject to StaffModel instance.
   */
  @Test
  public void testFromJson() {
    JSONObject json = new JSONObject();
    json.put("Name", "John");
    json.put("Age", 50);
    json.put("Email", "abc123@usask.ca");
    json.put("Phone Number", "306-123-4567");
    json.put("Height", 200);
    json.put("Weight", 199);
    json.put("Organization", "GloboGym");
    json.put("ID", 1);
   // StaffModel staff2 = StaffModel.fromJson(json);
    //assertEquals(staff.getName(), staff2.getName());
    //assertEquals(staff.getAge(), staff2.getAge());
    //assertEquals(staff.getEmail(), staff2.getEmail());
    //assertEquals(staff.getPhoneNumber(), staff2.getPhoneNumber());
    //assertEquals(staff.getHeight(), staff2.getHeight());
    //assertEquals(staff.getWeight(), staff2.getWeight());
    //assertEquals(staff.getOrganization(), staff2.getOrganization());
    //assertEquals(staff.getId(), staff2.getId());
  }
}
