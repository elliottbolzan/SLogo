/**
 * 
 */
package model.parser.tokenize;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/**
 * @author Elliott Bolzan
 *
 */
public class Tokenize {

	private static String syntaxPath = "resources/Syntax";

	private static String COMMENT_MATCH = "Comment";
	private static String CONSTANT_MATCH = "Constant";
	private static String VARIABLE_MATCH = "Variable";
	private static String COMMAND_MATCH = "Command";
	private static String LIST_START_MATCH = "ListStart";
	private static String LIST_END_MATCH = "ListEnd";
	private static String GROUP_START_MATCH = "GroupStart";
	private static String GROUP_END_MATCH = "GroupEnd";

	public Token typeOf(String token) {
		if (checkArgument(token).equals(COMMENT_MATCH)) {
			return Token.COMMENT;
		} else if (checkArgument(token).equals(CONSTANT_MATCH)) {
			return Token.CONSTANT;
		} else if (checkArgument(token).equals(VARIABLE_MATCH)) {
			return Token.VARIABLE;
		} else if (checkArgument(token).equals(COMMAND_MATCH)) {
			return Token.COMMAND;
		} else if (checkArgument(token).equals(LIST_START_MATCH)) {
			return Token.LIST_START;
		} else if (checkArgument(token).equals(LIST_END_MATCH)) {
			return Token.LIST_END;
		} else if (checkArgument(token).equals(GROUP_START_MATCH)) {
			return Token.GROUP_START;
		} else if (checkArgument(token).equals(GROUP_END_MATCH)) {
			return Token.GROUP_END;
		}
		return Token.COMMENT;
	}

	private boolean match(String text, Pattern regex) {
		return regex.matcher(text).matches();
	}

	private String checkArgument(String text) {
		for (Entry<String, Pattern> e : createPatternMap()) {
			if (match(text, e.getValue())) {
				return e.getKey();
			}
		}
		return "";
	}

	private List<Entry<String, Pattern>> createPatternMap() {
		ResourceBundle resources = ResourceBundle.getBundle(syntaxPath);
		Enumeration<String> iter = resources.getKeys();
		List<Entry<String, Pattern>> symbols = new ArrayList<Entry<String, Pattern>>();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			symbols.add(new SimpleEntry<String, Pattern>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
		return symbols;
	}

}
