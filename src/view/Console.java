/**
 * 
 */
package view;

import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.StringProperty;

/**
 * @author Elliott Bolzan
 *
 */
public class Console extends Group {

	private String preamble = "slogo$ ";
	private TextArea textArea;
	
	private int width = 400;
	private int height = 400;

	/**
	 * 
	 */
	public Console() {
		createTextArea();
	}

	protected void print(String message) {
		textArea.appendText("\n" + message + "\n");
	}

	protected void clear() {
		textArea.setText(preamble);
	}

	private void createTextArea() {
		textArea = new TextArea();
		textArea.setPrefWidth(width);
		textArea.setPrefHeight(height);
		textArea.setWrapText(true);
		textArea.setOnKeyPressed(e -> handleKeyCode(e));
		textArea.textProperty().addListener(e -> determineTextInput(textArea.getText()));
		getChildren().add(textArea);
		appendPreamble();
	}

	private void handleKeyCode(KeyEvent event) {
		if (event.getCode() == KeyCode.UP) {
			upPressed();
			event.consume();
		}
		else if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.BACK_SPACE) {
			if (textArea.getText().lastIndexOf(preamble) + preamble.length() >= textArea.getCaretPosition()) { 
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

	private void appendPreamble() {
		textArea.appendText(preamble);
	}
	
	private String getCurrentCommand() {
		int index = textArea.getText().lastIndexOf(preamble);
		return textArea.getText(index + preamble.length(), textArea.getText().length() - 1);
	}
	
	private void enterPressed() {
		System.out.println(getCurrentCommand());
		appendPreamble();
	}

	private void upPressed() {
		// DISPLAY HISTORY WINDOW
	}
	
}
