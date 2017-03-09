package view.panel;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
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

/**
 * @author Jay Doherty
 *
 */
public class ColorView extends BorderPane {

	private ObservableList<ColorElement> data;

	/**
	 * 
	 */
	public ColorView() {
		this.data = FXCollections.observableArrayList(new ArrayList<ColorElement>());
		this.setup();
		this.makeDefaultColors();
	}

	protected Color getColor(int index) {
		return data.get(index - 1).colorProperty().getValue();
	}
	
	private void setup() {
		TableView<ColorElement> table = new TableView<ColorElement>();
		table.setPrefHeight(150);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setEditable(false);
		table.getStyleClass().add("panel-table");
		table.prefHeightProperty().bind(heightProperty());

		TableColumn<ColorElement, Integer> indexColumn = new TableColumn<ColorElement, Integer>("Index");
		indexColumn.setCellValueFactory(e -> e.getValue().indexProperty().asObject());
		
		TableColumn<ColorElement, String> colorColumn = new TableColumn<ColorElement, String>("Color");
		colorColumn.setCellValueFactory(e -> e.getValue().colorProperty().asString());
		colorColumn.setCellFactory(column -> {
		    return new TableCell<ColorElement, String>() {
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
		table.getColumns().addAll(indexColumn, colorColumn);

		setCenter(table);
	}
	
	private void makeDefaultColors() {
		data.add(new ColorElement(1, Color.RED));
		data.add(new ColorElement(2, Color.ORANGE));
		data.add(new ColorElement(3, Color.YELLOW));
		data.add(new ColorElement(4, Color.GREEN));
		data.add(new ColorElement(5, Color.BLUE));
		data.add(new ColorElement(6, Color.PURPLE));
		data.add(new ColorElement(7, Color.BLACK));
		data.add(new ColorElement(8, Color.WHITE));
		data.add(new ColorElement(9, Color.GOLD));
		data.add(new ColorElement(10, Color.SILVER));
	}
	
	protected class ColorElement {
		private SimpleIntegerProperty index;
		private SimpleObjectProperty<Color> color;
		
		private ColorElement(int id, Color initial) {
			index = new SimpleIntegerProperty(this, "index", id);
			color = new SimpleObjectProperty<Color>(this, "color", initial);
		}
		
		public SimpleObjectProperty<Color> colorProperty() {
			return color;
		}
		
		public SimpleIntegerProperty indexProperty() {
			return index;
		}
	}
}
