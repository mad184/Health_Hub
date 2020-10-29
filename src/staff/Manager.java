package staff;

import java.util.ArrayList;

/**
 * Manager Class that implements Manager interface, this class is a View
 */
public class Manager implements ManagerInterface{

    // Attributes for a Manager
    String name, email, instructor, organization, phoneNumber;
    int age, id;
    double weight, height, goal, calories;
    String[] comment;
    ArrayList<InstructorInterface> instructorsList;

    /**
     * Create a new manager
     * @param name manager's name
     * @param email manager's email
     * @param instructor manager's instructor (manager can work out and get recommendations as well)
     * @param organization name of the organization the manager is working
     * @param phoneNumber manager's phone number
     * @param age manager's age
     * @param id manager's id
     * @param weight manager's weight
     * @param height manager's height
     * @param goal manager's goal
     * @param calories manager's calories
     * @param comment manager's comment
     */
    public Manager(
            String name,
            String email,
            String instructor,
            String organization,
            String phoneNumber,
            int age,
            int id,
            double weight,
            double height,
            double goal,
            double calories,
            String[] comment) {

        this.name = name;
        this.email = email;
        this.instructor = instructor;
        this.organization = organization;
        this.age = age;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
        this.calories = calories;
        this.comment = comment;
        instructorsList = new ArrayList<>();
    }

    /**
     * Set a name to the manager
     * @param name manager's name
     */
    @Override
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Set a age to a manager
     * @param age manager's age
     */
    @Override
    public void setAge(int age) {

        this.age = age;
    }

    /**
     * Set a id to a manager
     * @param id manager's id
     */
    @Override
    public void setId(int id) {

        this.id = id;
    }

    /**
     * Set a email to a manager
     * @param email manager's email
     */
    @Override
    public void setEmail(String email) {

        this.email = email;
    }

    /**
     * Set a phone number to a manager
     * @param phoneNumber manager's phone number
     */
    @Override
    public void setPhoneNum(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    /**
     * Set manager's height
     * @param height manager's height
     */
    @Override
    public void setHeight(double height) {

        this.height = height;
    }

    /**
     * Set manager's weight
     * @param weight manager's weight
     */
    @Override
    public void setWeight(double weight) {

        this.weight = weight;
    }

    /**
     * Set a goal t manager
     * @param goal manager's goal
     */
    @Override
    public void setGoal(double goal) {

        this.goal = goal;
    }

    /**
     * Set manager's calories
     * @param calories manager's calories
     */
    @Override
    public void setCalories(double calories) {

        this.calories = calories;
    }

    /**
     * Set a instructor for a manager
     * @param instructor manager's instructor
     */
    @Override
    public void setInstructor(String instructor) {

        this.instructor = instructor;
    }

    /**
     * Set the organization the manager is working at
     * @param organization manager's organization
     */
    @Override
    public void setOrganization(String organization) {

        this.organization = organization;
    }

    /**
     * set a comment for manager
     * @param comment manager's comment
     */
    @Override
    public void setComment(String[] comment) {

        this.comment = comment;
    }

    /**
     * Add an employee under manager
     * @param employee employee to be added
     */
    @Override
    public void addEmployee(InstructorInterface employee) {

        this.instructorsList.add(employee);
    }

    /**
     * Remove an employee from the list, if the employee is found in the list.
     * @param employee employee to be removed
     */
    @Override
    public void removeEmployee(InstructorInterface employee) {

        if (!instructorsList.isEmpty()){
            instructorsList.removeIf(emp -> emp.equals(employee));
        }
    }

    /**
     * Remove a calorie
     * @param calories calorie to be removed - not fully developed -
     */
    @Override
    public void removeCalories(double calories) {

        this.calories -= calories;
    }

    /**
     * Get manager's name
     * @return manager's name
     */
    @Override
    public String getName() {

        return this.name;
    }

    /**
     * Getter of manager age
     * @return manager's age
     */
    @Override
    public int getAge() {

        return this.age;
    }

    /**
     * Getter of manager id
     * @return manager's id
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Getter for manager email
     * @return manager's email
     */
    @Override
    public String getEmail() {

        return this.email;
    }

    /**
     * Getter for manager's phone number
     * @return manager's phone number
     */
    @Override
    public String getPhoneNum() {

        return this.phoneNumber;
    }

    /**
     * Getter for manager's height
     * @return manager's height
     */
    @Override
    public double getHeight() {

        return this.height;
    }

    /**
     * Getter for manager's weight
     * @return manager's weight
     */
    @Override
    public double getWeight() {

        return this.weight;
    }

    /**
     * Getter for manager's goal
     * @return manager's goal
     */
    @Override
    public double getGoal() {

        return this.goal;
    }

    /**
     * Getter for manager's calorie
     * @return manager's calorie
     */
    @Override
    public double getCalories() {

        return this.calories;
    }

    /**
     * Getter for manager's id
     * @return manager's id
     */
    @Override
    public String getInstructor() {

        return this.instructor;
    }

    /**
     * Getter for manager's organization
     * @return manager's organization
     */
    @Override
    public String getOrganization() {

        return this.organization;
    }

    /**
     * Getter for manager's comment
     * @return manager's comment
     */
    @Override
    public String[] getComment() {

        return this.comment;
    }

}
