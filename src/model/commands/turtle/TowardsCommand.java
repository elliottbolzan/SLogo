package model.commands.turtle;

import controller.Controller;
import parser.Argument;
import view.visualization.Turtle;

public class TowardsCommand extends TurtleCommand {

	public TowardsCommand() {
		super();
	}
	
	public double execute(double[] parameters, Turtle myTurtle, Controller view){
		double xdiff = parameters[0] - myTurtle.getLocation().getX();
		double ydiff = parameters[1] - myTurtle.getLocation().getY();
		double tempd = Math.atan2(ydiff, xdiff);
		
		double degrees = Math.toDegrees(tempd) - myTurtle.getRotation();
		view.turn(degrees);
		return degrees;
	}
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public Argument getReturnValue() {
		double xdiff = this.getParameterList().get(0) - this.getController().getTurtle().getLocation().getX();
		double ydiff = this.getParameterList().get(1) - this.getController().getTurtle().getLocation().getY();
		double tempd = Math.atan2(ydiff, xdiff);
		 
		return (Math.toDegrees(tempd) - this.getController().getTurtle().getRotation());
	}

	@Override
	public Argument execute() {
		double xdiff = this.getParameterList().get(0) - this.getController().getTurtle().getLocation().getX();
		double ydiff = this.getParameterList().get(1) - this.getController().getTurtle().getLocation().getY();
		double tempd = Math.atan2(ydiff, xdiff);
		 
		double degrees = (Math.toDegrees(tempd) - this.getController().getTurtle().getRotation());
		this.getController().turn(degrees);
	}
}
