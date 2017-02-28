/**
 * 
 */
package view.settings;

import controller.Controller;
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
	public HelpView(Controller controller) {
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.load(getClass().getClassLoader().getResource(PATH_TO_HELP).toExternalForm());
		Scene scene = new Scene(browser, 800, 600);
		setTitle(controller.getResources().getString("HelpTitle"));
		setScene(scene);
	}

}
