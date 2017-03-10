package view.panel;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import utils.Direction;
import view.CollapsibleView;
import view.Workspace;
import view.components.Factory;

/**
 * @author Elliott Bolzan
 *
 */
public class Panel extends CollapsibleView {

	private Workspace workspace;
	private List<Node> subviews;
	private List<String> subviewTitles;
	
	/**
	 * 
	 */
	public Panel(Workspace workspace, int index) {
		super(workspace.getPane(), index, Direction.RIGHT, true);
		this.workspace = workspace;
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
				add(workspace.getController().getResources().getString("ColorsTitle"));
				add(workspace.getController().getResources().getString("ImagesTitle"));
				add(workspace.getController().getResources().getString("TurtleSettingsTitle"));
			}
		};
		subviews = new ArrayList<Node>() {
			private static final long serialVersionUID = 1L;
			{
				add(new CommandView(workspace, workspace.getController().getHistory()));
				add(new VariableView(workspace, workspace.getController().getVariables()));
				add(new CommandView(workspace, workspace.getController().getUserDefinedCommands()));
				add(new SettingsView(workspace));
				add(new ColorView(workspace.getController().getColorPalette()));
				add(new TurtleImageView(workspace.getController().getImagePalette()));
				add(new TurtleSettingsView(workspace.getController()).getView());
			}
		};
	}

	private void setup() {
		setTitle(workspace.getController().getResources().getString("PanelTitle"));
		Factory factory = new Factory(workspace.getController().getResources());
		setCenter(factory.makeAccordion(subviews, subviewTitles));
	}
}
