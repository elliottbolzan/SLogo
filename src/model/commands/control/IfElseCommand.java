package model.commands.control;

import controller.Controller;
import model.commands.logic.LogicCommand;
import view.visualization.Turtle;

public abstract class IfElseCommand extends LogicCommand {

	public IfElseCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return super.booleanToInt(parameters[0] != 0);
	}
}
