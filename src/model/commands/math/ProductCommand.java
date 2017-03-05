package model.commands.math;

import model.commands.Command;
import model.parser.Argument;

public class ProductCommand extends Command {
	
	public ProductCommand() {
		super();
	}
	
	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public Argument execute() {
		return new Argument(getParameter(0).getDouble() * getParameter(1).getDouble());
	}
}
