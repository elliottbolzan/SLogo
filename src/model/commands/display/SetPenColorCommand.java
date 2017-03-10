package model.commands.display;

import javafx.scene.paint.Color;
import model.commands.turtle.RepeatableTurtleCommand;
import model.parser.Argument;

public class SetPenColorCommand extends RepeatableTurtleCommand {

	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument innerExecute() {
		int index = (int) this.getParameter(0).getDouble();
		Color color = getController().getColorPalette().get(index - 1).colorProperty().get();
		getTurtle().setPenColor(color);
		getTurtle().setColorIndex(index);
		return new Argument(index);
	}

}