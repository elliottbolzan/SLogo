/**
 * 
 */
package view;

import java.io.File;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
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
	private Image turtleImage = new Image(
			getClass().getClassLoader().getResourceAsStream("resources/images/turtle_1.png"));

	/**
	 * 
	 */
	protected Defaults(Workspace workspace, String path) {
		ResourceBundle resources = ResourceBundle.getBundle(path);
		try {
			backgroundColor = Color.valueOf(resources.getString("Color"));
			numberOfTurtles = Integer.parseInt(resources.getString("TurtleNumber"));
			language = resources.getString("Language");
			scriptPath = resources.getString("File");
			String turtlePath = resources.getString("TurtlePath").replaceAll("\\s+", "");
			if (!(turtlePath.equals(""))) {
				if (!(new File(turtlePath.replaceAll("(file:)", "")).exists())) {
					throw new Exception();
				}
				turtleImage = new Image(resources.getString("TurtlePath"));
			}
		} catch (Exception e) {
			workspace.showMessage(workspace.getController().getResources().getString("ConfigurationError"));
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

	public Image getTurtleImage() {
		return turtleImage;
	}

}
