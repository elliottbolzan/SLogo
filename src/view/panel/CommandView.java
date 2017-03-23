package view.panel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import view.Workspace;

/**
 * @author Elliott Bolzan
 *
 * A class that serves to display commands to the user.
 * These commands should be clickable: upon being clicked, they should be placed into the shell.
 */
public class CommandView extends BorderPane {

	private Workspace workspace;
	private ObservableList<String> data;

	/**
	 * Returns a CommandView object.
	 * @param workspace the Workspace that owns the CommandView.
	 * @param data the commands that the CommandView is loaded with.
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
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String selected = list.getSelectionModel().getSelectedItem();
				if (selected != null) {
					workspace.getShell().clearCurrentCommand();
					workspace.getShell().append(selected);
					workspace.getShell().getTextArea().requestFocus();
				}
			}
		});
		listProperty.set(data);
		setCenter(list);
	}

}
