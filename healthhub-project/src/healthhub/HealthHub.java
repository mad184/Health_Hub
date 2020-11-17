package healthhub;

import staff.Manager;

public class HealthHub {
    String organizationName;
    Manager owner;

    protected HealthHub(String organizationName, Manager owner){
        this.organizationName = organizationName;
        this.owner = owner;
    }

    protected String getOrganizationName(){
        return this.organizationName;
    }

    protected Manager getOwner(){
        return this.owner;
    }


}
