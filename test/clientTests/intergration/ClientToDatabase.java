package clientTests.intergration;

import Client.Client;
import Client.ClientController;
import database.Dbms;
import database.EmptyQueryException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClientToDatabase {
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

  ClientController clientController1 = new ClientController(client1);

  Dbms testDB = new Dbms("Justyn", "Staff1", "Test-Justyn-Db", "testCollection");

  //Tests adding Client to database
  @Test
  void addClientToDB() throws EmptyQueryException {
    testDB.createClient(clientController1.getClientID(), clientController1.clientToJson());
  }

  @Test
  void readClientFromDB() throws EmptyQueryException {
    JSONObject clientJSON = testDB.readClientData(clientController1.getClientID());
    clientController1.jsonToClient(clientJSON);
    assertEquals("dustin", clientController1.getClientName());
    assertEquals("dcr518@usask.ca", clientController1.getClientEmail());
    assertEquals("Rick", clientController1.getClientInstructor());
    assertEquals("Golds", clientController1.getClientOrg());
    assertEquals(1, clientController1.getClientID());
    assertEquals(29, clientController1.getClientAge());
    assertEquals(177, clientController1.getClientHeight());
    assertEquals(182, clientController1.getClientWeight());
    assertEquals("306-111-1111", clientController1.getClientPhoneNum());
    assertEquals(170, clientController1.getClientWeightGoal());
    assertEquals(2000, clientController1.getClientCals());
    assertEquals(new ArrayList<String>(), clientController1.getClientComment());
    assertEquals(new ArrayList<String>(), clientController1.getClientAllergies());
  }

  @Test
  void removeClientFromDB(){
    testDB.removeClient(clientController1.getClientID());
  }

  }


