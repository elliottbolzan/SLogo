package model.commands;

import controller.Controller;
import utils.Point;
import view.Turtle;

public class FowardCommand extends TurtleCommand {

	public FowardCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	protected int calcValue(int[] parameters, Turtle myTurtle, Controller view){
		Point loc = endLocation(parameters[0], myTurtle);
		view.moveTo(loc);
		return parameters[0];
	}
	
    private Point endLocation(int distance, Turtle t) {
    	double rad = Math.toRadians(t.getRotation());
        double x = (Math.cos(rad) * distance);
        double y = (Math.sin(rad) * distance);

        return new Point(t.getLocation().getX() + x, t.getLocation().getY() + y);
    }

}
