package model.commands.display;

import javafx.scene.paint.Color;
import model.commands.turtle.TurtleCommand;
import model.parser.Argument;

public class SetPenColorCommand extends TurtleCommand {

	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument execute() {
		int index = (int) this.getParameter(0).getDouble();
		Color color = getController().getColorPalette().get(index - 1).colorProperty().get();
		getController().getTurtleManager().setTurtlePenColor(index, color);
		return new Argument(index);
	}

}