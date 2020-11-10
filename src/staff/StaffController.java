package staff;

import staff.InstructorInterface;
import staff.ManagerInterface;
import staff.StaffModel;

import java.util.List;

public class StaffController {
    private InstructorModel model;

    //constructor
    public StaffController(InstructorModel model) {

        this.model = model;
    }

    /**
     * Not sure yet
     */
    public void deleteItself() {

        // TODO: not sure what todo
    }

    /**
     * @param calories
     */
    public void removeCal(double calories) {
        model.removeCal(calories);
        // TODO: HOW DOES MODEL KNOWS WHO IS CALLING ? SHOULD HAVE A 2ND PARAM ?
    }

    /**
     * @param clientName
     * @param instructorName
     */
    public void removeClient(String clientName, String instructorName) {

        model.removeClient(clientName, instructorName);
    }

    /**
     * @param client
     * @param instructor
     */
    public void addClient(ClientInterface client, InstructorInterface instructor) {

        model.addClient(client, instructor);
    }

    /**
     * ask model to add a comment into client database
     *
     * @param client     Client to have comment added
     * @param instructor Instructor adding
     * @param comment    The comment to be added
     */
    public void addComment(ClientInterface client, InstructorInterface instructor, String comment) {

        model.addComment(client, instructor, comment);
    }

    /**
     * ask model to remove a comment from a client
     *
     * @param client     The client
     * @param instructor Instructor asking to remove comment
     * @param comment    The comment
     */
    public void removeComment(ClientInterface client, InstructorInterface instructor, String comment) {

        model.removeComment(client, instructor, comment);
    }

    /**
     * Get information from client
     *
     * @param client The client
     * @return return all info from client
     */
    public String getClientInfo(ClientInterface client) {

        return model.getClientInfo(client);
    }

    /**
     * Get info for a instructor
     */
    public void getInstructorInfo(InstructorInterface instructor) {
        //TODO: Check return tipe of database on model, and how we want to handle it
    }

    /**
     * Get instructor id
     *
     * @param instructor Instructor
     * @return instructor's id
     */
    public int getInstructorId(InstructorInterface instructor) {

        return model.getInstructorID(instructor);
    }


    /**
     * ask model to remove a instructor from database
     *
     * @param instructor Instructor to be removed
     * @param manager    Manager with permission to remove a instructor
     */
    public void removeInstructor(InstructorInterface instructor, ManagerInterface manager) {

        model.removeInstructor(instructor, manager);
    }

    /**
     * Remove a manager from the organization
     *
     * @param manager manager to be removed
     * @param owner   owner to remove manager
     */
    public void removeManager(ManagerInterface manager, ManagerInterface owner) {

        model.removeManager(manager, owner);
    }


}
