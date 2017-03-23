/**
 * 
 */
package view.input;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import utils.Direction;
import view.CollapsibleView;
import view.Workspace;

/**
 * @author Elliott Bolzan
 *
 *         This class serves to contain the different kind of text input views.
 *         As of now, it contains a ShellView and a ScriptView. It could be
 *         extended to contain additional views, however. Currently, those views
 *         are arranged vertically.
 */
public class InputContainer extends CollapsibleView {

	private Workspace workspace;
	private ShellView shellView;
	private ScriptView scriptView;

	/**
	 * Returns an InputContainer.
	 * 
	 * @param pane
	 *            the SplitPane the view belongs to.
	 * @param index
	 *            the index of the divider in the SplitPane that the view
	 *            collapses to.
	 */
	public InputContainer(Workspace workspace, int index) {
		super(workspace.getPane(), index, Direction.BACK, false);
		this.workspace = workspace;
		setup();
	}

	/**
	 * Creates the GUI components for the InputContainer.
	 */
	private void setup() {
		setTitle(workspace.getController().getResources().getString("InputTitle"));
		SplitPane pane = new SplitPane();
		pane.setOrientation(Orientation.VERTICAL);
		shellView = new ShellView(workspace, pane, 0);
		scriptView = new ScriptView(workspace, pane, 0);
		pane.getItems().addAll(shellView, scriptView);
		setCenter(pane);
	}

	public ShellView getShellView() {
		return shellView;
	}

	public ScriptView getScriptView() {
		return scriptView;
	}

}
