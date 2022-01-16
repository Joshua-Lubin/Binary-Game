import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

public class UserInput extends Label{
	//Define instance variables
	private UserInput userInput;
	
	public UserInput() {
		userInput = this;
		
		//Setup UserInput
		this.setText("");
		this.getStyleClass().add("userInput");
		this.setAlignment(Pos.CENTER);
		
	}
	
	public class KeyboardHandler implements EventHandler<KeyEvent> {
		public void handle(KeyEvent event) {
			//Get event information
			String key = event.getCharacter();
			String originalText = userInput.getText();
			
			//If the key is not equal to 0 or 1, then exit out of the function
			if(!(key.equals("0") || key.equals("1"))) {
				return;
			}
			
			//Add the key that was pressed to the displayed text
			String newText = originalText + key;
			userInput.setText(newText);
		}
	}
	
	public KeyboardHandler getKeyboardHandler() {
		return new KeyboardHandler();
	}
}
