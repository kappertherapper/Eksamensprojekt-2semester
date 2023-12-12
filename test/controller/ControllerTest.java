package controller;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    @BeforeEach
    public void setup() {
        Controller.setStorage(new ListStorage());
    }

    @Test
    void opretDestillat() {

        //Arrange
        Controller.opretDestillat("Blended", "Bygsort", "Lars", 0, null, "Nice", "nm1", LocalDate.of(2023, 12, 1), LocalDate.of(2026, 12, 2), 130);

        //Act
        double expected = 1;
        double actual = Controller.getDestillater().size();

        //Assert
        assertEquals(expected, actual);
    }

    //---------------------------------------------------------------------

    @Test
    void opretFad() {

        //Arrange
        Controller.opretFad("Italien", "Bourbon", 1, 80.00);

        //Act
        double resultat = 1;

        //Assert
        assertEquals(resultat, Controller.getFade().size());
    }

    //---------------------------------------------------------------------

    @Test
    void beregnProcentAlkohol() {
        //Arrange
        Fad f = Controller.opretFad("Frankrig", "Sherry", 1, 100);
        Destillat nm1 = Controller.opretDestillat("Blended", "Bygsort", "Lars", 40, null, "Nice", "nm1",
                LocalDate.of(2023,12,1), LocalDate.of(2026,12,2), 10);
        Destillat nm2 = Controller.opretDestillat("Blended", "Bygsort", "Lars", 60, null, "Nice", "nm2",
                LocalDate.of(2023, 12, 1), LocalDate.of(2026, 12, 2), 15);

        DestillatTilPåfyldning d1 = Controller.opretDestillatTilPåfyldning(nm1, 10);
        DestillatTilPåfyldning d2 = Controller.opretDestillatTilPåfyldning(nm2, 15);

        Påfyldning påfyldning = Controller.opretPåfyldning(List.of(d1, d2), f, LocalDate.of(2023, 12, 06), "Lars");

        //Assert
        assertEquals(52, påfyldning.getAlkoholProcent());
    }

    //---------------------------------------------------------------------

    @Test
    void findLokationPåFad() {
        //Arrange
        Lager lager = Controller.opretLager("Sall Whisky", "Lager 1", 3, 3,6);
        Fad fad = Controller.opretFad("Italien", "Bourbon", 1, 80.00);
        Fad fad2 = Controller.opretFad("Frankrig", "Sherry", 2, 135.00);

        Controller.addFadTilHylde(fad, 2, 2, lager);
        Controller.findLokationPåFad(fad);


        //Act
        /**
         * T1
         */
        String expected = "Lager: Lager 1, Række: 2, Hylde: 2";
        String actual = Controller.findLokationPåFad(fad);

        /**
         * T2
         */
        String error = "Fad ikke fundet i Lageret.";
        String actual2 = Controller.findLokationPåFad(fad2);


        //Assert
        assertEquals(expected, actual);
        assertEquals(error, actual2);

    }

    //---------------------------------------------------------------------

    @Test
    void treAarGammel() {
        //Arrange
        Fad fad = Controller.opretFad("Italien", "Bourbon", 1, 80.00);
        Destillat nm1 = Controller.opretDestillat("Blended", "Bygsort", "Lars", 40, null, "Nice", "nm1",
                LocalDate.of(2023,12,1), LocalDate.of(2026,12,2), 10);
        DestillatTilPåfyldning d1 = Controller.opretDestillatTilPåfyldning(nm1, 10);
        Påfyldning påfyldning = Controller.opretPåfyldning(List.of(d1), fad, LocalDate.of(2019, 12, 06), "Lars");

        //Act
        List<Fad> result = Controller.treAarGammel();

        //Assert
        assertEquals(1, result.size());
    }

    //---------------------------------------------------------------------

}
