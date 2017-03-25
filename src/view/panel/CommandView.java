// This entire file is part of my masterpiece.
// Elliott Bolzan

package view.panel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import view.Workspace;

/**
 * @author Elliott Bolzan
 *
 *         A class that serves to display commands to the user. These commands
 *         should be clickable: upon being clicked, they should be placed into
 *         the shell.
 * 
 *         This code's purpose: displaying commands and allowing the user to
 *         click on them and place them into his or her shell.
 * 
 *         Why I think this code is well designed: this code is reusable (both
 *         the History view and the User-Defined Commands views use this class
 *         to display commands); the code is open for extension but closed for
 *         modification (if a programmer wants to modify the class's behavior
 *         when the command is clicked, he or she only needs to modify the
 *         mouseClicked method); the look and feel of the ListView created by
 *         the class can be modified using a CSS style sheet, which is a prime
 *         example of the open / closed principle.
 */
public class CommandView extends BorderPane {

	private Workspace workspace;
	private ObservableList<String> data;

	/**
	 * Returns a CommandView object.
	 * 
	 * @param workspace
	 *            the Workspace that owns the CommandView.
	 * @param data
	 *            the commands that the CommandView is loaded with.
	 */
	protected CommandView(Workspace workspace, ObservableList<String> data) {
		this.workspace = workspace;
		this.data = data;
		setup();
	}

	/**
	 * Setup the graphic components for the CommandView.
	 */
	private void setup() {
		ListProperty<String> listProperty = new SimpleListProperty<>();
		ListView<String> list = new ListView<String>();
		list.itemsProperty().bind(listProperty);
		list.getStyleClass().add("panel-list");
		list.setPlaceholder(new Label(workspace.getController().getResources().getString("EmptyCommands")));
		list.setEditable(false);
		list.prefHeightProperty().bind(heightProperty());
		list.setOnMouseClicked(e -> mouseClicked(list));
		listProperty.set(data);
		setCenter(list);
	}

	/**
	 * Handle a click upon a command in the ListView. Paste the clicked command
	 * into the Workspace's ShellView.
	 * 
	 * @param list
	 *            the ListView that has been clicked.
	 */
	private void mouseClicked(ListView<String> list) {
		String selected = list.getSelectionModel().getSelectedItem();
		if (selected != null) {
			workspace.getShell().clearCurrentCommand();
			workspace.getShell().append(selected);
			workspace.getShell().getTextArea().requestFocus();
		}
	}

}
