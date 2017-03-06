package model.commands;

import model.parser.Argument;
import utils.BadInputException;

public interface CommandInterface {

	public int numParameters();
		
	public Argument execute() throws BadInputException;
	
}
