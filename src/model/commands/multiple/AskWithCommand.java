package model.commands.multiple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import model.commands.Command;
import model.parser.Argument;
import model.parser.nodes.Node;
import view.visualization.Turtle;


public class AskWithCommand extends Command{
	
	public AskWithCommand(){
		super();
	}

	@Override
	public int numParameters() {
		return 2;
	}

	@Override
	public Argument execute() {
		Argument result = new Argument();
		
		//store current active turtles
		List<Integer> ids = new ArrayList<Integer>();
		for(Turtle getID: getController().getTurtleManager().getActiveTurtles()){
			ids.add(getID.getID());
		}		
		
		//make list of turtles to become active that fit condition
		HashSet<Integer> turtleIDs = new HashSet<Integer>(getController().getTurtleManager().getTurtles().keySet());
		List<Integer> activate = new ArrayList<Integer>();

		double value = getParameter(0).getDouble();
		for(Integer check : turtleIDs){
			if(value > 0){
				activate.add(check);
			}
		}
		getController().getTurtleManager().setActiveTurtles(activate);
		
		result = getParameter(1);
		
		//getController().getTurtleManager().setActiveTurtles(ids);
		
		return result;
	}
	
}