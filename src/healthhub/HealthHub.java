package healthhub;

import staff.Manager;

public class HealthHub {
    String organizationName;
    String owner;

    protected HealthHub(String organizationName, String ownerName) {
        this.organizationName = organizationName;
        this.owner = ownerName;
    }

    protected String getOrganizationName() {
        return this.organizationName;
    }

    protected String getOwner() {
        return this.owner;
    }


}
