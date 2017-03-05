package utils;

public class BadInputException extends Exception {
	private static final long serialVersionUID = 1L;

	public BadInputException() {}

    public BadInputException(String message)
    {
       super("Command not found.");
    }
}
