/**
 * 
 */
package view.input;

import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import view.View;

/**
 * @author Elliott Bolzan
 *
 */
public abstract class InputView extends View {
	
	private TextArea textArea;
	
	public InputView(SplitPane owner, int dividerIndex, boolean collapseRight, boolean showToolbar) {
		super(owner, dividerIndex, collapseRight, showToolbar);
		textArea = new TextArea();
		textArea.setWrapText(true);
	}
	
	public TextArea getTextArea() {
		return textArea;
	}
		
	protected abstract String getCurrentCommand();
	
	public abstract void clear();

}
