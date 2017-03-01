package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class EqualCommand extends LogicCommand {

	public EqualCommand(int numParameters, String name) {
		super(2, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return super.booleanToInt(parameters[0] == parameters[1]);
	}
}
