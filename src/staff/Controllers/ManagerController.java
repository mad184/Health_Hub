package staff.Controllers;

import org.json.JSONObject;
import staff.ManagerInterface;
import staff.Models.ManagerModel;
import staff.UserID;

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

}
