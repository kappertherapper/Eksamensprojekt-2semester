package gui;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Fad;
import model.Hylde;
import model.Lager;
import model.Række;

import java.io.Closeable;
import java.io.IOException;
import java.util.ConcurrentModificationException;

public class FlytFadGuiController {
    @FXML
    private Button btnFlytFad;

    @FXML
    private Label lblHylde;

    @FXML
    private Label lblLager;

    @FXML
    private Label lblRække;

    @FXML
    private Label lblFade;
    @FXML
    private Pane pnFlytFad;

    @FXML
    private ListView<Lager> lvwLager;

    @FXML
    private ListView<Fad> lvwFade;
    @FXML
    private TextField txfHylde;

    @FXML
    private TextField txfRække;

    //---------------------------------------------------------------------

    @FXML
    void flytFadAction(ActionEvent event) throws IOException {
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        Lager lager = lvwLager.getSelectionModel().getSelectedItem();
        try {
            int rækker = Integer.valueOf(txfRække.getText());
            int hylde = Integer.valueOf(txfHylde.getText());

            for (Lager lag : Controller.getLagere()) {
                for (Række række : lag.getRækker()) {
                    for (Hylde hyld : række.getHylder()) {
                        if (!hyld.getFade().isEmpty()) {
                            for (int i = 0; i < hyld.getFade().size(); i++) {
                                if (hyld.getFade().get(i).equals(fad)) {
                                    hyld.removeFad(fad);
                                }
                            }
                        }
                    }
                }
            }
            Controller.addFadTilHylde(fad, hylde, rækker, lager);
            txfRække.clear();
            txfHylde.clear();
            lvwLager.getSelectionModel().clearSelection();
            lvwFade.getSelectionModel().clearSelection();
            ((Stage) pnFlytFad.getScene().getWindow()).close();
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(pnFlytFad.getScene().getWindow());
            alert.setTitle("Format error");
            alert.setHeaderText("fejl i række eller hyde tekst felt");
            alert.show();
        } catch (IllegalArgumentException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(pnFlytFad.getScene().getWindow());
            alert.setTitle(ex.getMessage());
            alert.setHeaderText("hylden er fuld");
            alert.show();
        }
    }

    //---------------------------------------------------------------------

    public void initialize(){
        lvwFade.getItems().addAll(Controller.getFade());
        lvwLager.getItems().addAll(Controller.getLagere());
    }


}
