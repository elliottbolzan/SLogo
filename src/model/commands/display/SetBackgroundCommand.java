package model.commands.display;

import model.commands.turtle.TurtleCommand;
import model.parser.Argument;

public class SetBackgroundCommand extends TurtleCommand{
	public SetBackgroundCommand(){
		super();
	}

	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		int index = (int)this.getParameter(0).getDouble();
		getController().setBackgroundColor(getController().getColorPalette().get(index-1).colorProperty().get());
		return new Argument(index);
	}
	
}