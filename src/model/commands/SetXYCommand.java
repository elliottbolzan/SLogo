package model.commands;

import controller.Controller;
import utils.Point;
import view.visualization.Turtle;

public class SetXYCommand extends TurtleCommand {

	public SetXYCommand() {
		super();
	}

	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		double distance = super.distance(parameters, myTurtle);
		Point loc = new Point(parameters[0], parameters[1]);
		view.moveTo(loc);
		return distance;
	}
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public double getReturnValue() {
		return super.distance(new double[] {this.getParameterList().get(0), this.getParameterList().get(1)}, this.getController().getTurtle());
	}

	@Override
	public void execute() {
		Point loc = new Point(this.getParameterList().get(0), this.getParameterList().get(1));
		this.getController().moveTo(loc);
	}
}
