package database;

import org.json.JSONObject;

public interface WriteInterface {

  void createClient(int uniqueCid, JSONObject value) throws NullPointerException;

  void createInstructor(int uniqueIid, JSONObject value) throws NullPointerException;

  void createManager(int uniqueMid, JSONObject value) throws NullPointerException;

  void createOrganization(String uniqueOrgName, JSONObject value) throws NullPointerException;

  void updateClient(int uniqueCid, JSONObject updatedData)
      throws NullPointerException, JsonObjectException, EmptyQueryException;

  void updateInstructor(int uniqueIid, JSONObject updatedData)
      throws NullPointerException, JsonObjectException, EmptyQueryException;

  void updateManager(int uniqueMid, JSONObject updatedData)
      throws JsonObjectException, NullPointerException, EmptyQueryException;

  void updateOrganization(String uniqueOrgName, JSONObject updatedData)
          throws JsonObjectException, NullPointerException, EmptyQueryException;

  void removeClient(int uniqueCid);

  void removeInstructor(int uniqueIid);

  void removeManager(int uniqueMid);

  void removeOrganization(String uniqueOrgName);
}
