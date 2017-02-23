package parse;

import java.util.List;

import commands.Command;

public interface Parser {
	public Command parse(String input);
	public List<String> getHistory();
	public String getPreviousCommand(int k);
	public void addUserDefinedCommand(String newCommand);
}
