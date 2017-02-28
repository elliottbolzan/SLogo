package model.commands;

import controller.Controller;
import utils.Point;
import view.visualization.Turtle;

public abstract class TurtleCommand extends Command {

	protected TurtleCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	public abstract double execute(int[] parameters, Turtle turtle, Controller view);
	
    protected Point endLocation(int distance, Turtle t) {
    	double rad = Math.toRadians(t.getRotation());
        double x = (Math.cos(rad) * distance);
        double y = (Math.sin(rad) * distance);

        return new Point(t.getLocation().getX() + x, t.getLocation().getY() + y);
    }
    
    protected double distance(int[] parameters, Turtle t){
    	double x = parameters[0]-t.getLocation().getX();
		double y = parameters[1]-t.getLocation().getY();
		return Math.sqrt(x*x+y*y);
    }
}
