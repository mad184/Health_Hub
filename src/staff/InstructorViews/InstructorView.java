package staff.InstructorViews;


import staff.Interfaces.StaffInterface;
import javafx.scene.image.Image;
import org.json.JSONObject;
import com.google.gson.Gson;
import API.FoodItem;
import java.util.ArrayList;

public class InstructorView implements StaffInterface {


    private String name, email, password, instructor, organization, phoneNumber;
    private int id, age, height, weight, goalWeight, goalCals, calories;
    private ArrayList<String> allergies, comment;
    private ArrayList<FoodItem> breakFoods, lunchFoods, dinnerFoods, snackFoods;
    Image profilePicture;

    public InstructorView(
            String name,
            String email,
            String password,
            String instructor,
            String organization,
            int id,
            int age,
            int height,
            int weight,
            String phoneNumber,
            int goalWeight,
            int goalCals,
            int calories,
            ArrayList<String> allergies,
            ArrayList<String> comment,
            Image profilePicture,
            ArrayList<FoodItem> breakFoods,
            ArrayList<FoodItem> lunchFoods,
            ArrayList<FoodItem> dinnerFoods,
            ArrayList<FoodItem> snackFoods){
        this.name = name;
        this.email = email;
        this.password = password;
        this.instructor = instructor;
        this.organization = organization;
        this.id = id;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.phoneNumber = phoneNumber;
        this.goalWeight = goalWeight;
        this.goalCals = goalCals;
        this.calories = calories;
        this.allergies = allergies;
        this.comment = comment;
        this.profilePicture = profilePicture;
        this.breakFoods = breakFoods;
        this.lunchFoods = lunchFoods;
        this.dinnerFoods = dinnerFoods;
        this.snackFoods = snackFoods;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getPhoneNumber() {
        return null;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public String getOrganization() {
        return null;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setAge(int age) {

    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public void setPhoneNumber(String phoneNumber) {

    }

    @Override
    public void setHeight(int height) {

    }

    @Override
    public void setWeight(int weight) {

    }

    @Override
    public void setOrganization(String orgName) {

    }
}
