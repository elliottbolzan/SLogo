package view.input;

import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.View;
import view.Workspace;

/**
 * @author Elliott Bolzan
 *
 */
public class ShellView extends InputView {
		
	private Workspace workspace;
	private String preamble;
	private int commandIndex = 0;
		
	/**
	 * 
	 */
	public ShellView(Workspace workspace, SplitPane pane, int index) {
		super(pane, index, false, true);
		this.workspace = workspace;
		setTitle(workspace.getController().getResources().getString("ShellTitle"));
		preamble = workspace.getController().getResources().getString("Preamble");
		setMinHeight(0);
		createTextArea();
	}

	public void print(String message) {
		append("\n" + message);
	}
	
	public void append(String string) {
		getTextArea().appendText(string);
	}
	
	public void clear() {
		getTextArea().setText(preamble);
	}
	
	public void clearCurrentCommand() {
		getTextArea().setText(getTextArea().getText().substring(0, getTextArea().getText().lastIndexOf(getCurrentCommand())));
		getTextArea().positionCaret(getTextArea().getText().length());
	}
	
	public void focus() {
		getTextArea().requestFocus();
	}
	
	private void createTextArea() {
		getTextArea().setOnKeyPressed(e -> handleKeyCode(e));
		getTextArea().setOnMouseClicked(e -> getTextArea().positionCaret(getTextArea().getText().length()));
		append(preamble);
		setCenter(getTextArea());
	}

	private void handleKeyCode(KeyEvent event) {
		if (event.getCode() == KeyCode.UP) {
			upPressed();
			event.consume();
		}
		else if (event.getCode() == KeyCode.DOWN) {
			downPressed();
			event.consume();
		}
		else if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.BACK_SPACE) {
			int position = getTextArea().getText().lastIndexOf(preamble) + preamble.length();
			if (getTextArea().getSelectedText().length() != 0 || position == getTextArea().getCaretPosition()) { 
				event.consume();
			}
		}
		else if (event.getCode() == KeyCode.ENTER) {
			event.consume();
			if (getTextArea().getSelectedText().length() == 0) {
				enterPressed();
			}
		}
	}
	
	protected String getCurrentCommand() {
		int index = getTextArea().getText().lastIndexOf(preamble);
		return getTextArea().getText(index + preamble.length(), getTextArea().getText().length());
	}
	
	private String removeWhitespace(String string) {
		return string.trim().replaceAll(" +", " ");
	}
	
	private void enterPressed() {
		String input = removeWhitespace(getCurrentCommand());
		commandIndex = 0;
			if (!(input.equals(""))) {
				workspace.getController().parse(input);
			}
			append("\n" + preamble);
	}
	
	private void upPressed() {
		if (commandIndex <= workspace.getController().getHistory().size() - 1) {
			appendCommand();
		}
		if (commandIndex < workspace.getController().getHistory().size() - 1) {
			commandIndex++;
		}
	}
	
	private void downPressed() {
		if (commandIndex == 0) {
			clearCurrentCommand();
			return;
		}
		commandIndex--;
		appendCommand();
	}
	
	private void appendCommand() {
		clearCurrentCommand();
		append(workspace.getController().getHistory().get(commandIndex));
	}
	
}
