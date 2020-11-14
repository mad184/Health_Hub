package database.main;

import org.json.JSONArray;
import org.json.JSONObject;

public class Dbms {
    public Dbms(String realUserName, String realPassWord, String dbName, String tableName) {
    }

    public void createClient(int uniqueCid, JSONObject newClientInfo) {

    }
    public void createInstructor(int uniqueIid, JSONObject newInstructorInfo) {
    }

    public void createManager(int uniqueMid, JSONObject newManagerInfo) {
    }


    public JSONArray getAllClients() {
        return new JSONArray();
    }

    public JSONArray getAllInstructor() {
        return new JSONArray();
    }

    public JSONArray getAllManager() {
        return new JSONArray();
    }
}
