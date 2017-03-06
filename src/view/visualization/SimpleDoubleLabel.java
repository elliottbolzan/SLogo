package view.visualization;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;

public class SimpleDoubleLabel {

	Label propertyLabel;
	String propertyName;
	SimpleDoubleProperty propertyValue;
	
	public SimpleDoubleLabel(String name, SimpleDoubleProperty property) {
		propertyLabel = new Label();
		propertyName = name;
		propertyValue = property;
		property.addListener(e -> this.updateLabel());
		this.updateLabel();
	}
	
	protected Label getLabel() {
		return propertyLabel;
	}
	
	private void updateLabel() {
		propertyLabel.setText(String.format("%s : %.2f", propertyName, propertyValue.get()));
	}
}
