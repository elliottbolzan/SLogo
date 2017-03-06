package model.commands.math;

import java.util.Random;
import model.commands.Command;
import model.parser.Argument;

public class RandomCommand extends Command {
	
	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		return new Argument(new Random().nextInt((int) getParameter(0).getDouble()));
	}
}
