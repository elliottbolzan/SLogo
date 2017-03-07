/**
 * 
 */
package view.settings;

import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * @author Elliott Bolzan
 *
 */
public class HelpView extends Stage {
	
	private static final String PATH_TO_HELP = "resources/help.html";

	/**
	 * 
	 */
	public HelpView(ResourceBundle resources) {
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.load(getClass().getClassLoader().getResource(PATH_TO_HELP).toExternalForm());
		Scene scene = new Scene(browser, 800, 600);
		setTitle(resources.getString("HelpTitle"));
		setScene(scene);
	}

}
