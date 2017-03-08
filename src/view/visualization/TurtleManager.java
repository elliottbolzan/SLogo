/**
 * 
 */
package view.visualization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import utils.Point;

/**
 * @author Elliott Bolzan
 *
 */
public class TurtleManager {

	public interface Runnable {
		public double run(Turtle turtle);
	}

	private HashMap<Integer, Turtle> turtles;
	private ObservableList<Turtle> activeTurtles;
	private TurtleDisplay display;
	private int initialTurtles;
	private Turtle currentTurtle;

	/**
	 * 
	 */
	public TurtleManager(int initialTurtles, TurtleDisplay display) {
		this.initialTurtles = initialTurtles;
		this.display = display;
		clear();
	}

	private double applyToTurtles(Runnable runnable) {
		double result = 0;
		for (Turtle turtle : activeTurtles) {
			currentTurtle = turtle;
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
			if (!(indices.contains(ID))) {
				setTurtleActive(ID, false);
			}
		}
	}

	public void setTurtleActive(int ID, boolean active) {
		if (turtles.containsKey(ID)) {
			Turtle turtle = turtles.get(ID);
			if (active && !(activeTurtles.contains(turtle))) {
				activeTurtles.add(turtle);
			} else if (!active && activeTurtles.contains(turtle)) {
				activeTurtles.remove(turtle);
			}
		} else {
			Turtle turtle = new Turtle(display, ID);
			turtles.put(ID, turtle);
			activeTurtles.add(turtle);
			addTurtle(turtle);
		}
	}

	public Turtle getCurrentTurtle() {
		return currentTurtle;
	}

	private void addTurtle(Turtle turtle) {
		display.addToDisplayArea(turtle.getView());
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
				}
			}
			return 0;
		});
	}

	public void setPenDown(boolean down) {
		applyToTurtles(turtle -> {
			turtle.setPenDown(down);
			return 0;
		});
	}

	public void setPenColor(Color color) {
		applyToTurtles(turtle -> {
			turtle.setPenColor(color);
			return 0;
		});
	}

	public void setTurtleVisible(boolean visible) {
		applyToTurtles(turtle -> {
			turtle.getView().setVisible(visible);
			return 0;
		});
	}

	public void setTurtleImage(String url) {
		applyToTurtles(turtle -> {
			turtle.setImage(url);
			return 0;
		});
	}

	public void turnTurtle(double degrees) {
		applyToTurtles(turtle -> {
			turtle.setRotation(turtle.getRotation() + degrees);
			return 0;
		});
	}

	public void verticalMove(double amount) {
		applyToTurtles(turtle -> {
			display.moveTurtle(turtle, endLocation(amount, turtle));
			return 0;
		});
	}

	public void moveTo(Point point) {
		applyToTurtles(turtle -> {
			display.moveTurtle(turtle, point);
			return 0;
		});
	}

	public double setHeading(double degrees) {
		return applyToTurtles(turtle -> {
			double result = degrees - turtle.getRotation();
			turtle.turn(degrees - turtle.getRotation());
			return result;
		});
	}
	
	public double towards(double input) {
		return applyToTurtles(turtle -> {
			double result = Math.toDegrees(input) - turtle.getRotation();
			turtle.turn(result);
			return result;
		});
	}

	private Point endLocation(double value, Turtle turtle) {
		double rad = Math.toRadians(turtle.getRotation());
		double x = (Math.cos(rad) * value);
		double y = (Math.sin(rad) * value);
		return new Point(turtle.getDestination().getX() + x, turtle.getDestination().getY() + y);
	}

}
