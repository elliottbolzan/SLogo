package model.commands.display;

import model.commands.turtle.TurtleCommand;
import model.parser.Argument;

public class SetPenSizeCommand extends TurtleCommand{
	public SetPenSizeCommand(){
		super();
	}

	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		int width = (int)this.getParameter(0).getDouble();
		getController().getTurtleManager().setTurtlePenSize(width);
		
		return new Argument(width);
	}
	
}