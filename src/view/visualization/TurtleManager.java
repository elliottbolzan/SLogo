/**
 * 
 */
package view.visualization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * @author Elliott Bolzan
 * This class manages multiple turtles and handles simultaneously animating all of the turtles. 
 */
public class TurtleManager {

	public interface Runnable {
		public void run(Turtle turtle);
	}

	private Map<Integer, Turtle> turtles;
	private ObservableList<Turtle> activeTurtles;
	private TurtleDisplay display;
	private int initialTurtles;
	private Turtle currentTurtle;
	private Image turtleImage;

	/**
	 * 
	 */
	public TurtleManager(int initialTurtles, TurtleDisplay display, Image turtleImage) {
		this.initialTurtles = initialTurtles;
		this.display = display;
		this.turtleImage = turtleImage;
		clear();
	}

	public ObservableList<Turtle> getActiveTurtles() {
		return activeTurtles;
	}

	public Map<Integer, Turtle> getAllTurtles() {
		return turtles;
	}
	
	public Turtle getTurtleByID(int ID) {
		return turtles.get(ID);
	}

	public Turtle getCurrentTurtle() {
		return currentTurtle;
	}

	public void setCurrentTurtle(Turtle turtle) {
		currentTurtle = turtle;
	}

	private void applyToTurtles(Runnable runnable) {
		for (Turtle turtle : activeTurtles) {
			runnable.run(turtle);
		}
	}

	private void createInitialTurtles() {
		for (int i = 1; i < initialTurtles + 1; i++) {
			setTurtleActive(i, true);
		}
	}

	public void setActiveTurtles(List<Integer> indices) {
		for (Integer ID : indices) {
			setTurtleActive(ID, true);
		}
		for (Integer ID : turtles.keySet()) {
			setTurtleActive(ID, indices.contains(ID));
		}
	}

	private void setTurtleActive(int ID, boolean active) {
		if (turtles.containsKey(ID)) {
			Turtle turtle = turtles.get(ID);
			if (active && !(activeTurtles.contains(turtle))) {
				turtle.activeProperty().set(true);
			} else if (!active && activeTurtles.contains(turtle)) {
				turtle.activeProperty().set(false);
			}
		} else {
			createTurtle(ID);
		}
	}
	
	private void createTurtle(int ID) {
		Turtle turtle = new Turtle(display, ID, turtleImage);
		turtles.put(ID, turtle);
		activeTurtles.add(turtle);
		turtle.activeProperty().set(true);
		turtle.activeProperty().addListener(e -> this.onTurtleActivityModified(turtle));
		addTurtle(turtle);
	}

	private void addTurtle(Turtle turtle) {
		display.addToDisplayArea(turtle.getView());
		currentTurtle = turtle;
	}

	protected void clear() {
		turtles = new HashMap<Integer, Turtle>();
		activeTurtles = FXCollections.observableArrayList(new ArrayList<Turtle>());
		createInitialTurtles();
	}

	protected void stepTurtles() {
		applyToTurtles(turtle -> {
			if (turtle.isMovingProperty().get()) {
				turtle.updateMovement();
			} else {
				if (turtle.getSchedule().hasAnotherDestination()) {
					display.moveTurtle(turtle, turtle.getSchedule().pollFutureDestination());
					if (!display.animationIsPlaying()) {
						display.stopAnimation();
					}
				}
			}
		});
	}

	public void setTurtleImage(int imageIndex, String URL) {
		applyToTurtles(turtle -> {
			turtle.setImage(URL);
			turtle.setShapeIndex(imageIndex);
		});
	}

	private void onTurtleActivityModified(Turtle turtle) {
		if (!turtle.activeProperty().get()) {
			activeTurtles.remove(turtle);
		} else {
			activeTurtles.add(turtle);
		}
	}
}
