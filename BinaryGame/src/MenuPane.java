import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuPane extends BorderPane {
	//Define instance variables
	private RadioButton twoDigits;
	private RadioButton threeDigits;
	private RadioButton fourDigits;
	private RadioButton fiveDigits;
	private RadioButton sixDigits;
	
	private TextField time;
	
	private CheckBox timeDecreasing;
	
	private int digits;
	private int timeAdded;
	private int timeAddedDecreasing;
	
	public MenuPane() {
		//Initiate Labels
		Label binaryDigitsLabel = new Label("# of Bits");
		Label timeLabel = new Label("Time Added Per #");
		Label timeDecreasingLabel = new Label("Time Added Per #\nDecreases?");
		
		//Add styling to labels
		binaryDigitsLabel.getStyleClass().add("menuLabel");
		timeLabel.getStyleClass().add("menuLabel");
		timeDecreasingLabel.getStyleClass().add("menuLabel");
		
		//Create ToggleGroup and add all RadioButtons to it
		ToggleGroup binaryDigits = new ToggleGroup();
		
		twoDigits = new RadioButton("2");
		threeDigits = new RadioButton("3");
		fourDigits = new RadioButton("4");
		fiveDigits = new RadioButton("5");
		sixDigits = new RadioButton("6");
		
		twoDigits.setToggleGroup(binaryDigits);
		threeDigits.setToggleGroup(binaryDigits);
		fourDigits.setToggleGroup(binaryDigits);
		fiveDigits.setToggleGroup(binaryDigits);
		sixDigits.setToggleGroup(binaryDigits);
		
		twoDigits.setSelected(true);
		
		//Setup other form items
		time = new TextField();
		
		timeDecreasing = new CheckBox();
		
		Button startGame = new Button("Start Game");
		startGame.getStyleClass().add("startButton");
		
		//Create formatting elements
		HBox binaryDigitsGroup = new HBox();
		binaryDigitsGroup.getChildren().addAll(twoDigits, threeDigits, fourDigits, fiveDigits, sixDigits);
		
		
		GridPane menuCenter = new GridPane();
		menuCenter.setAlignment(Pos.CENTER);
		
		menuCenter.add(binaryDigitsLabel, 0, 0);
		menuCenter.add(binaryDigitsGroup, 1, 0);
		
		menuCenter.add(timeLabel, 0, 1);
		menuCenter.add(time, 1, 1);
		
		menuCenter.add(timeDecreasingLabel, 0, 2);
		menuCenter.add(timeDecreasing, 1, 2);
		
		HBox startGameWrapper = new HBox();
		startGameWrapper.setAlignment(Pos.CENTER);
		startGameWrapper.getChildren().add(startGame);
		
		VBox menuCenterWrapper = new VBox();
		menuCenterWrapper.getStyleClass().add("menu");
		menuCenterWrapper.getChildren().addAll(menuCenter, startGameWrapper);
		
		this.setCenter(menuCenterWrapper);
		this.setStyle("-fx-background-color: white");
		
		//Setup event handlers
		startGame.setOnMousePressed(new ButtonHandler());
	}
	
	private class ButtonHandler implements EventHandler<MouseEvent> {
		public void handle(MouseEvent event) {
			//Define variables
			String timeValue = time.getText();
			int timeValueNumber = 0;
			
			//Make sure a number is in the TextArea field
			try {
				timeValueNumber = Integer.parseInt(timeValue);
			} catch(Exception e) {
				time.setText("Error: Please enter a # between 1 and 60");
			}
			
			//If a number is in the textarea field and it is between 1 and 60,
			//then set the settings and start the gameplay
			if(timeValueNumber <= 60 && timeValueNumber >= 1) {
				//Set settings
				//Get which number of bits is selected and set digits to that #
				if(twoDigits.isSelected()) {
					digits = 2;
				} else if(threeDigits.isSelected()) {
					digits = 3;
				} else if(fourDigits.isSelected()) {
					digits = 4;
				} else if(fiveDigits.isSelected()) {
					digits = 5;
				} else if(sixDigits.isSelected()) {
					digits = 6;
				}
				timeAdded = timeValueNumber;
				
				if(timeDecreasing.isSelected()) {
					timeAddedDecreasing = 1;
				} else {
					timeAddedDecreasing = 0;
				}
				
				//Start gameplay
				BinaryGame.switchScene("game");
			} else {
				time.setText("Error: Please enter a # between 1 and 60");
			}
		}
	}
	public int[] getSettings() {
		//Return settings
		int[] output = {digits, timeAdded, timeAddedDecreasing};
		return output;
	}
}
