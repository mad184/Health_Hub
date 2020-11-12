package clientTests.Intergration;

import Client.Client;
import Client.ClientController;
import database.Dbms;
import database.EmptyQueryException;
import org.junit.jupiter.api.Test;

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
  void removeClientFromDB(){
    testDB.removeClient(clientController1.getClientID());
  }
  }


