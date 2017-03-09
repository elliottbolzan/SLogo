package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class IndexedImage {
	private SimpleIntegerProperty index;
	private SimpleStringProperty path;

	public IndexedImage(int id, String initial) {
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