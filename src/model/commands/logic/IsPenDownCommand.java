package model.commands.logic;

import model.parser.Argument;

public class IsPenDownCommand extends LogicCommand {
	
	@Override
	protected int internalNumParameters() {
		return 0;
	}

	@Override
	protected Argument execute() {
		return new Argument(super.booleanToInt(this.getController().getTurtleManager().getCurrentTurtle().isPenDown()));
	}
}
