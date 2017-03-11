package model.commands.multiple;

import java.util.ArrayList;
import java.util.List;

import model.commands.Command;
import model.parser.Argument;
import model.parser.nodes.Node;
import view.visualization.Turtle;

public class AskCommand extends Command{

	@Override
	protected int internalNumParameters() {
		return 2;
	}

	@Override
	protected Argument execute() {
		Argument result = new Argument();
		List<Integer> ids = new ArrayList<Integer>();
		for(Turtle t: getController().getTurtleManager().getActiveTurtles()){
			ids.add(t.getID());
		}

		List<Integer> turtles = new ArrayList<Integer>();
		for(Node child : getChildren().get(0).getChildren()){
			int num = (int) child.evaluate().getDouble();
			turtles.add(num);
		}
		getController().getTurtleManager().setActiveTurtles(turtles);
		result = getParameter(1);
		getController().getTurtleManager().setActiveTurtles(ids);	

		return result;
	}

}