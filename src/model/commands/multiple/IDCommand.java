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
	public int numParameters() {
		return 0;
	}

	@Override
	public Argument execute() {
		return new Argument(getController().getTurtleManager().getCurrentTurtle().getID());
	}

}
