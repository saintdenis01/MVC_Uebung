package gui;

import business.Bibliothek;
import business.BibliothekenModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.MeldungsfensterAnzeiger;

public class BibliothekenView {

    private BibliothekenControl bibliothekenControl;
    private BibliothekenModel bibliothekenModel;

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

    public BibliothekenView(BibliothekenControl bibliothekenControl,
        Stage primaryStage, BibliothekenModel bibliothekenModel) {
        this.bibliothekenControl = bibliothekenControl;
        this.bibliothekenModel = bibliothekenModel;
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
                bibliothekenControl.nehmeBibliothekAuf();
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
                bibliothekenControl.leseAusDatei("csv");
            }
        });
        mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                bibliothekenControl.leseAusDatei("txt");
            }
        });
        mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                bibliothekenControl.schreibeBibliothekenInCsvDatei();
            }
        });
    }

    private void zeigeBibliothekenAn() {
        // 1. Bibliothek aus dem Model holen
        Bibliothek bibliothek = this.bibliothekenModel.getBibliothek();

        // 2. anzeigen oder Hinweis geben
        if (bibliothek != null) {
            txtAnzeige.setText(bibliothek.gibBibliothekZurueck(' '));
        }
        else {
            zeigeInformationsfensterAn("Bisher wurde keine Bibliothek aufgenommen!");
        }
    }

    public void zeigeInformationsfensterAn(String meldung) {
        MeldungsfensterAnzeiger anzeiger =
            new MeldungsfensterAnzeiger(AlertType.INFORMATION, "Information", meldung);
        anzeiger.zeigeMeldungsfensterAn();
    }

    public void zeigeFehlermeldungsfensterAn(String meldung) {
        MeldungsfensterAnzeiger anzeiger =
            new MeldungsfensterAnzeiger(AlertType.ERROR, "Fehler", meldung);
        anzeiger.zeigeMeldungsfensterAn();
    }

    public TextField getTxtName() {
        return txtName;
    }

    public TextField getTxtOrt() {
        return txtOrt;
    }

    public TextField getTxtAnzahlBuecher() {
        return txtAnzahlBuecher;
    }

    public TextField getTxtAnzahlLeser() {
        return txtAnzahlLeser;
    }

    public TextField getTxtAbteilungen() {
        return txtAbteilungen;
    }
}
