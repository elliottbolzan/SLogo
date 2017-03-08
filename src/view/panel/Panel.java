package view.panel;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import view.View;
import view.Workspace;

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
		super(workspace.getPane(), index, true, true);
		this.workspace = workspace;
		setTitle(workspace.getController().getResources().getString("PanelTitle"));
		createSubviews();
		setup();
	}

	private void createSubviews() {
		subviewTitles = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;
			{
				add(workspace.getController().getResources().getString("HistoryTitle"));
				add(workspace.getController().getResources().getString("VariablesTitle"));
				add(workspace.getController().getResources().getString("UserCommandsTitle"));
				add(workspace.getController().getResources().getString("SettingsTitle"));
				add(workspace.getController().getResources().getString("ActiveTurtlesTitle"));
				add(workspace.getController().getResources().getString("ColorsTitle"));
				add(workspace.getController().getResources().getString("ImagesTitle"));
			}
		};
		subviews = new ArrayList<Node>() {
			private static final long serialVersionUID = 1L;
			{
				add(new CommandView(workspace, workspace.getController().getHistory()));
				add(new VariableView(workspace, workspace.getController().getVariables()));
				add(new CommandView(workspace, workspace.getController().getUserDefinedCommands()));
				add(new SettingsView(workspace));
				add(new ActiveTurtlesView());
				add(new ColorView());
				add(new TurtleImageView());
			}
		};
	}

	private void setup() {

		final Accordion accordion = new Accordion();
		
		List<TitledPane> titledPanes = new ArrayList<TitledPane>();
		for (int i = 0; i < subviews.size(); i++) {
			TitledPane pane = new TitledPane(subviewTitles.get(i), subviews.get(i));
			titledPanes.add(pane);
		}
		accordion.getPanes().addAll(titledPanes);
		accordion.setPrefWidth(300);
		accordion.setExpandedPane(titledPanes.get(0));

		setCenter(accordion);

	}

}
