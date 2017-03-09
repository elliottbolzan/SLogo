/**
 * 
 */
package model.commands.turtle;

import model.parser.Argument;
import view.visualization.TurtleManager;

/**
 * @author Elliott Bolzan
 *
 */
public abstract class RepeatableTurtleCommand extends TurtleCommand {
	
	@Override
	protected Argument execute() {
		Argument result = new Argument();
		TurtleManager manager = getController().getTurtleManager();
		for (int i = 0; i < manager.getActiveTurtles().size(); i++) {
			setTurtle(manager.getTurtleByID(manager.getActiveTurtles().get(i).getID()));
			result = innerExecute();
		}
		return result;
	}

    protected abstract Argument innerExecute();

}
