package model.commands.display;

import model.commands.turtle.TurtleCommand;
import model.parser.Argument;

public class SetShapeCommand extends TurtleCommand {

	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument execute() {
		int index = (int) getParameter(0).getDouble();
		String path = getController().getImagePalette().get(index - 1).pathProperty().get();
		getController().getTurtleManager().setTurtleImage(index, path);
		return new Argument(index);
	}
}