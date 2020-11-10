package staff;

import java.util.List;

/**
 * Model of the Instructor classes, which allows for modification of the Instructors in the system.
 */
public class InstructorModel {
    private Database database;
    private Controller controller;
    private List<Instructor> instructorList;
    private List<Manager> managerList;
    private List<Owner> ownerList;

    /**
     * Constructor
     * @param database: Database for permanent storage
     * @param controller: Controller for interfacing with the view
     */
    public InstructorModel(
            Database database,
            Controller controller) {
        this.database = database;
        this.controller = controller;
    }

    /**
     * TODO: Figure out what this is for
     */
    public void deleteItself() {

    }

    /**
     * TODO: Figure out what this is supposed to do
     * @param calories: A Calories object
     */
    public void removeCal(Calories calories) {

    }

    /**
     * Removes a client from the Instructor's client list.
     * @param clientName: Name of the client to remove.
     * @param instructorName: Name of the Instructor who is having the client removed.
     */
    public void removeClient(String clientName, String instructorName) {
        // Instructor instructor = database.getInstructor(instructorName);
        database.removeClient(clientName, instructorName);
    }

    /**
     * Adds a Client to an Instructor's list of clients.
     * @param client: A client in the system
     * @param instructor: An instructor in the system
     */
    public void addClient(ClientInterface client, InstructorInterface instructor) {
        database.addClient(client.id, instructor.id);
    }

    /**
     * Adds a comment to the Client's profile from the Instructor.
     * @param client: Client receiving the comment
     * @param instructor: Instructor sending the comment
     * @param comment: The message itself.
     */
    public void addComment(ClientInterface client, InstructorInterface instructor, String comment) {
        // TODO: Figure out how to make a JSON object of the comment
        // TODO: Add the comment to the client in the database
    }

    /**
     * Remove a comment from a Client's profile.
     * @param client: Client who has the comment
     * @param instructor: Instructor who gave the comment
     * @param comment: The comment itself
     */
    public void removeComment(ClientInterface client, InstructorInterface instructor, String comment) {
        // TODO: Figure out how to remove a comment from a client's profile
    }

    /**
     * Gets information about a client.
     * @param client
     * @return
     */
    public String getClientInfo(ClientInterface client) {
        // TODO: Figure out how to get the client's info from the database
    }

    /**
     * Gets the information for a particular Instructor.
     * @param instructor: The instructor whose information is being accessed.
     * @return All information about the instructor.
     */
    public InstructorInterface getInstructorInfo(InstructorInterface instructor) {
        return instructor;  // TODO: Make this do something useful
    }

    /**
     * Gets the unique ID of an Instructor.
     * @param instructor: The Instructor
     * @return The ID of the Instructor
     */
    public int getInstructorID(InstructorInterface instructor) {
        return instructor.id;
    }

    /**
     * Removes an Instructor from the system
     * @param instructor: Instructor to be removed.
     */
    public void removeInstructor(InstructorInterface instructor) {
        // TODO: Figure out how to delete instructor from database
    }

    /**
     * Creates a new organization.
     */
    public void createOrganization() {
        // TODO: Create an organization
    }

    /**
     * Removes a manager from the system.
     * @param manager: Manager to be deleted
     */
    public void removeManager(ManagerInterface manager) {
        // TODO: Remove a manager from the system
    }

    /**
     * Adds a new manager to the system.
     * @param manager: Manager being added
     */
    public void addManager(ManagerInterface manager) {
        // TODO: Add the manager to the system
    }

    /**
     * Fetches the instructor list from the database
     */
    public void refreshInstructors() {
        this.instructorList = database.getInstructors();
    }


}
