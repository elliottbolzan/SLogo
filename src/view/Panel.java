package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import model.Variable;

/**
 * @author Elliott Bolzan
 *
 */
public class Panel extends Group {

	private View view;
	private ObservableList<Variable> data;

	/**
	 * 
	 */
	public Panel(View view) {
		this.view = view;
		setup();
	}
	
	private void setup() {

		data = FXCollections.observableArrayList(new Variable("Jacob", 5.25),
				new Variable("Roger", 12));

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setPrefViewportWidth(280);
		scrollPane.setPrefViewportHeight(400);
		scrollPane.getStyleClass().add("panel-scroll-pane");

		VBox box = new VBox(16);
		box.setPadding(new Insets(20, 20, 20, 20));
		Node historyView = addLabelTo(new CommandList(view, view.getController().getHistory()), "Past Commands");
		Node variableView = addLabelTo(new VariableTable(view, view.getController().getVariables()), "Variables");
		Node commandView = addLabelTo(new CommandList(view, view.getController().getUserDefinedCommands()),
				"User-Defined Commands");
		Button commandReference = makeButton("Command Reference", e -> view.showHelp());
		Button settings = makeButton("Settings", e -> view.showSettings());
		box.getChildren().addAll(historyView, makeSeparator(), variableView, makeSeparator(), commandView,
				commandReference, settings);
		box.setAlignment(Pos.CENTER);

		scrollPane.setContent(box);

		getChildren().add(scrollPane);

	}

	private Node addLabelTo(Group group, String string) {
		VBox result = new VBox(8);
		Label label = new Label(string);
		result.getChildren().addAll(label, group);
		result.setAlignment(Pos.CENTER);
		return result;
	}

	private Button makeButton(String string, EventHandler<ActionEvent> handler) {
		Button button = new Button(string);
		button.setOnAction(handler);
		return button;
	}

	private Node makeSeparator() {
		return new Separator();
	}

}
