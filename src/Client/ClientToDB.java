package Client;

import database.Dbms;
import database.EmptyQueryException;
import database.JsonObjectException;
import org.json.JSONObject;

public class ClientToDB {

    Dbms DB = new Dbms("production_user", "healthhub1", "production-database", "testCollection");

    /**
     * Creates client in database from json object
     * @param clientID Id used to identify client
     * @param clientJSON object with client info
     */
    public void createClient(int clientID, JSONObject clientJSON){
        DB.createClient(clientID, clientJSON);
    }

    /**
     * Grabs client JSON from database
     * @param clientID The id used to identify client
     * @return client JSONObject
     * @throws EmptyQueryException If the client with id doesn't exist
     */
    public JSONObject getClient(int clientID) throws EmptyQueryException {
        return DB.readClientData(clientID);
    }

    /**
     * Overwrites current client info with new info from json
     * @param clientID The id used to identify client
     * @param clientJSON JSON client with client info
     * @throws EmptyQueryException When query returns nothing
     */
    public void updateClient(int clientID, JSONObject clientJSON) throws JsonObjectException, EmptyQueryException {
        DB.updateClient(clientID, clientJSON);
    }

    /**
     * Removes client from data
     * @param clientID The id used to identify client
     */
    public void removeClient(int clientID){
        DB.removeClient(clientID);
    }

}
