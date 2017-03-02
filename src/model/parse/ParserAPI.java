package model.parse;

import java.util.List;

public interface ParserAPI {
	public void parse(String input);
	public List<String> getHistory();
	public String getPreviousCommand(int k);
}
