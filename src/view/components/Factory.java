/**
 * 
 */
package view.components;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Elliott Bolzan
 *
 */
public class Factory {
	
	private ResourceBundle resources;
	
	public Factory(ResourceBundle resources) {
		this.resources = resources;
	}

	public FileChooser makeFileChooser(String path, String extensionName, String ...types) {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(path));
		chooser.getExtensionFilters().setAll(new ExtensionFilter(extensionName, types));
		return chooser;
	}
	
	public DirectoryChooser makeDirectoryChooser(String path, String titleProperty) {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle(resources.getString(titleProperty));
		chooser.setInitialDirectory(new File(path));
		return chooser;
	}
	
	public TextInputDialog makeTextInputDialog(String titleProperty, String headerProperty, String contentProperty, String placeholderProperty) {
		TextInputDialog dialog = new TextInputDialog(resources.getString(placeholderProperty));
		dialog.setTitle(resources.getString(titleProperty));
		dialog.setHeaderText(resources.getString(headerProperty));
		dialog.setContentText(resources.getString(contentProperty));
		return dialog;
	}
	
	public Alert makeAlert(AlertType type, String titleProperty, String headerProperty, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(resources.getString(titleProperty));
		alert.setHeaderText(resources.getString(headerProperty));
		alert.setContentText(content);
		return alert;
	}
	
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

}
