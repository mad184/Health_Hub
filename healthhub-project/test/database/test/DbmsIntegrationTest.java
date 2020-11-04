package database.test;

import com.mongodb.MongoWriteException;
import database.EmptyQueryException;
import database.Dbms;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@TestMethodOrder(OrderAnnotation.class)
public class DbmsIntegrationTest {

  private static final String realUserName = "test-user";
  private static final String realPassWord = "healthhub1";
  private static final String dbName = "Test-General-Database";
  private static final String tableName = "testCollection";
  private static JSONArray expectedFullData = new JSONArray();

  static Dbms realDbms = new Dbms(realUserName, realPassWord, dbName, tableName);
  static Random generator = new Random();

  @BeforeAll
  static void initialCreate() {

    JSONArray expectedNicknames = new JSONArray();
    JSONArray expectedAges = new JSONArray();
    JSONArray expectedNames = new JSONArray();
    String expectedManager = "Yagoo";
    String[] createNames = {
      "Matsuri", "Fubuki", "Haato", "Subaru", "Shion", "Aqua", "Korone", "Pekora", "Rushia",
      "Marine", "Kanata", "Coco", "Miko", "Yagoo", "Kiara", "Amelia", "Gura", "", "EnMa"
    };
    int[] createAges = {
      18, 16, 9000, 50, 24, 22, 21, 20, 0, 10, 17, 15, 25, 3501, 27, 28, 29, 111, 60
    };

    ArrayList<List<String>> createNicknames = new ArrayList<>();
    createNicknames.add(Arrays.asList("City Pop Shark", "Apex Predator", "Same-Chan"));
    createNicknames.add(Arrays.asList("Koronen", "Doggo", "Doog"));
    createNicknames.add(Arrays.asList("Miko-chi", "elite-Miko"));
    createNicknames.add(Arrays.asList("Haato", "Haachama"));
    createNicknames.add(Arrays.asList("GOD"));
    createNicknames.add(Arrays.asList("Fubuking", "FBK", ""));
    createNicknames.add(Arrays.asList("Senchou", "Maririn"));
    createNicknames.add(Arrays.asList());
    createNicknames.add(Arrays.asList("Rushifer", "Ruu", "Boing Boing Rushia"));
    createNicknames.add(Arrays.asList("Peko-chan"));
    createNicknames.add(Arrays.asList("PP Tenshi", "Kanatan", "Kabaya"));
    createNicknames.add(Arrays.asList("Aka-chan", "Luna-Hime"));
    createNicknames.add(Arrays.asList("Adidas Lion"));
    createNicknames.add(Arrays.asList("CallioP", "Miss Mori"));
    createNicknames.add(Arrays.asList("Kusotori", "Tenchou"));
    createNicknames.add(Arrays.asList("Ina"));
    createNicknames.add(Arrays.asList("Ame", "Amechan"));
    createNicknames.add(Arrays.asList("Kaichou", "Coco-chi", "Coco-chin"));
    createNicknames.add(Arrays.asList("Sololive", "Baqua", "Akutan"));

    // for loop to create the data for JSONArrays
    for (int i = 0; i < 19; i++) {
      expectedNames.put(createNames[i]);
      expectedAges.put(createAges[i]);
      expectedNicknames.put(createNicknames.get(i));
    }

    int counter = 0;
    for (Object name : expectedNames) {
      JSONObject expectedJson = new JSONObject();
      expectedJson.put("Name", name);
      expectedJson.put("Age", expectedAges.get(counter));
      expectedJson.put("Manager", expectedManager);
      expectedJson.put("Active", true);
      expectedJson.put("Retired", false);
      expectedJson.put("Nicknames", expectedNicknames.get(counter));
      expectedFullData.put(expectedJson);
      counter++;
    }
  }

  @Test
  @Order(1)
  void testCIMCreate() {

    for (int uniqueId = 0; uniqueId < 19; uniqueId++) {
      int finalUniqueId = uniqueId;
      Assertions.assertDoesNotThrow(
          () -> {
            // System.out.println(expectedFullData.getJSONObject(finalUniqueId));
            realDbms.createClient(finalUniqueId, expectedFullData.getJSONObject(finalUniqueId));
            realDbms.createInstructor(finalUniqueId, expectedFullData.getJSONObject(finalUniqueId));
            realDbms.createManager(finalUniqueId, expectedFullData.getJSONObject(finalUniqueId));
          });
    }
  }

  @Order(2)
  // @RepeatedTest(100)
  @Disabled
  void testDuplicateClientCreate() {

    Assertions.assertThrows(
        MongoWriteException.class,
        () ->
            realDbms.createClient(
                generator.nextInt(19), expectedFullData.getJSONObject(generator.nextInt(19))));
  }

  @Order(3)
  // @RepeatedTest(100)
  @Disabled
  void testDuplicateInstrCreate() {

    Assertions.assertThrows(
        MongoWriteException.class,
        () ->
            realDbms.createInstructor(
                generator.nextInt(19), expectedFullData.getJSONObject(generator.nextInt(19))));
  }

  @Order(4)
  // @RepeatedTest(100)
  @Disabled
  void testDuplicateManagerCreate() {

    Assertions.assertThrows(
        MongoWriteException.class,
        () ->
            realDbms.createManager(
                generator.nextInt(19), expectedFullData.getJSONObject(generator.nextInt(19))));
  }

  @Order(5)
  @Test
  void testCIMRead() {

    for (int uniqueId = 0; uniqueId < 19; uniqueId++) {
      int finalUniqueId = uniqueId;
      Assertions.assertDoesNotThrow(
          () -> {
            // System.out.println(expectedFullData.getJSONObject(finalUniqueId));
            realDbms.readClientData(finalUniqueId);
            realDbms.readInstructorData(finalUniqueId);
            realDbms.readManagerData(finalUniqueId);
          });
    }
  }

  @Order(6)
  @Test
  void testDataRead() throws EmptyQueryException {

    for (int uniqueId = 0; uniqueId < 19; uniqueId++) {

      JSONObject expectedData = expectedFullData.getJSONObject(uniqueId);
      JSONObject actualClientData = realDbms.readClientData(uniqueId);
      JSONObject actualInstrData = realDbms.readInstructorData(uniqueId);
      JSONObject actualManagerData = realDbms.readManagerData(uniqueId);

      // Checking the client data
      Assertions.assertEquals(expectedData.get("Name"), actualClientData.get("Name"));
      Assertions.assertEquals(expectedData.get("Age"), actualClientData.get("Age"));
      Assertions.assertEquals(expectedData.get("Manager"), actualClientData.get("Manager"));
      Assertions.assertEquals(expectedData.get("Active"), actualClientData.get("Active"));
      Assertions.assertEquals(expectedData.get("Retired"), actualClientData.get("Retired"));

      // Checking the instructor data
      Assertions.assertEquals(expectedData.get("Name"), actualInstrData.get("Name"));
      Assertions.assertEquals(expectedData.get("Age"), actualInstrData.get("Age"));
      Assertions.assertEquals(expectedData.get("Manager"), actualInstrData.get("Manager"));
      Assertions.assertEquals(expectedData.get("Active"), actualInstrData.get("Active"));
      Assertions.assertEquals(expectedData.get("Retired"), actualInstrData.get("Retired"));

      // Checking the Manager Data
      Assertions.assertEquals(expectedData.get("Name"), actualManagerData.get("Name"));
      Assertions.assertEquals(expectedData.get("Age"), actualManagerData.get("Age"));
      Assertions.assertEquals(expectedData.get("Manager"), actualManagerData.get("Manager"));
      Assertions.assertEquals(expectedData.get("Active"), actualManagerData.get("Active"));
      Assertions.assertEquals(expectedData.get("Retired"), actualManagerData.get("Retired"));

      // Testing the array within array. I recommend using JSONArrays for arrays
      if (((JSONArray) expectedData.get("Nicknames")).isEmpty()) {
        Assertions.assertEquals(
            expectedData.get("Nicknames").toString(), actualClientData.get("Nicknames").toString());
      } else {
        for (int each = 0; each < ((JSONArray) expectedData.get("Nicknames")).length(); each++) {
          Assertions.assertEquals(
              ((JSONArray) expectedData.get("Nicknames")).get(each),
              ((List<String>) actualClientData.get("Nicknames")).get(each));
        }
      }
    }
  }

  // @BeforeAll
  @AfterAll
  static void postDataDeletion() {
    for (int uniqueId = 0; uniqueId < 19; uniqueId++) {
      int finalUniqueId = uniqueId;
      Assertions.assertDoesNotThrow(
          () -> {
            realDbms.removeClient(finalUniqueId);
            realDbms.removeInstructor(finalUniqueId);
            realDbms.removeManager(finalUniqueId);
            // System.out.println(finalUniqueId);
            // System.out.println((JSONObject) allClients.get(finalUniqueId));
          });
    }
  }
}
