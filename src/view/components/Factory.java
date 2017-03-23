/**
 * 
 */
package view.components;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Elliott Bolzan
 *
 *         A class that creates default GUI components. Currently, the class can
 *         create the following items: file choosers, directory choosers, text
 *         input dialogs, alerts, accordions, buttons, and tab buttons.
 */
public class Factory {

	private ResourceBundle resources;

	/**
	 * Returns a Factory.
	 * 
	 * @param resources
	 *            the ResourceBundle that the Factory makes use of.
	 */
	public Factory(ResourceBundle resources) {
		this.resources = resources;
	}

	/**
	 * @param path
	 *            the default path for the FileChooser.
	 * @param extensionName
	 *            the name of the type of file being picked.
	 * @param types
	 *            the types of file that can be picked.
	 * @return a FileChooser.
	 */
	public FileChooser makeFileChooser(String path, String extensionName, String... types) {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(path));
		chooser.getExtensionFilters().setAll(new ExtensionFilter(extensionName, types));
		return chooser;
	}

	/**
	 * @param path
	 *            the default path for the DirectoryChooser.
	 * @param titleProperty
	 *            the property that provides the title for the chooser.
	 * @return a DirectoryChooser.
	 */
	public DirectoryChooser makeDirectoryChooser(String path, String titleProperty) {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle(resources.getString(titleProperty));
		chooser.setInitialDirectory(new File(path));
		return chooser;
	}

	/**
	 * @param titleProperty
	 *            the property that provides the title for the dialog.
	 * @param headerProperty
	 *            the property that provides the header for the dialog.
	 * @param contentProperty
	 *            the property that provides the content for the dialog.
	 * @param placeholderProperty
	 *            the property that provides a placeholder for the dialog.
	 * @return a TextInputDialog.
	 */
	public TextInputDialog makeTextInputDialog(String titleProperty, String headerProperty, String contentProperty,
			String placeholderProperty) {
		TextInputDialog dialog = new TextInputDialog(resources.getString(placeholderProperty));
		dialog.setTitle(resources.getString(titleProperty));
		dialog.setHeaderText(resources.getString(headerProperty));
		dialog.setContentText(resources.getString(contentProperty));
		return dialog;
	}

	/**
	 * @param type
	 *            the type of Alert that needs to be created.
	 * @param titleProperty
	 *            the property that provides the title for the Alert.
	 * @param headerProperty
	 *            the property that provides the header for the Alert.
	 * @param content
	 *            the property that provides the content for the Alert.
	 * @return the Alert.
	 */
	public Alert makeAlert(AlertType type, String titleProperty, String headerProperty, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(resources.getString(titleProperty));
		alert.setHeaderText(resources.getString(headerProperty));
		alert.setContentText(content);
		return alert;
	}

	/**
	 * @param subviews
	 *            the subviews that are to be added to the Accordion.
	 * @param subviewTitles
	 *            the titles of the subviews that are added to the Accordion.
	 * @return an Accordion.
	 */
	public Accordion makeAccordion(List<Node> subviews, List<String> subviewTitles) {
		Accordion accordion = new Accordion();
		List<TitledPane> titledPanes = new ArrayList<TitledPane>();
		for (int i = 0; i < subviews.size(); i++) {
			TitledPane pane = new TitledPane(subviewTitles.get(i), subviews.get(i));
			titledPanes.add(pane);
		}
		accordion.getPanes().addAll(titledPanes);
		accordion.setExpandedPane(titledPanes.get(0));
		return accordion;
	}

	/**
	 * @param property the property that provides the title of the Button.
	 * @param handler the handler executed when the button is clicked.
	 * @param fill whether the button should fill its available space.
	 * @return a Button.
	 */
	public Button makeButton(String property, EventHandler<ActionEvent> handler, boolean fill) {
		Button button = new Button(resources.getString(property));
		button.setOnAction(handler);
		if (fill) {
			HBox.setHgrow(button, Priority.ALWAYS);
			button.setMaxWidth(Double.MAX_VALUE);
		}
		return button;
	}

	/**
	 * @param path the path to the image for the tab button.
	 * @param action the action to be executed when the tab button is clicked.
	 * @param style the class of the tab button in the CSS stylesheet.
	 * @return the tab button.
	 */
	public Button makeTabButton(String path, EventHandler<ActionEvent> action, String style) {
		ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(path)));
		imageView.setFitHeight(20);
		imageView.setFitWidth(20);
		Button button = new Button("", imageView);
		button.setOnAction(action);
		button.getStyleClass().add(style);
		return button;
	}

}
