package staff;

public class ClientNotFoundException extends Exception {

    final String message;

    public ClientNotFoundException() {
        message = "Client is not assigned to this Instructor";
    }

    public String toString() {
        return message;
    }
}
