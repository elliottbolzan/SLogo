package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * @author Elliott Bolzan
 *
 */
public class CommandList extends Group {

	private View view;

	/**
	 * 
	 */
	public CommandList(View view) {
		this.view = view;
		setup();
	}

	private void setup() {
		ListView<String> list = new ListView<String>();
		list.getStyleClass().add("panel-list");
		list.setEditable(false);
		list.setPrefHeight(100);
		ObservableList<String> items = FXCollections.observableArrayList("Single", "Double", "Suite", "Family App");
		list.setItems(items);
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				view.getConsole().clearCurrentCommand();
				view.getConsole().append(list.getSelectionModel().getSelectedItem());
				view.getConsole().focus();
			}
		});
		getChildren().add(list);
	}

}
