package utils;

/**
 * @author Robert Duvall This class was taken from lecture example for handling
 *         XML errors.
 */
public class XMLException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Create an exception based on an issue in our code.
	 */
	public XMLException(String message, Object... values) {
		super(String.format(message, values));
	}

	/**
	 * Create an exception based on a caught exception with a different message.
	 */
	public XMLException(Throwable cause, String message, Object... values) {
		super(String.format(message, values), cause);
	}

	/**
	 * Create an exception based on a caught exception, with no additional
	 * message.
	 */
	public XMLException(Throwable cause) {
		super(cause);
	}
}
