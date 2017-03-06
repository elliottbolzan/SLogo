package view.visualization;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class TurtleInfo {

	TurtleDisplay myDisplay;
	VBox turtleInfoGraphic;
	
	public TurtleInfo(TurtleDisplay display, int id) {
		myDisplay = display;
		turtleInfoGraphic = new VBox();
		turtleInfoGraphic.setPadding(new Insets(5, 5, 5, 5));
		
		SimpleDoubleLabel xLabel = new SimpleDoubleLabel("X", myDisplay.getTurtle(id).xProperty());
		SimpleDoubleLabel yLabel = new SimpleDoubleLabel("Y", myDisplay.getTurtle(id).yProperty());
		SimpleDoubleLabel rotateLabel = new SimpleDoubleLabel("Heading", myDisplay.getTurtle(id).rotationProperty());
		SimpleBooleanLabel penLabel = new SimpleBooleanLabel("Pen", myDisplay.getTurtle(id).penDownProperty(), "down", "up");

		turtleInfoGraphic.getChildren().addAll(xLabel.getLabel(), yLabel.getLabel(), rotateLabel.getLabel(), penLabel.getLabel());
	}
	
	protected VBox getView() {
		return turtleInfoGraphic;
	}
}
