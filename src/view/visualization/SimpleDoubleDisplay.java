package view.visualization;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;

public class SimpleDoubleDisplay {

	Label propertyLabel;
	String propertyName;
	SimpleDoubleProperty propertyValue;
	
	public SimpleDoubleDisplay(String name, SimpleDoubleProperty property) {
		propertyLabel = new Label();
		propertyName = name;
		propertyValue = property;
		property.addListener(e -> this.updateLabel());
	}
	
	protected Label getLabel() {
		return propertyLabel;
	}
	
	private void updateLabel() {
		propertyLabel.setText(propertyName + " : " + propertyValue.get());
	}
}
