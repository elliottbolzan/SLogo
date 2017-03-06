package view;

import java.awt.Dimension;

import model.commands.Command;
import utils.BadInputException;
import utils.Point;
import view.visualization.Turtle;

/**
 * @author Elliott Bolzan
 *
 */
public interface ViewAPI {
	
	public void print(String string);
	
	public void clearConsole();
		
	public void moveTo(Point point);
	
	public void turn(double degrees);
	
	public void setPenDown(boolean down);
	
	public void setTurtleVisible(boolean visible);
		
	public Turtle getTurtle();
	
	public void clearDisplay();
	
	public Dimension getDisplaySize();
}
