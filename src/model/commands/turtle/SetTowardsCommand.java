package model.commands.turtle;

import model.parser.Argument;

public class SetTowardsCommand extends TurtleCommand {

	public SetTowardsCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public Argument execute() {
		double xdiff = getParameter(0).getDouble() - this.getController().getTurtle().getLocation().getX();
		double ydiff = getParameter(1).getDouble() - this.getController().getTurtle().getLocation().getY();
		double tempd = Math.atan2(ydiff, xdiff);
		 
		double degrees = (Math.toDegrees(tempd) - this.getController().getTurtle().getRotation());
		this.getController().turn(degrees);
		return new Argument(degrees);
	}
}
