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
 * 
 *         This class keeps track of multiple turtles. It maintains a list of
 *         active turtles, and allows for the modifying of the state of all of
 *         the active turtles at once, using lambda expresions.
 */
public class TurtleManager {

	/**
	 * An interface used to reduce the looping through multiple turtles in this
	 * class. One method provides a looping mechanism, and the interior of the
	 * loop is provided through the run(Turtle turtle) method in this interface.
	 */
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
	 * Returns a TurtleManager.
	 * 
	 * @param initialTurtles
	 *            the number of initial turtles.
	 * @param display
	 *            the TurtleDisplay these turtles lie on.
	 * @param turtleImage
	 *            the image for these turtles.
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

	/**
	 * Apply a change to every active turtle.
	 * 
	 * @param runnable
	 *            the action that is to be applied to every Turtle.
	 */
	private void applyToTurtles(Runnable runnable) {
		for (Turtle turtle : activeTurtles) {
			runnable.run(turtle);
		}
	}

	/**
	 * Where the creation of the turtles takes place.
	 */
	private void createInitialTurtles() {
		for (int i = 1; i < initialTurtles + 1; i++) {
			setTurtleActive(i, true);
		}
	}

	/**
	 * Set the current active turtles.
	 * 
	 * @param indices
	 *            the indices of the turtles that are set to become active.
	 */
	public void setActiveTurtles(List<Integer> indices) {
		for (Integer ID : indices) {
			setTurtleActive(ID, true);
		}
		for (Integer ID : turtles.keySet()) {
			setTurtleActive(ID, indices.contains(ID));
		}
	}

	/**
	 * Set the status of one specific turtle.
	 * 
	 * @param ID
	 *            the ID of the turtle.
	 * @param active
	 *            whether it should be active or not.
	 */
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

	/**
	 * Create a turtle with a specific ID.
	 * 
	 * @param ID
	 *            the ID of the created turtle.
	 */
	private void createTurtle(int ID) {
		Turtle turtle = new Turtle(display, ID, turtleImage);
		turtles.put(ID, turtle);
		activeTurtles.add(turtle);
		turtle.activeProperty().set(true);
		turtle.activeProperty().addListener(e -> this.onTurtleActivityModified(turtle));
		addTurtle(turtle);
	}

	/**
	 * Add a turtle to the TurtleDisplay.
	 * 
	 * @param turtle
	 *            the turtle to be added to the display.
	 */
	private void addTurtle(Turtle turtle) {
		display.addToDisplayArea(turtle.getView());
		currentTurtle = turtle;
	}

	/**
	 * Clear the list of turtles.
	 */
	protected void clear() {
		turtles = new HashMap<Integer, Turtle>();
		activeTurtles = FXCollections.observableArrayList(new ArrayList<Turtle>());
		createInitialTurtles();
	}

	/**
	 * Step the turtles through one time interval in their animation.
	 */
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

	/**
	 * Set the image for all the active turtles.
	 * 
	 * @param imageIndex
	 *            the index of the image they must be set to.
	 * @param URL
	 *            the URL of the image they must be set to.
	 */
	public void setTurtleImage(int imageIndex, String URL) {
		applyToTurtles(turtle -> {
			turtle.setImage(URL);
			turtle.setShapeIndex(imageIndex);
		});
	}

	/**
	 * Listen in on the turtle's activity property and modify the activeTurtles
	 * variable accordingly.
	 * 
	 * @param turtle
	 *            the turtle who's property is being modified.
	 */
	private void onTurtleActivityModified(Turtle turtle) {
		if (!turtle.activeProperty().get()) {
			activeTurtles.remove(turtle);
		} else {
			activeTurtles.add(turtle);
		}
	}
}
