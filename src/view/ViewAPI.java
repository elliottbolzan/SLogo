package view;

import javafx.scene.paint.Color;
import view.visualization.TurtleManager;

/**
 * @author Elliott Bolzan
 *
 *         Represents the high-level methods that the front-end provides to the
 *         back-end. These include general methods, like the ability to show a
 *         message to the user, and more basic getters, like the ability to
 *         return the TurtleManager.
 */
public interface ViewAPI {

	/**
	 * Clears the console / shell in the input area.
	 */
	public void clearConsole();

	/**
	 * Clears the TurtleDisplay of any drawings and lines.
	 */
	public void clearDisplay();

	/**
	 * Provides the TurtleManager to the caller.
	 * 
	 * @return the Workspace's TurtleManager.
	 */
	public TurtleManager getTurtleManager();

	/**
	 * Prints a message to the console. This method is useful for debugging and
	 * printing the return value of sample commands.
	 * 
	 * @param string
	 *            the string to be printed.
	 */
	public void printToConsole(String string);

	/**
	 * Show a message to the user. Implemented using a dialog box.
	 * 
	 * @param message
	 *            the message to be displayed.
	 */
	public void showMessage(String message);

	/**
	 * Set the background color of the TurtleDisplay to a new color.
	 * 
	 * @param color
	 *            the color to set the background to.
	 */
	public void setBackgroundColor(Color color);
}
