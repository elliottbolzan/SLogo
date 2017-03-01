package model.commands.logic;

import controller.Controller;
import model.commands.Command;
import view.visualization.Turtle;

public abstract class LogicCommand extends Command {

	protected LogicCommand(int numParameters, String name) {
		super();
	}

	public abstract double execute(double[] parameters, Turtle myTurtle, Controller controller);

	protected int booleanToInt(boolean input) {
		return input ? 1 : 0;
	}
}
