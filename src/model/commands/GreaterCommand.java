package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class GreaterCommand extends LogicCommand {

	public GreaterCommand(int numParameters, String name) {
		super(2, name);
	}

	@Override
	protected double calcValue(double[] parameters, Turtle myTurtle, Controller view) {
		return super.checker(parameters[0] > parameters[1]);
	}
}
