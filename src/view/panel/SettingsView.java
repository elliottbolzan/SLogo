package view.panel;

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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.Workspace;
import view.components.FilePicker;
import view.visualization.Turtle;
import view.visualization.TurtleDisplay;

public class SettingsView extends BorderPane {

	private Workspace workspace;
	private Controller controller;
	private TurtleDisplay turtleDisplay;
	private ComboBox<String> myLanguagePicker;
	private ColorPicker myBackgroundPicker;
	private ColorPicker myPenPicker;
	private FilePicker myImagePicker;
	
	private static final String PATH_TO_LANGUAGES = "src/resources/languages";

	public SettingsView(Workspace workspace) {
		this.workspace = workspace;
		controller = workspace.getController();
		turtleDisplay = workspace.getDisplay();
		this.setup();
	}

	private void setup() {
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		scrollPane.prefViewportWidthProperty().bind(widthProperty());
		scrollPane.prefViewportHeightProperty().bind(heightProperty());
		
		scrollPane.getStyleClass().add("panel-scroll-pane");

		VBox box = new VBox(8);
		box.setPadding(new Insets(5));
		box.prefWidthProperty().bind(widthProperty());

		myImagePicker = new FilePicker(controller, 200, controller.getResources().getString("ImagePickerFieldString"), new File(System.getProperty("user.dir") + "/src/resources/images"), "*.png", "*.jpg", "*.gif");
		myImagePicker.getTextField().textProperty().addListener(e -> this.setTurtleImage());
		VBox imagePickerBox = addLabelTo(myImagePicker, controller.getResources().getString("TurtlePickerLabel"));
		
		myLanguagePicker = new ComboBox<String>();
		myLanguagePicker.getItems().addAll(getLanguages());
		myLanguagePicker.setOnAction(e -> setLanguage());
		myLanguagePicker.setValue(getLanguage());
		VBox languagePickerBox = addLabelTo(myLanguagePicker, controller.getResources().getString("LanguagePickerLabel"));

		myBackgroundPicker = new ColorPicker();
		myBackgroundPicker.setOnAction(e -> this.setTurtleBackground(myBackgroundPicker.getValue()));
		VBox backgroundPickerBox = addLabelTo(myBackgroundPicker, controller.getResources().getString("BackgroundPickerLabel"));

		myPenPicker = new ColorPicker();
		myPenPicker.setOnAction(e -> setPenColor());
		VBox penPickerBox = addLabelTo(myPenPicker, controller.getResources().getString("PenPickerLabel"));

		box.getChildren().addAll(languagePickerBox, new Separator(), imagePickerBox, new Separator(), backgroundPickerBox, new Separator(), penPickerBox);
		
		scrollPane.setContent(box);
		scrollPane.prefHeightProperty().bind(heightProperty());

		setCenter(scrollPane);
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
		turtleDisplay.getTurtleManager().setPenColor(myPenPicker.getValue());
	}

	private void setTurtleImage() {
		turtleDisplay.getTurtleManager().setTurtleImage(myImagePicker.getURL());
	}

	private void setTurtleBackground(Color color) {
		turtleDisplay.setBackgroundColor(color);
	}

	private VBox addLabelTo(Node node, String string) {
		VBox result = new VBox(8);
		Label label = new Label(string);
		result.getChildren().addAll(label, node);
		return result;
	}
}
