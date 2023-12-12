package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lager implements Serializable {
    private String adresse;
    private int kapacitet;
    private String navn;
    private List<Række> rækker = new ArrayList<>();

    public Lager(String adresse, String navn) {
        this.adresse = adresse;
        this.navn = navn;
    }

    public Lager(String adresse, String navn, List<Række> rækker) {
        this.adresse = adresse;
        this.navn = navn;
        this.rækker = rækker;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setNavn(String navn) {
        this.navn = navn;
    }
    public List<Række> getRækker() {
        return rækker;
    }
    public void addRække(Række række) {
        rækker.add(række);
    }

    //---------------------------------------------------------------------

    public int getmaxKapacitet() {
        int sum = 0;
        for (Række r : rækker) {
            for (Hylde h : r.getHylder()) {
                sum += h.getMaxKapacitet();
            }
        }
        return sum;
    }

    //---------------------------------------------------------------------

    @Override
    public String toString() {
        return navn + "\nAdresse: " + adresse + "\nKapacitet: " + getmaxKapacitet();
    }
}
