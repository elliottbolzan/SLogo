package model.commands.display;

import model.commands.turtle.RepeatableTurtleCommand;
import model.parser.Argument;

public class SetShapeCommand extends RepeatableTurtleCommand {

	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument innerExecute() {
		int index = (int) getParameter(0).getDouble();
		String path = getController().getImagePalette().get(index - 1).pathProperty().get();
		getTurtle().setImage(path);
		getTurtle().setShapeIndex(index);
		return new Argument(index);
	}
}