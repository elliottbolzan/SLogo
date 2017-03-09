package model.commands.display;

import model.commands.turtle.TurtleCommand;
import model.parser.Argument;

public class SetShapeCommand extends TurtleCommand{
	public SetShapeCommand(){
		super();
	}

	@Override
	public int numParameters() {
		return 1;
	}

	@Override
	public Argument execute() {
		int index = (int)this.getParameter(0).getDouble();
		String path = getController().getImagePalette().get(index-1).pathProperty().get();
		getController().getTurtleManager().setTurtleImage(index, path);
		
		return new Argument(index);
	}
}