package model.commands.logic;

import model.parser.Argument;

public class LessThanCommand extends LogicCommand {

	public LessThanCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public Argument execute() {
		return new Argument(super.booleanToInt(getParameter(0).getDouble() < getParameter(1).getDouble()));
	}
}
