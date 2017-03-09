/**
 * 
 */
package view.visualization;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * @author Elliott Bolzan
 *
 */
public class History {
	
	List<Pane> panes = new ArrayList<Pane>();
	
	public void add(Pane pane) {
		panes.add(pane);
	}
	
	public Pane returnToPrevious() {
		System.out.println(panes.size());
		System.out.println(panes.toString());
		panes.remove(panes.size() - 1);
		Pane pane = panes.get(0);
		pane.getChildren().add(new Label("Test"));
		return pane;
	}

}
