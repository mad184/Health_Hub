package Client;

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
                  3000.0,
                  2000.0,
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
    assertEquals(3000.0, client1.getGoal(), 0.001); // delta means accurracy
    assertEquals(2000.0, client1.getCalories(), 0.001); // delta means accurracy
    assertNull(client1.getAllergies());
    assertNull(client1.getComment());
  }

  @Test
  void changeName(){
    client1.setName("John");
    assertEquals("John", client1.getName());
  }

  void changeAge(){
    client1.setAge(13);
    assertEquals(13, client1.getAge());
  }
}
