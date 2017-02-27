package view;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingsView extends Stage {

	private Controller controller;
	private TurtleDisplay myTurtleDisplay;
	private ComboBox<String> myLanguagePicker;
	private ColorPicker myBackgroundPicker;
	private ColorPicker myPenPicker;

	public SettingsView(Controller controller, TurtleDisplay display, Stage primaryStage) {
		myTurtleDisplay = display;
		this.controller = controller;
		this.setTitle("SLogo Settings");
		this.setResizable(false);
		this.setupStage(primaryStage);
	}

	private void setupStage(Stage primaryStage) {
		VBox box = new VBox(16);
		box.setAlignment(Pos.TOP_CENTER);
		box.setPadding(new Insets(20, 20, 20, 20));

		// TODO: button to launch file chooser for a turtle image

		myLanguagePicker = new ComboBox<String>();
		myLanguagePicker.getItems().addAll(getLanguages());
		myLanguagePicker.setOnAction(e -> setLanguage());
		// languagePicker.setValue(value); // Set to current language
		VBox languagePickerBox = addLabelTo(myLanguagePicker, "Language:");

		myBackgroundPicker = new ColorPicker(myTurtleDisplay.getBackgroundColor(), (e -> this.setTurtleBackground()));
		VBox backgroundPickerBox = addLabelTo(myBackgroundPicker, "Background Color:");

		myPenPicker = new ColorPicker(myTurtleDisplay.getPenColor(), (e -> this.setPenColor()));
		VBox penPickerBox = addLabelTo(myPenPicker, "Pen Color:");

		box.getChildren().addAll(languagePickerBox, new Separator(), backgroundPickerBox, new Separator(), penPickerBox);

		Scene scene = new Scene(box, 250, 400);
		scene.getStylesheets().add("view/style.css");
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(primaryStage);
		this.setScene(scene);
	}

	private List<String> getLanguages() {
		List<String> result = new ArrayList<String>();
		List<File> options = Arrays.asList(new File("src/resources/languages").listFiles());
		Collections.sort(options);
		for (File file : options) {
			result.add(file.getName().split("\\.")[0]);
		}
		return result;
	}

	private void setLanguage() {
		controller.setLanguage(myLanguagePicker.getValue());
	}

	private void setPenColor() {
		myTurtleDisplay.setPenColor(myPenPicker.getColor());
	}

	private void setTurtleImage() {
		// TODO:
	}

	private void setTurtleBackground() {
		myTurtleDisplay.setBackgroundColor(myBackgroundPicker.getColor());
	}

	private VBox addLabelTo(Node node, String string) {
		VBox result = new VBox(8);
		Label label = new Label(string);
		result.getChildren().addAll(label, node);
		result.setAlignment(Pos.CENTER);
		return result;
	}
}
