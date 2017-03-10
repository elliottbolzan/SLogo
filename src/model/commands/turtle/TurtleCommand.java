package model.commands.turtle;

import model.commands.Command;
import model.parser.Argument;
import utils.Point;
import view.visualization.Turtle;

public abstract class TurtleCommand extends Command {

    protected Turtle getTurtle() {
    	return getController().getTurtleManager().getCurrentTurtle();
    }    
    
	protected void setTurtle(Turtle turtle) {
		getController().getTurtleManager().setCurrentTurtle(turtle);
	}
	
    protected Point endLocation(double parameters, Turtle t) {
    	double rad = Math.toRadians(t.getRotation());
        double x = (Math.cos(rad) * parameters);
        double y = (Math.sin(rad) * parameters);
        return new Point(t.getDestination().getX() + x, t.getDestination().getY() + y);
    }
 
    protected double distance(Point point1, Point point2) {
    	return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
    }
    
    protected abstract Argument execute();
    
}
