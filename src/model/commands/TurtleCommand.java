package model.commands;

import controller.Controller;
import view.Turtle;

public abstract class TurtleCommand extends Command {

	protected TurtleCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	protected abstract int calcValue(int[] parameters, Turtle turtle, Controller view);
}
