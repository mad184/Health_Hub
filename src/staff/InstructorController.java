package staff;

import java.util.List;

public class InstructorController implements InstructorInterface {

    private InstructorModel model;

    /**
     * Constructor for controller
     * @param model
     */
    public InstructorController(InstructorModel model){
        this.model = model;
    }


    @Override
    public List<UserID> getClients() {
        return model.getClients();
    }

    @Override
    public void addClient(UserID client) {
        model.addCleint(client);
    }

    @Override
    public void removeClient(UserID client) {
        model.removeClient(client);
    }

    @Override
    public void addComment(UserID client, String comment) {
        model.addComment(client, comment);
    }

    @Override
    public void removeComment(UserID client, String comment) {
        model.removeComment(client, comment);
    }

    //----------GETTERS--------//

    @Override
    public Client getClientInfo(UserID client) {
        return model.getClientInfo(client);
    }

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


    //--------SETTER----------//

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

    /**
     * TODO: Should it be set on manager on instructor ?
     * @param orgName: Name of the organization
     */
    @Override
    public void setOrganization(String orgName) {
        model.setOrganization(orgName);
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}
