// This entire file is part of my masterpiece.
// Elliott Bolzan

package view;

import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import utils.Direction;
import view.components.Factory;

/**
 * @author Elliott Bolzan This abstract class represents a collapsible view. It
 *         provides a tool bar with a minimize button: when this button is
 *         clicked, the view is collapsed. Any collapsible view should inherit
 *         from this class.
 * 
 *         This code's purpose: allowing for View elements to be collapsed.
 * 
 *         Why I think this code is well designed: this code makes use of
 *         practical design patterns, like the Factory design pattern; this code
 *         is concise, making use of the ternary operator in the minimize()
 *         method; this code is flexible, allowing for a View to either display
 *         a toolbar or not do so; this code is closed for modification but open
 *         for extension; this code extends an abstract class, View; this code
 *         makes use of a ResourceBundle.
 */
public abstract class CollapsibleView extends View {

	private Workspace workspace;
	private SplitPane owner;
	private int dividerIndex;
	private Direction collapseDirection;

	/**
	 * Creates a CollapsibleView.
	 * 
	 * @param workspace
	 *            the Workspace that owns the CollapsibleView.
	 * @param owner
	 *            the SplitPane the view is placed in.
	 * @param dividerIndex
	 *            the index of the divider the view depends on.
	 * @param collapseDirection
	 *            the direction the view needs to collapse in.
	 * @param hasToolbar
	 *            whether a toolbar should be displayed.
	 */
	public CollapsibleView(Workspace workspace, SplitPane owner, int dividerIndex, Direction collapseDirection,
			boolean hasToolbar) {
		this.workspace = workspace;
		this.owner = owner;
		this.dividerIndex = dividerIndex;
		this.collapseDirection = collapseDirection;
		setMinSize(0, 280);
		if (hasToolbar) {
			createToolbar();
		}
	}

	/**
	 * Creates a toolbar and adds it to the top of the view.
	 */
	private void createToolbar() {
		HBox spacing = new HBox();
		spacing.maxWidth(Double.MAX_VALUE);
		HBox.setHgrow(spacing, Priority.ALWAYS);
		ToolBar toolBar = new ToolBar(getTitle(), spacing,
				makeMinimizeButton(workspace.getController().getResources().getString("MinimizePath")));
		toolBar.setPrefSize(getWidth(), 18);
		setTop(toolBar);
	}

	/**
	 * Creates a minimize button.
	 * 
	 * @param imagePath
	 *            the path to the minimize button's image.
	 * @return a minimize button.
	 */
	private Button makeMinimizeButton(String imagePath) {
		Factory factory = new Factory(workspace.getController().getResources());
		return factory.makeTabButton(imagePath, e -> minimize(), "minimize", 16, 10);
	}

	/**
	 * Minimize the view by modifying the SplitPane it is located in.
	 */
	private void minimize() {
		owner.setDividerPosition(dividerIndex,
				(collapseDirection == Direction.RIGHT || collapseDirection == Direction.BACK) ? 1 : 0);
	}

}
