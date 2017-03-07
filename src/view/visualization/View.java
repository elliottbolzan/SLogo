package view.visualization;

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
	private SplitPane pane;
	private int index;
	private boolean collapseOnRight;

	/**
	 * 
	 */
	public View(SplitPane pane, int index, boolean collapseOnRight) {
		this.pane = pane;
		this.index = index;
		this.collapseOnRight = collapseOnRight;
		setMinSize(0, 280);
		createToolbar();
	}

	private void createToolbar() {

		title = new Label("");
		
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
		pane.setDividerPosition(index, collapseOnRight ? 1 : 0);
	}

}
