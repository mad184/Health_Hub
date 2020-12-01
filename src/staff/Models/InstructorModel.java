package staff.Models;

import java.util.ArrayList;
import java.util.List;
import database.Dbms;
import database.EmptyQueryException;
import database.JsonObjectException;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import staff.Interfaces.InstructorInterface;
import staff.UserID;

/** Storage class for Instructor users. */
public class InstructorModel extends StaffModel implements InstructorInterface {
  private List<UserID> clients;

  /**
   * Constructs a new InstructorModel object.
   *
   * @param name : Name of the staff member
   * @param age : Age of the staff member (years)
   * @param email : Email of the staff member
   * @param phoneNumber : Phone number of the staff member
   * @param height : Height of the staff member (cm)
   * @param weight : Weight of the staff member (lbs)
   * @param organization : Organization the staff member is affiliated with
   * @param id : Database ID of the staff member
   * @param clients : Client list for the Instructor
   */
  public InstructorModel(
      String name,
      String userPassword,
      int age,
      String email,
      String phoneNumber,
      int height,
      int weight,
      String organization,
      int id,
      List<UserID> clients,
      String username,
      String password,
      String dbName,
      String tableName) {
    super(
        name,
        userPassword,
        age,
        email,
        phoneNumber,
        height,
        weight,
        organization,
        id,
        username,
        password,
        dbName,
        tableName);
    this.clients = clients;
  }

  /**
   * Converts a JSONObject representation of the Instructor to InstructorModel.
   *
   * @param instructor: JSONObject of the instructor
   * @return InstructorModel object
   *     <p>public static InstructorModel fromJson(JSONObject instructor) { Gson converter = new
   *     Gson(); return converter.fromJson(String.valueOf(instructor), InstructorModel.class); }
   */

  /**
   * Gets the Client list for the Instructor.
   *
   * @return List of UserIDs for the Clients
   */
  @Override
  public List<UserID> getClients() {
    return this.clients;
  }

  /**
   * Adds a Client (UserID) to the Instructor's list.
   *
   * @param client: UserID of Client to add
   */
  @Override
  public void addClient(UserID client) {
    this.clients.add(client);
  }

  /**
   * Removes a Client (UserID) from the Instructor's list.
   *
   * @param client: Client to remove
   */
  @Override
  public void removeClient(UserID client) {
    this.clients.remove(client);
  }

  /**
   * Adds a comment to a Client's profile.
   *
   * @param client: Client receiving the comment
   * @param comment: The comment itself
   */
  @Override
  public void addComment(UserID client, String comment)
      throws JSONException, JsonObjectException, EmptyQueryException {
    JSONObject clientJson = this.getClientInfo(client);
    List<String> comments =
        (List<String>) clientJson.get("Comments"); // TODO: Verify with client package
    comments.add(comment);
    clientJson.put("Comments", comments);
    this.db.updateClient(client.getId(), clientJson);
  }

  /**
   * Removes a comment from a Client's profile.
   *
   * @param client: Client whose profile the comment is on
   * @param comment: The comment itself
   */
  @Override
  public void removeComment(UserID client, String comment)
      throws JSONException, JsonObjectException, EmptyQueryException {
    JSONObject clientJson = this.getClientInfo(client);
    List<String> comments =
        (List<String>) clientJson.get("Comments"); // TODO: Verify with client package
    comments.remove(comment);
    clientJson.put("Comments", comments);
    this.db.updateClient(client.getId(), clientJson);
  }

  /**
   * Gets all the information on a Client.
   *
   * @param client: Client to fetch
   * @return Client object
   */
  @Override
  public JSONObject getClientInfo(UserID client) throws EmptyQueryException {
    return this.db.readClientData(client.getId());
  }

  /**
   * Converts the object into a JSONObject representation.
   *
   * @return JSONObject representation of an Instructor
   */
  @Override
  public JSONObject toJson() throws JSONException {
    JSONObject json = super.toJson();
    ArrayList<String> clientList = new ArrayList<>();
    for(UserID user : this.clients) {
      clientList.add(user.toString());
    }
    json.put("Clients", clientList);
    return json;
  }

  /**
   * A method that sets a JSONObject back to InstructorModel Class
   *
   * @param jsonObject InstructorModel class in JSONObject
   * @return InstructorModel Class
   */
  public Gson fromJson(JSONObject jsonObject) {
    Gson ObjectClass = super.fromJson(jsonObject);
    String[] ListArray = String.valueOf(jsonObject.get("Clients")).split(",");
    for (String item : ListArray) {
      String stripped = item.replace("\"", "");
      String bare = stripped.replace("[", "");
      String done = bare.replace("]", "");
      String[] clientInfo = done.split(";");
      UserID userClient = new UserID(Integer.parseInt(clientInfo[1]), clientInfo[0]);
      addClient(userClient);
    }
    return ObjectClass;
  }
}
