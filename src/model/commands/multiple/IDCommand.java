/**
 * 
 */
package model.commands.multiple;

import model.commands.Command;
import model.parser.Argument;

/**
 * @author Elliott Bolzan
 *
 */
public class IDCommand extends Command {
	@Override
	protected int internalNumParameters() {
		return 0;
	}

	@Override
	protected Argument execute() {
		return new Argument(getController().getTurtleManager().getCurrentTurtle().getID());
	}
}