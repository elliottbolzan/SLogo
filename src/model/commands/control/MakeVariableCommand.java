package model.commands.control;

import model.StateStorage;
import model.Variable;
import model.commands.Command;

public class MakeVariableCommand extends Command {
	
	private String name;
	private StateStorage stateStorage;
	
	public void initialize(String name, StateStorage stateStorage) {
		this.name = name;
		this.stateStorage = stateStorage;
	}

	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public double getReturnValue() {
		return getParameterList().get(0);
	}

	@Override
	public void execute() {
		stateStorage.setVariable(new Variable(name, getParameterList().get(0)));
	}

}
