package view;

import javafx.scene.paint.Color;
import view.visualization.TurtleManager;

/**
 * @author Elliott Bolzan
 *
 */
public interface ViewAPI {
	
	public void clearConsole();
	
	public void clearDisplay();
		
	public TurtleManager getTurtleManager();
	
	public void print(String string);
	
	public void showMessage(String message);
	
	public void setBackgroundColor(Color color);
}
