package model.commands.multiple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.commands.Command;
import model.parser.Argument;
import view.visualization.Turtle;

public class AskWithCommand extends Command{

	@Override
	protected int internalNumParameters() {
		return 2;
	}

	@Override
	protected Argument execute() {
		Argument result = new Argument();
		List<Integer> ids = new ArrayList<Integer>();
		for(Turtle getID: getController().getTurtleManager().getActiveTurtles()){
			ids.add(getID.getID());
		}		
		
		HashMap<Integer, Turtle> turtles = new HashMap<Integer, Turtle>(getController().getTurtleManager().getAllTurtles());
		List<Integer> activate = new ArrayList<Integer>();
		
		for(Turtle check : turtles.values()){
			getController().getTurtleManager().setCurrentTurtle(check);
			double value = getParameter(0).getDouble();
			if(value > 0){
				activate.add(check.getID());
			}
		}
		getController().getTurtleManager().setActiveTurtles(activate);
		result = getParameter(1);
		getController().getTurtleManager().setActiveTurtles(ids);
		
		return result;
	}
	
}