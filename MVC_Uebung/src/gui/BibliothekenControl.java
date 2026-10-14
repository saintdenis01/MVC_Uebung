package gui;

import java.io.IOException;

import business.Bibliothek;
import business.BibliothekenModel;
import javafx.stage.Stage;

public class BibliothekenControl {

    private BibliothekenModel bibliothekenModel;
    private BibliothekenView bibliothekenView;

    public BibliothekenControl(Stage primaryStage) {
        this.bibliothekenModel = new BibliothekenModel();
        this.bibliothekenView = new BibliothekenView(this, primaryStage, bibliothekenModel);
    }

    public void nehmeBibliothekAuf() {
        try {
            // 1. Eingaben aus der View holen
            String name          = this.bibliothekenView.getTxtName().getText();
            String ort           = this.bibliothekenView.getTxtOrt().getText();
            int anzahlBuecher    = Integer.parseInt(this.bibliothekenView.getTxtAnzahlBuecher().getText());
            int anzahlLeser      = Integer.parseInt(this.bibliothekenView.getTxtAnzahlLeser().getText());
            String[] abteilungen = this.bibliothekenView.getTxtAbteilungen().getText().split(";");

            // 2. Bibliothek-Objekt erzeugen
            Bibliothek bibliothek = new Bibliothek(name, ort, anzahlBuecher, anzahlLeser, abteilungen);

            // 3. im Model ablegen
            this.bibliothekenModel.setBibliothek(bibliothek);

            // 4. Erfolgsmeldung über die View
            this.bibliothekenView.zeigeInformationsfensterAn("Die Bibliothek wurde aufgenommen!");
        }
        catch (Exception exc) {
            // Fehler über die View melden
            this.bibliothekenView.zeigeFehlermeldungsfensterAn(exc.getMessage());
        }
    }

    public void leseAusDatei(String typ) {
        try {
            if ("csv".equals(typ)) {
                // 1. Model liest die Datei
                this.bibliothekenModel.leseAusCsvDatei();

                // 2. Erfolgsmeldung über die View
                this.bibliothekenView.zeigeInformationsfensterAn("Die Bibliothek wurde gelesen!");
            }
            else {
                this.bibliothekenView.zeigeInformationsfensterAn("Noch nicht implementiert!");
            }
        }
        catch (IOException exc) {
            // Datei fehlt oder ist nicht lesbar
            this.bibliothekenView.zeigeFehlermeldungsfensterAn("IOException beim Lesen!");
        }
        catch (Exception exc) {
            // z. B. falsches Format in der Datei
            this.bibliothekenView.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Lesen!");
        }
    }

    public void schreibeBibliothekenInCsvDatei() {
        try {
            // 1. Model schreibt die Datei
            this.bibliothekenModel.schreibeBibliothekenInCsvDatei();

            // 2. Erfolgsmeldung über die View
            this.bibliothekenView.zeigeInformationsfensterAn("Die Bibliotheken wurden gespeichert!");
        }
        catch (IOException exc) {
            // Datei kann nicht geschrieben werden
            this.bibliothekenView.zeigeFehlermeldungsfensterAn("IOException beim Speichern!");
        }
        catch (Exception exc) {
            // z. B. noch keine Bibliothek aufgenommen
            this.bibliothekenView.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Speichern!");
        }
    }
}
