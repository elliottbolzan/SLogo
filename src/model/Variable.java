package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Variable {

	private SimpleStringProperty name;
	private SimpleDoubleProperty value;

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
