package model.parse;

import java.util.List;

import utils.BadInputException;

public interface ParserAPI {
	public void parse(String input) throws BadInputException;
	public List<String> getHistory();
	public String getPreviousCommand(int k);
}
