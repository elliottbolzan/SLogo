package view.panel;

import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import model.IndexedImage;

/**
 * @author Jay Doherty
 * This class creates a table of images from an observable list of image paths.
 */
public class TurtleImageView extends BorderPane {

	private ObservableList<IndexedImage> data;

	/**
	 * 
	 */
	protected TurtleImageView(ObservableList<IndexedImage> data) {
		this.data = data;
		this.setup();
	}

	private void setup() {
		TableView<IndexedImage> table = new TableView<IndexedImage>();
		table.setPrefHeight(200);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setEditable(false);
		table.getStyleClass().add("panel-table");
		table.prefHeightProperty().bind(heightProperty());

		TableColumn<IndexedImage, Integer> indexColumn = new TableColumn<IndexedImage, Integer>("Index");
		indexColumn.setCellValueFactory(e -> e.getValue().indexProperty().asObject());

		TableColumn<IndexedImage, String> imageColumn = new TableColumn<IndexedImage, String>("Image");
		imageColumn.setCellValueFactory(e -> e.getValue().pathProperty());
		imageColumn.setCellFactory(column -> {
			return new TableCell<IndexedImage, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem("", empty);
					if (item != null) {
						Image img = new Image(getClass().getClassLoader().getResourceAsStream(item));
						this.setBackground(new Background(new BackgroundImage(img, null, null, null, null)));
						this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
						this.setPrefSize(img.getWidth(), img.getHeight());
					}
				}
			};
		});

		table.setItems(data);
		table.getColumns().add(indexColumn);
		table.getColumns().add(imageColumn);

		setCenter(table);
	}
}
