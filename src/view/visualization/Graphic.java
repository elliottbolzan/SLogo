package view.visualization;

import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import utils.Point;

public class Graphic {
	private static final double OPACITY_ACTIVE = 1.0;
	private static final double OPACITY_INACTIVE = 0.3;
	private static final double TURN_TIME_MS = 250;
	
	private TurtleDisplay display;
	private ImageView graphic;
	private int imageIndex;
	
	protected Graphic(TurtleDisplay home, Image image, int index) {
		display = home;
		graphic = new ImageView(image);
		imageIndex = index;
	}

	protected ImageView getView() {
		return graphic;
	}
	
	protected void setImage(String url) {
		graphic.setImage(new Image(url));
	}
	
	protected int getIndex() {
		return imageIndex;
	}
	
	protected void setIndex(int index) {
		imageIndex = index;
	}
	
	protected boolean isVisible() {
		return graphic.isVisible();
	}
	
	protected void setVisible(boolean visible) {
		graphic.setVisible(visible);
	}
	
	protected void bindOpacityTo(BooleanProperty property) {
		graphic.opacityProperty().bind(Bindings.when(property).then(OPACITY_ACTIVE).otherwise(OPACITY_INACTIVE));
	}
	
	protected void setCenter(Point center) {
		if (!display.isInBounds(center)) {
			center = display.wrapIntoView(center);
		}
		graphic.setX(center.getX() - graphic.getBoundsInLocal().getWidth() / 2.0);
		graphic.setY(center.getY() - graphic.getBoundsInLocal().getHeight() / 2.0);
	}
	
	protected void setRotation(double degrees) {
		RotateTransition rotate = new RotateTransition(Duration.millis(TURN_TIME_MS));
		rotate.setToAngle((degrees + 90) % 360);
		new SequentialTransition(graphic, rotate).play();
	}
}
