package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

/**
 * @author Jay Doherty
 * This class represents a color in the environment. The color can be referred to by its 
 * index and should be stored in an observable list that will be monitored by the front end
 * element that displays all of the colors to the user: view/panel/ColorView.java.
 */
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