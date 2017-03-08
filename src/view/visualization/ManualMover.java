/**
 * 
 */
package view.visualization;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Elliott Bolzan
 *
 */
public class ManualMover {

	private final static double INCREMENT = 10;

	public String keyPressed(KeyEvent e) {
		String result = "";
		if (e.getCode() == KeyCode.LEFT) {
			result += "left";
		} else if (e.getCode() == KeyCode.RIGHT) {
			result += "right";
		} else if (e.getCode() == KeyCode.UP) {
			result += "forward";
		} else if (e.getCode() == KeyCode.DOWN) {
			result += "back";
		}
		if (!(result.equals(""))) {
			result += " " + String.valueOf(INCREMENT);
		}
		return result;
	}

}
