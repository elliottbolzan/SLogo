package model.commands;

import controller.Controller;
import utils.Point;
import view.Turtle;

public abstract class TurtleCommand extends Command {

	protected TurtleCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	protected abstract double calcValue(int[] parameters, Turtle turtle, Controller view);
	
    protected Point endLocation(int distance, Turtle t) {
    	double rad = Math.toRadians(t.getRotation());
        double x = (Math.cos(rad) * distance);
        double y = (Math.sin(rad) * distance);

        return new Point(t.getLocation().getX() + x, t.getLocation().getY() + y);
    }
}
