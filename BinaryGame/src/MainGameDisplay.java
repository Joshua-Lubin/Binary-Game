import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class MainGameDisplay extends VBox {
	//Define instance variables
	private MenuPane menuPane;
	private UserInput userInput;
	private CountdownBar countdown;
	
	private Label base10Label;
	private int base10;
	
	private Timer timer;
	
	private long startTime;
	private double flashTime;
	
	private int score;
	
	public MainGameDisplay(MenuPane menuPane) {
		//Initiate variables
		base10 = -1;
		score = 0;
		startTime = 0;
		flashTime = 0;
		
		countdown = new CountdownBar();
		userInput = new UserInput();
		
		timer = new Timer();
		
		//Setup base10Label
		base10Label = new Label();
		base10Label.setAlignment(Pos.CENTER);
		base10Label.getStyleClass().add("base10");
		
		//Setup labelWrapper
		VBox labelWrapper = new VBox();
		labelWrapper.setAlignment(Pos.CENTER);
		labelWrapper.getChildren().addAll(base10Label, userInput);
		
		//Configure this
		this.menuPane = menuPane;
		this.getChildren().addAll(countdown, labelWrapper);
		this.setStyle("-fx-background-color: white;-fx-text-alignment: center;");
	}
	
	public void startAnimation() {
		timer.start();
	}
	
	public void stopAnimation() {
		timer.stop();
	}
	
	private class Timer extends AnimationTimer {
		public void handle(long now) {
			double timeRemaining;
			
			//Record the start time
			if(startTime == 0) {
				startTime = now;
			}
			
			//Convert now into # of seconds that have passed
			double time = ((double) now - startTime) / 1000000000;
			
			//Undo flash if 0.5 seconds have passed
			if(time - flashTime > 0.5) {
				if(time - flashTime < 0.6) {
					flash(0);
				}
			}
			
			//Either set timeRemaining based on a linear scale or an exponential scale depending on
			//whether the user said to decrease the time added as the game progresses
			if(menuPane.getSettings()[2] == 1) {
				timeRemaining = (double) (Math.pow(score, 7.0/8) + 1) * menuPane.getSettings()[1] - time;
			} else {
				timeRemaining = (double) (score + 1.0) * menuPane.getSettings()[1] - time;
			}
			
			//Calculate how much of the bar should be filled based on timeRemaining
			double barWidth = timeRemaining / 120.0 * 500.0;
			
			//If the bar is completely empty, end the game. If it is completely full, then don't over-
			//exetend the inner rectangle. Otherwise, set the bar width to barWidth
			if(barWidth < 0) {
				countdown.setBarWidth(0);
				BinaryGame.switchScene("end");
			} else if(barWidth > 500) {
				countdown.setBarWidth(500);
			} else {
				countdown.setBarWidth(barWidth);
			}
			
			//If base10 has not been generated yet, generate it
			if(base10 == -1) {
				base10 = generateBase10();
				base10Label.setText(Integer.toString(base10));
			}
			
			//IF the user has entered enough bits, then check to see if they are right
			if(menuPane.getSettings()[0] <= userInput.getText().length()) {
				//There will be a flash no matter what, so record the time under flashTime
				flashTime = time;
				
				//Flash red
				flash(2);
				
				if(calculateBinary(base10) == Integer.parseInt(userInput.getText())) {
					//Add one to the score and update the base 10 number displayed
					base10 = generateBase10();
					base10Label.setText(Integer.toString(base10));
					score++;
					
					//Override red flash with green flash
					flash(1);
				}
				
				//Erase the user's input
				userInput.setText("");
			}
		}
	}
	
	private int generateBase10() {
		//Generate random base10 that can be represented by the number of bits
		//that the user chose
		int bits = menuPane.getSettings()[0];
		int output = (int) (Math.random() * Math.pow(2, bits));
				
		return output;
	}
	
	static private int calculateBinary(int base10) {
		//Calculate the binary equivalent of a base 10 number
		int numberOfPlaces = 0;
		String output = "";
		
		//Find out how many bits are necessary to represent the number
		while(Math.pow(2, numberOfPlaces) < base10) {
			numberOfPlaces++;
		}
		
		//For each bit that is necessary, check if it is a 1 or 0 and add that to output
		for(int i = numberOfPlaces; i >= 0; i--) {
			int currentOutput = base10 / (int) Math.pow(2, i);
			
			if(currentOutput == 1) {
				base10 -= (int) Math.pow(2, i);
			}
			
			output += currentOutput;
		}
		
		return Integer.parseInt(output);
	}
	
	public UserInput getUserInput() {
		return userInput;
	}
	
	public void flash(int flash) {
		//If flash is 1, flash green, if 2, flash red, if anything else, revert back
		if(flash == 1) {
			base10Label.setTextFill(Paint.valueOf("#0fb000"));
		} else if(flash == 2) {
			base10Label.setTextFill(Paint.valueOf("#fc7458"));
		} else {
			base10Label.setTextFill(Paint.valueOf("#000000"));
		}
	}
	
	public int getScore() {
		return score;
	}
	
	public void reset() {
		//Reset all values to their original value
		userInput.setText("");
		timer = new Timer();
		startTime = 0;
		base10 = -1;
		score = 0;
		flashTime = 0;
	}
}
