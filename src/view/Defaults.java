/**
 * 
 */
package view;

import java.util.ResourceBundle;

import javafx.scene.paint.Color;

/**
 * @author Elliott Bolzan
 *
 */
public class Defaults {
	
	private Color backgroundColor = Color.WHITE;
	private int numberOfTurtles = 1;
	private String language = "English";
	private String scriptPath = "";
	private String turtlePath = "resources/images/turtle_1.png";

	/**
	 * 
	 */
	public Defaults(String path) {
		ResourceBundle resources = ResourceBundle.getBundle(path);
		try {
			backgroundColor = Color.valueOf(resources.getString("Color"));
			numberOfTurtles = Integer.valueOf(resources.getString("TurtleNumber"));
			language = resources.getString("Language");
			scriptPath = resources.getString("File");
			//turtlePath = resources.getString("TurtlePath");
		} catch (Exception e) {
			// show error
			// check for valid paths
			System.out.println(e.getMessage());
		}
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public int getNumberOfTurtles() {
		return numberOfTurtles;
	}

	public String getLanguage() {
		return language;
	}

	public String getScriptPath() {
		return scriptPath;
	}
	
	public String getTurtlePath() {
		return turtlePath;
	}

}
