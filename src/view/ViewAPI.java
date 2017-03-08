package view;

import java.awt.Dimension;

import model.ActiveTurtles;
import utils.Point;
import view.visualization.Turtle;

/**
 * @author Elliott Bolzan
 *
 */
public interface ViewAPI {
	
	public void print(String string);
	
	public void clearConsole();
		
	//public void moveTo(Point point);
	
	public void turn(double degrees);
	
	public void setPenDown(boolean down);
	
	public void setTurtleVisible(boolean visible);
		
	//public Turtle getTurtle();
	
	public void clearDisplay();
	
	public Dimension getDisplaySize();
	
	public void showMessage(String message);

	void moveTo(int turtle, Point point);

	ActiveTurtles getActiveTurtles();

	Turtle getTurtle(int index);
	
}
