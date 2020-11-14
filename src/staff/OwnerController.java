package staff;

import java.util.List;

public class OwnerController implements OwnerInterface {

    private OwnerModel model;

    public OwnerController(OwnerModel model) {
        this.model = model;
    }

    @Override
    public List<UserID> getManagers() {
        return model.getManagers();
    }

    @Override
    public void addManager(UserID manager) {
        model.addManage(manager);
    }

    @Override
    public void removeManager(UserID manager) {
        model.removeManager(manager);
    }

    @Override
    public List<UserID> getInstructors() {
        return model.getINstructors();
    }

    @Override
    public void addInstructor(UserID instructor) {
        model.addInstructor(instructor);
    }

    @Override
    public void removeInstructor(UserID instructor) {
        model.removeInstructor(instructor);
    }

    //-----------GETTER----------//

    @Override
    public String getName() {
        return model.getName();
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


    //----------------SETTER------------//

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
