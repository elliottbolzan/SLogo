package model.commands.multiple;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import model.commands.Command;
import model.parser.Argument;
import model.parser.nodes.Node;
import view.visualization.Turtle;


public class AskCommand extends Command{
	
	public AskCommand(){
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
		for(Turtle t: getController().getTurtleManager().getActiveTurtles()){
			ids.add(t.getID());
			System.out.println(t.getID());
		}
		
		//make list of turtles to become active
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