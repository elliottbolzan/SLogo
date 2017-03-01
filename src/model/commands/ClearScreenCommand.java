package model.commands;

import controller.Controller;
import utils.Point;
import view.visualization.Turtle;

public class ClearScreenCommand extends TurtleCommand {

	public ClearScreenCommand() {
		super();
	}

	public double execute(double[] parameters, Turtle myTurtle, Controller controller) {
		double distance = super.distance(myTurtle.getLocation(), new Point(0, 0));
		controller.moveTo(new Point(0, 0));
		controller.clearDisplay();
		return distance;
	}
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public double getReturnValue() {
		return super.distance(this.getController().getTurtle().getLocation(), new Point(0, 0));
	}

	@Override
	public void execute() {
		this.getController().moveTo(new Point(0, 0));
		this.getController().clearDisplay();
	}
}
