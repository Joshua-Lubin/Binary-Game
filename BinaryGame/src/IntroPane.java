import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class IntroPane extends BorderPane {
	//Define variables
	private VBox logoAndText;
	private Label begin;
	
	private Timer timer;
	private long startTime;
	private long endTime;
	private boolean fadeOut;
	
	public IntroPane() {
		//Initialize variables
		fadeOut = false;
		timer = new Timer();
		logoAndText = new VBox();
		logoAndText.setAlignment(Pos.CENTER);
		startTime = 0;
		endTime = 0;
		
		//Setup intro screen organization
		//Setup logo
		Image logo = new Image("/resources/logo.png", 400, 400, true, true);
		ImageView logoNode = new ImageView(logo);
		
		//Setup game name
		Label name = new Label("Binary Game");
		name.setFont(new Font("Arial Bold", 40));
		name.setPadding(new Insets(50, 0, 25, 0));
		
		//Setup label
		begin = new Label("Press any button to begin");
		begin.setFont(new Font("Arial Italic", 25));
		begin.setTextFill(Paint.valueOf("#616161"));
		begin.setPadding(new Insets(25, 0, 70, 0));
		
		//Add all items to logoAndText
		logoAndText.getChildren().addAll(logoNode, name, begin);
		this.setCenter(logoAndText);
		
		this.setStyle("-fx-background-color: white;");
		
		//Setup event handers
		this.setOnMouseClicked(new MouseHandler());
	}
	
	public void startAnimation() {
		timer.start();
	}
	public void stopAnimation() {
		timer.stop();
	}
	
	private class Timer extends AnimationTimer {
		public void handle(long now) {
			//Record the start time
			if(startTime == 0) {
				startTime = now;
			}
			
			//Convert now into usable format
			double time = ((double) now - startTime) / 1000000000;
			
			//Initial fade in
			if(time < 2) {
				double opacity = time / 2;
				
				logoAndText.setOpacity(opacity);
				begin.setOpacity(opacity);
			}
			//Fade out when fadeOut is true
			else if(fadeOut) {
				if(endTime == 0) {
					endTime = now;
				}
				time = ((double) now - endTime) / 1000000000;
				double opacity = (1 - time) / 2;
				
				if(opacity <= 0) {
					BinaryGame.switchScene("menu");
				}
				
				logoAndText.setOpacity(opacity);
			}
			//Otherwise use algorithm to fade in and out begin
			else {
				begin.setOpacity(Math.abs(time % 2 - 1));
			}
		}
	}
	
	private class MouseHandler implements EventHandler<MouseEvent> {
		public void handle(MouseEvent event) {
			//Start exiting intro screen
			fadeOut = true;
		}
	}
	
	public class KeyboardHandler implements EventHandler<KeyEvent> {
		public void handle(KeyEvent event) {
			//Start exiting intro screen
			fadeOut = true;
		}
	}
	
	public KeyboardHandler getKeyboardHandler() {
		return new KeyboardHandler();
	}
}
