package model.commands;

import controller.Controller;
import view.visualization.Turtle;

public class OrCommand extends LogicCommand {

	public OrCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return super.checker((parameters[0] != 0) || (parameters[1] != 0));
	}
}
