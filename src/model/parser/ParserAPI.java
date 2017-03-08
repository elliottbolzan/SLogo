package model.parser;

import java.util.List;

import model.parser.nodes.Node;

public interface ParserAPI {
	public Node parse(String input, boolean addToHistory);
	public List<String> getHistory();
	public String getPreviousCommand(int k);
}
