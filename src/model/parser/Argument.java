package model.parser;

/*
 * Data structure to manage the potential for string or double parameters in each command.
 */
public class Argument {

	private String string;
	private double value;
	
	public Argument(String string, double value) {
		this.string = string;
		this.value = value;
	}
	
	public Argument() {
		this("", 0);
	}
	
	public Argument(String string) {
		this(string, 0);
	}
	
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
