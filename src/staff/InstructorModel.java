package staff;

import java.util.List;
import org.json.JSONObject;
import com.google.gson.Gson;

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
      int age,
      String email,
      String phoneNumber,
      int height,
      int weight,
      String organization,
      int id,
      List<UserID> clients) {
    super(name, age, email, phoneNumber, height, weight, organization, id);
    this.clients = clients;
  }

  /**
   * Converts a JSONObject representation of the Instructor to InstructorModel.
   *
   * @param instructor: JSONObject of the instructor
   * @return InstructorModel object
   */
  public static InstructorModel fromJson(JSONObject instructor) {
    Gson converter = new Gson();
    return converter.fromJson(String.valueOf(instructor), InstructorModel.class);
  }

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
  public void addComment(UserID client, String comment) {
    ClientInterface clientObj = this.getClientInfo(client);
    List<String> comments = clientObj.getComment();
    comments.add(comment);
    // TODO: Write the Client object back to the Database
  }

  /**
   * Removes a comment from a Client's profile.
   *
   * @param client: Client whose profile the comment is on
   * @param comment: The comment itself
   */
  @Override
  public void removeComment(UserID client, String comment) {
    ClientInterface clientObj = this.getClientInfo(client);
    List<String> comments = clientObj.getComment();
    comments.remove(comment);
    // TODO: Write the Client back to the Database
  }

  /**
   * Gets all the information on a Client.
   *
   * @param client: Client to fetch
   * @return Client object
   */
  @Override
  public ClientInterface getClientInfo(UserID client) {
    // TODO: Figure out how to connect to the Database.
    // Dbms db = new Dbms();
    // JSONObject clientJson = db.readClientData(client.id);
    // return Client.fromJson(clientJson);
    return null;
  }

  /**
   * Converts the object into a JSONObject representation.
   *
   * @return JSONObject representation of an Instructor
   */
  public JSONObject toJson() {
    JSONObject json = super.toJson();
    json.put("Clients", this.clients);
    return json;
  }
}
