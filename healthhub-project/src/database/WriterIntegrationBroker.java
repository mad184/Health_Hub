package database;

//import com.mongodb.util.JSON;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import org.json.JSONObject;

public class WriterIntegrationBroker {

  protected Queue<JSONObject> queueJson = new LinkedList<>();

  /**
   * Checks if the Broker Queue is empty.
   *
   * @return true if broker is empty, false otherwise
   */
  public boolean isEmpty() {
    try {
      queueJson.element();
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean jsonAdd(JSONObject dataAdd) {
    return queueJson.offer(dataAdd);
  }

  /**
   * Removes the first item in the Broker Queue.
   *
   * @return true if successful, false otherwise
   */
  public boolean jsonRemove() {
    try {
      queueJson.remove();
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
}
