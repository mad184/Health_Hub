package clientTests.integration;

import API.ExerciseItem;
import API.FoodItem;
import Client.Client;
import Client.ClientController;
import database.Dbms;
import database.EmptyQueryException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientToDatabase {
  Client client1 =
      new Client(
          "dustin",
          "dcr518@usask.ca",
          "1234",
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
          null,
          new ArrayList<FoodItem>(),
          new ArrayList<FoodItem>(),
          new ArrayList<FoodItem>(),
          new ArrayList<FoodItem>(),
          new ArrayList<ExerciseItem>(),
          null);

  ClientController clientController1 = new ClientController(client1);

  Dbms testDB =
      new Dbms("production_user", "healthhub1", "Test-Production-Database", "testCollection");

  // Tests adding Client to database
  @Test
  void addClientToDB() throws EmptyQueryException {
    clientController1.addClientBreakfastFood(new FoodItem("Donut", 1.0, 100));
    clientController1.addClientBreakfastFood(new FoodItem("Donut", 1.0, 100));
    clientController1.addClientLunchFood(new FoodItem("Toast", 1.0, 100));
    clientController1.addClientDinnerFood(new FoodItem("Pizza", 1.0, 100));
    clientController1.addClientSnackFood(new FoodItem("Chips", 1.0, 100));
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
    assertEquals("Donut", clientController1.getClientBreakfastFoods().get(0).getFoodName());
    assertEquals("Toast", clientController1.getClientLunchFoods().get(0).getFoodName());
    assertEquals("Pizza", clientController1.getClientDinnerFoods().get(0).getFoodName());
    assertEquals("Chips", clientController1.getClientSnackFoods().get(0).getFoodName());
    assertEquals(1.0, clientController1.getClientBreakfastFoods().get(0).getServingAmount());
    assertEquals(1.0, clientController1.getClientLunchFoods().get(0).getServingAmount());
    assertEquals(1.0, clientController1.getClientDinnerFoods().get(0).getServingAmount());
    assertEquals(1.0, clientController1.getClientSnackFoods().get(0).getServingAmount());
    assertEquals(100, clientController1.getClientBreakfastFoods().get(0).getCalories());
    assertEquals(100, clientController1.getClientLunchFoods().get(0).getCalories());
    assertEquals(100, clientController1.getClientDinnerFoods().get(0).getCalories());
    assertEquals(100, clientController1.getClientSnackFoods().get(0).getCalories());
  }
}
