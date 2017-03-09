/**
 * 
 */
package view.input;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import view.Workspace;

/**
 * @author Elliott Bolzan
 *
 */
public class ScriptView extends InputView {

	private Workspace workspace;

	/**
	 * 
	 */
	public ScriptView(Workspace workspace, SplitPane pane, int index) {
		super(pane, index, true, true);
		this.workspace = workspace;
		setTitle(workspace.getController().getResources().getString("ScriptTitle"));
		setup();
	}

	private void setup() {
		setBottom(createButtonBar());
		setCenter(getTextArea());
		setMinHeight(0);
	}

	private Node createButtonBar() {
		HBox box = new HBox();
		Button loadButton = makeButton(workspace.getController().getResources().getString("LoadTitle"), e -> load());
		Button saveButton = makeButton(workspace.getController().getResources().getString("SaveTitle"), e -> save());
		Button clearButton = makeButton(workspace.getController().getResources().getString("ClearTitle"), e -> clear());
		Button runButton = makeButton(workspace.getController().getResources().getString("RunTitle"), e -> run());
		HBox.setHgrow(loadButton, Priority.ALWAYS);
		HBox.setHgrow(saveButton, Priority.ALWAYS);
		HBox.setHgrow(clearButton, Priority.ALWAYS);
		HBox.setHgrow(runButton, Priority.ALWAYS);
		loadButton.setMaxWidth(Double.MAX_VALUE);
		saveButton.setMaxWidth(Double.MAX_VALUE);
		clearButton.setMaxWidth(Double.MAX_VALUE);
		runButton.setMaxWidth(Double.MAX_VALUE);
		box.getChildren().addAll(loadButton, saveButton, clearButton, runButton);
		return box;
	}

	private Button makeButton(String title, EventHandler<ActionEvent> handler) {
		Button button = new Button(title);
		button.setOnAction(handler);
		return button;
	}

	@Override
	public void clear() {
		getTextArea().setText("");
	}

	@Override
	protected String getCurrentCommand() {
		return getTextArea().getText();
	}

	private void load() {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/data/examples"));
		chooser.getExtensionFilters().setAll(new ExtensionFilter("Logo Files", "*.logo", "*.txt"));
		File dataFile = chooser.showOpenDialog(getScene().getWindow());
		if (dataFile != null) {
			clear();
			readFileIn(dataFile.toPath().toString());
		}
	}
	
	public void readFileIn(String path) {
		try {
			FileReader fileReader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				getTextArea().appendText(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			// Don't use file
		}
	}

	private void save() {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Output Folder");
		chooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/data/examples"));
		File selectedDirectory = chooser.showDialog(getScene().getWindow());
		TextInputDialog dialog = new TextInputDialog("script.logo");
		dialog.setTitle("File Name");
		dialog.setHeaderText("Enter your file name below.");
		dialog.setContentText("Please enter your output file's name:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			try {
				File file = new File(selectedDirectory + "/" + result.get());
				BufferedWriter out = new BufferedWriter(new FileWriter(file));
				out.write(getCurrentCommand());
				out.close();
			} catch (IOException e) {

			}
		}
	}

	private void run() {
		workspace.getController().parse(getCurrentCommand(), false);
	}

}
