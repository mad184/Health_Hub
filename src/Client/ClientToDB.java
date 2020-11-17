package Client;

import database.Dbms;
import database.EmptyQueryException;
import database.JsonObjectException;
import org.json.JSONObject;

public class ClientToDB {

    Dbms DB = new Dbms("Justyn", "Staff1", "Test-Justyn-Db", "testCollection");

    public void createClient(int clientID, JSONObject clientJSON){
        DB.createClient(clientID, clientJSON);
    }

    public void updateClient(int clientID, JSONObject clientJSON) throws JsonObjectException, EmptyQueryException {
        DB.updateClient(clientID, clientJSON);
    }

    public void removeClient(int clientID){
        DB.removeClient(clientID);
    }

}
