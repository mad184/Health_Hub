package staff;

import java.util.ArrayList;

/**
 * An interface for Manager class
 */
public interface ManagerInterface {

    // setters for manager attributes
    void setName(String name);

    void setAge(int age);

    void setId(int id);

    void setEmail(String email);

    void setPhoneNum(String phoneNumber);

    void setHeight(double height);

    void setWeight(double weight);

    void setGoal(double goal);

    void setCalories(double calories);

    void setInstructor(String instructor);

    void setOrganization(String organization);

    void setComment(String comment);

    void setPermission(String permission);

    void addEmployee(InstructorInterface employee);

    void removeEmployee(InstructorInterface employee);

    void removeCalories(double calories);

    // getters for manager attributes
    String getName();

    int getAge();

    int getId();

    String getEmail();

    String getPhoneNum();

    double getHeight();

    double getWeight();

    double getGoal();

    double getCalories();

    String getInstructor();

    String getPermission();

    ArrayList<InstructorInterface> getEmployeeList();

    InstructorInterface getEmployee(String employeeName);

    String getOrganization();

    String getComment();
}
