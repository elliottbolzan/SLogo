package view;

import utils.Point;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle extends Group {
	private static final String TURTLE_IMAGE = "view/turtle.png";
	private ImageView myImage;
	private Point myLocation;
	private double myRotation;
	private boolean myPen;
	
	public Turtle() {
		myLocation = new Point(0,0);
		myRotation = 0.0;
		myPen = true;
		myImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE)));
		this.getChildren().add(myImage);
	}
	
	protected Point getLocation() {
		return myLocation;
	}
	
	protected double getRotation() {
		return myRotation;
	}
	
	protected boolean isPenDown() {
		return myPen;
	}
	
	protected void setLocation(Point point) {
		myLocation = point;
		myImage.setX(point.getX() - myImage.getBoundsInLocal().getWidth()/2.0);
		myImage.setY(point.getY() - myImage.getBoundsInLocal().getHeight()/2.0);
	}
	
	protected void setRotation(double degrees) {
		myRotation = degrees;
		myImage.setRotate(degrees);
	}
	
	protected void setPenDown(boolean down) {
		myPen = down;
	}
}
