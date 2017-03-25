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
 *         This class has two purposes: to read defaults in from a configuration
 *         property file, and to provide default values in the case that this
 *         reading fails.
 */
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
	 * Returns an instance of Defaults.
	 * 
	 * @param workspace
	 *            the workspace that owns this instance of Defaults.
	 * @param path
	 *            the path to the ResourceBundle in question.
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

	/**
	 * Seeks to read the path to a default turtle image.
	 * 
	 * @throws Exception
	 */
	private void readTurtlePath() throws Exception {
		String turtlePath = configurationResources.getString("TurtlePath").replaceAll("\\s+", "");
		if (!(turtlePath.equals(""))) {
			if (!(new File(turtlePath.replaceAll("(file:)", "")).exists())) {
				throw new Exception();
			}
			turtleImage = new Image(configurationResources.getString("TurtlePath"));
		}
	}

	/**
	 * Seeks to read the path to a default script.
	 */
	private void readScriptPath() {
		scriptPath = configurationResources.getString("File");
	}

	/**
	 * Seeks to load a default language from file.
	 */
	private void readLanguage() {
		language = configurationResources.getString("Language");
	}

	/**
	 * Seeks to read the default number of turtles from file.
	 */
	private void readNumberOfTurtles() {
		numberOfTurtles = Integer.parseInt(configurationResources.getString("TurtleNumber"));
	}

	
	/**
	 * Seeks to read the default background color.
	 */
	private void readBackgroundColor() {
		backgroundColor = Color.valueOf(configurationResources.getString("Color"));
	}
	
	/**
	 * @return the default background color.
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @return the default number of turtles.
	 */
	public int getNumberOfTurtles() {
		return numberOfTurtles;
	}

	/**
	 * @return the default language.
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @return the default script path.
	 */
	public String getScriptPath() {
		return scriptPath;
	}

	/**
	 * @return the default turtle image.
	 */
	public Image getTurtleImage() {
		return turtleImage;
	}

}
