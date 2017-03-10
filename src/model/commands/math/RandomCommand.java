package model.commands.math;

import java.util.Random;
import model.parser.Argument;

public class RandomCommand extends MathCommand {
	
	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument execute() {
		return new Argument(new Random().nextInt((int) getParameter(0).getDouble()));
	}
}
