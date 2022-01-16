import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class EndPane extends VBox {
	//Define instance variable
	private Label score;
	
	public EndPane() {
		//Setup "Game Over!" text
		Label gameOver = new Label("Game Over!");
		gameOver.getStyleClass().add("gameOver");
		
		//Setup score display
		score = new Label();
		score.getStyleClass().add("score");
		
		//Setup "Play Again" button
		Button playAgain = new Button("Play Again");
		playAgain.getStyleClass().add("startButton");
		playAgain.setOnAction(new ButtonClicked());
		
		//Setup wrapper
		VBox gameEndWrapper = new VBox();
		gameEndWrapper.getStyleClass().add("gameEndWrapper");
		gameEndWrapper.getChildren().addAll(gameOver, score, playAgain);
		
		//Add everything to this class and set this class' background
		this.getChildren().add(gameEndWrapper);
		this.setStyle("-fx-background-color: white");
	}
	
	public void setScore(int scoreInt) {
		//Update the score label and take into account whether score
		//should be plural or not
		if(scoreInt != 1) {
			score.setText("You scored " + scoreInt + " points");
		} else {
			score.setText("You scored " + scoreInt + " point");
		}
	}
	
	private class ButtonClicked implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			BinaryGame.switchScene("menu");
		}
	}
}
