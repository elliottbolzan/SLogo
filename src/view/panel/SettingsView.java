package view.panel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.Workspace;
import view.components.FilePicker;
import view.visualization.TurtleDisplay;

public class SettingsView extends BorderPane {

	private Workspace workspace;
	private Controller controller;
	private TurtleDisplay turtleDisplay;
	private ComboBox<String> myLanguagePicker;
	private Spinner<Integer> myTurtleNumberPicker;
	private ColorPicker myBackgroundPicker;
	private FilePicker myImagePicker;
	private FilePicker myScriptPicker;
	
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

		myImagePicker = new FilePicker(controller, 200, workspace.getDefaults().getTurtlePath(), new File(System.getProperty("user.dir") + "/src/resources/images"), "*.png", "*.jpg", "*.gif");
		myImagePicker.getTextField().textProperty().addListener(e -> this.setTurtleImage());
		VBox imagePickerBox = addLabelTo(myImagePicker, controller.getResources().getString("DefaultTurtleImageString"));
		
		myLanguagePicker = new ComboBox<String>();
		myLanguagePicker.getItems().addAll(getLanguages());
		myLanguagePicker.setOnAction(e -> setLanguage());
		myLanguagePicker.setValue(getLanguage());
		VBox languagePickerBox = addLabelTo(myLanguagePicker, controller.getResources().getString("LanguagePickerLabel"));
		
		myTurtleNumberPicker = new Spinner<Integer>();
		myTurtleNumberPicker.prefWidthProperty().bind(widthProperty().divide(2.0));
		myTurtleNumberPicker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, workspace.getDefaults().getNumberOfTurtles()));
		VBox turtlePickerBox = addLabelTo(myTurtleNumberPicker, controller.getResources().getString("TurtleNumberLabel"));

		myBackgroundPicker = new ColorPicker();
		myBackgroundPicker.setOnAction(e -> this.setTurtleBackground(myBackgroundPicker.getValue()));
		myBackgroundPicker.setValue(workspace.getDefaults().getBackgroundColor());
		VBox backgroundPickerBox = addLabelTo(myBackgroundPicker, controller.getResources().getString("BackgroundPickerLabel"));
		
		myScriptPicker = new FilePicker(controller, 200, workspace.getDefaults().getScriptPath(), new File(System.getProperty("user.dir") + "/data/examples"), "*.txt", "*.logo");
		VBox scriptPickerBox = addLabelTo(myScriptPicker, controller.getResources().getString("DefaultScriptString"));
		
		Button saveButton = new Button(controller.getResources().getString("SaveSettingsButton"));
		saveButton.setOnAction(e -> save());

		box.getChildren().addAll(languagePickerBox, new Separator(), turtlePickerBox, new Separator(), imagePickerBox, new Separator(), backgroundPickerBox, new Separator(), scriptPickerBox, new Separator(), saveButton);
		
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

	private void setTurtleImage() {
		workspace.getTurtleManager().setTurtleImage(myImagePicker.getURL());
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
	
	private void save() {
		// TO WRITE. SEE WORKSPACESETTINGS FOR FORMAT
		String backgroundColor = turtleDisplay.getBackgroundColor().toString();
		String numberOfTurtles = String.valueOf(myTurtleNumberPicker.getValue());
		String defaultScript = myScriptPicker.getTextField().getText();
		String defaultTurtle = myImagePicker.getTextField().getText();
		// GET THE REAL, FULL PATHS - not just the last part, for the previous two lines!
		String language = getLanguage();
	}
	
}
