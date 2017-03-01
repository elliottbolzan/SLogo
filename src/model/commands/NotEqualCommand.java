package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class NotEqualCommand extends LogicCommand {

	public NotEqualCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return super.booleanToInt(parameters[0] != parameters[1]);
	}
}
