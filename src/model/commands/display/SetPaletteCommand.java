package model.commands.display;

import model.commands.turtle.TurtleCommand;
import model.parser.Argument;

public class SetPaletteCommand extends TurtleCommand{
	public SetPaletteCommand(){
		super();
	}

	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		return null;
	}
	
}