package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hylde implements Serializable {
    private int hyldeNr;
    private boolean ledig;
    private int maxKapacitet;
    private final List<Fad> fade = new ArrayList<>();

    public Hylde(int hyldeNr, int maxKapacitet) {
        this.hyldeNr = hyldeNr;
        this.ledig = true;
        this.maxKapacitet = maxKapacitet;
    }

    //---------------------------------------------------------------------

    public int getHyldeNr() {
        return hyldeNr;
    }

    public boolean isLedig() {
        return ledig;
    }

    public int getMaxKapacitet() {
        return maxKapacitet;
    }

    public List<Fad> getFade() {
        return fade;
    }


    //---------------------------------------------------------------------

    /**
     * Tilføjer et fad til hylden.
     * Hvis størrelsen på listen af fad er lig med mmaxkapacitet, bliver ledig sat til false.
     * Thrower en IllegalArgumentException, hvis size på Listen af fade er lig med maxKapacitet.
     */
    public void addFad(Fad fad) {
        if (fade.size() < maxKapacitet) {
            fade.add(fad);
            if (!ledig) {
                ledig = false;
            }
        } else {
            throw new IllegalArgumentException("Hylden er fuld.");
        }
    }

    //---------------------------------------------------------------------

    /**
     * Fjerner et fad fra hylden, og subtrahere 1 fra maxKapacitet.
     * @param fad der skal fjernes
     * @throws en NullPointerException, hvis fadet ikke findes på hylden.
     */
    public void removeFad(Fad fad) {
        if (fade.contains(fad)) {
            fade.remove(fad);
            ledig = true;
        } else {
            throw new NullPointerException("Fadet er ikke på hylden.");
        }
    }
}
