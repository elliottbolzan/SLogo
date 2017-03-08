/**
 * 
 */
package view.input;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import view.Workspace;
import view.visualization.View;

/**
 * @author Elliott Bolzan
 *
 */
public class InputContainer extends View {
	
	private Workspace workspace;
	private ShellView shellView;
	private ScriptView scriptView;

	/**
	 * @param pane
	 * @param index
	 * @param collapseOnRight
	 */
	public InputContainer(Workspace workspace, int index) {
		super(workspace.getPane(), index, false, false);
		this.workspace = workspace;
		setTitle(workspace.getController().getResources().getString("InputTitle"));
		setup();
	}
	
	private void setup() {
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
