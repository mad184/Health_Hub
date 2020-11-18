package staff.Controllers;

import staff.InstructorInterface;
import staff.Models.InstructorModel;
import staff.UserID;

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
        model.addClient(client);
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


    @Override
    public JSONObject getClientInfo(UserID client) {
        return model.getClientInfo(client);
    }

}
