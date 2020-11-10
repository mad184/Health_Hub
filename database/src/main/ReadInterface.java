package main;

import org.json.JSONArray;
import org.json.JSONObject;

interface ReadInterface {

  JSONObject readClientData(int uniqueCid) throws EmptyQueryException;

  JSONObject readInstructorData(int uniqueIid) throws EmptyQueryException;

  JSONObject readManagerData(int uniqueMid) throws EmptyQueryException;

  JSONArray getAllClients();

  JSONArray getAllInstructors();

  JSONArray getAllManagers();
}
