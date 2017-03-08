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

/**
 * @author Elliott Bolzan
 *
 */
public class View extends BorderPane {

	private Label title;
	private SplitPane owner;
	private int dividerIndex;
	private boolean collapseRight;
	private boolean showToolbar;

	/**
	 * 
	 */
	public View(SplitPane owner, int dividerIndex, boolean collapseRight, boolean showToolbar) {
		this.owner = owner;
		this.dividerIndex = dividerIndex;
		this.collapseRight = collapseRight;
		this.showToolbar =  showToolbar;
		title = new Label("");
		setMinSize(0, 280);
		if (showToolbar) {
			createToolbar();
		}
	}

	private void createToolbar() {
		
		HBox spacing = new HBox();
		spacing.maxWidth(Double.MAX_VALUE);
		HBox.setHgrow(spacing, Priority.ALWAYS);

		Button minimizeButton = makeMinimizeButton("view/visualization/minimize.png");

		ToolBar toolBar = new ToolBar();
		toolBar.getItems().addAll(title, spacing, minimizeButton);
		toolBar.setPrefSize(getWidth(), 18);

		setTop(toolBar);
	}

	private Button makeMinimizeButton(String imagePath) {
		Image minimizeImage = new Image(getClass().getClassLoader().getResourceAsStream(imagePath), 16, 10, true, true);
		Button minimizeButton = new Button("", new ImageView(minimizeImage));
		minimizeButton.setOnAction(e -> minimize());
		minimizeButton.setPrefSize(16, 10);
		minimizeButton.getStyleClass().add("minimize");
		return minimizeButton;
	}

	public void setTitle(String string) {
		title.setText(string);
	}

	public void minimize() {
		owner.setDividerPosition(dividerIndex, collapseRight ? 1 : 0);
	}

}
