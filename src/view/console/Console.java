package view.console;

import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.View;

/**
 * @author Elliott Bolzan
 *
 */
public class Console extends Group {
		
	private View view;

	private TextArea textArea;
	private String preamble;
	
	private int width = 400;
	private int height = 400;
	private int commandIndex = 0;
		
	/**
	 * 
	 */
	public Console(View view) {
		this.view = view;
		preamble = view.getController().getResources().getString("Preamble");
		createTextArea();
	}

	public void print(String message) {
		append("\n" + message);
	}
	
	public void append(String string) {
		textArea.appendText(string);
	}
	
	public void clear() {
		textArea.setText(preamble);
	}
	
	public void clearCurrentCommand() {
		textArea.setText(textArea.getText().substring(0, textArea.getText().lastIndexOf(getCurrentCommand())));
		textArea.positionCaret(textArea.getText().length());
	}
	
	public void focus() {
		textArea.requestFocus();
	}

	private void createTextArea() {
		textArea = new TextArea();
		textArea.setPrefWidth(width);
		textArea.setPrefHeight(height);
		textArea.setWrapText(true);
		textArea.setOnMouseClicked(e -> textArea.positionCaret(textArea.getText().length()));
		textArea.setOnKeyPressed(e -> handleKeyCode(e));
		getChildren().add(textArea);
		append(preamble);
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
			int position = textArea.getText().lastIndexOf(preamble) + preamble.length();
			if (textArea.getSelectedText().length() != 0 || position == textArea.getCaretPosition()) { 
				event.consume();
			}
		}
		else if (event.getCode() == KeyCode.ENTER) {
			event.consume();
			if (textArea.getSelectedText().length() == 0) {
				enterPressed();
			}
		}
	}
	
	private String getCurrentCommand() {
		int index = textArea.getText().lastIndexOf(preamble);
		return textArea.getText(index + preamble.length(), textArea.getText().length());
	}
	
	private String removeWhitespace(String string) {
		return string.trim().replaceAll(" +", " ");
	}
	
	private void enterPressed() {
		String input = removeWhitespace(getCurrentCommand());
		commandIndex = 0;
		try {
			if (!(input.equals(""))) {
				view.getController().parse(input);
			}
			append("\n" + preamble);
		}
		catch (Exception e) {
			e.printStackTrace();
			view.showMessage(e.getMessage());
		}
	}
	
	private void upPressed() {
		if (commandIndex <= view.getController().getHistory().size() - 1) {
			appendCommand();
		}
		if (commandIndex < view.getController().getHistory().size() - 1) {
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
		append(view.getController().getHistory().get(commandIndex));
	}
	
}
