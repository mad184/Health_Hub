package healthhub;

import client.Client;
import staff.Instructor;
import staff.Manager;

public class HealthHubController {
    private static HealthHubModel model;

    private static HealthHubSingleton healthHubSingleton;

    //constructor
    public HealthHubController(HealthHubModel model){
        HealthHubController.model = model;
    }

    protected void addClient(Client client){
        model.addClient(client);
    }


    protected void addInstructor(Instructor instructor){
        model.addInstructor(instructor);
    }


    protected void addManager(Manager manager){
        model.addManager(manager);
    }

    protected boolean createOrganization(String name){
        try{
            HealthHubSingleton.newOrganziation(name);
            return true;
        }
        catch(RuntimeException e){
            return false;
        }
    }




    // LogIn success/Error Codes
    // 200 = success
    // 404 - account not found
    // 401 - exists not correct
    // 500 - server error
    // -1 - unknown error
    protected int LogIn(String userName, String passWord, String userType){
        int login = model.systemLogin(userName, passWord, userType);
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

}
