/**
 * 
 */
package view.visualization;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utils.Point;

/**
 * @author Elliott Bolzan
 *
 */
public class KeyHandler {
	
	private final static double MOVE_BY = 10;
	private Turtle selectedTurtle;

	/**
	 * 
	 */
	public KeyHandler() {

	}

	public Turtle getSelectedTurtle() {
		return selectedTurtle;
	}
	
	public void setSelectedTurtle(Turtle selectedTurtle) {
		this.selectedTurtle = selectedTurtle;
	}

	public void mouseClicked(MouseEvent e) {
		if (selectedTurtle != null && !(turtleContains(selectedTurtle, new Point(e.getX(), e.getY())))) {
			System.out.println("shrinking");
			selectedTurtle.shrink();
			selectedTurtle = null;
		}
	}

	public void keyPressed(KeyEvent e) {
		if (selectedTurtle != null && !(selectedTurtle.isMovingProperty().get())) {
			if (e.getCode() == KeyCode.LEFT) {
				selectedTurtle.getDisplay().turnTurtle(selectedTurtle.getID(), MOVE_BY);
			} else if (e.getCode() == KeyCode.RIGHT) {
				selectedTurtle.getDisplay().turnTurtle(selectedTurtle.getID(), -MOVE_BY);
			} else if (e.getCode() == KeyCode.UP) {
				selectedTurtle.getDisplay().moveTurtle(selectedTurtle.getID(), endLocation(MOVE_BY, selectedTurtle));
			} else if (e.getCode() == KeyCode.DOWN) {
				selectedTurtle.getDisplay().moveTurtle(selectedTurtle.getID(), endLocation(-MOVE_BY, selectedTurtle));
			}
		}
	}

	private boolean turtleContains(Turtle turtle, Point point) {
		return (turtle.getCurrentLocation().getX() < point.getX() && turtle.getCurrentLocation().getY() < point.getY()
				&& turtle.getCurrentLocation().getX() + turtle.getWidth() > point.getX()
				&& turtle.getCurrentLocation().getY() + turtle.getHeight() > point.getY());
	}
	
    protected Point endLocation(double parameters, Turtle selectedTurtle) {
    	double rad = Math.toRadians(selectedTurtle.getRotation());
        double x = (Math.cos(rad) * parameters);
        double y = (Math.sin(rad) * parameters);
        return new Point(selectedTurtle.getCurrentLocation().getX() + x, selectedTurtle.getCurrentLocation().getY() + y);
    }

}
