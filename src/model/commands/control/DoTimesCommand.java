package model.commands.control;

import model.Variable;
import model.commands.Command;
import model.parser.Argument;

public class DoTimesCommand extends Command {

	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public Argument execute(){
		Argument result = new Argument();
		String name = getChildren().get(0).getChildren().get(0).evaluate().getString();
		int limit = (int) getChildren().get(0).getChildren().get(1).evaluate().getDouble();
		for (double k = 0; k <= limit; k += 1) {
			getState().setVariable(new Variable(name, k));
			result = getParameter(1);
		}
		return result;
	}

}
