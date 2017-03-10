package view.panel;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import model.Variable;
import view.Workspace;

/**
 * @author Elliott Bolzan
 *
 */
public class VariableView extends BorderPane {

	private Workspace workspace;
	private ObservableList<Variable> data;

	/**
	 * 
	 */
	protected VariableView(Workspace workspace, ObservableList<Variable> data) {
		this.workspace = workspace;
		this.data = data;
		setup();
	}

	private void setup() {

		TableView<Variable> table = new TableView<Variable>();
		table.setPrefHeight(100);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setEditable(true);
		table.getStyleClass().add("panel-table");
		table.setPlaceholder(new Label(workspace.getController().getResources().getString("EmptyVariables")));
		table.prefHeightProperty().bind(heightProperty());

		TableColumn<Variable, String> nameColumn = new TableColumn<Variable, String>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Variable, String>("name"));
		nameColumn.setEditable(false);

		TableColumn<Variable, String> valueColumn = new TableColumn<Variable, String>("Value");
		valueColumn.setCellValueFactory(new PropertyValueFactory<Variable, String>("value"));
		valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		valueColumn.setOnEditCommit(new EventHandler<CellEditEvent<Variable, String>>() {
			@Override
			public void handle(CellEditEvent<Variable, String> event) {
				try {
					((Variable) event.getTableView().getItems().get(event.getTablePosition().getRow())).setValue(event.getNewValue());
				}
				catch (Exception e) {
					((Variable) event.getTableView().getItems().get(event.getTablePosition().getRow())).setValue("0");
					event.getTableView().refresh();
					workspace.showMessage(e.getMessage());
				}
			}
		});
		valueColumn.setEditable(true);

		table.setItems(data);
		table.getColumns().add(nameColumn);
		table.getColumns().add(valueColumn);
		
		setCenter(table);
	}

}
