package view.visualization;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import utils.Point;

/**
 * @author Jay Doherty
 * This class encapsulates the part of the turtle that draws lines on the display.
 */
public class Pen {

	private TurtleDisplay display;
	private SimpleBooleanProperty isDownProperty;
	private Color color;
	private double thickness;
	private int colorIndex;
	
	protected Pen(TurtleDisplay canvas, boolean isDown, Color initialColor, int initialColorIndex, double initialWidth) {
		display = canvas;
		isDownProperty = new SimpleBooleanProperty(isDown);
		color = initialColor;
		colorIndex = initialColorIndex;
		thickness = initialWidth;
	}

	protected void drawLine(Point start, Point finish) {
		Line line = new Line(start.getX(), start.getY(), finish.getX(), finish.getY());
		line.setStroke(color);
		line.setStrokeWidth(thickness);
		display.addToDisplayArea(line);
	}
	
	protected ReadOnlyBooleanProperty readOnlyIsDownProperty() {
		return ReadOnlyBooleanProperty.readOnlyBooleanProperty(isDownProperty);
	}
	
	protected boolean isDown() {
		return isDownProperty.get();
	}
	
	protected void setDown(boolean down) {
		isDownProperty.set(down);
	}
	
	protected Color getColor() {
		return color;
	}
	
	protected void setColor(Color c) {
		color = c;
	}
	
	protected int getColorIndex() {
		return colorIndex;
	}
	
	protected void setColorIndex(int index) {
		colorIndex = index;
	}
	
	protected void setThickness(double width) {
		thickness = width;
	}
}
