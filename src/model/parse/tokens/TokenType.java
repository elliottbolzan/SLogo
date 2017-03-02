package model.parse.tokens;

/**
 * @author Elliott Bolzan
 *
 */

public enum TokenType {
	COMMENT,
	CONSTANT,
	VARIABLE,
	COMMAND,
	LIST_START,
	LIST_END,
	GROUP_START,
	GROUP_END,
	WHITESPACE,
	NEWLINE
}