package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BibliothekenModel {

    // speichert temporaer ein Objekt vom Typ Bibliothek
    private Bibliothek bibliothek;

    public BibliothekenModel() {
    }

    public Bibliothek getBibliothek() {
        return bibliothek;
    }

    public void setBibliothek(Bibliothek bibliothek) {
        this.bibliothek = bibliothek;
    }

    public void leseAusCsvDatei() throws IOException {
        // 1. Datei öffnen, erste Zeile lesen, Datei schließen
        BufferedReader ein = new BufferedReader(new FileReader("Bibliothek.csv"));
        String[] zeile = ein.readLine().split(";");
        ein.close();

        // 2. Werte aus der Zeile holen
        String name          = zeile[0];
        String ort           = zeile[1];
        int anzahlBuecher    = Integer.parseInt(zeile[2]);
        int anzahlLeser      = Integer.parseInt(zeile[3]);
        String[] abteilungen = zeile[4].split("_");

        // 3. Bibliothek erzeugen und im Attribut speichern
        this.bibliothek = new Bibliothek(name, ort, anzahlBuecher, anzahlLeser, abteilungen);
    }

    public void schreibeBibliothekenInCsvDatei() throws IOException {
        // 1. Datei zum Anhängen öffnen (true = anhängen)
        BufferedWriter aus = new BufferedWriter(new FileWriter("BibliothekenAusgabe.csv", true));

        // 2. Text der Bibliothek mit ';' als Trenner schreiben
        aus.write(this.bibliothek.gibBibliothekZurueck(';'));

        // 3. Datei schließen
        aus.close();
    }
}
