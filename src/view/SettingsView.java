package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingsView extends Stage {

	private TurtleDisplay myTurtleDisplay;
	private ColorPicker myBackgroundPicker;
	private ColorPicker myPenPicker;

	
	public SettingsView(TurtleDisplay display, Stage primaryStage) {
		myTurtleDisplay = display;
		this.setTitle("SLogo Settings");
		this.setResizable(false);
		this.setupStage(primaryStage);
	}

	private void setupStage(Stage primaryStage) {
		VBox box = new VBox(16);
		box.setPadding(new Insets(20, 20, 20, 20));
		
		myBackgroundPicker = new ColorPicker(myTurtleDisplay.getBackgroundColor(), (e -> this.setTurtleBackground()));
		VBox backgroundPickerBox = addLabelTo(myBackgroundPicker, "Background Color:");
		
		myPenPicker = new ColorPicker(myTurtleDisplay.getPenColor(), (e -> this.setPenColor()));
		VBox penPickerBox = addLabelTo(myPenPicker, "Pen Color:");
		
		box.getChildren().addAll(backgroundPickerBox, new Separator(), penPickerBox);
		
		Scene scene = new Scene(box, 250, 400);
		scene.getStylesheets().add("view/style.css");
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(primaryStage);
		this.setScene(scene);
	}
	
	private void setPenColor() {
		myTurtleDisplay.setPenColor(myPenPicker.getColor());
	}
	
	private void setTurtleImage() {
		//TODO:
	}
	
	private void setTurtleBackground() {
		myTurtleDisplay.setBackgroundColor(myBackgroundPicker.getColor());
	}
	
	private VBox addLabelTo(Group group, String string) {
		VBox result = new VBox(8);
		Label label = new Label(string);
		result.getChildren().addAll(label, group);
		result.setAlignment(Pos.CENTER);
		return result;
	}
}
