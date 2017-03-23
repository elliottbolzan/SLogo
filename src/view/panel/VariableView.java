package view.panel;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import model.Variable;
import view.Workspace;
import view.components.Factory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Elliott Bolzan
 *
 *         A class that serves to display the existing variables; edit them;
 *         load them from file; and save them to file.
 */
public class VariableView extends BorderPane {

	private Workspace workspace;
	private ObservableList<Variable> data;
	private Factory factory;
	private String defaultPath;

	/**
	 * Returns a VariableView.
	 * @param workspace the Workspace that owns this VariableView.
	 * @param data the variables that this view is loaded with.
	 */
	protected VariableView(Workspace workspace, ObservableList<Variable> data) {
		this.workspace = workspace;
		this.data = data;
		factory = new Factory(workspace.getController().getResources());
		setup();
		defaultPath = System.getProperty("user.dir")
				+ workspace.getController().getResources().getString("ExamplesPath");
	}

	/**
	 * Create the VariableView's GUI components.
	 */
	private void setup() {
		TableView<Variable> table = makeTable();
		TableColumn<Variable, String> nameColumn = makeNameColumn();
		TableColumn<Variable, String> valueColumn = makeValueColumn();
		table.setItems(data);
		table.getColumns().add(nameColumn);
		table.getColumns().add(valueColumn);
		setCenter(table);
		setBottom(createButtonBar());
	}

	/**
	 * @return a button bar.
	 */
	private Node createButtonBar() {
		return new HBox(factory.makeButton("LoadTitle", e -> load(), true),
				factory.makeButton("SaveTitle", e -> save(), true));
	}

	/**
	 * @return a TableView.
	 */
	private TableView<Variable> makeTable() {
		TableView<Variable> table = new TableView<Variable>();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setEditable(true);
		table.getStyleClass().add("panel-table");
		table.setPlaceholder(new Label(workspace.getController().getResources().getString("EmptyVariables")));
		table.prefHeightProperty().bind(heightProperty());
		return table;
	}

	/**
	 * @return a TableColumn with the names of variables.
	 */
	private TableColumn<Variable, String> makeNameColumn() {
		return makeColumn("VariableTableNameString", "name", false);
	}

	/**
	 * @return a TableColumn with the values of variables.
	 */
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

	/**
	 * Load a variable file.
	 */
	private void load() {
		FileChooser chooser = factory.makeFileChooser(defaultPath, "Logo Files", "*.logo", "*.txt");
		File dataFile = chooser.showOpenDialog(getScene().getWindow());
		if (dataFile != null) {
			readFileIn(dataFile.toPath().toString());
		}
	}

	/**
	 * Read a variable file in.
	 * @param path the path of the file to be read in.
	 */
	private void readFileIn(String path) {
		if (!(path.equals(""))) {
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
				String line = "";
				while ((line = bufferedReader.readLine()) != null) {
					String[] splitLine = line.split(" ");
					if (!isVariable(splitLine[0]))
						workspace.getController().getVariables()
								.add(new Variable(splitLine[0], Double.parseDouble(splitLine[1])));
				}
				bufferedReader.close();
			} catch (Exception e) {
				workspace.showMessage(workspace.getController().getResources().getString("FileNotRead"));
			}
		}
	}

	private boolean isVariable(String s) {
		boolean isCommand = false;
		ObservableList<Variable> list = workspace.getController().getVariables();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			isCommand = list.get(i).getName().equals(s);
			if (isCommand == true)
				break;
		}
		return isCommand;
	}

	/**
	 * Save variables to file.
	 */
	private void save() {
		DirectoryChooser chooser = factory.makeDirectoryChooser(defaultPath, "OutputFolderTitle");
		File selectedDirectory = chooser.showDialog(getScene().getWindow());
		TextInputDialog dialog = factory.makeTextInputDialog("SaveFileTitleString", "SaveFileHeaderString",
				"SaveFileContentString", "SaveFilePlaceholderString");
		handleDialogResult(selectedDirectory, dialog.showAndWait());
	}

	/**
	 * Handle the user's response to the saving dialog.
	 * @param selectedDirectory the directory the user selected.
	 * @param result the result of the user's interaction.
	 */
	private void handleDialogResult(File selectedDirectory, Optional<String> result) {
		if (result.isPresent()) {
			try {
				File file = new File(selectedDirectory + "/" + result.get());
				BufferedWriter out = new BufferedWriter(new FileWriter(file));
				data.stream().forEach(e -> {
					try {
						out.write(e.getName().toString() + " " + e.getValue().toString() + "\n");
					} catch (IOException e1) {
						workspace.showMessage(workspace.getController().getResources().getString("CanWrite"));
					}
				});
				out.close();
			} catch (IOException e) {
				workspace.showMessage(workspace.getController().getResources().getString("FileSaveFail"));
			}
		}
	}

}
