package healthhub;

import staff.Manager;

public class HealthHub {
    String organizationName;
    Manager owner;

    public HealthHub(String organizationName, Manager owner){
        this.organizationName = organizationName;
        this.owner = owner;
    }

}
