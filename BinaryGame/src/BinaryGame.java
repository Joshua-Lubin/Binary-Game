import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;

public class BinaryGame extends Application {
	//Define variables
	public static StackPane root;
	public static IntroPane introPane;
	public static MenuPane menuPane;
	public static MainGameDisplay game;
	public static EndPane endPane;
	
    public void start(Stage mainStage) {
		//Initiate
        root = new StackPane();
        introPane = new IntroPane();
        menuPane = new MenuPane();
        game = new MainGameDisplay(menuPane);
        endPane = new EndPane();
        
        //Define variables
        Scene scene = new Scene(root, 1200, 800);
        
        //Setup root
        root.setBackground(Background.EMPTY);
        root.getChildren().addAll(introPane, menuPane, game, endPane);
        
        //Setup mainStage
        mainStage.setScene(scene);
        mainStage.setTitle("Binary Game");
        mainStage.show();
        
        //Setup keyboard event handlers
        scene.addEventHandler(KeyEvent.KEY_TYPED, introPane.getKeyboardHandler());
        scene.addEventHandler(KeyEvent.KEY_TYPED, game.getUserInput().getKeyboardHandler());
        scene.getStylesheets().add("/resources/style.css");
        
        //Start intro pane animation
        switchScene("intro");
    }
    
    public static void switchScene(String input) {
    	//Take input and depending on input, switch what is on screen
    	switch(input) {
	    	case "intro":
	    		introPane.startAnimation();
	    		introPane.toFront();
	    		break;
	    	case "menu":
	    		introPane.stopAnimation();
	    		game.reset();
	    		menuPane.toFront();
	    		break;
	    	case "game":
	    		game.toFront();
	    		game.startAnimation();
	    		break;
	    	case "end":
	    		game.stopAnimation();
	    		endPane.toFront();
	    		endPane.setScore(game.getScore());
	    		break;
    	}
    }
    
    public static void main(String[] args)
    {
        Application.launch(args);
    }
}