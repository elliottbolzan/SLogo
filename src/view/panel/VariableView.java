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
		TableView<Variable> table = makeTable();
		TableColumn<Variable, String> nameColumn = makeNameColumn();
		TableColumn<Variable, String> valueColumn = makeValueColumn();
		table.setItems(data);
		table.getColumns().add(nameColumn);
		table.getColumns().add(valueColumn);
		setCenter(table);
	}

	private TableView<Variable> makeTable() {
		TableView<Variable> table = new TableView<Variable>();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setEditable(true);
		table.getStyleClass().add("panel-table");
		table.setPlaceholder(new Label(workspace.getController().getResources().getString("EmptyVariables")));
		table.prefHeightProperty().bind(heightProperty());
		return table;
	}

	private TableColumn<Variable, String> makeNameColumn() {
		return makeColumn("VariableTableNameString", "name", false);
	}

	private TableColumn<Variable, String> makeValueColumn() {
		TableColumn<Variable, String> valueColumn = makeColumn("VariableTableValueString", "value", true);
		valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		valueColumn.setOnEditCommit(new EventHandler<CellEditEvent<Variable, String>>() {
			@Override
			public void handle(CellEditEvent<Variable, String> event) {
				try {
					((Variable) event.getTableView().getItems().get(event.getTablePosition().getRow()))
							.setValue(event.getNewValue());
				} catch (Exception e) {
					((Variable) event.getTableView().getItems().get(event.getTablePosition().getRow())).setValue("0");
					event.getTableView().refresh();
					workspace.showMessage(e.getMessage());
				}
			}
		});
		return valueColumn;
	}

	private TableColumn<Variable, String> makeColumn(String titleProperty, String valueProperty, boolean editable) {
		TableColumn<Variable, String> nameColumn = new TableColumn<Variable, String>(
				workspace.getController().getResources().getString(titleProperty));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Variable, String>(valueProperty));
		nameColumn.setEditable(editable);
		return nameColumn;
	}

}
