package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import utils.Direction;

/**
 * @author Elliott Bolzan This abstract class represents a collapsible view. It
 *         provides a tool bar with a minimize button: when this button is
 *         clicked, the view is collapsed. Any collapsible view should inherit
 *         from this class.
 */
public abstract class CollapsibleView extends BorderPane {

	private Label title;
	private SplitPane owner;
	private int dividerIndex;
	private Direction collapseDirection;

	/**
	 * Creates a CollapsibleView.
	 * 
	 * @param owner
	 *            the SplitPane the view is placed in.
	 * @param dividerIndex
	 *            the index of the divider the view depends on.
	 * @param collapseDirection
	 *            the direction the view needs to collapse in.
	 * @param hasToolbar
	 *            whether a toolbar should be displayed.
	 */
	public CollapsibleView(SplitPane owner, int dividerIndex, Direction collapseDirection, boolean hasToolbar) {
		this.owner = owner;
		this.dividerIndex = dividerIndex;
		this.collapseDirection = collapseDirection;
		title = new Label();
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
		ToolBar toolBar = new ToolBar(title, spacing, makeMinimizeButton("resources/images/minimize.png"));
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
		Image minimizeImage = new Image(getClass().getClassLoader().getResourceAsStream(imagePath), 16, 10, true, true);
		Button minimizeButton = new Button("", new ImageView(minimizeImage));
		minimizeButton.setOnAction(e -> minimize());
		minimizeButton.getStyleClass().add("minimize");
		return minimizeButton;
	}

	/**
	 * Set the view's title to a specific string.
	 * 
	 * @param string
	 *            the view's title.
	 */
	protected void setTitle(String string) {
		title.setText(string);
	}

	/**
	 * Minimize the view by modifying the SplitPane it is located in.
	 */
	private void minimize() {
		owner.setDividerPosition(dividerIndex,
				(collapseDirection == Direction.RIGHT || collapseDirection == Direction.BACK) ? 1 : 0);
	}

}
