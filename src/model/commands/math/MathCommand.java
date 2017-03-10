/**
 * 
 */
package model.commands.math;

import model.commands.Command;
import model.parser.Argument;

/**
 * @author Elliott Bolzan
 *
 */
public abstract class MathCommand extends Command {

	@Override
	protected abstract Argument execute();

	@Override
	protected abstract int internalNumParameters();

}
