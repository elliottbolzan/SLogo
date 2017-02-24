package view;

import java.awt.Point;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
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
	private Pane myDisplayArea;
	private Turtle myTurtle;
	private int width = 400;
	private int height = 400;
	
	public TurtleDisplay() {
		this.createDisplayArea();
		this.createTurtle();
		this.setBackgroundColor(Color.ALICEBLUE); //TODO: remove this later, just helps to see placement
	}
	
	protected void clear() {
		myDisplayArea.getChildren().clear();
		this.createTurtle();
	}
	
	/**
	 * Right now this method works by breaking up the turtle path into steps (10),
	 * executing them sequentially and wrapping around the screen if it goes off. 
	 * There is still a lot of error in this method and perhaps this code could be
	 * moved to the turtle's class?
	 * @param point
	 */
	protected void moveTurtle(Point point) {
		int steps = 10;
		double stepX = (point.getX() - myTurtle.getLocation().getX())/steps;
		double stepY = (point.getY() - myTurtle.getLocation().getY())/steps;
		
		for(int i = 0; i < steps; i++) {
			Point step = new Point((int)(myTurtle.getLocation().getX() + stepX), 
								   (int)(myTurtle.getLocation().getY() + stepY));
			if(myTurtle.isPenDown()) {
				Line line = new Line(myTurtle.getLocation().getX(), myTurtle.getLocation().getY(), step.getX(), step.getY());
				myDisplayArea.getChildren().add(line);
			}
						
			myTurtle.setLocation(step);
			
			if(!isInBounds(myTurtle.getLocation())) {
				myTurtle.setLocation(this.wrap(myTurtle.getLocation()));
			}
		}
	}
	
	protected void turnTurtle(double degrees) {
		myTurtle.setRotation(myTurtle.getRotation() + degrees);
	}
	
	protected void setPenDown(boolean down) {
		myTurtle.setPenDown(down);
	}
	
	protected void setTurtleVisible(boolean visible) {
		myTurtle.setVisible(visible);
	}
	
	protected void setBackgroundColor(Paint paint) {
		BackgroundFill primaryLayer = new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(primaryLayer);
		myDisplayArea.setBackground(background);
	}
	
	private void createDisplayArea() {
		myDisplayArea = new Pane();
		myDisplayArea.setPrefSize(width, height);
		
		Rectangle clipBoundaries = new Rectangle();
		clipBoundaries.widthProperty().bind(myDisplayArea.widthProperty());
		clipBoundaries.heightProperty().bind(myDisplayArea.heightProperty());
		myDisplayArea.setClip(clipBoundaries);
				
		this.getChildren().addAll(myDisplayArea);		
	}
	
	private void createTurtle() {
		myTurtle = new Turtle();
		myDisplayArea.getChildren().add(myTurtle);
	}
	
	private boolean isInBounds(Point point) {
		return (point.getX() >= 0 && point.getX() < myDisplayArea.getWidth() && 
				point.getY() >= 0 && point.getY() < myDisplayArea.getHeight());
	}
	
	private Point wrap(Point point) {
		Point result = new Point(point);
		if(point.getX() < 0) {
			result.setLocation(myDisplayArea.getWidth(), result.getY());
		} else if (point.getX() >= myDisplayArea.getWidth()) {
			result.setLocation(0, result.getY());
		}
		
		if(point.getY() < 0) {
			result.setLocation(result.getX(), myDisplayArea.getHeight());
		} else if(point.getY() >= myDisplayArea.getHeight()) {
			result.setLocation(result.getX(), 0);
		}
		return result;
	}
}
