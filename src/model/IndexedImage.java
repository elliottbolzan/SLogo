package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Jay Doherty
 * This class represents an image in the environment. The image can be referred to by its 
 * index and should be stored in an observable list that will be monitored by the front end
 * element that displays all of the images to the user: view/panel/TurtleImageView.java.
 */
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