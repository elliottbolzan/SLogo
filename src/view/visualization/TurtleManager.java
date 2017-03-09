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
import javafx.scene.paint.Color;

/**
 * @author Elliott Bolzan
 *
 */
public class TurtleManager {

	public interface Runnable {
		public double run(Turtle turtle);
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
	
	public Turtle getCurrentTurtle() {
		return currentTurtle;
	}
	
	public void setCurrentTurtle(Turtle turtle) {
		currentTurtle = turtle;
	}

	public Turtle getTurtleByID(int ID) {
		return turtles.get(ID);
	}
	
	private double applyToTurtles(Runnable runnable) {
		double result = 0;
		for (Turtle turtle : activeTurtles) {
			result = runnable.run(turtle);
		}
		return result;
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
			Turtle turtle = new Turtle(display, ID, turtleImage);
			turtles.put(ID, turtle);
			activeTurtles.add(turtle);
			turtle.activeProperty().set(true);
			turtle.activeProperty().addListener(e -> this.onTurtleActivityModified(turtle));
			addTurtle(turtle);
		}
	}

	public Map<Integer,Turtle> getTurtles(){
		return turtles;
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
				if (turtle.hasAnotherDestination()) {
					display.moveTurtle(turtle, turtle.pollFutureDestination());
					if(!display.animationIsPlaying()) {
						display.stopAnimation();
					}
				}
			}
			return 0;
		});
	}
	
	public void setTurtleImage(int imageIndex, String URL) {
		applyToTurtles(turtle -> {
			turtle.setImage(URL);
			turtle.setShapeIndex(imageIndex);
			return 0;
		});
	}
	
	public void setTurtlePenColor(int colorIndex, Color color) {
		applyToTurtles(turtle -> {
			turtle.setPenColor(color);
			turtle.setColorIndex(colorIndex);
			return 0;
		});
	}
	
	public void setTurtlePenSize(double width) {
		applyToTurtles(turtle -> {
			turtle.setPenWidth(width);
			return 0;
		});
	}
	
	private void onTurtleActivityModified(Turtle turtle) {
		if(!turtle.activeProperty().get()) {
			activeTurtles.remove(turtle);
		} else {
			activeTurtles.add(turtle);
		}
	}
}
