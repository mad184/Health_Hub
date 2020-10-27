public class Client implements ClientInterface {
    private String name, email, instructor, organization;
    private int id, age, height, weight, phoneNumber;
    private Double goal, calories;
    private String[] allergies, comment;

    //setters
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPhoneNum(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public void setGoal(double goal) {
        this.goal = goal;
    }

    @Override
    public void setCalories(double calories) {
        this.calories = calories;
    }

    @Override
    public void setAllergies(String[] allergies) {
        this.allergies = allergies;
    }

    @Override
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public void setOrganization(String organization) {
        this.organization = organization;
    }


    @Override
    public void setComment(String[] comment) {
        this.comment = comment;
    }

    //getters
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public int getPhoneNum() {
        return this.phoneNumber;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public double getGoal() {
        return this.goal;
    }

    @Override
    public double getCalories() {
        return this.calories;
    }

    @Override
    public String[] getAllergies() {
        return this.allergies;
    }

    @Override
    public String getInstructor() {
        return this.instructor;
    }

    @Override
    public String getOrganization() {
        return this.organization;
    }

    @Override
    public String[] getComment() {
        return this.comment;
    }
}
