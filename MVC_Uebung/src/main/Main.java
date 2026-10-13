package main;

import gui.BibliothekenControl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args){
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new BibliothekenControl(primaryStage);
		
	}

}



//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
