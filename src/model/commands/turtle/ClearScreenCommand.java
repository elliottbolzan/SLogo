package model.commands.turtle;

import controller.Controller;
import utils.Point;
import view.visualization.Turtle;

public class ClearScreenCommand extends TurtleCommand {

	public ClearScreenCommand(int numParameters, String name) {
		super(numParameters, name);
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
}
