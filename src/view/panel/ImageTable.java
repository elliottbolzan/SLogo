package view.panel;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;

/**
 * @author Jay Doherty
 *
 */
public class ImageTable extends Group {

	private ObservableList<ImageElement> data;

	/**
	 * 
	 */
	public ImageTable() {
		this.data = FXCollections.observableArrayList(new ArrayList<ImageElement>());
		this.setup();
		this.makeDefaultImages();
	}

	protected String getImagePath(int index) {
		return data.get(index - 1).pathProperty().get();
	}
	
	private void setup() {
		TableView<ImageElement> table = new TableView<ImageElement>();
		table.setPrefHeight(200);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setEditable(false);
		table.getStyleClass().add("panel-table");

		TableColumn<ImageElement, Integer> indexColumn = new TableColumn<ImageElement, Integer>("Index");
		indexColumn.setCellValueFactory(e -> e.getValue().indexProperty().asObject());
		
		TableColumn<ImageElement, String> colorColumn = new TableColumn<ImageElement, String>("Image");
		colorColumn.setCellValueFactory(e -> e.getValue().pathProperty());
		colorColumn.setCellFactory(column -> {
		    return new TableCell<ImageElement, String>() {
		        @Override
		        protected void updateItem(String item, boolean empty) {
		            super.updateItem("", empty);
		            if(item != null) {
		            	Image img = new Image(getClass().getClassLoader().getResourceAsStream(item));
		            	this.setBackground(new Background(new BackgroundImage(img, null, null, null, null)));
		            	this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
		            	this.setPrefSize(img.getWidth(), img.getHeight());
		            }
		        }
		    };
		});
		
		table.setItems(data);
		table.getColumns().addAll(indexColumn, colorColumn);

		getChildren().add(table);
	}
	
	private void makeDefaultImages() {
		data.add(new ImageElement(1, "view/visualization/turtle.png"));
		data.add(new ImageElement(2, "view/visualization/bigTurtle.png"));
	}
	
	protected class ImageElement {
		private SimpleIntegerProperty index;
		private SimpleStringProperty path;
		
		private ImageElement(int id, String initial) {
			index = new SimpleIntegerProperty(this, "index", id);
			path = new SimpleStringProperty(this, "path", initial);
		}
		
		public SimpleStringProperty pathProperty() {
			return path;
		}
		
		public SimpleIntegerProperty indexProperty() {
			return index;
		}
	}
}
