package model.commands;

import controller.Controller;
import view.Turtle;

public class AndCommand extends LogicCommand {

	public AndCommand(int numParameters, String name) {
		super(1, name);
	}

	@Override
	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view) {
		return super.checker((parameters[0] != 0) && (parameters[1] != 0));
	}
}
