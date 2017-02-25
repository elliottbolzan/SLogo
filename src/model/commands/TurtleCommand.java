package model.commands;

public abstract class TurtleCommand extends Command {

	protected TurtleCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	protected abstract int calcValue(int[] parameters, Turtle turtle);
}
