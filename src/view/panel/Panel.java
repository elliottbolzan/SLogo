package view.panel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import view.View;

/**
 * @author Elliott Bolzan
 *
 */
public class Panel extends Group {

	private View view;

	/**
	 * 
	 */
	public Panel(View view) {
		this.view = view;
		setup();
	}

	private void setup() {

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setPrefViewportWidth(280);
		scrollPane.setPrefViewportHeight(400);
		scrollPane.getStyleClass().add("panel-scroll-pane");

		VBox box = new VBox(16);
		box.setPadding(new Insets(20, 20, 20, 20));

		Label title = new Label(view.getController().getResources().getString("PanelTitle"));
		title.setStyle("-fx-font-size: 28; -fx-font-weight: bold;");

		Node historyView = addLabelTo(new CommandList(view, view.getController().getHistory()),
				view.getController().getResources().getString("HistoryTitle"));
		Node variableView = addLabelTo(new VariableTable(view, view.getController().getVariables()),
				view.getController().getResources().getString("VariablesTitle"));
		Node commandView = addLabelTo(new CommandList(view, view.getController().getUserDefinedCommands()),
				view.getController().getResources().getString("UserCommandsTitle"));

		box.getChildren().addAll(title, makeSeparator(), historyView, makeSeparator(), variableView, makeSeparator(),
				commandView, makeSeparator(), makeButtonBar());
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

	private Node makeButtonBar() {
		ButtonBar bar = new ButtonBar();
		Button commandReferenceButton = makeButton(view.getController().getResources().getString("HelpButton"), e -> view.showHelp());
		Button settingsButton = makeButton(view.getController().getResources().getString("SettingsButton"), e -> view.showSettings());
		ButtonBar.setButtonData(commandReferenceButton, ButtonData.RIGHT);
		ButtonBar.setButtonData(settingsButton, ButtonData.LEFT);
		bar.getButtons().addAll(commandReferenceButton, settingsButton);
		return bar;
	}

	private Node makeSeparator() {
		return new Separator();
	}

}
