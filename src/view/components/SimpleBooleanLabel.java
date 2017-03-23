package view.components;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.scene.control.Label;

/**
 * @author Jay Doherty
 * This class makes a label that displays one of two words based on the value of a boolean property.
 */
public class SimpleBooleanLabel {

	Label propertyLabel;
	String propertyName;
	String trueLabel;
	String falseLabel;
	ReadOnlyBooleanProperty propertyValue;
	
	public SimpleBooleanLabel(String name, ReadOnlyBooleanProperty property, String trueLabel, String falseLabel) {
		propertyLabel = new Label();
		propertyName = name;
		this.trueLabel = trueLabel;
		this.falseLabel = falseLabel;
		propertyValue = property;
		propertyValue.addListener(e -> this.updateLabel());
		this.updateLabel();
	}
	
	/**
	 * @return the JavaFX label
	 */
	public Label getView() {
		return propertyLabel;
	}
	
	private void updateLabel() {
		if(propertyValue.get()) {
			propertyLabel.setText(String.format("%s%s", propertyName, trueLabel));
		} else {
			propertyLabel.setText(String.format("%s%s", propertyName, falseLabel));
		}
	}
}
