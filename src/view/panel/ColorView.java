package view.panel;

import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import model.IndexedColor;

/**
 * @author Jay Doherty
 * This class creates a table of colors from an observable list of colors.
 */
public class ColorView extends BorderPane {

	private ObservableList<IndexedColor> data;

	protected ColorView(ObservableList<IndexedColor> data) {
		this.data = data;
		this.setup();
	}
	
	private void setup() {
		TableView<IndexedColor> table = new TableView<IndexedColor>();
		table.setPrefHeight(150);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setEditable(false);
		table.getStyleClass().add("panel-table");
		table.prefHeightProperty().bind(heightProperty());

		TableColumn<IndexedColor, Integer> indexColumn = new TableColumn<IndexedColor, Integer>("Index");
		indexColumn.setCellValueFactory(e -> e.getValue().indexProperty().asObject());
		
		TableColumn<IndexedColor, String> colorColumn = new TableColumn<IndexedColor, String>("Color");
		colorColumn.setCellValueFactory(e -> e.getValue().colorProperty().asString());
		colorColumn.setCellFactory(column -> {
		    return new TableCell<IndexedColor, String>() {
		        @Override
		        protected void updateItem(String item, boolean empty) {
		            super.updateItem("", empty);
		            if(item != null) {
		            	this.setBackground(new Background(new BackgroundFill(Color.valueOf(item), null, null)));
		            	this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
		            }
		        }
		    };
		});
		
		table.setItems(data);
		table.getColumns().add(indexColumn);
		table.getColumns().add(colorColumn);

		setCenter(table);
	}
}
