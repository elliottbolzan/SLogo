package model.commands.control;

import model.Variable;
import model.commands.Command;
import model.parser.Argument;

public class ForCommand extends Command {

	@Override
	protected int internalNumParameters() {
		return 2;
	}

	@Override
	protected Argument execute(){
		Argument result = new Argument();
		String name = getChildren().get(0).getChildren().get(0).evaluate().getString();
		int start = (int) getChildren().get(0).getChildren().get(1).evaluate().getDouble();
		int end = (int) getChildren().get(0).getChildren().get(2).evaluate().getDouble();
		int increment = (int) getChildren().get(0).getChildren().get(3).evaluate().getDouble();
		for (double k = start; k <= end; k += increment) {
			getState().setVariable(new Variable(name, k));
			result = getParameter(1);
		}
		return result;
	}

}
