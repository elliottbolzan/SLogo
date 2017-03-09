package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class IndexedColor {
	private SimpleIntegerProperty index;
	private SimpleObjectProperty<Color> color;
	
	public IndexedColor(int id, Color initial) {
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