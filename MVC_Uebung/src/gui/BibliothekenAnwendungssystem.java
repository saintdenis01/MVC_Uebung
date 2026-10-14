package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import business.Bibliothek;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class BibliothekenAnwendungssystem {

    //---Anfang Attribute der grafischen Oberflaeche---
    private Pane pane                   = new Pane();
    private Label lblEingabe            = new Label("Eingabe");
    private Label lblAnzeige            = new Label("Anzeige");
    private Label lblName               = new Label("Name:");
    private Label lblOrt                = new Label("Ort:");
    private Label lblAnzahlBuecher      = new Label("Anzahl Buecher:");
    private Label lblAnzahlLeser        = new Label("Anzahl Leser:");
    private Label lblAbteilungen        = new Label("Abteilungen:");
    private TextField txtName           = new TextField();
    private TextField txtOrt            = new TextField();
    private TextField txtAnzahlBuecher  = new TextField();
    private TextField txtAnzahlLeser    = new TextField();
    private TextField txtAbteilungen    = new TextField();
    private TextArea txtAnzeige         = new TextArea();
    private Button btnEingabe           = new Button("Eingabe");
    private Button btnAnzeige           = new Button("Anzeige");
    private MenuBar mnbrMenuLeiste      = new MenuBar();
    private Menu mnDatei                = new Menu("Datei");
    private MenuItem mnItmCsvImport     = new MenuItem("csv-Import");
    private MenuItem mnItmTxtImport     = new MenuItem("txt-Import");
    private MenuItem mnItmCsvExport     = new MenuItem("csv-Export");
    //-------Ende Attribute der grafischen Oberflaeche-------

    // speichert temporaer ein Objekt vom Typ Bibliothek
    private Bibliothek bibliothek;

    public BibliothekenAnwendungssystem(Stage primaryStage) {
        Scene scene = new Scene(this.pane, 700, 340);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Verwaltung von Bibliotheken");
        primaryStage.show();
        this.initKomponenten();
        this.initListener();
    }

    private void initKomponenten() {
        // Labels
        lblEingabe.setLayoutX(20);
        lblEingabe.setLayoutY(40);
        Font font = new Font("Arial", 24);
        lblEingabe.setFont(font);
        lblEingabe.setStyle("-fx-font-weight: bold;");
        lblAnzeige.setLayoutX(400);
        lblAnzeige.setLayoutY(40);
        lblAnzeige.setFont(font);
        lblAnzeige.setStyle("-fx-font-weight: bold;");
        lblName.setLayoutX(20);
        lblName.setLayoutY(90);
        lblOrt.setLayoutX(20);
        lblOrt.setLayoutY(130);
        lblAnzahlBuecher.setLayoutX(20);
        lblAnzahlBuecher.setLayoutY(170);
        lblAnzahlLeser.setLayoutX(20);
        lblAnzahlLeser.setLayoutY(210);
        lblAbteilungen.setLayoutX(20);
        lblAbteilungen.setLayoutY(250);
        pane.getChildren().addAll(lblEingabe, lblAnzeige,
            lblName, lblOrt, lblAnzahlBuecher,
            lblAnzahlLeser, lblAbteilungen);

        // Textfelder
        txtName.setLayoutX(170);
        txtName.setLayoutY(90);
        txtName.setPrefWidth(200);
        txtOrt.setLayoutX(170);
        txtOrt.setLayoutY(130);
        txtOrt.setPrefWidth(200);
        txtAnzahlBuecher.setLayoutX(170);
        txtAnzahlBuecher.setLayoutY(170);
        txtAnzahlBuecher.setPrefWidth(200);
        txtAnzahlLeser.setLayoutX(170);
        txtAnzahlLeser.setLayoutY(210);
        txtAnzahlLeser.setPrefWidth(200);
        txtAbteilungen.setLayoutX(170);
        txtAbteilungen.setLayoutY(250);
        txtAbteilungen.setPrefWidth(200);
        pane.getChildren().addAll(
            txtName, txtOrt, txtAnzahlBuecher,
            txtAnzahlLeser, txtAbteilungen);

        // Textbereich
        txtAnzeige.setEditable(false);
        txtAnzeige.setLayoutX(400);
        txtAnzeige.setLayoutY(90);
        txtAnzeige.setPrefWidth(270);
        txtAnzeige.setPrefHeight(185);
        pane.getChildren().add(txtAnzeige);

        // Buttons
        btnEingabe.setLayoutX(20);
        btnEingabe.setLayoutY(290);
        btnAnzeige.setLayoutX(400);
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnEingabe, btnAnzeige);

        // Menue
        this.mnbrMenuLeiste.getMenus().add(mnDatei);
        this.mnDatei.getItems().add(mnItmCsvImport);
        this.mnDatei.getItems().add(mnItmTxtImport);
        this.mnDatei.getItems().add(new SeparatorMenuItem());
        this.mnDatei.getItems().add(mnItmCsvExport);
        pane.getChildren().add(mnbrMenuLeiste);
    }

    private void initListener() {
        btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                nehmeBibliothekAuf();
            }
        });
        btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                zeigeBibliothekenAn();
            }
        });
        mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                leseAusDatei("csv");
            }
        });
        mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                leseAusDatei("txt");
            }
        });
        mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                schreibeBibliothekenInCsvDatei();
            }
        });
    }

    private void nehmeBibliothekAuf() {
        try {
            // 1. Eingaben aus den Textfeldern holen
            String name          = txtName.getText();
            String ort           = txtOrt.getText();
            int anzahlBuecher    = Integer.parseInt(txtAnzahlBuecher.getText());
            int anzahlLeser      = Integer.parseInt(txtAnzahlLeser.getText());
            String[] abteilungen = txtAbteilungen.getText().split(";");

            // 2. Bibliothek erzeugen und im Attribut speichern
            this.bibliothek = new Bibliothek(name, ort, anzahlBuecher, anzahlLeser, abteilungen);

            // 3. Erfolgsmeldung
            zeigeInformationsfensterAn("Die Bibliothek wurde aufgenommen!");
        }
        catch (Exception exc) {
            // z. B. Buchstaben statt Zahl
            zeigeFehlermeldungsfensterAn(exc.getMessage());
        }
    }

    private void zeigeBibliothekenAn() {
        // anzeigen oder Hinweis geben
        if (this.bibliothek != null) {
            txtAnzeige.setText(this.bibliothek.gibBibliothekZurueck(' '));
        }
        else {
            zeigeInformationsfensterAn("Bisher wurde keine Bibliothek aufgenommen!");
        }
    }

    private void leseAusDatei(String typ) {
        try {
            if ("csv".equals(typ)) {
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

                // 4. Erfolgsmeldung
                zeigeInformationsfensterAn("Die Bibliothek wurde gelesen!");
            }
            else {
                zeigeInformationsfensterAn("Noch nicht implementiert!");
            }
        }
        catch (IOException exc) {
            // Datei fehlt oder ist nicht lesbar
            zeigeFehlermeldungsfensterAn("IOException beim Lesen!");
        }
        catch (Exception exc) {
            // z. B. falsches Format in der Datei
            zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Lesen!");
        }
    }

    private void schreibeBibliothekenInCsvDatei() {
        try {
            // 1. Datei zum Anhängen öffnen (true = anhängen)
            BufferedWriter aus = new BufferedWriter(new FileWriter("BibliothekenAusgabe.csv", true));

            // 2. Text der Bibliothek mit ';' als Trenner schreiben
            aus.write(this.bibliothek.gibBibliothekZurueck(';'));

            // 3. Datei schließen
            aus.close();

            // 4. Erfolgsmeldung
            zeigeInformationsfensterAn("Die Bibliotheken wurden gespeichert!");
        }
        catch (IOException exc) {
            // Datei kann nicht geschrieben werden
            zeigeFehlermeldungsfensterAn("IOException beim Speichern!");
        }
        catch (Exception exc) {
            // z. B. noch keine Bibliothek aufgenommen
            zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Speichern!");
        }
    }

    private void zeigeInformationsfensterAn(String meldung) {
        MeldungsfensterAnzeiger anzeiger =
            new MeldungsfensterAnzeiger(AlertType.INFORMATION, "Information", meldung);
        anzeiger.zeigeMeldungsfensterAn();
    }

    private void zeigeFehlermeldungsfensterAn(String meldung) {
        MeldungsfensterAnzeiger anzeiger =
            new MeldungsfensterAnzeiger(AlertType.ERROR, "Fehler", meldung);
        anzeiger.zeigeMeldungsfensterAn();
    }
}