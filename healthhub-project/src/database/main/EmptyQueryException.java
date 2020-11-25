package database.main;

public class EmptyQueryException extends Exception{
    final String exceptionMessage;

    public EmptyQueryException() {
        exceptionMessage = "Query result returned nothing";
    }

    public String toString() {
        return exceptionMessage;
    }
}
