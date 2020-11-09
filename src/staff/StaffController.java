package staff;

import staff.InstructorInterface;
import staff.ManagerInterface;
import staff.StaffModel;

public class StaffController {
    private static StaffModel model;

    //constructor
    public StaffController(StaffModel model){

        StaffController.model = model;
    }


}
