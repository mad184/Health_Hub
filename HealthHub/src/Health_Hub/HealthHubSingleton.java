package Health_Hub;

import Client.ClientInterface;
import Staff.ManagerInterface;
import Staff.Owner;

import java.util.List;

public class HealthHubSingleton {
    // Discuss/delete on tuesday
//    List<ClientInterface> clients;
//    List<ManagerInterface> managers;
//    List<ManagerInterface> owners;

    //private constructor to ensure no instances are created
    private HealthHubSingleton() {

    }

    private static HealthHubSingleton health_hub;

    public static void initialize(){

    }
}
