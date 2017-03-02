package model.commands.control;

import java.util.ArrayList;

import model.StateStorage;
import model.Variable;

public class MakeVariableCommand extends ControlCommand {
	
	private String name;
	private StateStorage stateStorage;

	public MakeVariableCommand() {
		super(new ArrayList<ControlCommand>());
	}
	
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
