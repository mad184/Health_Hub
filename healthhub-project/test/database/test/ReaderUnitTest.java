package database.test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoSecurityException;
import com.mongodb.util.JSON;
import database.EmptyQueryException;
import database.Reader;
import database.Writer;
import database.JsonObjectException;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;

@TestMethodOrder(OrderAnnotation.class)
public class ReaderUnitTest {
    private static final String realUserName = "test-user";
    private static final String realPassWord = "healthhub1";
    private static final String dbName = "Test-General-Database";
    private static final String tableName = "testCollection";

    private static final String realUriString =
            "mongodb+srv://"
                    + realUserName
                    + ":"
                    + realPassWord
                    + "@healthhub-cluster.7y7j0.mongodb.net/"
                    + dbName
                    + "?retryWrites=true&w=majority";

    static Reader realCon = new Reader(realUriString, dbName, tableName);

    @BeforeAll
    static void preWriteData(){
        Writer preWriter = new Writer(realUriString,dbName,tableName);

        JSONObject testAdd = new JSONObject();
        testAdd.put("Name", "Gawr Gura");
        testAdd.put("Age",7);
        JSONArray testArray = new JSONArray();
        testArray.put("Kiara");
        testArray.put("Ina");
        testArray.put("Amelia");
        testAdd.put("Hololive Friends",testArray);
        System.out.println(testAdd.toString());

        // PreWrite of data
        Assertions.assertDoesNotThrow(
                () -> {
                    preWriter.createClient(1, testAdd);
                    preWriter.createManager(1, testAdd);
                    preWriter.createInstructor(1, testAdd);
                });
    }

    @Test
    @Order(1)
    void testCredentials() {

        String fakeUserName = "fakeUdesu";
        String fakePassWord = "fakePdesu";
        String fakeUriString =
                "mongodb+srv://"
                        + fakeUserName
                        + ":"
                        + fakePassWord
                        + "@healthhub-cluster.7y7j0.mongodb.net/"
                        + dbName
                        + "?retryWrites=true&w=majority";

        Reader fakeTest = new Reader(fakeUriString, dbName, tableName);

        // Test for fake credentials.
       Assertions.assertThrows(MongoSecurityException.class, () -> fakeTest.readClientData(1));

        // Test for real credentials.
        Assertions.assertDoesNotThrow(() -> realCon.readClientData(1));
    }

    @Test
    @Order(2)
    void testDnExistReads() {
        Assertions.assertThrows(EmptyQueryException.class, () ->realCon.readClientData(2));
        Assertions.assertNull(realCon.readInstructorData(2));
        Assertions.assertNull(realCon.readManagerData(2));
    }

    @Test
    @Order(3)
    void testClientExists() throws EmptyQueryException {
        Assertions.assertDoesNotThrow( () -> realCon.readClientData(1));

        JSONObject jsonData = realCon.readClientData(1);
        Assertions.assertEquals(jsonData.get("Age"), 7);
        Assertions.assertEquals(jsonData.get("Name"), "Gawr Gura");

        ArrayList<String> testArray = new ArrayList<>();
        testArray.add("Kiara");
        testArray.add("Ina");
        testArray.add("Amelia");

        Assertions.assertEquals(jsonData.get("Hololive Friends"),testArray);
    }

    @AfterAll
    static void postDelete() {

        Writer postWriter = new Writer(realUriString,dbName,tableName);
        Assertions.assertDoesNotThrow(
                () -> {
                    postWriter.removeClient(1);
                    postWriter.removeManager(1);
                    postWriter.removeInstructor(1);
                });
    }
}
