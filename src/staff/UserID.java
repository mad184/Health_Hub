package staff;

/** A storage class which stores the name and database ID of a user. */
public class UserID {
  private int id;
  private String name;


  public String toString() {
    return this.name + ";" + this.id;
  }

  /**
   * Constructs a UserID object.
   *
   * @param id: Database ID of the user
   * @param name: Name of the user
   */
  public UserID(int id, String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * Gets the name of the user
   *
   * @return the name of the user
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the database ID of the user.
   *
   * @return the id of the user
   */
  public int getId() {
    return this.id;
  }

  /**
   * Changes the name of the user.
   *
   * @param name: Name of the user
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the database ID of the user.
   *
   * @param id: ID of the user
   */
  public void setId(int id) {
    this.id = id;
  }
}
