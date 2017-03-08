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
 */
public class CommandView extends BorderPane {

	private Workspace workspace;
	private ObservableList<String> data;

	/**
	 * 
	 */
	public CommandView(Workspace workspace, ObservableList<String> data) {
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
