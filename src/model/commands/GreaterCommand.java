package model.commands;

import controller.Controller;
import view.Turtle;

public class GreaterCommand extends LogicCommand {

	public GreaterCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	protected double calcValue(int[] parameters, Turtle myTurtle, Controller view) {
		return super.checker(parameters[0] > parameters[1]);
	}
}
