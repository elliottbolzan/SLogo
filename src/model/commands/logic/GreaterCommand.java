package model.commands.logic;

import controller.Controller;
import view.visualization.Turtle;

public class GreaterCommand extends LogicCommand {

	public GreaterCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return super.booleanToInt(parameters[0] > parameters[1]);
	}
	
	@Override
	public int numParameters() {
		return 2;
	}
}
