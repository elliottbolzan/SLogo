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
 */
public class InputContainer extends CollapsibleView {
	
	private Workspace workspace;
	private ShellView shellView;
	private ScriptView scriptView;

	/**
	 * @param pane
	 * @param index
	 * @param collapseOnRight
	 */
	public InputContainer(Workspace workspace, int index) {
		super(workspace.getPane(), index, Direction.BACK, false);
		this.workspace = workspace;
		setup();
	}
	
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
