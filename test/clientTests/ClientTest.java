package clientTests;

import Client.Client;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
  Client client1 =
      new Client(
          "dustin",
          "dcr518@usask.ca",
          "Rick",
          "Golds",
          1,
          29,
          177,
          182,
          "306-111-1111",
          170,
          3000,
          2000,
          null,
              null,
              null);

  @Test
  void testObjectCreate() {
    assertEquals("dustin", client1.getName());
    assertEquals("dcr518@usask.ca", client1.getEmail());
    assertEquals("Rick", client1.getInstructor());
    assertEquals("Golds", client1.getOrganization());
    assertEquals(1, client1.getId());
    assertEquals(29, client1.getAge());
    assertEquals(177, client1.getHeight());
    assertEquals(182, client1.getWeight());
    assertEquals("306-111-1111", client1.getPhoneNum());
    assertEquals(170, client1.getWeightGoal()); // 0.0001 accuracy due to comparison of double
    assertEquals(2000, client1.getCalories()); // 0.0001 accuracy due to comparison of double
    assertNull(client1.getAllergies());
    assertNull(client1.getComment());
  }

  @Test
  void changeName() {
    client1.setName("John");
    assertEquals("John", client1.getName());
  }

  void changeAge() {
    client1.setAge(13);
    assertEquals(13, client1.getAge());
  }
}
