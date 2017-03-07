package view.settings;

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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.Workspace;
import view.visualization.Turtle;
import view.visualization.TurtleDisplay;

public class SettingsView extends VBox {

	private Workspace workspace;
	private Controller controller;
	private TurtleDisplay turtleDisplay;
	private ComboBox<String> myLanguagePicker;
	private ColorPicker myBackgroundPicker;
	private ColorPicker myPenPicker;
	private FilePicker myImagePicker;
	
	private static final String PATH_TO_LANGUAGES = "src/resources/languages";

	public SettingsView(Workspace workspace) {
		super(16);
		this.workspace = workspace;
		controller = workspace.getController();
		turtleDisplay = workspace.getDisplay();
		this.setup();
	}

	private void setup() {

		setAlignment(Pos.TOP_CENTER);
		setPadding(new Insets(20, 20, 20, 20));

		myImagePicker = new FilePicker(controller, 200, controller.getResources().getString("ImagePickerFieldString"), "*.png", "*.jpg", "*.gif");
		myImagePicker.getTextField().textProperty().addListener(e -> this.setTurtleImage());
		VBox imagePickerBox = addLabelTo(myImagePicker, controller.getResources().getString("TurtlePickerLabel"));
		
		myLanguagePicker = new ComboBox<String>();
		myLanguagePicker.getItems().addAll(getLanguages());
		myLanguagePicker.setOnAction(e -> setLanguage());
		myLanguagePicker.setValue(getLanguage());
		VBox languagePickerBox = addLabelTo(myLanguagePicker, controller.getResources().getString("LanguagePickerLabel"));

		myBackgroundPicker = new ColorPicker(controller, turtleDisplay.getBackgroundColor(), (e -> this.setTurtleBackground()));
		VBox backgroundPickerBox = addLabelTo(myBackgroundPicker, controller.getResources().getString("BackgroundPickerLabel"));

		myPenPicker = new ColorPicker(controller, Color.BLACK, (e -> this.setPenColor()));
		VBox penPickerBox = addLabelTo(myPenPicker, controller.getResources().getString("PenPickerLabel"));

		getChildren().addAll(languagePickerBox, new Separator(), imagePickerBox, new Separator(), backgroundPickerBox, new Separator(), penPickerBox);
		
	}

	private List<String> getLanguages() {
		List<String> result = new ArrayList<String>();
		List<File> options = Arrays.asList(new File(PATH_TO_LANGUAGES).listFiles());
		Collections.sort(options);
		for (File file : options) {
			if (!(file.getName().contains("Syntax"))) {
				result.add(file.getName().split("\\.")[0]);
			}
		}
		return result;
	}
	
	private String getLanguage() {
		return controller.getLanguage();
	}

	private void setLanguage() {
		controller.setLanguage(myLanguagePicker.getValue());
	}

	private void setPenColor() {
		for(Turtle t : turtleDisplay.getAllTurtles()) {
			turtleDisplay.setPenColor(t.getID(), myPenPicker.getColor());
		}
	}

	private void setTurtleImage() {
		for(Turtle t : turtleDisplay.getAllTurtles()) {
			turtleDisplay.setTurtleImage(t.getID(), myImagePicker.getTextField().getText());
		}
	}

	private void setTurtleBackground() {
		turtleDisplay.setBackgroundColor(myBackgroundPicker.getColor());
	}

	private VBox addLabelTo(Node node, String string) {
		VBox result = new VBox(8);
		Label label = new Label(string);
		result.getChildren().addAll(label, node);
		result.setAlignment(Pos.CENTER);
		return result;
	}
}
