import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class CountdownBar extends HBox {
	private Rectangle timeRemaining;
	
	public CountdownBar() {
		//Setup inner green rectangle
		timeRemaining = new Rectangle();
		timeRemaining.setHeight(50);
		timeRemaining.setX(0);
		timeRemaining.setFill(Paint.valueOf("#61fa78"));
		timeRemaining.getStyleClass().add("timeRemaining");
		
		//Setup HBox wrapper
		HBox timeRemainingWrapper = new HBox();
		timeRemainingWrapper.getChildren().add(timeRemaining);
		timeRemainingWrapper.getStyleClass().add("timeRemainingWrapper");
		timeRemainingWrapper.setAlignment(Pos.CENTER);
		
		//Configure this class
		this.getChildren().add(timeRemainingWrapper);
		this.getStyleClass().add("timeRemainingWrapperWrapper");
		this.setAlignment(Pos.CENTER);
	}
	
	public void setBarWidth(double width) {
		//Set the width of the green portion of the countdown bar and adjust its positioning
		//so that it stays aligned to the left
		timeRemaining.setWidth(width);
		timeRemaining.setTranslateX(-(500.0 - width) / 2.0);
	}
}
