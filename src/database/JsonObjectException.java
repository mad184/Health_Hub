package database;

public class JsonObjectException extends Exception {

  final String message;

  public JsonObjectException() {
    message = "JSONObject cannot be empty. Database will not accept empty data";
  }

  public String toString() {
    return message;
  }
}
