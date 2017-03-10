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
 */
public abstract class InputView extends CollapsibleView {
	
	private TextArea textArea;
	
	public InputView(SplitPane owner, int dividerIndex, Direction collapseDirection, boolean showToolbar) {
		super(owner, dividerIndex, collapseDirection, showToolbar);
		textArea = new TextArea();
		textArea.setWrapText(true);
	}
	
	public TextArea getTextArea() {
		return textArea;
	}
		
	protected abstract String getCurrentCommand();
	
	public abstract void clear();

}
