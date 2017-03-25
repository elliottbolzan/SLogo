package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Elliott Bolzan
 *
 *         This class represents one variable. A variable has a name, and a
 *         value. Both the name and the value of a variable are Observable
 *         through JavaFX's Property system.
 */
public class Variable {

	private SimpleStringProperty name;
	private SimpleDoubleProperty value;

	/**
	 * Create a Variable.
	 * @param name the name of the variable.
	 * @param value the value of the variable.
	 */
	public Variable(String name, double value) {
		this.name = new SimpleStringProperty(name);
		this.value = new SimpleDoubleProperty(value);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getValue() {
		return Double.toString(value.get());
	}

	public void setValue(String string) {
		this.value.set(Double.parseDouble(string));
	}

}
