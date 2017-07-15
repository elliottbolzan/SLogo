package view.panel;

import java.io.File;
import java.net.MalformedURLException;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import view.Workspace;
import view.visualization.Turtle;

/**
 * 
 * This class is a view that displays a table of Turtles. Each turtle is
 * represented by its ID and its current image in the table. By clicking a
 * Turtle's image, the user can pick a new one from file. The Turtle image then
 * updates, both in the table and on the Canvas.
 * 
 * @author Elliott Bolzan
 *
 */
public class CurrentTurtleImages extends BorderPane {

	private TableView<Turtle> table;
	private Workspace workspace;

	/**
	 * Creates a CurrentTurtleImages.
	 * 
	 * @param workspace
	 *            the Workspace that owns this view.
	 */
	public CurrentTurtleImages(Workspace workspace) {
		this.workspace = workspace;
		setupView(workspace.getTurtleManager().getObservableTurtles());
	}

	/**
	 * Creates the TableView.
	 * 
	 * @param turtles
	 *            a reference to an ObservableList<Turtle> representing the
	 *            Turtles.
	 */
	private void setupView(ObservableList<Turtle> turtles) {
		table = new TableView<Turtle>();
		table.setPrefHeight(200);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setEditable(false);
		table.getStyleClass().add("panel-table");
		table.prefHeightProperty().bind(heightProperty());
		TableColumn<Turtle, Integer> indexColumn = makeIDColumn();
		TableColumn<Turtle, ImageView> imageColumn = makeImageColumn();
		table.setItems(turtles);
		table.getColumns().add(indexColumn);
		table.getColumns().add(imageColumn);
		table.setOnMouseClicked(e -> rowClicked());
		setCenter(table);
	}

	/**
	 * Creates the TableColumn for Turtle images. A new ImageView is
	 * instantiated from the Turtle's Graphic, so as to not "steal" the
	 * ImageView from the Turtle and prevent it from being displayed in two
	 * places.
	 * 
	 * @return a TableColumn representing the Turtle image.
	 */
	private TableColumn<Turtle, ImageView> makeImageColumn() {
		TableColumn<Turtle, ImageView> imageColumn = new TableColumn<Turtle, ImageView>("Image");
		imageColumn.setCellValueFactory(e -> new SimpleObjectProperty<ImageView>(e.getValue().getView()));
		imageColumn.setCellFactory(column -> {
			return new TableCell<Turtle, ImageView>() {
				@Override
				protected void updateItem(ImageView item, boolean empty) {
					super.updateItem(new ImageView(), empty);
					setGraphic(null);
					if (item != null && !empty) {
						ImageView view = new ImageView(item.getImage());
						view.setFitHeight(20);
						view.setFitWidth(20);
						setPadding(new Insets(10));
						setGraphic(view);
					}
				}
			};
		});
		return imageColumn;
	}

	/**
	 * @return a TableColumn representing the Turtles' ID.
	 */
	private TableColumn<Turtle, Integer> makeIDColumn() {
		TableColumn<Turtle, Integer> indexColumn = new TableColumn<Turtle, Integer>("Turtle ID");
		indexColumn.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getID()).asObject());
		return indexColumn;
	}

	/**
	 * A method triggered on a row click by the user. Displays a FileChooser. In
	 * the event of a result, calls setTurtleImage() with the appropriate path.
	 */
	private void rowClicked() {
		FileChooser myChooser = new FileChooser();
		myChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/src/resources/images"));
		myChooser.getExtensionFilters().setAll(new ExtensionFilter(
				workspace.getController().getResources().getString("PictureFilesLabel"), "*.png", "*.jpg", "*.gif"));
		File dataFile = myChooser.showOpenDialog(getScene().getWindow());
		if (dataFile != null) {
			try {
				setTurtleImage(dataFile.toURI().toURL().toExternalForm());
			} catch (MalformedURLException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.show();
			}
		}
	}

	/**
	 * Sets the Turtle's image to a new one.
	 * 
	 * @param path
	 *            the path to the new image.
	 */
	private void setTurtleImage(String path) {
		Turtle turtle = table.getSelectionModel().getSelectedItem();
		turtle.setImage(path);
		refresh();
	}

	/**
	 * Refreshes the TableView (takes care of a JavaFX bug which causes
	 * TableViews to not refresh until the user hovers over the table).
	 */
	private void refresh() {
		table.refresh();
		table.getColumns().get(1).setVisible(false);
		table.getColumns().get(1).setVisible(true);
	}

}
