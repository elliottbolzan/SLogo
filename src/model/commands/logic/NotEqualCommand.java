package model.commands.logic;

import model.parser.Argument;

public class NotEqualCommand extends LogicCommand {

	public NotEqualCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public Argument execute() {
		return new Argument(super.booleanToInt(getParameter(0).getDouble() != getParameter(1).getDouble()));
	}
}
