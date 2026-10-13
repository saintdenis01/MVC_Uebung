package gui;

import business.BibliothekenModel;
import javafx.stage.Stage;

public class BibliothekenControl {
	
	
	private BibliothekenModel bibliothekenModel;
	private BibliothekenView  bibliothekenView;
	
	public BibliothekenControl(Stage primaryStage) {
		
		this.bibliothekenModel = new BibliothekenModel();
		this.bibliothekenView = new BibliothekenView(this, primaryStage , bibliothekenModel);
	}

}
