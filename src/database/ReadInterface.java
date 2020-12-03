package database;

import org.json.JSONArray;
import org.json.JSONObject;

interface ReadInterface {

  JSONObject readClientData(int uniqueCid) throws EmptyQueryException;

  JSONObject readInstructorData(int uniqueIid) throws EmptyQueryException;

  JSONObject readManagerData(int uniqueMid) throws EmptyQueryException;

  JSONObject readOrganizationData(String uniqueOrgString) throws EmptyQueryException;

  JSONArray getAllClients();

  JSONArray getAllInstructors();

  JSONArray getAllManagers();

  JSONArray getAllOrganization();
}

