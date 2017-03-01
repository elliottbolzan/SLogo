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
		store.setVariable(new Variable(varName, expr));
		return expr;
	}

	@Override
	public int getNumOfArguments() {
		return 2;
	}

	@Override
	protected double execute() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numParameters() {
		// TODO Auto-generated method stub
		return 0;
	}
}
