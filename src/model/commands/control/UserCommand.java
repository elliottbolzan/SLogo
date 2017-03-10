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
	protected UserCommand(String name, List<String> variableNames, String expression) {
		this.name = name;
		this.variableNames = variableNames;
		this.expression = expression;
	}
	
	public UserCommand(UserCommand command) {
		this.name = command.name;
		this.variableNames = command.variableNames;
		this.expression = command.expression;
	}

	@Override
	protected int internalNumParameters() {
		return variableNames.size();
	}

	@Override
	protected Argument execute() {
		int i = 0;
		String completeExpression = expression;
		for (String variableName: variableNames) {
			double realValue = getParameter(i).getDouble();
			completeExpression = completeExpression.replaceAll(variableName, Double.toString(realValue));
			i++;
		}
		return getParser().parseInternal(completeExpression.trim()).evaluate();
	}
	
	protected String getName() {
		return name;
	}
}
