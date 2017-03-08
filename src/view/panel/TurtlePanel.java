package view.panel;

import controller.Controller;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.components.TurtleButtons;
import view.visualization.TurtleInfo;

public class TurtlePanel {

	private Controller myController;
	
	private BorderPane myPanel;
	private VBox myContent;
	
	private TurtleInfo myTurtleInfo;
	private TurtleButtons myTurtleButtons;
	
	public TurtlePanel(Controller control) {
		myPanel = new BorderPane();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.prefViewportWidthProperty().bind(myPanel.widthProperty());
		scrollPane.prefViewportHeightProperty().bind(myPanel.heightProperty());
		scrollPane.prefHeightProperty().bind(myPanel.heightProperty());
		scrollPane.getStyleClass().add("panel-scroll-pane");
		
		myController = control;
		myContent = new VBox();
		
		myTurtleButtons = new TurtleButtons(control);
		myTurtleInfo = null;
		
		HBox turtleID = new HBox(5);
		Label idLabel = new Label("ID:");
		Spinner<Integer> idPicker = new Spinner<Integer>();
		idPicker.prefWidthProperty().bind(myContent.widthProperty().divide(2.0));
		idPicker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0));
		idPicker.valueProperty().addListener(e -> this.trackTurtle(idPicker.getValue()));
		turtleID.getChildren().addAll(idLabel, idPicker);
		
		myContent.getChildren().addAll(turtleID, myTurtleButtons.getView());
		
		scrollPane.setContent(myContent);
		myPanel.setCenter(scrollPane);
	}
	
	public BorderPane getView() {
		return myPanel;
	}
	
	private void trackTurtle(int id) {
		if(myTurtleInfo != null) {
			myContent.getChildren().remove(myTurtleInfo.getView());
		}
		//TODO: only re-add the panel if ID is valid
		myTurtleInfo = new TurtleInfo(myController.getTurtle());
		myContent.getChildren().add(myTurtleInfo.getView());
	}
}
