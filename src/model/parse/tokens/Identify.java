/**
 * 
 */
package model.parse.tokens;

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
public class Identify {

	private static String syntaxPath = "resources/languages/Syntax";

	private static String COMMENT_MATCH = "Comment";
	private static String CONSTANT_MATCH = "Constant";
	private static String VARIABLE_MATCH = "Variable";
	private static String COMMAND_MATCH = "Command";
	private static String LIST_START_MATCH = "ListStart";
	private static String LIST_END_MATCH = "ListEnd";

	public static TokenType determineType(String token) throws Exception {
		if (checkArgument(token).equals(COMMENT_MATCH)) {
			return TokenType.COMMENT;
		}
		else if (checkArgument(token).equals(CONSTANT_MATCH)) {
			return TokenType.CONSTANT;
		}
		else if (checkArgument(token).equals(VARIABLE_MATCH)) {
			return TokenType.VARIABLE;
		}
		else if (checkArgument(token).equals(COMMAND_MATCH)) {
			return TokenType.COMMAND;
		}
		else if (checkArgument(token).equals(LIST_START_MATCH)) {
			return TokenType.LIST_START;
		}
		else if (checkArgument(token).equals(LIST_END_MATCH)) {
			return TokenType.LIST_END;
		}
		throw new Exception();
	}

	private static boolean match(String text, Pattern regex) {
		return regex.matcher(text).matches();
	}

	private static String checkArgument(String text) throws Exception {
		for (Entry<String, Pattern> e : createPatternMap()) {
			if (match(text, e.getValue())) {
				return e.getKey();
			}
		}
		throw new Exception();
	}

	private static List<Entry<String, Pattern>> createPatternMap() {
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
