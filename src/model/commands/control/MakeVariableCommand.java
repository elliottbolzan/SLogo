package model.commands.control;

import model.Variable;
import model.commands.Command;
import model.parser.Argument;

public class MakeVariableCommand extends Command {
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public Argument execute() {
		getState().setVariable(new Variable(getParameter(0).getString(), getParameter(1).getDouble()));
		return getParameter(1);
	}

}
