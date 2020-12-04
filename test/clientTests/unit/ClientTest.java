package clientTests.unit;

import API.ExerciseItem;
import API.FoodItem;
import Client.Client;
import Client.ClientController;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClientTest {
  Client client1 =
      new Client(
          "dustin",
          "dcr518@usask.ca",
          "password",
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
  void changeEmail() {
    client1.setEmail("jmp447@usask.ca");
    assertEquals("jmp447@usask.ca", client1.getEmail());
  }

  @Test
  void changePhoneNumber() {
    client1.setPhoneNum("306-850-0727");
    assertEquals("306-850-0727", client1.getPhoneNum());
  }

  @Test
  void changeWeight(){
    client1.setWeight(180);
    assertEquals(180, client1.getWeight());
  }

  @Test
  void changeWeightGoal(){
    client1.setWeightGoal(175);
    assertEquals(175, client1.getWeightGoal());
  }

  @Test
  void changeCalGoal(){
    client1.setCalGoal(2500);
    assertEquals(2500, client1.getCalGoal());
  }

  @Test
  void changeCalories(){
    client1.setCalories(2000);
    assertEquals(2000, client1.getCalories());
  }

  @Test
  void addAllergies(){
    ArrayList<String> allergyList = new ArrayList<>();
    allergyList.add("Peanuts");
    allergyList.add("Shellfish");
    client1.setAllergies(allergyList);
    assertEquals(allergyList, client1.getAllergies());
  }

  @Test
  void changeInstructor(){
    client1.setInstructor("Eddie");
    assertEquals("Eddie", client1.getInstructor());
  }

  @Test
  void changeOrganization(){
    client1.setOrganization("8th Street");
    assertEquals("8th Street", client1.getOrganization());
  }

  @Test
  void changePassword(){
    client1.setPassword("12345");
    assertEquals("12345", client1.getPassword());
  }

  @Test
  void testToString(){
    assertEquals("Client{name='dustin', email='dcr518@usask.ca', password='password', instructor='Rick', " +
            "organization='Golds', phoneNumber='306-111-1111', id=1, age=29, height=177, weight=182, goalWeight=170, " +
            "goalCals=3000, calories=2000, allergies=null, comment=null, profilePicture=null, breakfastFoods=[], " +
            "lunchFoods=[], dinnerFoods=[], snackFoods=[], exercises=[]}", client1.toString());
  }

  @Test
  void addFood(){
    clientController1.addClientBreakfastFood(new FoodItem("Donut", 1.0, 100));
    clientController1.addClientBreakfastFood(new FoodItem("Donut", 1.0, 100));
    clientController1.addClientLunchFood(new FoodItem("Toast", 1.0, 100));
    clientController1.addClientDinnerFood(new FoodItem("Pizza", 1.0, 100));
    clientController1.addClientSnackFood(new FoodItem("Chips", 1.0, 100));
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

  @Test
  void getJsonFromClient() {
    JSONObject jsonClient = clientController1.clientToJson();
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
    //Have to test food methods being converted to json
    //JSONAssert.assertEquals("{breakfastFoods:[{\"foodName\":\"Donut\",\"servingAmount\":1.0,\"calories\":100}]}", jsonClient, false);
  }

  @Test
  void getClientFromJson() {
    clientController1.addClientBreakfastFood(new FoodItem("Donut", 1.0, 100));
    clientController1.addClientLunchFood(new FoodItem("Toast", 1.0, 100));
    clientController1.addClientDinnerFood(new FoodItem("Pizza", 1.0, 100));
    clientController1.addClientSnackFood(new FoodItem("Chips", 1.0, 100));
    JSONObject jsonClient = clientController1.clientToJson();
    clientController1.jsonToClient(jsonClient);
    Client clientFromJson = clientController1.getModel();
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
    assertEquals("Donut", clientFromJson.getBreakfastFoods().get(0).getFoodName());
    assertEquals("Toast", clientFromJson.getLunchFoods().get(0).getFoodName());
    assertEquals("Pizza", clientFromJson.getDinnerFoods().get(0).getFoodName());
    assertEquals("Chips", clientFromJson.getSnackFoods().get(0).getFoodName());
    assertEquals(1.0, clientFromJson.getBreakfastFoods().get(0).getServingAmount());
    assertEquals(1.0, clientFromJson.getLunchFoods().get(0).getServingAmount());
    assertEquals(1.0, clientFromJson.getDinnerFoods().get(0).getServingAmount());
    assertEquals(1.0, clientFromJson.getSnackFoods().get(0).getServingAmount());
    assertEquals(100, clientFromJson.getBreakfastFoods().get(0).getCalories());
    assertEquals(100, clientFromJson.getLunchFoods().get(0).getCalories());
    assertEquals(100, clientFromJson.getDinnerFoods().get(0).getCalories());
    assertEquals(100, clientFromJson.getSnackFoods().get(0).getCalories());
  }
}
