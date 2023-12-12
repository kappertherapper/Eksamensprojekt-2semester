package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Række implements Serializable {
    private int rækkeNr;
    private boolean ledig;
    private final List<Hylde> hylder = new ArrayList<>();

    public Række(int rækkeNr, Lager lager) {
        this.rækkeNr = rækkeNr;
        this.ledig = true;
    }

    //---------------------------------------------------------------------

    public int getRækkeNr() {
        return rækkeNr;
    }
    public List<Hylde> getHylder() {
        return hylder;
    }
    public void addHylde(Hylde h) {
        hylder.add(h);
    }

    //---------------------------------------------------------------------

    /**
     * Returnere true hvis rækken er fyldt
     * Returnere false hvis det er mere plads
     */
    public boolean ErRækkeFyldt() {
        boolean erLedig = false;
        int i = 0;
        while (!erLedig && (i < hylder.size())) {
            erLedig = hylder.get(i).isLedig();
            i++;
        }
        return erLedig;
    }
}
