package model;

import controller.Controller;
import model.commands.LogicCommand;
import view.visualization.Turtle;

public class IfElseCommand extends LogicCommand {

	public IfElseCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return super.checker(parameters[0] != 0);
	}
}
