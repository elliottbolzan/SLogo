package view;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * @author Elliott Bolzan
 *
 */
public class CommandList extends Group {

	private View view;
	private ObservableList<String> data;

	/**
	 * 
	 */
	public CommandList(View view, ObservableList<String> data) {
		this.view = view;
		this.data = data;
		setup();
	}

	private void setup() {
		ListProperty<String> listProperty = new SimpleListProperty<>();
		ListView<String> list = new ListView<String>();
		list.itemsProperty().bind(listProperty);
		list.getStyleClass().add("panel-list");
		list.setMinWidth(240);
		list.setPlaceholder(new Label("No commands"));
		list.setEditable(false);
		list.setPrefHeight(100);
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String selected = list.getSelectionModel().getSelectedItem();
				if (selected != null) {
					view.getConsole().clearCurrentCommand();
					view.getConsole().append(selected);
					view.getConsole().focus();
				}
			}
		});
		listProperty.set(data);
		getChildren().add(list);
	}

}
