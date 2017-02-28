package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class LessCommand extends LogicCommand {

	public LessCommand(int numParameters, String name) {
		super(2, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return super.checker(parameters[0] < parameters[1]);
	}
}
