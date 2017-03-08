package model.commands.multiple;

import model.commands.Command;
import model.parser.Argument;

public class IDCommand extends Command{
	
	public IDCommand(){
		super();
	}

	@Override
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument execute() {
		return new Argument(0);
	}
	
}