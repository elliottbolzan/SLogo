package model.parser;

/**
 * @author Elliott Bolzan
 *
 *         A data structure used in the executing of the parsed Tree. The
 *         structure allows for both String and double values: this is to
 *         accomodate for every possible type of argument in the SLogo language.
 */
public class Argument {

	private String string;
	private double value;

	/**
	 * Creates an Argument.
	 * @param string the string that the Argument should be created with.
	 * @param value the value that the Argument should be created with.
	 */
	public Argument(String string, double value) {
		this.string = string;
		this.value = value;
	}

	/**
	 * An empty constructor for Argument.
	 */
	public Argument() {
		this("", 0);
	}

	/**
	 * A constructor for Argument that only takes a String.
	 * @param string the String to initialize the Argument with.
	 */
	public Argument(String string) {
		this(string, 0);
	}

	/**
	 * A constructor for Argument that only takes a double.
	 * @param value the value to initialize the Argument with.
	 */
	public Argument(double value) {
		this("", value);
	}

	public String getString() {
		return string;
	}

	public double getDouble() {
		return value;
	}

}
