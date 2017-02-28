package view;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FilePicker extends Group {

	private TextField myTextField;
	
	public FilePicker(Stage primaryStage, int width, String initialText, String buttonLabel, String... allowedFileExtensions) {
		myTextField = new TextField();
		
		myTextField.setText(initialText);
		myTextField.setEditable(false);
		myTextField.setPrefWidth(width*0.6);
		
		HBox container = new HBox();
		
		Button browseButton = new Button("Browse");
		browseButton.setOnAction(e -> this.launchChooser(primaryStage, allowedFileExtensions));
		
		container.setPrefWidth(width);
		
		container.getChildren().addAll(myTextField, browseButton);
		this.getChildren().add(container);
	}
	
	public TextField getTextField() {
		return myTextField;
	}
	
	private void launchChooser(Stage stage, String... extensions) {
		FileChooser myChooser = new FileChooser();
		myChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		myChooser.getExtensionFilters().setAll(new ExtensionFilter("Picture Files", extensions));
		File dataFile = myChooser.showOpenDialog(stage);
		if(dataFile != null) {
			try {
				myTextField.setText(dataFile.toURI().toURL().toExternalForm());
			} catch  (MalformedURLException e) {
				//Don't use file
			}
		}
	}
}
