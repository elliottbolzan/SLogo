package model.commands.logic;

import model.parser.Argument;

public class NotCommand extends LogicCommand {

	public NotCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		return new Argument(super.booleanToInt(getParameter(0).getDouble() == 0));
	}
}
