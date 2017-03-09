/**
 * 
 */
package model.commands.multiple;

import java.util.ArrayList;
import java.util.List;
import model.commands.Command;
import model.parser.Argument;
import model.parser.nodes.Node;

/**
 * @author Elliott Bolzan
 *
 */
public class TellCommand extends Command {
	@Override
	protected int internalNumParameters() {
		return 1;
	}

	@Override
	protected Argument execute() {
		Argument result = new Argument();
		List<Integer> indices = new ArrayList<Integer>();
		for (Node child : getChildren().get(0).getChildren()) {
			int index = (int) child.evaluate().getDouble();
			indices.add(index);
			result = new Argument(index);
		}
		getController().getTurtleManager().setActiveTurtles(indices);
		return result;
	}
}