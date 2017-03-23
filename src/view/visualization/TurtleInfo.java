package view.visualization;

import controller.Controller;
import javafx.geometry.Insets;
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

/**
 * @author Jay Doherty
 * This class wraps all of the turtle's info into a UI element. This UI element shows the turtle's
 * location, heading, and pen and allows the user to modify the pen and/or the turtle's active status.
 */
public class TurtleInfo {

	private VBox turtleInfoGraphic;
	
	public TurtleInfo(Controller controller, Turtle turtle) {
		turtleInfoGraphic = new VBox(5);
		turtleInfoGraphic.setPadding(new Insets(5, 5, 5, 5));
		
		SimpleDoubleLabel xLabel = new SimpleDoubleLabel(controller.getResources().getString("XLabel"), turtle.readOnlyXProperty());
		SimpleDoubleLabel yLabel = new SimpleDoubleLabel(controller.getResources().getString("YLabel"), turtle.readOnlyYProperty());
		SimpleDoubleLabel rotateLabel = new SimpleDoubleLabel(controller.getResources().getString("HeadingLabel"), turtle.readOnlyRotationProperty());
		SimpleBooleanLabel penLabel = new SimpleBooleanLabel(controller.getResources().getString("PenLabel"), turtle.readOnlyPenDownProperty(), controller.getResources().getString("PenDown"), controller.getResources().getString("PenUp"));
		
		ColorPicker penPicker = new ColorPicker();
		penPicker.setOnAction(e -> turtle.setPenColor(penPicker.getValue()));
		HBox penPickerBox = addLabel(penPicker, controller.getResources().getString("PenColorLabel"));
		
		Spinner<Double> fontPicker = new Spinner<Double>(1.0,100.0,1.0);
		fontPicker.valueProperty().addListener(e -> turtle.setPenWidth(fontPicker.getValue()));
		fontPicker.prefWidthProperty().bind(turtleInfoGraphic.widthProperty().divide(3.0));
		HBox fontPickerBox = addLabel(fontPicker, controller.getResources().getString("LineThicknessLabel"));
		
		CheckBox activeSelect = new CheckBox();
		activeSelect.selectedProperty().bindBidirectional(turtle.activeProperty());
		HBox activeSelectBox = addLabel(activeSelect, controller.getResources().getString("ActiveLabel"));
		
		turtleInfoGraphic.getChildren().addAll(activeSelectBox, new Separator(), xLabel.getView(), yLabel.getView(), rotateLabel.getView(), penLabel.getView(), 
											   new Separator(), penPickerBox, new Separator(), fontPickerBox);
	}
	
	/**
	 * @return the JavaFX element containing the TurtleInfo object
	 */
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
