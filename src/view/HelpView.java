/**
 * 
 */
package view;

import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * @author Elliott Bolzan
 *
 *         This class serves to display an HTML-formatted help view. In essence,
 *         the help view is a command reference for SLogo. The actual command
 *         reference was obtained from the course website.
 */
public class HelpView extends Stage {

	/**
	 * @param resources
	 *            the ResourceBundle that contains both the path to the HTML
	 *            page and the title of the view.
	 */
	protected HelpView(ResourceBundle resources) {
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.load(getClass().getClassLoader().getResource(resources.getString("HelpPath")).toExternalForm());
		Scene scene = new Scene(browser, 800, 600);
		setTitle(resources.getString("HelpTitle"));
		setScene(scene);
	}

}
