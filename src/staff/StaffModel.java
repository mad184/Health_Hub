package staff;

import org.json.JSONObject;
import com.google.gson.Gson;

/**
 * Storage class for Staff users.
 */
public class StaffModel implements StaffInterface {
    private String name;
    private int age;
    private String email;
    private String phoneNumber;
    private int height;
    private int weight;
    private String organization;

    /**
     * Constructs a new StaffModel object.
     *
     * @param name: Name of the staff member
     * @param age: Age of the staff member (years)
     * @param email: Email of the staff member
     * @param phoneNumber: Phone number of the staff member
     * @param height: Height of the staff member (cm)
     * @param weight: Weight of the staff member (lbs)
     * @param organization: Organization the staff member is affiliated with
     */
    public StaffModel(
            String name,
            int age,
            String email,
            String phoneNumber,
            int height,
            int weight,
            String organization) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.height = height;
        this.weight = weight;
        this.organization = organization;
    }

    /**
     * Converts a JSON representation of a Staff member into a StaffModel object.
     *
     * @param staff: JSONObject representation of a StaffModel object
     * @return a StaffModel object
     */
    static StaffModel fromJson(JSONObject staff) {
        Gson converter = new Gson();
        return converter.fromJson(String.valueOf(staff), StaffModel.class);
    }

    
}
