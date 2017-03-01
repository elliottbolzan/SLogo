package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class NotCommand extends LogicCommand {

	public NotCommand(int numParameters, String name) {
		super(1, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return super.booleanToInt(parameters[0] == 0);
	}
}
