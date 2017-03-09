package model.commands.multiple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.commands.Command;
import model.parser.Argument;
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
				List<Integer> ids = new ArrayList<Integer>();
		for(Turtle getID: getController().getTurtleManager().getActiveTurtles()){
			ids.add(getID.getID());
		}		
		
		HashMap<Integer, Turtle> turtles = new HashMap<Integer, Turtle>(getController().getTurtleManager().getAllTurtles());
		List<Integer> activate = new ArrayList<Integer>();
		List<Integer> temp = new ArrayList<Integer>();
		
		for(Turtle check : turtles.values()){
			temp.clear();
			temp.add(check.getID());
			getController().getTurtleManager().setActiveTurtles(temp);
			double value = getParameter(0).getDouble();
			System.out.println(value);
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