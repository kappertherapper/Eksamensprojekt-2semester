package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {

    // Denne test var gyldig i en tidlígere iteration, men efter vi har ændret opbygningen af systemet,
    // er den ikke længere gyldig.
    /*@Test
    void addFad() {
        Lager lager = new Lager("Blomstervej 2", "Lager 1");

        Fad et = new Fad("Italien", "Bourbon", 1, 80.00);
        Fad to = new Fad("Frankrig", "Sherry", 2, 135.00);
        Fad tre = new Fad("Polen", "Vin", 3, 100.00);

        //Arrange
        lager.add(et);
        lager.fade.add(to);
        lager.fade.add(tre);

        //Act
        int forventetResultat = 3;
        //Assert
        assertEquals(forventetResultat, lager.getFade.size);
    }*/

    //---------------------------------------------------------------------

    @Test
    void getMaxkapacitet() {
        Lager lager = new Lager("Blomstervej 2", "Lager 1");
        Række r1 = new Række(1, lager);
        Række r2 = new Række(2, lager);
        lager.addRække(r1);
        lager.addRække(r2);
        Hylde h1 = new Hylde(1, 4);
        Hylde h2 = new Hylde(2, 4);
        Hylde h3 = new Hylde(1, 4);
        Hylde h4 = new Hylde(2, 4);
        r1.addHylde(h1);
        r1.addHylde(h2);
        r2.addHylde(h3);
        r2.addHylde(h4);

        assertEquals(16, lager.getmaxKapacitet());
    }
}