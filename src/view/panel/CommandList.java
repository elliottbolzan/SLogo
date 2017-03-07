package view.panel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import view.Workspace;

/**
 * @author Elliott Bolzan
 *
 */
public class CommandList extends VBox {

	private Workspace workspace;
	private ObservableList<String> data;

	/**
	 * 
	 */
	public CommandList(Workspace workspace, ObservableList<String> data) {
		this.workspace = workspace;
		this.data = data;
		setup();
	}

	private void setup() {
		ListProperty<String> listProperty = new SimpleListProperty<>();
		ListView<String> list = new ListView<String>();
		list.itemsProperty().bind(listProperty);
		list.getStyleClass().add("panel-list");
		list.setPlaceholder(new Label(workspace.getController().getResources().getString("EmptyCommands")));
		list.setEditable(false);
		list.setPrefHeight(100);
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String selected = list.getSelectionModel().getSelectedItem();
				if (selected != null) {
					workspace.getConsole().clearCurrentCommand();
					workspace.getConsole().append(selected);
					workspace.getConsole().focus();
				}
			}
		});
		listProperty.set(data);
		getChildren().add(list);
	}

}
