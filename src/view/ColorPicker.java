package view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorPicker extends Group {
	
	private Rectangle myColorDisplay;
	private SimpleDoubleProperty myRed;
	private SimpleDoubleProperty myGreen;
	private SimpleDoubleProperty myBlue;
	
	public ColorPicker(Color initial, EventHandler<ActionEvent> applyAction) {
		int width = 200;
		myRed = new SimpleDoubleProperty(initial.getRed());
		myGreen = new SimpleDoubleProperty(initial.getGreen());
		myBlue = new SimpleDoubleProperty(initial.getBlue());
		this.setup(width, applyAction);
	}

	protected Color getColor() {
		return new Color(myRed.get(), myGreen.get(), myBlue.get(), 1.0);
	}
	
	private void setup(int width, EventHandler<ActionEvent> applyAction) {
		HBox container = new HBox(10);
		container.setPrefWidth(width);

		VBox sliders = new VBox();
		sliders.setPrefWidth(0.6*width);
		HBox redSlide = makeSlider("R", 0.0, 1.0, myRed.get(), myRed);
		HBox greenSlide = makeSlider("G", 0.0, 1.0, myGreen.get(), myGreen);
		HBox blueSlide = makeSlider("B", 0.0, 1.0, myBlue.get(), myBlue);
		sliders.getChildren().addAll(redSlide, greenSlide, blueSlide);
		
		VBox output = new VBox(10);
		output.setAlignment(Pos.CENTER);
		output.setPrefWidth(0.4*width);
		myColorDisplay = new Rectangle(0.4*width, 50);
		myColorDisplay.setFill(this.getColor());
		myColorDisplay.setStroke(Color.BLACK);
		Button applyButton = new Button("Apply");
		applyButton.setOnAction(applyAction);
		output.getChildren().addAll(myColorDisplay, applyButton);
		
		container.getChildren().addAll(sliders, output);
		this.getChildren().add(container);
	}
	
	private HBox makeSlider(String name, double min, double max, double init, SimpleDoubleProperty valueObserver) {
		Slider slide = new Slider(min,max, init);
		slide.setShowTickMarks(true);
		slide.setShowTickLabels(true);
		valueObserver.bind(slide.valueProperty());
		slide.valueProperty().addListener(e -> this.update());
		
		HBox labelBox = new HBox();
		Label label = new Label(name);
		labelBox.getChildren().addAll(label, slide);
		
		return labelBox;
	}
	
	private void update() {
		myColorDisplay.setFill(this.getColor());
	}
}
