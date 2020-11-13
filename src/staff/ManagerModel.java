package staff;

import java.util.List;
import org.json.JSONObject;
import com.google.gson.Gson;

/**
 * Storage class for Manager users.
 */
public class ManagerModel extends StaffModel implements ManagerInterface {
    private List<UserID> instructors;

    /**
     * Constructs a new ManagerModel object.
     *
     * @param name         : Name of the manager
     * @param age          : Age of the manager (years)
     * @param email        : Email of the manager
     * @param phoneNumber  : Phone number of the manager
     * @param height       : Height of the manager (cm)
     * @param weight       : Weight of the manager (lbs)
     * @param organization : Organization the manager is affiliated with
     * @param id           : Database ID of the manager
     * @param instructors  : List of Instructors
     */
    public ManagerModel(
            String name,
            int age,
            String email,
            String phoneNumber,
            int height,
            int weight,
            String organization,
            int id,
            List<UserID> instructors) {
        super(name, age, email, phoneNumber, height, weight, organization, id);
        this.instructors = instructors;
    }

    /**
     * Converts a JSONObject representation of the manager to ManagerModel.
     *
     * @param manager: JSONObject of the manager
     * @return ManagerModel object
     */
    public static ManagerModel fromJson(JSONObject manager) {
        Gson converter = new Gson();
        return converter.fromJson(String.valueOf(manager), ManagerModel.class);
    }

    /**
     * Gets the Instructor list (as UserIDs) for the Manager
     *
     * @return List of UserIDs for Instructors
     */
    @Override
    public List<UserID> getInstructors() {
        return this.instructors;
    }

    /**
     * Adds an Instructor (UserID) to the Instructor list.
     *
     * @param instructor: UserID of Instructor
     */
    @Override
    public void addInstructor(UserID instructor) {
        this.instructors.add(instructor);
    }

    /**
     * Removes and Instructor (UserID) from the list.
     *
     * @param instructor: UserID of Instructor
     */
    @Override
    public void removeInstructor(UserID instructor) {
        this.instructors.remove(instructor);
    }

    /**
     * Converts the ManagerModel to a JSON representation.
     * @return JSON representation of a Manager
     */
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("Clients", this.instructors);
        return json;
    }
}
