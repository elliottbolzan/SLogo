/**
 * 
 */
package view.input;

import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import utils.Direction;
import view.CollapsibleView;

/**
 * @author Elliott Bolzan
 *
 *         A class representing different kinds of ways of entering textual
 *         input: ScriptViews or ShellViews, as of now. This class could be
 *         extended to recognize different input methods.
 */
public abstract class InputView extends CollapsibleView {

	private TextArea textArea;

	/**
	 * Returns an InputView.
	 * 
	 * @param owner
	 *            the SplitPane that the InputView is located in.
	 * @param dividerIndex
	 *            the index of the SplitPane that this view collapses to.
	 * @param collapseDirection
	 *            the direction in which this view collapses.
	 * @param showToolbar
	 *            whether this view should have a toolbar or not.
	 */
	public InputView(SplitPane owner, int dividerIndex, Direction collapseDirection, boolean showToolbar) {
		super(owner, dividerIndex, collapseDirection, showToolbar);
		textArea = new TextArea();
		textArea.setWrapText(true);
	}

	public TextArea getTextArea() {
		return textArea;
	}

	/**
	 * Provides the user's current command to the caller.
	 * 
	 * @return a String representing the command the user currently has entered.
	 */
	protected abstract String getCurrentCommand();

	/**
	 * Clear the input field.
	 */
	public abstract void clear();

}
