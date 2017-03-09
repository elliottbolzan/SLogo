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
		} catch (Exception e) {
			// show error
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

}
