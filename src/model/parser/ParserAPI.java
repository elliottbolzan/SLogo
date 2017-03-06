package model.parser;

import java.util.List;

import model.parser.nodes.Node;
import utils.BadInputException;

public interface ParserAPI {
	public Node parse(String input) throws BadInputException;
	public List<String> getHistory();
	public String getPreviousCommand(int k);
}
