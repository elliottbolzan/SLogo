package model.parse;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

import model.commands.*;

public class CommandMap {

	private HashMap<String, Command> stringToCommandMap;
	private String path = "resources/languages/";
	private ResourceBundle myResources;

	public CommandMap() {
		updateMap("English");
	}

	public void updateMap(String language) {
		myResources = ResourceBundle.getBundle(path + language);
		stringToCommandMap = new HashMap<String, Command>();
		createCommands();
	}
	
	private void createCommands() {
		addCommand(myResources.getString("Forward"), new ForwardCommand(1, null));
		addCommand(myResources.getString("Backward"), new BackCommand(1, null));
		addCommand(myResources.getString("Left"), new LeftCommand(1, null));
		addCommand(myResources.getString("Right"), new RightCommand(1, null));
		addCommand(myResources.getString("SetHeading"), new SetHeadingCommand(1, null));
		addCommand(myResources.getString("SetTowards"), new TowardsCommand(2, null));
		addCommand(myResources.getString("SetPosition"), new SetXYCommand(2, null));
		addCommand(myResources.getString("PenDown"), new PenDownCommand(0, null));
		addCommand(myResources.getString("PenUp"), new PenUpCommand(0, null));
		addCommand(myResources.getString("ShowTurtle"), new ShowTurtleCommand(0, null));
		addCommand(myResources.getString("HideTurtle"), new HideTurtleCommand(0, null));
		addCommand(myResources.getString("Home"), new HomeCommand(0, null));
		addCommand(myResources.getString("ClearScreen"), new ClearScreenCommand(0, null));
		addCommand(myResources.getString("XCoordinate"), new XCorCommand(1, null));
		addCommand(myResources.getString("YCoordinate"), new YCorCommand(1, null));
		addCommand(myResources.getString("Heading"), new HeadingCommand(1, null));
		addCommand(myResources.getString("IsPenDown"), new PenQueryCommand(0, null));
		addCommand(myResources.getString("IsShowing"), new ShowQueryCommand(0, null));
		addCommand(myResources.getString("Sum"), new SumCommand(2, null));
		addCommand(myResources.getString("Difference"), new DifferenceCommand(2, null));
		addCommand(myResources.getString("Product"), new ProductCommand(2, null));
		addCommand(myResources.getString("Quotient"), new QuotientCommand(2, null));
		addCommand(myResources.getString("Remainder"), new RemainderCommand(2, null));
		addCommand(myResources.getString("Minus"), new MinusCommand(2, null));
		addCommand(myResources.getString("Random"), new RandomCommand(1, null));
		addCommand(myResources.getString("Sine"), new SinCommand(1, null));
		addCommand(myResources.getString("Cosine"), new CosCommand(1, null));
		addCommand(myResources.getString("Tangent"), new TanCommand(1, null));
		addCommand(myResources.getString("ArcTangent"), new AtanCommand(1, null));
		addCommand(myResources.getString("NaturalLog"), new LogCommand(1, null));
		addCommand(myResources.getString("Power"), new PowCommand(2, null));
		addCommand(myResources.getString("Pi"), new PiCommand(0, null));
		addCommand(myResources.getString("LessThan"), new LessCommand(2, null));
		addCommand(myResources.getString("GreaterThan"), new GreaterCommand(2, null));
		addCommand(myResources.getString("Equal"), new EqualCommand(2, null));
		addCommand(myResources.getString("NotEqual"), new NotEqualCommand(2, null));
		addCommand(myResources.getString("And"), new AndCommand(2, null));
		addCommand(myResources.getString("Or"), new OrCommand(2, null));
		addCommand(myResources.getString("Not"), new NotCommand(1, null));
	}

	private void addCommand(String string, Command command) {
		String[] names = string.split("[|]");
		for (String name : names) {
			stringToCommandMap.put(name, command);
		}
	}

	public Command get(String key) {
		return stringToCommandMap.get(key);
	}

	public Set<String> keySet() {
		return stringToCommandMap.keySet();
	}

}
