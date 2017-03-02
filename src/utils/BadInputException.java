package utils;

public class BadInputException extends Exception {
    public BadInputException() {}

    public BadInputException(String message)
    {
       super("Command not found.");
    }
}
