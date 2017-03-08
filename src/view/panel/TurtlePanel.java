package view.panel;

import controller.Controller;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import view.components.TurtleButtons;
import view.components.TurtleInfo;

public class TurtlePanel {

	private Controller myController;
	
	private VBox myPanel;
	private TurtleInfo myTurtleInfo;
	private TurtleButtons myTurtleButtons;
	
	public TurtlePanel(Controller control) {
		myController = control;
		myPanel = new VBox();
		
		myTurtleButtons = new TurtleButtons(control);
		myTurtleInfo = null;
		
		Spinner<Integer> turtlePicker = new Spinner<Integer>();
		turtlePicker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1));
		turtlePicker.valueProperty().addListener(e -> this.trackTurtle(turtlePicker.getValue()));
		
		myPanel.getChildren().addAll(turtlePicker, myTurtleButtons.getView());
	}
	
	public BorderPane getView() {
		BorderPane centered = new BorderPane();
		centered.setCenter(myPanel);
		return centered;
	}
	
	public void trackTurtle(int id) {
		if(myTurtleInfo != null) {
			myPanel.getChildren().remove(myTurtleInfo.getView());
		}
		//TODO: only re-add the panel if ID is valid
		myTurtleInfo = new TurtleInfo(myController.getTurtle());
		myPanel.getChildren().add(myTurtleInfo.getView());
	}
}
