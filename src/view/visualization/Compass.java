package view.visualization;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import utils.Point;

/**
 * @author Jay Doherty
 * This class encapsulates the part of the turtle that is responsible for keeping track of the 
 * turtle's location and heading. 
 */
public class Compass {

	private SimpleDoubleProperty myXProperty;
	private SimpleDoubleProperty myYProperty;
	private SimpleDoubleProperty myRotationProperty;
	
	protected Compass() {
		myXProperty = new SimpleDoubleProperty(0);
		myYProperty = new SimpleDoubleProperty(0);
		myRotationProperty = new SimpleDoubleProperty(0);
	}

	protected double getX() {
		return myXProperty.get();
	}
	
	protected double getY() {
		return myYProperty.get();
	}
	
	protected Point getLocation() {
		return new Point(myXProperty.get(), myYProperty.get());
	}
	
	protected void setX(double x) {
		myXProperty.set(x);
	}
	
	protected void setY(double y) {
		myYProperty.set(y);
	}
	
	protected double getHeading() {
		return myRotationProperty.get();
	}
	
	protected void setHeading(double degrees) {
		myRotationProperty.set(degrees);
	}
	
	protected ReadOnlyDoubleProperty readOnlyXProperty() {
		return ReadOnlyDoubleProperty.readOnlyDoubleProperty(myXProperty);
	}
	
	protected ReadOnlyDoubleProperty readOnlyYProperty() {
		return ReadOnlyDoubleProperty.readOnlyDoubleProperty(myYProperty);
	}

	protected ReadOnlyDoubleProperty readOnlyRotationProperty() {
		return ReadOnlyDoubleProperty.readOnlyDoubleProperty(myRotationProperty);
	}
}
