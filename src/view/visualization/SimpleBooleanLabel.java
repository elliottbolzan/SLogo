package view.visualization;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;

/**
 * @author Jay Doherty
 *
 */
public class SimpleBooleanLabel {

	Label propertyLabel;
	String propertyName;
	String myTrueLabel;
	String myFalseLabel;
	SimpleBooleanProperty propertyValue;
	
	public SimpleBooleanLabel(String name, SimpleBooleanProperty property, String trueLabel, String falseLabel) {
		propertyLabel = new Label();
		propertyName = name;
		propertyValue = property;
		myTrueLabel = trueLabel;
		myFalseLabel = falseLabel;
		property.addListener(e -> this.updateLabel());
		this.updateLabel();
	}
	
	protected Label getLabel() {
		return propertyLabel;
	}
	
	private void updateLabel() {
		if(propertyValue.get()) {
			propertyLabel.setText(String.format("%s %s", propertyName, myTrueLabel));
		} else {
			propertyLabel.setText(String.format("%s %s", propertyName, myFalseLabel));
		}
	}
}
