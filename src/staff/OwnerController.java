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
        model.addManager(manager);
    }

    @Override
    public void removeManager(UserID manager) {
        model.removeManager(manager);
    }

    @Override
    public JSONObject getManagerInfo(UserID manager) {
        return model.getManagerInfo(manager);
    }

}
