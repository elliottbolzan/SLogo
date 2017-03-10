package model.commands.control;

import model.Variable;
import model.commands.Command;
import model.parser.Argument;

public class RepeatCommand extends Command {

	@Override
	protected int internalNumParameters() {
		return 2;
	}

	@Override
	protected Argument execute(){
		Argument result = new Argument();
		int repeats = (int) getParameter(0).getDouble();
		for (int k = 1; k <= repeats; k++) {
			getState().setVariable(new Variable("repcount", k));
			result = getParameter(1);
		}
		return result;
	}

}
