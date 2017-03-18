package view.panel;

import controller.Controller;
import javafx.geometry.Pos;
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

/**
 * @author Jay Doherty
 * This class creates a panel that has buttons for moving all the turtles and also a picker
 * for displaying information about the various turtles on screen.
 */
public class TurtleSettingsView {

	private Controller myController;

	private BorderPane myPanel;
	private VBox myContent;

	private TurtleInfo myTurtleInfo;
	private TurtleButtons myTurtleButtons;

	protected TurtleSettingsView(Controller control) {
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
		turtleID.setAlignment(Pos.CENTER_LEFT);

		myContent.getChildren().addAll(myTurtleButtons.getView(), turtleID);

		scrollPane.setContent(myContent);
		myPanel.setCenter(scrollPane);
	}

	protected BorderPane getView() {
		return myPanel;
	}

	private void trackTurtle(int id) {
		if (myTurtleInfo != null) {
			myContent.getChildren().remove(myTurtleInfo.getView());
		}
		if(myController.getTurtleManager().getAllTurtles().keySet().contains(id)) {
			myTurtleInfo = new TurtleInfo(myController, myController.getTurtleManager().getAllTurtles().get(id));
			myContent.getChildren().add(myTurtleInfo.getView());
		}
	}
}
