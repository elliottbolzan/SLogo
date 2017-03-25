package view.components;

import java.io.File;
import java.net.MalformedURLException;

import controller.Controller;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Jay Doherty
 * This class makes a UI element for choosing a file and displays the name of the file afterwards.
 */
public class FilePicker extends Group {

	private Controller controller;
	private TextField myTextField;
	private String myURL = "";

	public FilePicker(Controller controller, int width, String initialText, File directory,
			String... allowedFileExtensions) {

		this.controller = controller;
		myTextField = new TextField();

		myTextField.setText(initialText);
		myTextField.setEditable(false);
		myTextField.setPrefWidth(width * 0.6);

		HBox container = new HBox();

		Button browseButton = new Button(controller.getResources().getString("BrowseButton"));
		browseButton.setOnAction(e -> this.launchChooser(directory, allowedFileExtensions));

		container.setPrefWidth(width);

		container.getChildren().addAll(myTextField, browseButton);
		this.getChildren().add(container);
	}

	/**
	 * @return the full path of the selected file
	 */
	public String getURL() {
		return myURL;
	}
	
	/**
	 * @return the JavaFX element showing the selected file name
	 */
	public TextField getTextField() {
		return myTextField;
	}

	private void launchChooser(File directory, String... extensions) {
		FileChooser myChooser = new FileChooser();
		myChooser.setInitialDirectory(directory);
		myChooser.getExtensionFilters()
				.setAll(new ExtensionFilter(controller.getResources().getString("PictureFilesLabel"), extensions));
		File dataFile = myChooser.showOpenDialog(getScene().getWindow());
		if (dataFile != null) {
			try {
				myURL = dataFile.toURI().toURL().toExternalForm();
				myTextField.setText(myURL.substring(myURL.lastIndexOf("/") + 1));
			} catch (MalformedURLException e) {
				// Don't use file
			}
		}
	}
	
}
