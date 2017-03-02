package model.commands;

public interface CommandInterface {

	public String toString();

	public int numParameters();
	
	public double getReturnValue();
	
	public void execute();
}
