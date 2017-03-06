package view.visualization;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;

public class SimpleBooleanDisplay {

	Label propertyLabel;
	String propertyName;
	String myTrueLabel;
	String myFalseLabel;
	SimpleBooleanProperty propertyValue;
	
	public SimpleBooleanDisplay(String name, SimpleBooleanProperty property, String trueLabel, String falseLabel) {
		propertyLabel = new Label();
		propertyName = name;
		propertyValue = property;
		myTrueLabel = trueLabel;
		myFalseLabel = falseLabel;
		property.addListener(e -> this.updateLabel());
	}
	
	protected Label getLabel() {
		return propertyLabel;
	}
	
	private void updateLabel() {
		if(propertyValue.get()) {
			propertyLabel.setText(propertyName + " : " + myTrueLabel);
		} else {
			propertyLabel.setText(propertyName + " : " + myFalseLabel);
		}
	}
}
