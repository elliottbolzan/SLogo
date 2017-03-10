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
	private ResourceBundle configurationResources;

	/**
	 * 
	 */
	protected Defaults(Workspace workspace, String path) {
		configurationResources = ResourceBundle.getBundle(path);
		try {
			readBackgroundColor();
			readNumberOfTurtles();
			readLanguage();
			readScriptPath();
			readTurtlePath();
		} catch (Exception e) {
			workspace.showMessage(workspace.getController().getResources().getString("ConfigurationError"));
		}
	}

	private void readTurtlePath() throws Exception {
		String turtlePath = configurationResources.getString("TurtlePath").replaceAll("\\s+", "");
		if (!(turtlePath.equals(""))) {
			if (!(new File(turtlePath.replaceAll("(file:)", "")).exists())) {
				throw new Exception();
			}
			turtleImage = new Image(configurationResources.getString("TurtlePath"));
		}
	}

	private void readScriptPath() {
		scriptPath = configurationResources.getString("File");
	}

	private void readLanguage() {
		language = configurationResources.getString("Language");
	}

	private void readNumberOfTurtles() {
		numberOfTurtles = Integer.parseInt(configurationResources.getString("TurtleNumber"));
	}

	private void readBackgroundColor() {
		backgroundColor = Color.valueOf(configurationResources.getString("Color"));
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
