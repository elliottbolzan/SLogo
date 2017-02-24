package model.parse;

import java.util.List;

import model.commands.Command;

public interface Parser {
	public List<Command> parse(String input);
	public List<String> getHistory();
	public String getPreviousCommand(int k);
	public void addUserDefinedCommand(String newCommand);
}
