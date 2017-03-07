package model.commands;

import model.parser.Argument;

public interface CommandInterface {

	public int numParameters();
		
	public Argument execute();
	
}
