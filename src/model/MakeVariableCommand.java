package model;

import java.util.ArrayList;

public class MakeVariableCommand extends ControlCommand {

	protected MakeVariableCommand(ArrayList<ControlCommand> childList) {
		super(childList);
	}

	@Override
	public double execute(String name, String[] varList, String[] cmdList, double expr, StateStorage s) {
		s.setVariable(new Variable(name, expr));
		return expr;
	}

	@Override
	public int getNumOfArguments() {
		return 2;
	}
}
