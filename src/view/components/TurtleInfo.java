package view.components;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import view.visualization.Turtle;

public class TurtleInfo {

	VBox turtleInfoGraphic;
	HBox controlButtons;
	
	public TurtleInfo(Turtle turtle) {
		turtleInfoGraphic = new VBox();
		turtleInfoGraphic.setPadding(new Insets(5, 5, 5, 5));
		
		SimpleDoubleLabel xLabel = new SimpleDoubleLabel("X: ", turtle.readOnlyXProperty());
		SimpleDoubleLabel yLabel = new SimpleDoubleLabel("Y: ", turtle.readOnlyYProperty());
		SimpleDoubleLabel rotateLabel = new SimpleDoubleLabel("Heading: ", turtle.readOnlyRotationProperty());
		SimpleBooleanLabel penLabel = new SimpleBooleanLabel("Pen down: ", turtle.readOnlyPenDownProperty(), "down", "up");
		
		turtleInfoGraphic.getChildren().addAll(xLabel.getView(), yLabel.getView(), rotateLabel.getView(), penLabel.getView());
	}
	
	public VBox getView() {
		return turtleInfoGraphic;
	}
}
