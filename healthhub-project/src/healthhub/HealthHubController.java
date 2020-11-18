package healthhub;

import client.Client;
import staff.Instructor;
import staff.Manager;

public class HealthHubController {
    private static HealthHubModel model;

    private static HealthHubAccessSingleton healthHub;

    //constructor takes the model
    public HealthHubController(HealthHubModel model){

        HealthHubController.model = model;
    }

    /**
     * Makes call the model to pass it the client created
     * @param name: String-client name
     * @param birthDate: String-client birthdate
     * @param email: String-client email
     * @param userName: String-client username
     * @param passWord: String-client passWord
     */
    protected void addClient(String name, String birthDate, String email, String userName, String passWord){
        /*TODO:
            make model take a method with these params
         */
        //model.addClient(name, birthDate, email, userName, passWord);
    }

    /**
     * Makes call the model to pass it the client created
     * @param name: String-Instructor name
     * @param birthDate: String-Instructor birthdate
     * @param email: String-Instructor email
     * @param userName: String-Instructor username
     * @param passWord: String-Instructor passWord
     */
    protected void addInstructor(String name, String birthDate, String email, String userName, String passWord){
        //model.addClient(name, birthDate, email, userName, passWord);
    }

    /**
     * Makes call the model to pass it the manager created
     * @param manager: of type Manager
     */
    protected void addManager(Manager manager){
        model.addManager(manager);
    }

    /**
     * A method to create the Organization,
     *
     * @param name: the name of the organization we want to create
     * @return
     *     true -> the organization has been created
     *     false -> the organziation has already been created
     */
    protected boolean createOrganization(String name, Manager owner){
        return model.CreateOrgnaization(name, owner);
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
  protected int LogIn(String userName, String passWord, String userType) {
        return model.systemLogin(userName, passWord, userType);
    }
}
