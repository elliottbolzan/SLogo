package model.commands.turtle;

import controller.Controller;
import utils.Point;
import view.visualization.Turtle;

public class HomeCommand extends TurtleCommand {

	public HomeCommand() {
		super();
	}

	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		view.moveTo(new Point(0, 0));
		return super.distance(new double[]{0, 0}, myTurtle);
	}
	
	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public double getReturnValue() {
		return super.distance(new double[]{0, 0}, this.getController().getTurtle());
	}

	@Override
	public void execute() {
		this.getController().moveTo(new Point(0 ,0));
	}
}
