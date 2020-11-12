package clientTests.unit;

import Client.Client;
import Client.ClientController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.ArrayList;

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

  ClientController clientController1 =
          new ClientController(client1);

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
    assertEquals(170, client1.getWeightGoal());
    assertEquals(2000, client1.getCalories());
    assertNull(client1.getAllergies());
    assertNull(client1.getComment());
  }

  @Test
  void changeName() {
    client1.setName("John");
    assertEquals("John", client1.getName());
  }

  @Test
  void changeAge() {
    client1.setAge(13);
    assertEquals(13, client1.getAge());
  }

  @Test
  void getJsonFromClient(){
    JSONObject jsonClient = clientController1.clientToJson();
    System.out.println(jsonClient);
    JSONAssert.assertEquals("{name:dustin}", jsonClient, false);
    JSONAssert.assertEquals("{email:dcr518@usask.ca}", jsonClient, false);
    JSONAssert.assertEquals("{instructor:Rick}", jsonClient, false);
    JSONAssert.assertEquals("{organization:Golds}", jsonClient, false);
    JSONAssert.assertEquals("{age:29}", jsonClient, false);
    JSONAssert.assertEquals("{id:1}", jsonClient, false);
    JSONAssert.assertEquals("{height:177}", jsonClient, false);
    JSONAssert.assertEquals("{weight:182}", jsonClient, false);
    JSONAssert.assertEquals("{phoneNumber:306-111-1111}", jsonClient, false);
    JSONAssert.assertEquals("{goalWeight:170}", jsonClient, false);
    JSONAssert.assertEquals("{goalCals:3000}", jsonClient, false);
    JSONAssert.assertEquals("{calories:2000}", jsonClient, false);
  }

  @Test void getClientFromJson(){
    JSONObject jsonClient = clientController1.clientToJson();
    Client clientFromJson = clientController1.jsonToClient(jsonClient);
    assertEquals("dustin", clientFromJson.getName());
    assertEquals("dcr518@usask.ca", clientFromJson.getEmail());
    assertEquals("Rick", clientFromJson.getInstructor());
    assertEquals("Golds", clientFromJson.getOrganization());
    assertEquals(1, clientFromJson.getId());
    assertEquals(29, clientFromJson.getAge());
    assertEquals(177, clientFromJson.getHeight());
    assertEquals(182, clientFromJson.getWeight());
    assertEquals("306-111-1111", clientFromJson.getPhoneNum());
    assertEquals(170, clientFromJson.getWeightGoal());
    assertEquals(2000, clientFromJson.getCalories());
    assertEquals(new ArrayList<String>(), clientFromJson.getComment());
    assertEquals(new ArrayList<String>(), clientFromJson.getAllergies());
  }
}
