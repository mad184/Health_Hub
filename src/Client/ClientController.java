package Client;

public class ClientController {
    private Client model;


    public ClientController(Client model) {
        this.model = model;
    }

    public void setClientName(String name){
        model.setName(name);
    }

    public void setClientOrg(String org){
        model.setOrganization(org);
    }

    public void setClientInstructor(String name){
        model.setInstructor(name);
    }

    public void setClientEmail(String email){
        model.setEmail(email);
    }

    public void setClientID(int ID){
        model.setId(ID);
    }

    public void setClientAge(int age){
        model.setAge(age);
    }

    public void setClientWeight(int weight){
        model.setWeight(weight);
    }

    public void setClientHeight(int height){
        model.setHeight(height);
    }

    public void setClientPhoneNum(String num){
        model.setPhoneNum(num);
    }

    public void setClientGoal(double goal){
        model.setGoal(goal);
    }

    public void setClientCals(double cals){
        model.setCalories(cals);
    }

    public void setClientAllergies(String[] allergies){
        model.setAllergies(allergies);
    }

    public void setClientComment(String[] comment){
        model.setComment(comment);
    }
}
