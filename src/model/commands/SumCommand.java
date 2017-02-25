package model.commands;

public class SumCommand extends MathCommand {
	
	public SumCommand(int numParameters, String name) {
		super(numParameters, name);
	}

	public int calcValue(int[] parameters){
		return parameters[0] + parameters[1];
	}
}
