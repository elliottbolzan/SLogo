package model.commands;

import utils.BadInputException;

public interface CommandInterface {

	public String toString();

	public int numParameters();
	
	public double getReturnValue();
	
	public void execute() throws BadInputException;
}
