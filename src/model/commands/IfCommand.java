package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class IfCommand extends LogicCommand {

	public IfCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return super.checker(parameters[1] != 0);
	}
}
