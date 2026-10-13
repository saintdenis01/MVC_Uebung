package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BibliothekenModel {
	
	// speichert temporaer ein Objekt vom Typ Bibliothek
	private Bibliothek bibliothek;
	
	
	public BibliothekenModel() {
		
		
	}
	 private void leseAusDatei(String typ){
	    	try {
	      		if("csv".equals(typ)){
	      			BufferedReader ein = new BufferedReader(new FileReader("Bibliothek.csv"));
	      			String[] zeile = ein.readLine().split(";");
	      			this.bibliothek = new Bibliothek(zeile[0],
	      				zeile[1],
	      				Integer.parseInt(zeile[2]),
	      				Integer.parseInt(zeile[3]),
	      				zeile[4].split("_"));
	      			ein.close();
	      	  		zeigeInformationsfensterAn(
	      	  	   		"Die Bibliothek wurde gelesen!");
	      		}
	       		else{
		   			zeigeInformationsfensterAn(
		   				"Noch nicht implementiert!");
		   		}
			}
			catch(IOException exc){
				zeigeFehlermeldungsfensterAn(
					"IOException beim Lesen!");
			}
			catch(Exception exc){
				zeigeFehlermeldungsfensterAn(
					"Unbekannter Fehler beim Lesen!");
			}
		}

	
	  public Bibliothek getBibliothek() {
		return bibliothek;
	}
	 public void setBibliothek(Bibliothek bibliothek) {
		 this.bibliothek = bibliothek;
	 }
	  private void nehmeBibliothekAuf(){
	    	try{
	    		this.bibliothek = new Bibliothek(
	    			txtName.getText(),
	   	            txtOrt.getText(),
	   	            Integer.parseInt(txtAnzahlBuecher.getText()),
	   	        	Integer.parseInt(txtAnzahlLeser.getText()),
	    		    txtAbteilungen.getText().split(";"));
	    		zeigeInformationsfensterAn("Die Bibliothek wurde aufgenommen!");
	       	}
	       	catch(Exception exc){
	       		zeigeFehlermeldungsfensterAn(exc.getMessage());
	     	}
	    }

	   
}
