package view;

import java.util.ResourceBundle;

import controller.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Elliott Bolzan
 *
 */
public class WorkspaceBrowser extends BorderPane {

	private Stage stage;
	private TabPane tabPane;
	private int workspaces = 0;
	private ResourceBundle resources = ResourceBundle.getBundle("resources/UserInterface");
	private String stylesheetPath = "resources/style.css";

	/**
	 * 
	 */
	public WorkspaceBrowser(Stage stage) {
		this.stage = stage;
		setupStage();
		newWorkspace();
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	getCurrentWorkspace().getShell().getTextArea().requestFocus();
		    }   
		});
	}

	private void setupStage() {
		stage.setTitle(resources.getString("SLogoTitle"));
		stage.setMinWidth(600);
		stage.setMinHeight(300);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				System.exit(0);
			}
		});
		
		stage.setScene(createScene());
		stage.show();

		tabPane = new TabPane();

		HBox hbox = new HBox();
		hbox.getChildren().addAll(createTabButton("view/visualization/new.png", e -> newWorkspace()),
				createTabButton("view/visualization/help.png", e -> showHelp()));

		AnchorPane anchor = new AnchorPane();
		anchor.getChildren().addAll(tabPane, hbox);
		AnchorPane.setTopAnchor(hbox, 3.0);
		AnchorPane.setRightAnchor(hbox, 5.0);
		AnchorPane.setTopAnchor(tabPane, 1.0);
		AnchorPane.setRightAnchor(tabPane, 1.0);
		AnchorPane.setLeftAnchor(tabPane, 1.0);
		AnchorPane.setBottomAnchor(tabPane, 1.0);

		setCenter(anchor);
		
	}

	private Scene createScene() {
		Scene scene = new Scene(this, 1000, 480);
		scene.getStylesheets().add(stylesheetPath);
		return scene;
	}
	
	private void hello() {
		System.out.println("called");
	}
	
	private Workspace getCurrentWorkspace() {
    	return (Workspace) tabPane.getSelectionModel().getSelectedItem().getContent();  
	}

	private void newWorkspace() {
		workspaces++;
		Controller controller = new Controller(this);
		Tab tab = new Tab();
		tab.setText("Workspace " + String.valueOf(workspaces));
		tab.setContent(controller.getView());
		tab.setOnCloseRequest(e -> handleClose(e, controller));
		tabPane.getTabs().add(tab);
		tabPane.getSelectionModel().select(tab);
	}

	private void handleClose(Event e, Controller controller) {
		if (workspaces > 1) {
			workspaces--;
		} else {
			controller.showMessage(resources.getString("OneTabRequirement"));
			e.consume();
		}
	}

	private Button createTabButton(String path, EventHandler<ActionEvent> action) {
		ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(path)));
		imageView.setFitHeight(20);
		imageView.setFitWidth(20);
		Button button = new Button("", imageView);
		button.setOnAction(action);
		button.getStyleClass().add("tab-button");
		button.setMinWidth(Region.USE_PREF_SIZE);
		return button;
	}

	private void showHelp() {
		HelpView helpView = new HelpView(resources);
		helpView.show();
	}

}
