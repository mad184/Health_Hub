public interface ClientInterface {

    //setters for client attributes
    void setName(String name);
    void setId(int id);
    void setEmail(String email);
    void setPhoneNum(int phoneNumber);
    void setHeight(int height);
    void setWeight(int weight);
    void setGoal(double goal);
    void setCalories(double calories);
    void setAllergies(String[] allergies);
    void setInstructor(String instructor);
    void setOrganization(String organization);
    void setComment(String[] comment);
    //void controller(Controller);

    //getters for client
    String getName();
    int getId();
    String getEmail();
    int getPhoneNum();
    int getHeight();
    int getWeight();
    double getGoal();
    double getCalories();
    String[] getAllergies();
    String getInstructor();
    String getOrganization();
    //void controller();
    String[] getComment();

}
