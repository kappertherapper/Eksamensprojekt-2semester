package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {

    @Test
    void updateVolumen() {
        Fad et = new Fad("Italien", "Bourbon", 1, 80.00);
        Fad to = new Fad("Frankrig", "Sherry", 2, 135.00);

        //Arrange
        et.updatenuværendeMængdeLiter(50);

        //Act
        double resultat = 50;

        //Assert

        /**
         * T1
         */
        double forventetResultat = et.getNuværendeMængdeLiter();
        assertEquals(forventetResultat, resultat);

        /**
         * T2
         */
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            to.updatenuværendeMængdeLiter(140);
        });

        assertEquals("Mængden er større end fad størrelse", exception.getMessage());
    }

    //---------------------------------------------------------------------

    @Test
    void getFadFra() {
        // Arrange
        Fad fad = new Fad("Italien", "Bourbon", 1, 80.00);
        fad.setFadFra("Danmark");

        // Act
        String result = fad.getFadFra();

        // Assert
        assertEquals("Danmark", result);
    }

    @Test
    void setFadFra() {
        // Arrange
        Fad fad = new Fad("Italien", "Bourbon", 1, 80.00);

        // Act
        fad.setFadFra("Danmark");

        // Assert
        assertEquals("Danmark", fad.getFadFra());
    }

    //---------------------------------------------------------------------

    @Test
    void getFadType() {
        // Arrange
        Fad fad = new Fad("Italien", "Bourbon", 1, 80.00);
        fad.setFadType("Vin");

        // Act
        String result = fad.getFadType();

        // Assert
        assertEquals("Vin", result);
    }

    @Test
    void setFadType() {
        // Arrange
        Fad fad = new Fad("Italien", "Bourbon", 1, 80.00);

        // Act
        fad.setFadType("Vin");

        // Assert
        assertEquals("Vin", fad.getFadType());
    }

    //---------------------------------------------------------------------

    @Test
    void getFillNr() {
        // Arrange
        Fad fad = new Fad("Italien", "Bourbon", 1, 80.00);
        fad.setFillNr(42);

        // Act
        int result = fad.getFillNr();

        // Assert
        assertEquals(42, result);
    }

    @Test
    void setFillNr() {
        // Arrange
        Fad fad = new Fad("Italien", "Bourbon", 1, 80.00);

        // Act
        fad.setFillNr(42);

        // Assert
        assertEquals(42, fad.getFillNr());
    }

    //---------------------------------------------------------------------

    @Test
    void getFadNr() {
        // Arrange
        Fad fad = new Fad("Italien", "Bourbon", 1, 80.00);
        fad.setFadNr(123);

        // Act
        int result = fad.getFadNr();

        // Assert
        assertEquals(123, result);
    }

    @Test
    void setFadNr() {
        // Arrange
        Fad fad = new Fad("Italien", "Bourbon", 1, 80.00);

        // Act
        fad.setFadNr(123);

        // Assert
        assertEquals(123, fad.getFadNr());
    }

    //---------------------------------------------------------------------

    @Test
    void getFadStørrelse() {
        // Arrange
        Fad fad = new Fad("Italien", "Bourbon", 1, 80.00);

        // Act
        double result = fad.getFadStørrelse();

        // Assert
        assertEquals(80.00, result, 0.0001);
    }

    @Test
    void setFadStørrelse() {
        // Arrange
        Fad fad = new Fad("Italien", "Bourbon", 1, 80.00);

        // Act
        fad.setFadStørrelse(5.5);

        // Assert
        assertEquals(5.5, fad.getFadStørrelse(), 0.0001);
    }

    //---------------------------------------------------------------------

    @Test
    void getPåfyldning() {
        // Arrange
        Fad fad = new Fad("Italien", "Bourbon", 1, 80.00);
        Destillat destillat = new Destillat("Blended", "Bygsort", "Lars", 40, null, "Nice", "nm1",
                LocalDate.of(2023,12,1), LocalDate.of(2026,12,2), 10);
        DestillatTilPåfyldning destillatTilPåfyldning = new DestillatTilPåfyldning(destillat,40, 10);
        List<DestillatTilPåfyldning> destillaterTilPåfyldninger = new ArrayList<>();
        destillaterTilPåfyldninger.add(destillatTilPåfyldning);


        Påfyldning Påfyldning = new Påfyldning(destillaterTilPåfyldninger, LocalDate.of(2023, 12, 06), "Lars");
        fad.setPåfyldning(Påfyldning);

        // Act
        Påfyldning result = fad.getPåfyldning();

        // Assert
        assertEquals(Påfyldning, result);
    }

    //---------------------------------------------------------------------

    @Test
    void getNuværendeMængdeLiter() {
        // Arrange
        Fad fad = new Fad("Italien", "Bourbon", 1, 80.00);
        fad.updatenuværendeMængdeLiter(10.5);

        // Act
        double result = fad.getNuværendeMængdeLiter();

        // Assert
        assertEquals(10.5, result, 0.0001);
    }

    //---------------------------------------------------------------------

    @Test
    void setPåfyldning() {
        // Arrange
        Fad fad = new Fad("Italien", "Bourbon", 1, 80.00);
        Destillat destillat = new Destillat("Blended", "Bygsort", "Lars", 40, null, "Nice", "nm1",
                LocalDate.of(2023,12,1), LocalDate.of(2026,12,2), 10);
        DestillatTilPåfyldning destillatTilPåfyldning = new DestillatTilPåfyldning(destillat,40, 10);
        List<DestillatTilPåfyldning> destillaterTilPåfyldninger = new ArrayList<>();
        destillaterTilPåfyldninger.add(destillatTilPåfyldning);
        Påfyldning påfyldning = new Påfyldning(destillaterTilPåfyldninger, LocalDate.of(2023, 12, 06), "Lars");

        // Act
        fad.setPåfyldning(påfyldning);

        // Assert
        assertEquals(påfyldning, fad.getPåfyldning());
    }
}