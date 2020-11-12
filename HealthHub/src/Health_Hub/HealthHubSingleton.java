package Health_Hub;

public class HealthHubSingleton {

  // attributes
  private static HealthHubSingleton HealthHub;
  String organizationName;


  // private constructor to ensure no instances are created
  private HealthHubSingleton(String Name) {
    organizationName = Name;
  }


  public static void newOrganziation(String name) {
    //the organization must have a name
    if (name == null || name.equals("")) {
      throw new RuntimeException("The name of a ward cannot be null or empty.  " + "It is " + name);
    }

    // we can only only initialize the organization once
    else if (HealthHub != null) {
      throw new RuntimeException("Initialize should only be invoked once.");
    }

    HealthHub = new HealthHubSingleton(name);
  }


  public static HealthHubSingleton getHealthHub() {
    if (HealthHub == null) {
      throw new RuntimeException(
          "The organization must be previously initialized before it can be accessed.");
    }
    return HealthHub;
  }
}
