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
 *         A class designed to edit, load, save, clear, and run scripts. Can
 *         load .logo or .txt Logo scripts from file.
 */
public class ScriptView extends InputView {

	private Workspace workspace;
	private String defaultPath;
	private Factory factory;

	/**
	 * Returns a ScriptView.
	 * 
	 * @param workspace
	 *            the Workspace that owns the ScriptView.
	 * @param pane
	 *            the SplitPane that the view resides in.
	 * @param index
	 *            the index of the SplitPane that the view will be collapsed to.
	 */
	public ScriptView(Workspace workspace, SplitPane pane, int index) {
		super(pane, index, Direction.BACK, true);
		this.workspace = workspace;
		setup();
	}

	/**
	 * Create the GUI components for the ScriptView.
	 */
	private void setup() {
		factory = new Factory(workspace.getController().getResources());
		setTitle(workspace.getController().getResources().getString("ScriptTitle"));
		setCenter(getTextArea());
		setBottom(createButtonBar());
		setMinHeight(0);
		defaultPath = System.getProperty("user.dir")
				+ workspace.getController().getResources().getString("ExamplesPath");
	}

	/**
	 * @return a button bar.
	 */
	private Node createButtonBar() {
		return new HBox(factory.makeButton("LoadTitle", e -> load(), true),
				factory.makeButton("SaveTitle", e -> save(), true),
				factory.makeButton("ClearTitle", e -> clear(), true), factory.makeButton("RunTitle", e -> run(), true));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.input.InputView#clear()
	 */
	@Override
	public void clear() {
		getTextArea().setText("");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.input.InputView#getCurrentCommand()
	 */
	@Override
	protected String getCurrentCommand() {
		return getTextArea().getText();
	}

	/**
	 * Load a script from disk.
	 */
	private void load() {
		FileChooser chooser = factory.makeFileChooser(defaultPath, "Logo Files", "*.logo", "*.txt");
		File dataFile = chooser.showOpenDialog(getScene().getWindow());
		if (dataFile != null) {
			clear();
			readFileIn(dataFile.toPath().toString());
		}
	}

	/**
	 * Read a file in from disk.
	 * 
	 * @param path
	 *            the path of the file to be read in.
	 */
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

	/**
	 * Save a file to disk.
	 */
	private void save() {
		DirectoryChooser chooser = factory.makeDirectoryChooser(defaultPath, "OutputFolderTitle");
		File selectedDirectory = chooser.showDialog(getScene().getWindow());
		TextInputDialog dialog = factory.makeTextInputDialog("SaveFileTitleString", "SaveFileHeaderString",
				"SaveFileContentString", "SaveFilePlaceholderString");
		handleDialogResult(selectedDirectory, dialog.showAndWait());
	}

	/**
	 * Handle the user's interaction with the save dialog.
	 * 
	 * @param selectedDirectory
	 *            the directory the user selected.
	 * @param result
	 *            the result of the user's interaction.
	 */
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

	/**
	 * Run the commands that the user entered in the ScriptView.
	 */
	private void run() {
		workspace.getController().parse(getCurrentCommand(), false);
	}

}
