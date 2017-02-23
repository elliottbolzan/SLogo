package view;

import java.awt.Point;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;

/**
 * @author Jay Doherty
 *
 */
public class TurtleDisplay extends Group {
	private Pane displayArea;
	private int width = 400;
	private int height = 400;
	
	public TurtleDisplay() {
		this.createDisplayArea();
		this.setBackgroundColor(Color.BLUE); //TODO: remove this later, just helps to see placement
	}
	
	protected void clear() {
		//TODO
		//maybe this: displayArea.getChildren().clear();
	}
	
	protected void moveTurtle(Point point) {
		//TODO
	}
	
	protected void turnTurtle(double degrees) {
		//TODO
	}
	
	protected void setPenDown(boolean down) {
		//TODO
	}
	
	protected void setTurtleVisible(boolean visible) {
		//TODO
	}
	
	protected void setBackgroundColor(Paint paint) {
		BackgroundFill primaryLayer = new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(primaryLayer);
		displayArea.setBackground(background);
	}
	
	private void createDisplayArea() {
		displayArea = new Pane();
		displayArea.setPrefSize(width, height);
		this.getChildren().add(displayArea);
	}
}
