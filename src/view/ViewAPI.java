package view;
import java.awt.Dimension;
import view.visualization.TurtleManager;
/**
 * @author Elliott Bolzan
 *
 */
public interface ViewAPI {
	
	public void clearConsole();
	
	public void clearDisplay();
		
	public Dimension getDisplaySize();
	public TurtleManager getTurtleManager();
	
	public void print(String string);
	
	public void showMessage(String message);
	
}