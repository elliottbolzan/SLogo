package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author Elliott Bolzan
 *
 */
public class VariableTable extends Group {

	private View view;

	/**
	 * 
	 */
	public VariableTable(View view) {
		this.view = view;
		setup();
	}

	private void setup() {

		TableView<String> table = new TableView<String>();
		table.setPrefHeight(100);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getStyleClass().add("panel-table");

		ObservableList<String> items = FXCollections.observableArrayList("Single", "Double", "Suite", "Family App");

		TableColumn nameColumn = new TableColumn("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("firstName"));
		nameColumn.setEditable(false);

		TableColumn valueColumn = new TableColumn("Value");
		valueColumn.setCellValueFactory(new PropertyValueFactory<String, String>("lastName"));
		valueColumn.setEditable(false);

		table.setItems(items);
        table.getColumns().addAll(nameColumn, valueColumn);
		
		getChildren().add(table);
	}

}
