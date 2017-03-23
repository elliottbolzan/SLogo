package view.input;

import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import utils.Direction;
import view.Workspace;

/**
 * @author Elliott Bolzan
 *
 *         This class represents a shell. Each line is preceded by a "preamble."
 *         When the user presses enter, his or her command is sent to the
 *         back-end for parsing.
 */
public class ShellView extends InputView {

	private Workspace workspace;
	private String preamble;
	private int commandIndex = 0;

	/**
	 * Returns a ShellView.
	 * 
	 * @param workspace
	 *            the Workspace that owns this Shell.
	 * @param pane
	 *            the SplitPane that this Shell is placed in.
	 * @param index
	 *            the index of the divider that this view collapses to.
	 */
	public ShellView(Workspace workspace, SplitPane pane, int index) {
		super(pane, index, Direction.FORWARD, true);
		this.workspace = workspace;
		setup();
	}

	/**
	 * Create the GUI components for the ShellView.
	 */
	private void setup() {
		setTitle(workspace.getController().getResources().getString("ShellTitle"));
		setMinHeight(0);
		preamble = workspace.getController().getResources().getString("Preamble");
		createTextArea();
	}

	/**
	 * Print a message to the shell.
	 * 
	 * @param message
	 *            the message to be printed.
	 */
	public void print(String message) {
		append("\n" + message);
	}

	/**
	 * Append a message to the shell.
	 * 
	 * @param string
	 *            the message to be appended to the shell.
	 */
	public void append(String string) {
		getTextArea().appendText(string);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.input.InputView#clear()
	 */
	@Override
	public void clear() {
		getTextArea().setText(preamble);
	}

	/**
	 * Clear the user's current command.
	 */
	public void clearCurrentCommand() {
		getTextArea().setText(
				getTextArea().getText().substring(0, getTextArea().getText().lastIndexOf(getCurrentCommand())));
		getTextArea().positionCaret(getTextArea().getText().length());
	}

	/**
	 * Create the TextArea for the ShellView.
	 */
	private void createTextArea() {
		getTextArea().setOnKeyPressed(e -> handleKeyCode(e));
		getTextArea().setOnMouseClicked(e -> getTextArea().positionCaret(getTextArea().getText().length()));
		append(preamble);
		setCenter(getTextArea());
	}

	/**
	 * Handle the user's pressing of certain keys.
	 * 
	 * @param event
	 *            the KeyCode in question.
	 */
	private void handleKeyCode(KeyEvent event) {
		if (event.getCode() == KeyCode.UP) {
			upPressed();
			event.consume();
		} else if (event.getCode() == KeyCode.DOWN) {
			downPressed();
			event.consume();
		} else if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.BACK_SPACE) {
			int position = getTextArea().getText().lastIndexOf(preamble) + preamble.length();
			if (getTextArea().getSelectedText().length() != 0 || position == getTextArea().getCaretPosition()) {
				event.consume();
			}
		} else if (event.getCode() == KeyCode.ENTER) {
			event.consume();
			if (getTextArea().getSelectedText().length() == 0) {
				enterPressed();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.input.InputView#getCurrentCommand()
	 */
	@Override
	protected String getCurrentCommand() {
		int index = getTextArea().getText().lastIndexOf(preamble);
		return getTextArea().getText(index + preamble.length(), getTextArea().getText().length());
	}

	private String removeExtraWhitespace(String string) {
		return string.trim().replaceAll(" +", " ");
	}

	/**
	 * Parse the user's expression when he or she presses enter.
	 */
	private void enterPressed() {
		String input = removeExtraWhitespace(getCurrentCommand());
		commandIndex = 0;
		if (!(input.equals(""))) {
			workspace.getController().parse(input, true);
		}
		append("\n" + preamble);
	}

	/**
	 * Handle the user's pressing of the up button. Display previously run
	 * commands.
	 */
	private void upPressed() {
		if (commandIndex <= workspace.getController().getHistory().size() - 1) {
			appendCommand();
		}
		if (commandIndex < workspace.getController().getHistory().size() - 1) {
			commandIndex++;
		}
	}

	/**
	 * Handle the user's pressing of the down button. Display commands that have
	 * been run more recently in history – if possible.
	 */
	private void downPressed() {
		if (commandIndex == 0) {
			clearCurrentCommand();
			return;
		}
		commandIndex--;
		appendCommand();
	}

	/**
	 * Append a command to the TextArea.
	 */
	private void appendCommand() {
		clearCurrentCommand();
		append(workspace.getController().getHistory().get(commandIndex));
	}

}
