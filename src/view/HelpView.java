/**
 * 
 */
package view;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * @author Elliott Bolzan
 *
 */
public class HelpView extends Stage {

	/**
	 * 
	 */
	public HelpView() {
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.load(getClass().getClassLoader().getResource("view/help.html").toExternalForm());
		Scene scene = new Scene(browser, 800, 600);
		setTitle("SLogo Help");
		setScene(scene);
	}

}
