package view.visualization;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.components.SimpleBooleanLabel;
import view.components.SimpleDoubleLabel;

public class TurtleInfo {

	private VBox turtleInfoGraphic;
	
	public TurtleInfo(Turtle turtle) {
		turtleInfoGraphic = new VBox(5);
		turtleInfoGraphic.setPadding(new Insets(5, 5, 5, 5));
		
		SimpleDoubleLabel xLabel = new SimpleDoubleLabel("X: ", turtle.readOnlyXProperty());
		SimpleDoubleLabel yLabel = new SimpleDoubleLabel("Y: ", turtle.readOnlyYProperty());
		SimpleDoubleLabel rotateLabel = new SimpleDoubleLabel("Heading: ", turtle.readOnlyRotationProperty());
		SimpleBooleanLabel penLabel = new SimpleBooleanLabel("Pen down: ", turtle.readOnlyPenDownProperty(), "down", "up");
		
		ColorPicker penPicker = new ColorPicker();
		penPicker.setOnAction(e -> turtle.setPenColor(penPicker.getValue()));
		HBox penPickerBox = addLabel(penPicker, "Pen Color:");
		
		Spinner<Double> fontPicker = new Spinner<Double>(1.0,100.0,1.0);
		fontPicker.valueProperty().addListener(e -> turtle.setPenWidth(fontPicker.getValue()));
		fontPicker.prefWidthProperty().bind(turtleInfoGraphic.widthProperty().divide(3.0));
		HBox fontPickerBox = addLabel(fontPicker, "Line Thickness:");
		
		CheckBox activeSelect = new CheckBox();
		activeSelect.selectedProperty().bindBidirectional(turtle.activeProperty());
		HBox activeSelectBox = addLabel(activeSelect, "Active");
		
		turtleInfoGraphic.getChildren().addAll(activeSelectBox, new Separator(), xLabel.getView(), yLabel.getView(), rotateLabel.getView(), penLabel.getView(), 
											   new Separator(), penPickerBox, new Separator(), fontPickerBox);
	}
	
	public VBox getView() {
		return turtleInfoGraphic;
	}
	
	private HBox addLabel(Node element, String name) {
		HBox box = new HBox(5);
		Label label = new Label(name);
		box.getChildren().addAll(label, element);
		return box;
	}
}
