package view.panel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import view.Workspace;
import view.settings.SettingsView;
import view.visualization.View;

/**
 * @author Elliott Bolzan
 *
 */
public class Panel extends View {

	private Workspace workspace;
	private List<Node> subviews;
	private List<String> subviewTitles;

	/**
	 * 
	 */
	public Panel(Workspace workspace, int index) {
		super(workspace.getPane(), index, true);
		this.workspace = workspace;
		setTitle(workspace.getController().getResources().getString("PanelTitle"));
		createSubviews();
		setup();
	}

	private void createSubviews() {
		subviewTitles = new ArrayList<String>() {
			{
				add(workspace.getController().getResources().getString("HistoryTitle"));
				add(workspace.getController().getResources().getString("VariablesTitle"));
				add(workspace.getController().getResources().getString("UserCommandsTitle"));
				add(workspace.getController().getResources().getString("SettingsTitle"));
			}
		};
		subviews = new ArrayList<Node>() {
			{
				add(new CommandList(workspace, workspace.getController().getHistory()));
				add(new VariableTable(workspace, workspace.getController().getVariables()));
				add(new CommandList(workspace, workspace.getController().getUserDefinedCommands()));
				add(new SettingsView(workspace));
			}
		};
	}

	private void setup() {

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setPrefViewportWidth(280);
		scrollPane.setPrefViewportHeight(400);
		scrollPane.getStyleClass().add("panel-scroll-pane");

		final Accordion accordion = new Accordion();

		List<TitledPane> titledPanes = new ArrayList<TitledPane>();
		for (int i = 0; i < subviews.size(); i++) {
			TitledPane pane = new TitledPane(subviewTitles.get(i), subviews.get(i));
			titledPanes.add(pane);
		}
		accordion.getPanes().addAll(titledPanes);
		accordion.setPrefWidth(300);
		accordion.setExpandedPane(titledPanes.get(0));
		scrollPane.setContent(accordion);

		setCenter(accordion);

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
		Button settingsButton = makeButton(workspace.getController().getResources().getString("SettingsButton"),
				e -> workspace.showSettings());
		ButtonBar.setButtonData(settingsButton, ButtonData.LEFT);
		bar.getButtons().addAll(settingsButton);
		return bar;
	}

	private Node makeSeparator() {
		return new Separator();
	}

}
