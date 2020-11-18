package staff;

import java.util.List;

public class ManagerController implements ManagerInterface {

    private ManagerModel model;


    //-------GETTERS-------//

    @Override
    public List<UserID> getInstructors() {
        return model.getInstructors();
    }

    @Override
    public void removeInstructor(UserID instructor) {
        model.removeInstructor(instructor);
    }

    @Override
    public JSONObject getInstructorInfo(UserID instructor) {
        return model.getInstructorInfo(instructor);
    }

    @Override
    public void addInstructor(UserID instructor) {
        model.addInstructor(instructor);
    }

    @Override
    public String getName() {
        model.getName();
    }

    @Override
    public int getAge() {
        return model.getAge();
    }

    @Override
    public String getEmail() {
        return model.getEmail();
    }

    @Override
    public String getPhoneNumber() {
        return model.getPhoneNumber();
    }

    @Override
    public int getHeight() {
        return model.getHeight();
    }

    @Override
    public int getWeight() {
        return model.getWeight();
    }

    @Override
    public String getOrganization() {
        return model.getOrganization();
    }

    @Override
    public int getId() {
        return model.getId();
    }

    // --------- SETTERS -------//

    @Override
    public void setName(String name) {
        model.setName(name);
    }

    @Override
    public void setAge(int age) {
        model.setAge(age);
    }

    @Override
    public void setEmail(String email) {
        model.setEmail(email);
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        model.setPhoneNumber(phoneNumber);
    }

    @Override
    public void setHeight(int height) {
        model.setHeight(height);
    }

    @Override
    public void setWeight(int weight) {
        model.setWeight(weight);
    }

    @Override
    public void setOrganization(String orgName) {
        model.setOrganization(orgName);
    }

    @Override
    public JSONObject toJson() {
        return null;
    }

}
