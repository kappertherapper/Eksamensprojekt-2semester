package gui;

import controller.Controller;
import controller.Storage;
import javafx.application.Application;
import model.*;
import storage.ListStorage;

import java.time.LocalDate;
import java.util.List;


public class App {
    public static void main(String[] args) {
        Storage storage = ListStorage.loadStorage();

        if (storage == null) {
            storage = new ListStorage();
            System.out.println("Empty ListStorage created");
        }
        Controller.setStorage(storage);

        if (Controller.getLagere().isEmpty()) {
            initStorage();
            System.out.println("Storage initialized.");
        }
        Application.launch(Gui.class);
        ListStorage.saveStorage(storage);
    }

    //---------------------------------------------------------------------

    public static void initStorage() {
         // Fade
        Fad fad1 = Controller.opretFad("Frankrig", "Sherry", 3, 60);
        Fad fad2 = Controller.opretFad("Frankrig", "Bourbon", 4, 90);
        Fad fad3 = Controller.opretFad("Italien", "Rødvin", 5, 120);

        // Lager
        Lager lager1 = Controller.opretLager("Mosevej 3", "Snævar's baggård", 5, 3, 5);
        Lager lager2 = Controller.opretLager("Blomstervej 17", "Depot Lager", 7, 3, 7);

        // Tilføj fad til lager
        Controller.addFadTilHylde(fad1, 1, 1, lager1);
        Controller.addFadTilHylde(fad2, 1, 1, lager1);
        Controller.addFadTilHylde(fad3, 1, 1, lager2);

        // Destillat
        Destillat d1 = Controller.opretDestillat("Corny Black Roast", "Byg","Snævar", 63.3, "Egetræ", "God batch",
                "NM-34A", LocalDate.of(2020,01,01), LocalDate.of(2020,01,04), 120);
        Destillat d2 = Controller.opretDestillat("Blonde Peachy Almond", "Byg","Niels", 70.7, "Egetræ", "Stærk batch",
                "NM-34B", LocalDate.of(2020,01,01), LocalDate.of(2020,01,04), 80);
        Destillat d3 = Controller.opretDestillat("Roasted Brown Nuts", "Byg","Snævar", 55.4, "Egetræ", "OK batch",
                "NM-56A", LocalDate.of(2023,11,25), LocalDate.of(2023,11,28), 150);

        // Destillat til påfyldning
        DestillatTilPåfyldning dtp = Controller.opretDestillatTilPåfyldning(d1, 120);

        // Påfyldning
        Påfyldning p = Controller.opretPåfyldning(List.of(dtp), fad3, LocalDate.of(2020,01,04), "Snævar");
    }
}
