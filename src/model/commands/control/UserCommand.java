/**
 * 
 */
package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.parser.Argument;

/**
 * @author Elliott Bolzan
 *
 */
public class UserCommand extends Command {
	
	private String name;
	private List<String> variableNames;
	private String expression;

	/**
	 * 
	 */
	public UserCommand(String name, List<String> variableNames, String expression) {
		this.name = name;
		this.variableNames = variableNames;
		this.expression = expression;
	}

	@Override
	public int numParameters() {
		return variableNames.size();
	}

	@Override
	public Argument execute() {
		int i = 0;
		for (String variableName: variableNames) {
			double realValue = getParameter(i).getDouble();
			expression = expression.replaceAll(variableName, Double.toString(realValue));
			i++;
		}
		return getParser().parseInternal(expression.trim()).evaluate();
	}
	
	public String getName() {
		return name;
	}

}
