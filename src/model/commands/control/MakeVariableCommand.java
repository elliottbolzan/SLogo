package model.commands.control;

import java.util.ArrayList;

import model.StateStorage;
import model.Variable;

public class MakeVariableCommand extends ControlCommand {
	String varName;
	StateStorage store;

	protected MakeVariableCommand(String var, StateStorage s) {
		super(new ArrayList<ControlCommand>());
		varName = var;
		store = s;
	}

	@Override
	public double execute() {
		//store.setVariable(new Variable(varName, expr));
		return 0;
	}



	@Override
	public int numParameters() {
		return 2;
	}
}
