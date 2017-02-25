/**
 * 
 */
package view;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Elliott Bolzan
 *
 */
public class Console extends Group {
		
	private View view;

	private TextArea textArea;
	private String preamble = "slogo$ ";
	
	private int width = 400;
	private int height = 400;
	private int commandIndex = 0;
		
	/**
	 * 
	 */
	public Console(View view) {
		this.view = view;
		createTextArea();
	}

	protected void print(String message) {
		textArea.appendText("\n" + message + "\n");
	}
	
	protected void append(String string) {
		textArea.appendText(string);
	}
	
	protected void clear() {
		textArea.setText(preamble);
	}
	
	protected void clearCurrentCommand() {
		textArea.setText(textArea.getText().substring(0, textArea.getText().lastIndexOf(getCurrentCommand())));
		textArea.positionCaret(textArea.getText().length());
	}
	
	protected void focus() {
		textArea.requestFocus();
	}

	private void createTextArea() {
		textArea = new TextArea();
		textArea.setPrefWidth(width);
		textArea.setPrefHeight(height);
		textArea.setWrapText(true);
		textArea.setOnMouseClicked(e -> textArea.positionCaret(textArea.getText().length()));
		textArea.setOnKeyPressed(e -> handleKeyCode(e));
		textArea.textProperty().addListener(e -> determineTextInput(textArea.getText()));
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
			if (textArea.getSelectedText().length() != 0) {
				event.consume();
			}
		}
	}
	
	private void handleTextInput(String input) {
		if (input.equals("\n")) {
			enterPressed();
		}
	}
	
	private void determineTextInput(String input) {
		if (input.length() > 0) {
			handleTextInput(input.substring(input.length() - 1));
		}
	}
	
	private String getCurrentCommand() {
		int index = textArea.getText().lastIndexOf(preamble);
		return textArea.getText(index + preamble.length(), textArea.getText().length());
	}
	
	private String removeNewline(String string) {
		return string.replace("\n", "");
	}
	
	private void enterPressed() {
		commandIndex = 0;
		append(preamble);
		try {
			String input = removeNewline(getCurrentCommand());
			view.getController().parse(input);
		}
		catch (Exception e) {
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
