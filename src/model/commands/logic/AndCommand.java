package model.commands.logic;

import model.parser.Argument;

public class AndCommand extends LogicCommand {

	public AndCommand() {
		super();
	}

	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public Argument execute() {
		return new Argument((super.booleanToInt(getParameter(0).getDouble() != 0 && getParameter(1).getDouble() != 0)));
	}
}
