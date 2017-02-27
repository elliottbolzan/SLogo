package model.commands;

import controller.Controller;
import view.Turtle;

public class NotEqualCommand extends LogicCommand {

	public NotEqualCommand(int numParameters, String name) {
		super(2, name);
	}

	@Override
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view) {
		return super.checker(parameters[0] != parameters[1]);
	}
}
