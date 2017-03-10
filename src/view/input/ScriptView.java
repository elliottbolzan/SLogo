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

import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import utils.Direction;
import view.Workspace;
import view.components.Factory;

/**
 * @author Elliott Bolzan
 *
 */
public class ScriptView extends InputView {

	private Workspace workspace;
	private String defaultPath;
	private Factory factory;

	/**
	 * 
	 */
	public ScriptView(Workspace workspace, SplitPane pane, int index) {
		super(pane, index, Direction.BACK, true);
		this.workspace = workspace;
		setup();
	}

	private void setup() {
		factory = new Factory(workspace.getController().getResources());
		setTitle(workspace.getController().getResources().getString("ScriptTitle"));
		setCenter(getTextArea());
		setBottom(createButtonBar());
		setMinHeight(0);
		defaultPath = System.getProperty("user.dir")
				+ workspace.getController().getResources().getString("ExamplesPath");
	}

	private Node createButtonBar() {
		return new HBox(factory.makeButton("LoadTitle", e -> load(), true), factory.makeButton("SaveTitle", e -> save(), true),
				factory.makeButton("ClearTitle", e -> clear(), true), factory.makeButton("RunTitle", e -> run(), true));
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
		FileChooser chooser = factory.makeFileChooser(defaultPath, "Logo Files", "*.logo", "*.txt");
		File dataFile = chooser.showOpenDialog(getScene().getWindow());
		if (dataFile != null) {
			clear();
			readFileIn(dataFile.toPath().toString());
		}
	}

	public void readFileIn(String path) {
		if (!(path.equals(""))) {
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
				String line = "";
				while ((line = bufferedReader.readLine()) != null) {
					getTextArea().appendText(line + "\n");
				}
				bufferedReader.close();
			} catch (Exception e) {
				workspace.showMessage(workspace.getController().getResources().getString("FileNotRead"));
			}
		}
	}

	private void save() {
		DirectoryChooser chooser = factory.makeDirectoryChooser(defaultPath, "OutputFolderTitle");
		File selectedDirectory = chooser.showDialog(getScene().getWindow());
		TextInputDialog dialog = factory.makeTextInputDialog("SaveFileTitleString", "SaveFileHeaderString",
				"SaveFileContentString", "SaveFilePlaceholderString");
		handleDialogResult(selectedDirectory, dialog.showAndWait());
	}
	
	private void handleDialogResult(File selectedDirectory, Optional<String> result) {
		if (result.isPresent()) {
			try {
				File file = new File(selectedDirectory + "/" + result.get());
				BufferedWriter out = new BufferedWriter(new FileWriter(file));
				out.write(getCurrentCommand());
				out.close();
			} catch (IOException e) {
				workspace.showMessage(workspace.getController().getResources().getString("FileSaveFail"));
			}
		}
	}

	private void run() {
		workspace.getController().parse(getCurrentCommand(), false);
	}

}
