package business;

public class Bibliothek {
	
    private String name;
    private String ort;
    private int anzahlBuecher;
    private int anzahlLeser;
    private String[] abteilungen;
    
    public Bibliothek(String name, String ort, int anzahlBuecher,
       	int anzahlLeser, String[] abteilungen){
    	this.name = name;
      	this.ort = ort;
       	this.anzahlBuecher = anzahlBuecher;
       	this.anzahlLeser = anzahlLeser;
       	this.abteilungen = abteilungen;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public int getAnzahlBuecher() {
		return anzahlBuecher;
	}

	public void setAnzahlBuecher(int anzahlBuecher) {
		this.anzahlBuecher = anzahlBuecher;
	}

	public int getAnzahlLeser() {
		return anzahlLeser;
	}

	public void setAnzahlLeser(int anzahlLeser) {
		this.anzahlLeser = anzahlLeser;
	}

	public String[] getAbteilungen() {
		return abteilungen;
	}

	public void setAbteilungen(String[] abteilungen) {
		this.abteilungen = abteilungen;
	}
	
 	public String getAbteilungenAlsString(char trenner) {
		String ergebnis = "";
		int i = 0;
		for(i = 0; i < this.getAbteilungen().length - 1; i++) {
			ergebnis = ergebnis + this.getAbteilungen()[i] + trenner; 
		}
		return ergebnis	+ this.getAbteilungen()[i];
	}
	
	public String gibBibliothekZurueck(char trenner){
  		return this.getName() + trenner 
  			+ this.getOrt() + trenner
  			+ this.getAnzahlBuecher() + trenner
  		    + this.getAnzahlLeser() + trenner + "\n"
  		    + this.getAbteilungenAlsString(trenner) + "\n";
  	}
}
