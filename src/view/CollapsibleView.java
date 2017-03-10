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
 * @author Elliott Bolzan
 *
 */
public abstract class CollapsibleView extends BorderPane {

	private Label title;
	private SplitPane owner;
	private int dividerIndex;
	private Direction collapseDirection;

	/**
	 * 
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

	private void createToolbar() {
		HBox spacing = new HBox();
		spacing.maxWidth(Double.MAX_VALUE);
		HBox.setHgrow(spacing, Priority.ALWAYS);
		ToolBar toolBar = new ToolBar(title, spacing, makeMinimizeButton("resources/images/minimize.png"));
		toolBar.setPrefSize(getWidth(), 18);
		setTop(toolBar);
	}

	private Button makeMinimizeButton(String imagePath) {
		Image minimizeImage = new Image(getClass().getClassLoader().getResourceAsStream(imagePath), 16, 10, true, true);
		Button minimizeButton = new Button("", new ImageView(minimizeImage));
		minimizeButton.setOnAction(e -> minimize());
		minimizeButton.getStyleClass().add("minimize");
		return minimizeButton;
	}

	protected void setTitle(String string) {
		title.setText(string);
	}

	private void minimize() {
		owner.setDividerPosition(dividerIndex, (collapseDirection == Direction.RIGHT || collapseDirection == Direction.BACK) ? 1 : 0);
	}

}
