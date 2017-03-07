package view.visualization;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class TurtleInfo {

	VBox turtleInfoGraphic;
	
	public TurtleInfo(Turtle turtle) {
		turtleInfoGraphic = new VBox();
		turtleInfoGraphic.setPadding(new Insets(5, 5, 5, 5));
		
		SimpleDoubleLabel xLabel = new SimpleDoubleLabel("X:", turtle.xProperty());
		SimpleDoubleLabel yLabel = new SimpleDoubleLabel("Y:", turtle.yProperty());
		SimpleDoubleLabel rotateLabel = new SimpleDoubleLabel("Heading:", turtle.rotationProperty());
		SimpleBooleanLabel penLabel = new SimpleBooleanLabel("Pen:", turtle.penDownProperty(), "down", "up");

		turtleInfoGraphic.getChildren().addAll(xLabel.getLabel(), yLabel.getLabel(), rotateLabel.getLabel(), penLabel.getLabel());
	}
	
	protected VBox getView() {
		return turtleInfoGraphic;
	}
}
