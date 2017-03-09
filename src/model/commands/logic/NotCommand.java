package model.commands.logic;

import model.parser.Argument;

public class NotCommand extends LogicCommand {

	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument execute() {
		return new Argument(super.booleanToInt(getParameter(0).getDouble() == 0));
	}
}
