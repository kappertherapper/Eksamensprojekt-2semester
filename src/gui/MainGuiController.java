package gui;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class MainGuiController {
    @FXML
    private Button btnFlytFad;
    @FXML
    private ImageView imgSall;

    @FXML
    private Button btnOpdaterAlkohol;

    @FXML
    private Button btnOpdaterMængde;

    @FXML
    private Button btnOpretDestillat;

    @FXML
    private Button btnOpretFad;

    @FXML
    private Button btnOpretLager;

    @FXML
    private Button btnOpretPåfyldning;

    @FXML
    private Button btnTilføjDestillat;

    @FXML
    private Button btnTilføjFadTilLager;

    @FXML
    private Button btnTilføjWhisky;

    @FXML
    private ImageView imgLogo;

    @FXML
    private TabPane guiStage;

    @FXML
    private Label lblAdresse;

    @FXML
    private Label lblAlkoholProcent;

    @FXML
    private Label lblDestillatPå;

    @FXML
    private Label lblDestillater;

    @FXML
    private Label lblDestillaterTilPåfyldning;

    @FXML
    private Label lblFadNr;

    @FXML
    private Label lblFadOprindelse;

    @FXML
    private Label lblFadPå;

    @FXML
    private Label lblFadStørrelse;

    @FXML
    private Label lblFadIkkeLager;

    @FXML
    private Label lblFadType;

    @FXML
    private Label lblFade;

    @FXML
    private Label lblFadeWhisky;

    @FXML
    private Label lblHyldeNr;

    @FXML
    private Label lblHylder;

    @FXML
    private Label lblInfoDestillat;

    @FXML
    private Label lblInfoFad;

    @FXML
    private Label lblKommentar;

    @FXML
    private Label lblKornsort;

    @FXML
    private Label lblLager;

    @FXML
    private Label lblLagre;

    @FXML
    private Label lblMaltBatch;

    @FXML
    private Label lblMedarbejder;

    @FXML
    private Label lblMængdeILiter;

    @FXML
    private Label lblNavn;

    @FXML
    private Label lblNewMakeNr;

    @FXML
    private Label lblPlads;

    @FXML
    private Label lblRygeMateriale;

    @FXML
    private Label lblRækkeNr;

    @FXML
    private Label lblRækker;

    @FXML
    private Label lblSlutDato;

    @FXML
    private Label lblStartDato;

    @FXML
    private Label lblStartVolume;

    @FXML
    private Label lblVand;

    @FXML
    private Label lblWhiskyInfo;

    @FXML
    private Label lblWhiskyer;

    @FXML
    private ComboBox<Lager> lstLager;

    @FXML
    private ListView<Destillat> lvwDestillatPå;


    @FXML
    private ListView<Fad> lvwFadeIkkePåLager;

    @FXML
    private ListView<Fad> lvwFade;


    @FXML
    private ListView<Fad> lvwFadeLager;

    @FXML
    private ListView<Destillat> lvwDestillater;

    @FXML
    private ListView<DestillatTilPåfyldning> lvwDestillaterTilPåfyldning;

    @FXML
    private ListView<Fad> lvwFadPå;

    @FXML
    private ListView<Fad> lvwFadeWhisky;

    @FXML
    private ListView<Lager> lvwLagre;

    @FXML
    private ListView<Whisky> lvwWhiskyer;

    @FXML
    private AnchorPane pnDestillater;

    @FXML
    private AnchorPane pnFade;

    @FXML
    private AnchorPane pnLager;

    @FXML
    private AnchorPane pnPåfyldning;

    @FXML
    private AnchorPane pnWhisky;

    @FXML
    private Tab tabDestilleringer;

    @FXML
    private Tab tabFade;

    @FXML
    private Tab tabLager;

    @FXML
    private Tab tabPåfyldning;

    @FXML
    private Tab tabWhisky;

    @FXML
    private TextArea txaInfoDestillat;

    @FXML
    private TextArea txaInfoFad;

    @FXML
    private TextArea txaWhiskyInfo;

    @FXML
    private TextField txfAdresse;

    @FXML
    private TextField txfAlkoholProcent;

    @FXML
    private TextField txfVand;

    @FXML
    private TextField txfFadNr;

    @FXML
    private TextField txfFadOprindelse;

    @FXML
    private TextField txfFadStørrelse;

    @FXML
    private TextField txfFadType;

    @FXML
    private TextField txfHylde;

    @FXML
    private TextField txfHyldeNr;

    @FXML
    private TextField txfHylder;

    @FXML
    private TextField txfKommentar;

    @FXML
    private TextField txfKornsort;

    @FXML
    private TextField txfMaltBatch;

    @FXML
    private TextField txfMedarbejder;

    @FXML
    private TextField txfMedarbejderPå;

    @FXML
    private TextField txfNavn;

    @FXML
    private TextField txfNewMakeNr;

    @FXML
    private TextField txfRygeMateriale;

    @FXML
    private TextField txfRækkeNr;

    @FXML
    private TextField txfAlkoholProcentLager;

    @FXML
    private TextField txfOpdaterMængdeLager;

    @FXML
    private TextField txfRækker;

    @FXML
    private DatePicker txfSlutDato;


    @FXML
    private DatePicker txfStartDato;

    @FXML
    private DatePicker txfStartDatoPåfyld;

    @FXML
    private TextField txfStartVolume;

    @FXML
    private TextField txfVolumen;

    //---------------------------------------------------------------------

    @FXML
    void opretDestillatAction(ActionEvent event) {
        try {
            double alkoholProcent = Double.valueOf(txfAlkoholProcent.getText());
            double startVolume = Double.valueOf(txfStartVolume.getText());
            LocalDate start = txfStartDato.getValue();
            LocalDate slut = txfSlutDato.getValue();

            if (alkoholProcent > 100 || alkoholProcent < 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(guiStage.getScene().getWindow());
                alert.setTitle("Procent Error");
                alert.setHeaderText("Alkohol procent skal være mellem 0 og 100.");
                alert.show();
            } else if (start.isAfter(slut)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(guiStage.getScene().getWindow());
                alert.setTitle("Date Error");
                alert.setHeaderText("Start dato skal være før slut dato.");
                alert.show();
            } else {
                Destillat d = Controller.opretDestillat(txfMaltBatch.getText(), txfKornsort.getText(), txfMedarbejder.getText(),
                        alkoholProcent, txfRygeMateriale.getText(), txfKommentar.getText(),
                        txfNewMakeNr.getText(), start, slut, startVolume);
                lvwDestillater.getItems().add(d);
                lvwDestillatPå.getItems().add(d);
                txfMaltBatch.clear();
                txfKornsort.clear();
                txfMedarbejder.clear();
                txfAlkoholProcent.clear();
                txfRygeMateriale.clear();
                txfKommentar.clear();
                txfNewMakeNr.clear();
                txfStartVolume.clear();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText("Fejl i alkohol procent ellser start volume.");
            alert.show();
        } catch (DateTimeParseException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText("fejl i dato.");
            alert.show();
        }
    }

    //---------------------------------------------------------------------

    @FXML
    void opretFadAction(ActionEvent event) {
        try {
            int fadNr = Integer.valueOf(txfFadNr.getText());
            double fadStørrelse = Double.valueOf(txfFadStørrelse.getText());
            Fad f = Controller.opretFad(txfFadOprindelse.getText(), txfFadType.getText(), fadNr, fadStørrelse);

            lvwFade.getItems().add(f);
            lvwFadPå.getItems().add(f);
            lvwFadeIkkePåLager.getItems().add(f);
            txfFadNr.clear();
            txfFadStørrelse.clear();
            txfFadOprindelse.clear();
            txfFadType.clear();

        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText("Fejl i Fad nr eller størrelse");
            alert.show();
        }
    }

    //---------------------------------------------------------------------

    @FXML
    void opretLagerAction(ActionEvent event) {
        try {
            int rækker = Integer.valueOf(txfRækker.getText());
            int hylder = Integer.valueOf(txfHylder.getText());
            int pladsHylde = Integer.valueOf(txfHylde.getText());

            String adresse = txfAdresse.getText();
            String navn = txfNavn.getText();
            if (adresse == null || navn == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(guiStage.getScene().getWindow());
                alert.setTitle("Indtastnings Fejl");
                alert.setHeaderText("Indtast venlist en adresse og et navn");
                alert.show();
            } else {
                Lager l = Controller.opretLager(adresse, navn, rækker, hylder, pladsHylde);
                lvwLagre.getItems().add(l);
                lstLager.getItems().add(l);
                txfRækker.clear();
                txfHylder.clear();
                txfHylde.clear();
                txfAdresse.clear();
                txfNavn.clear();
            }
        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText("Fejl i indtastning");
            alert.show();
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText("Fejl række, hylde  eller plads på hylden");
            alert.show();
        }
    }

    //---------------------------------------------------------------------

    @FXML
    void opretPåfyldningAction(ActionEvent event) {
        if (lvwDestillaterTilPåfyldning.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Indtastnings Fejl");
            alert.setHeaderText("Destillat ikke valgt");
            alert.show();
            return;
        }

        try {
            List<DestillatTilPåfyldning> destillat = lvwDestillaterTilPåfyldning.getItems();
            Fad fad = lvwFadPå.getSelectionModel().getSelectedItem(); // måske ikke korrekt
            LocalDate startDato = txfStartDatoPåfyld.getValue();
            String medarbejder = txfMedarbejderPå.getText();

            for (DestillatTilPåfyldning destillatTilPåfyldning : destillat) {
                if (startDato.isBefore(destillatTilPåfyldning.getDestillat().getSlutDato())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(guiStage.getScene().getWindow());
                    alert.setTitle("Date Error");
                    alert.setHeaderText("Start dato skal efter detillatets slut dato");
                    alert.show();
                }
            }
            if (medarbejder == null || medarbejder.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(guiStage.getScene().getWindow());
                alert.setTitle("Indtastnings Fejl");
                alert.setHeaderText("Indtast venlist en medarbejder");
                alert.show();
            } else {
                Påfyldning p = Controller.opretPåfyldning(destillat, fad, startDato, medarbejder);
                if (p.getStartDato().plusYears(3).isBefore(LocalDate.now())) {
                    lvwFadeWhisky.getItems().add(fad);
                    lvwDestillaterTilPåfyldning.getItems().clear();
                    for (int i = 0; i < lvwDestillatPå.getItems().size(); i++){
                        if (lvwDestillatPå.getItems().get(i).getMængdeLiter() == 0){
                            lvwDestillatPå.getItems().remove(lvwDestillatPå.getItems().get(i));
                        }
                    }
                } else {
                    lvwDestillaterTilPåfyldning.getItems().clear();
                    lvwDestillatPå.getItems().clear();
                }
            }
        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText("Null pointer exception");
            alert.show();
        } catch (DateTimeParseException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText("Fejl i Dato indtastning");
            alert.show();

        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
    }

    //---------------------------------------------------------------------

    @FXML
    void tilføjFadTilLagerAction(ActionEvent event) {
        Lager lager = lvwLagre.getSelectionModel().getSelectedItem();
        Fad fadTilLager = lvwFadeIkkePåLager.getSelectionModel().getSelectedItem();

        try {
            int hylde = Integer.valueOf(txfHyldeNr.getText());
            int række = Integer.valueOf(txfRækkeNr.getText());

            Controller.addFadTilHylde(fadTilLager, hylde, række, lager);
            txfRækkeNr.clear();
            txfHyldeNr.clear();
            lvwFadeIkkePåLager.getSelectionModel().clearSelection();
            //lvwLagre.getSelectionModel().clearSelection();
            lvwFadeIkkePåLager.getItems().remove(fadTilLager);
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText("Fejl i række eller hylde nummer");
            alert.show();
        } catch (IndexOutOfBoundsException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText("Fejl række eller hylde nr existere ikke");
            alert.show();
        } catch (IllegalArgumentException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText(ex.getMessage());
            alert.show();
        }
        setFadePåLagerListe(event);
    }

    //---------------------------------------------------------------------

    @FXML
    void tilføjWhiskyAction(ActionEvent event) {
        if (lvwFadeWhisky.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Indtastnings Fejl");
            alert.setHeaderText("Fad ikke valgt");
            alert.show();
            return;
        }

        try {
            List<Fad> fade = lvwFadeWhisky.getSelectionModel().getSelectedItems();
            double vand = Double.valueOf(txfVand.getText());
            Whisky w = Controller.opretWhisky(fade, vand);
            lvwWhiskyer.getItems().add(w);
            for (int i = 0; i < fade.size(); i++) {
                lvwFadeWhisky.getItems().remove(fade.get(i));
            }
            lvwFadeWhisky.getSelectionModel().clearSelection();
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText("Fejl i mængde af vand");
            alert.show();
        }
    }

    //---------------------------------------------------------------------

    @FXML
    void opretDestillatTilPåfyldningAction(ActionEvent event) {
        try {
            double samletVolume = Double.valueOf(txfVolumen.getText());
            for (DestillatTilPåfyldning destillat : lvwDestillaterTilPåfyldning.getItems()) {
                if (destillat.getDestillat().equals(lvwDestillatPå.getSelectionModel().getSelectedItem())) {
                    samletVolume += destillat.getMængdeLiter();
                    if (samletVolume > destillat.getDestillat().getMængdeLiter()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.initOwner(guiStage.getScene().getWindow());
                        alert.setTitle("Format Error");
                        alert.setHeaderText("Der er ikke nok af dette Destillat");
                        alert.show();
                    }
                }
            }

            Destillat destillat = lvwDestillatPå.getSelectionModel().getSelectedItem();
            double mængdeLiter = Double.valueOf(txfVolumen.getText());
            DestillatTilPåfyldning d = Controller.opretDestillatTilPåfyldning(destillat, mængdeLiter);
            lvwDestillaterTilPåfyldning.getItems().add(d);
            txfVolumen.clear();
            lvwDestillatPå.getSelectionModel().clearSelection();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText("Fejl i Volumen");
            alert.show();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
    }


    //---------------------------------------------------------------------

    @FXML
    void showInfoDestillat(MouseEvent event) {
        try {
            txaInfoDestillat.clear();
            Destillat d = lvwDestillater.getSelectionModel().getSelectedItem();
            txaInfoDestillat.insertText(0, d.destillatInfo());
            lvwDestillater.getSelectionModel().clearSelection();
        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Null point exception");
            alert.setHeaderText("Du har ikke valgt et destillat");
            alert.show();
        }
    }

    //---------------------------------------------------------------------

    public void initialize() {
        lvwFade.getItems().addAll(Controller.getFade());
        ArrayList<Fad> fade = (ArrayList<Fad>) Controller.getFade();

        for (Fad fad : Controller.getFade()) {
            for (Lager lager : Controller.getLagere()) {
                for (Række række : lager.getRækker()) {
                    for (Hylde hylde : række.getHylder()) {
                        if (!hylde.getFade().isEmpty()) {
                            for (Fad hyldefad : hylde.getFade()) {
                                if (hyldefad.equals(fad)) {
                                    fade.remove(fad);
                                }
                            }
                        }
                    }
                }
            }
        }

        lvwFadeIkkePåLager.getItems().addAll(fade);
        lvwFadeWhisky.getItems().addAll(Controller.treAarGammel());
        lvwFadeWhisky.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvwDestillater.getItems().addAll(Controller.getDestillater());

        for (Destillat destillat : Controller.getDestillater()) {
            if (destillat.getMængdeLiter() > 0) {
                lvwDestillatPå.getItems().add(destillat);
            }
        }

        lvwLagre.getItems().addAll(Controller.getLagere());
        lstLager.getItems().addAll(Controller.getLagere());
        lvwWhiskyer.getItems().addAll(Controller.getWhisker());

        // tab change
        /*tabFade.setOnSelectionChanged (e -> { det her er cursed og ødelægger GUI, ved ikke hvordan
            updateWhisky((ActionEvent) e);
            updateDestilleringer((ActionEvent) e);
            updateLager((ActionEvent) e);
            updatePåfyldning((ActionEvent) e);
            updateDestilleringer((ActionEvent) e);
                }
        );*/
    }

    //---------------------------------------------------------------------

    @FXML
    void whiskyInfoAction(MouseEvent event) {
        try {
            txaWhiskyInfo.clear();
            Whisky d = lvwWhiskyer.getSelectionModel().getSelectedItem();
            txaWhiskyInfo.insertText(0, d.toEtikette());
            lvwWhiskyer.getSelectionModel().clearSelection();
        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Null point exception");
            alert.setHeaderText("Du har ikke valgt en whisky");
            alert.show();
        }
    }

    //---------------------------------------------------------------------

    @FXML
    void opdaterAlkoholProcentAction(ActionEvent event) {
        try {
            double alkohol = Double.valueOf(txfAlkoholProcentLager.getText());
            Fad fad = lvwFadeLager.getSelectionModel().getSelectedItem();

            if (alkohol > 100 || 0 > alkohol) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(guiStage.getScene().getWindow());
                alert.setTitle("Format Error");
                alert.setHeaderText("Indtast gyldig alkohol procent");
                alert.show();
            } else {
                Controller.opdaterAlkoholProcent(alkohol, fad.getPåfyldning());
                txfAlkoholProcentLager.clear();
                lvwFadeLager.getSelectionModel().clearSelection();
            }
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText("Indtast gyldig alkohol procent");
            alert.show();
        } catch (NullPointerException ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Ingen påfyldning");
            alert.setHeaderText("Der er ikke en påfyldning på dette fad");
            alert.show();
        }
    }

    //---------------------------------------------------------------------

    @FXML
    void opdaterMængdeAction(ActionEvent event) {
        try {
            double mængde = Double.valueOf(txfOpdaterMængdeLager.getText());
            Fad fad = lvwFadeLager.getSelectionModel().getSelectedItem();

            Controller.opdaterMængdeIFad(fad, mængde);

            txfOpdaterMængdeLager.clear();
            lvwFade.getItems().clear();
            lvwFade.getItems().addAll(Controller.getFade());
            lvwFadeLager.getSelectionModel().clearSelection();
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText("Indtast gyldig mængde væske");
            alert.show();
        } catch (IllegalArgumentException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Format Error");
            alert.setHeaderText(ex.getMessage());
            alert.show();
        }
    }

    //---------------------------------------------------------------------

    @FXML
    void lagerInfoAction(ActionEvent event) {
        lvwFadPå.getItems().clear();
        Lager lager = lstLager.getSelectionModel().getSelectedItem();
        for (Række række : lager.getRækker()) {
            for (Hylde hylde : række.getHylder()) {
                if (!hylde.getFade().isEmpty()) {
                    for (Fad fad : hylde.getFade()) {
                        lvwFadPå.getItems().add(fad);
                    }
                }
            }
        }
    }

    //---------------------------------------------------------------------

    @FXML
    void setFadePåLagerListe(Event e) {
        lvwFadeLager.getItems().clear();
        Lager l = lvwLagre.getSelectionModel().getSelectedItem();
        List<Fad> fadePåLager = new ArrayList<>();
        for (Række r : l.getRækker()) {
            for (Hylde h : r.getHylder()) {
                fadePåLager.addAll(h.getFade());
            }
        }
        lvwFadeLager.getItems().addAll(fadePåLager);

        lvwFadeLager.setCellFactory(lvw -> new ListCell<Fad>() {
            @Override
            protected void updateItem(Fad fad, boolean empty) {
                super.updateItem(fad, empty);
                if (empty || fad == null) {
                    setText(null);
                } else {
                    String text = fad + Controller.findLokationPåFad(fad);
                    setText(text);
                }
            }
        });
    }

    //---------------------------------------------------------------------

    @FXML
    void fadInfoAction(MouseEvent event) {
        try {
            txaInfoFad.clear();
            Fad f = lvwFade.getSelectionModel().getSelectedItem();
            txaInfoFad.insertText(0, f.toStringLong());
            lvwFade.getSelectionModel().clearSelection();
        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(guiStage.getScene().getWindow());
            alert.setTitle("Null point exception");
            alert.setHeaderText("Du har ikke valgt et fad");
            alert.show();
        }
    }

    //---------------------------------------------------------------------

    @FXML
    void flytFadAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FlytFadGui.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 450, 450));
        stage.showAndWait();
    }

    //---------------------------------------------------------------------



/*
    @FXML
    void updateDestilleringer(ActionEvent event) {
        txfMaltBatch.clear();
        txfKornsort.clear();
        txfMedarbejder.clear();
        txfAlkoholProcent.clear();
        txfRygeMateriale.clear();
        txfKommentar.clear();
        txfNewMakeNr.clear();
        txfStartDato.clear();
        txfSlutDato.clear();
        txfStartVolume.clear();
        lvwDestillater.getSelectionModel().clearSelection();
    }

    @FXML
    void updateFade(ActionEvent event) {
        txfFadOprindelse.clear();
        txfFadType.clear();
        txfFadStørrelse.clear();
        txfFadNr.clear();
        lvwFade.getSelectionModel().clearSelection();

    }

    @FXML
    void updateLager(ActionEvent event) {
        txfRækker.clear();
        txfHylder.clear();
        txfHylde.clear();
        txfAdresse.clear();
        txfNavn.clear();
        txfRækkeNr.clear();
        txfHyldeNr.clear();
        txfOpdaterMængdeLager.clear();
        txfAlkoholProcentLager.clear();
        lvwLagre.getSelectionModel().clearSelection();
        lvwFadeLager.getSelectionModel().clearSelection();

    }

    @FXML
    void updatePåfyldning(ActionEvent event) {
        txfVolumen.clear();
        txfStartDatoPåfyld.clear();
        txfMedarbejderPå.clear();
        lvwDestillaterTilPåfyldning.getItems().clear();
        lvwDestillatPå.getSelectionModel().clearSelection();
        lstLager.getSelectionModel().clearSelection();
        lvwFadPå.getSelectionModel().clearSelection();

    }

    @FXML
    void updateWhisky(ActionEvent event) {
        txfVand.clear();
        lvwFadeWhisky.getSelectionModel().clearSelection();
        lvwWhiskyer.getSelectionModel().clearSelection();

    }

*/


}
