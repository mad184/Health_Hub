package healthhub;

import staff.Manager;

public class HealthHubAccessSingleton {

  // attributes
  private static HealthHub healthHubOrganization = null;



  // private constructor to ensure no instances are created
  private HealthHubAccessSingleton() {
  }


  public static void newOrganziation(String name, Manager owner) {
    //the organization must have a name
    if (name == null || name.equals("")) {
      throw new RuntimeException("The name of a ward cannot be null or empty.  " + "It is " + name);
    }

    // we can only only initialize the organization once
    else if (healthHubOrganization != null) {
      throw new RuntimeException("Initialize should only be invoked once.");
    }
    healthHubOrganization = new HealthHub(name, owner);
  }


  public static HealthHub getHealthHub() {
    if (healthHubOrganization == null) {
      throw new RuntimeException(
          "The organization must be previously initialized before it can be accessed.");
    }
    return healthHubOrganization;
  }


  public static boolean isOrganizationCreated(){
    return healthHubOrganization != null;
  }
}
