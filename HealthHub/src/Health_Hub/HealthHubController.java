package Health_Hub;

import Client.ClientInterface;
import Staff.ManagerInterface;
import Staff.Owner;
import Health_Hub.HealthHubModel;

public class HealthHubController {
    private static HealthHubModel model;

    //constructor
    public HealthHubController(HealthHubModel model){
        HealthHubController.model = model;
    }

    protected void addClient(ClientInterface client){
        model.addClient(client);
    }


    protected void addInstructor(InstructorInterface instructor){
        model.addInstructor(instructor);
    }


    protected void addManager(ManagerInterface manager){
        model.addManager(manager);
    }

// talk about on tuesday
//    void addOwner(ManagerInterface owner){
//        model.addOwner(owner);
//    }

    // LogIn success/Error Codes
    // 200 = success
    // 404 - account not found
    // 401 - exists not correct
    // 500 - server error
    // -1 - unknown error
    protected int LogIn(String userName, String passWord){
        int login = model.LogIn(userName, passWord);
        if (login == 200){
            return 200;
        }

        else if(login == 404){
            // send account not found to LogInView
            return 404;
        }
        else if (login == 401){
            // send account exists, login is not correct to LogInView
            return 401;
        }
        else if (login == 500){
            // send server error to LogInView

            return 500;
        }
        else{
            //unknown error
            return -1;
        }
    }

// delete on Tuesday
//    int Search(String userName, String password, String name){
//        return 1;
//    }
}
