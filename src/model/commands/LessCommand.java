package model.commands;

public abstract class LessCommand extends LogicCommand {

	public LessCommand(int numParameters, String name) {
		super(numParameters, name);
	}
	
	public int calcValue(int[] parameters){
		return super.checker(parameters[0] == parameters[1]);
	}
}
