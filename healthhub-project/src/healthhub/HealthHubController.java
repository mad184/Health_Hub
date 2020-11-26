package healthhub;

import client.Client;
import staff.InstructorModel;
import staff.Manager;
import staff.OwnerModel;

public class HealthHubController {
    private static final HealthHubModel model = new HealthHubModel();

    /**
     * Makes call the model to pass it the client created
     * @param client: client object
     */
    public static int addClient(Client client){
        return model.addClient(client.getJSONData());
    }

    /**
     * Makes call the model to pass it the client created
     * @param instructor : Instructor object
     * @return error code or unique id from model
     */
    public static int addInstructor(InstructorModel instructor){
        return model.addInstructor(instructor.getJSONData());
    }

    /**
     * Makes call the model to pass it the manager created
     * @param manager: of type Manager
     */
    public static int addManager(Manager manager){
        return model.addManager(manager.getJSONData());
    }

    /**
     * A method to create the Organization,
     *
     * @param OrgName: the name of the organization we want to create
     * @return
     *     true -> the organization has been created
     *     false -> the organziation has already been created
     */
    public static int createOrganization(String OrgName, OwnerModel owner){
        return model.createOrganization(OrgName, owner.getJSONData());
    }

  /**
   * This method take the text box entry from the ClientSignUpView, verifys it and then sends it to
   * the healthHub Controller to be added to the database, Then switches the view to the clients view
   *
   * @param userName: the username the client will have
   * @param passWord: the passWord the client will have
   * @param userType: the type of user that tried to log in
   * @return:
   *    200 -> for loin success
   *    404 -> for account not found
   *    401 -> for exists not correct
   *    500 -> for server error
   *    -1 -> for unknown error
   */
  public static int LogIn(String userName, String passWord, String userType) {
        return model.systemLogin(userName, passWord, userType);
    }

  public static boolean organizationExists(){
      return HealthHubAccessSingleton.isOrganizationCreated();
  }

  public static int getUniqueID(){
      return model.determineUniqueId();
  }

  public static boolean uniqueEmail(String email){
      return model.determineUniqueEmail(email);
  }
}
