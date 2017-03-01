package model.commands.logic;

import controller.Controller;
import view.visualization.Turtle;

public class NotCommand extends LogicCommand {

	public NotCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	@Override
	public double execute(double[] parameters, Turtle myTurtle, Controller view) {
		return super.booleanToInt(parameters[0] == 0);
	}
	
	@Override
	public int numParameters() {
		return 1;
	}
}
