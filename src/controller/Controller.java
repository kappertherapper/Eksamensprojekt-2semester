package controller;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public abstract class Controller {
    private static Storage storage;

    public static void setStorage(Storage storage) {
        Controller.storage = storage;
    }

    //---------------------------------------------------------------------

    /**
     * Opret et nyt Destillat
     * Pre: startDato > slutdato, alkoholprocent < 0 & > 100,
     */
    public static Destillat opretDestillat(String maltBatch, String kornsort, String medarbejder, double alkoholProcent,
                                           String rygeMateriale, String kommentar, String newMakeNr, LocalDate startDato, LocalDate slutDato, double startVolume) {
        Destillat d = new Destillat(maltBatch, kornsort, medarbejder, alkoholProcent, rygeMateriale, kommentar, newMakeNr,
                startDato, slutDato, startVolume);
        storage.storeDestillat(d);
        return d;
    }

    public static List<Destillat> getDestillater() {
        return storage.getDestillater();
    }

    //---------------------------------------------------------------------

    /**
     * Opret et nyt Fad
     */
    public static Fad opretFad(String fadFra, String fadType, int fadNr, double fadStørrelse) {
        Fad f = new Fad(fadFra, fadType, fadNr, fadStørrelse);
        storage.storeFad(f);
        return f;
    }

    /**
     * Update Fad.
     */
    public static void updateFad(Fad fad, String fadFra, String fadType, int fillNr, int fadNr, double fadStørrelse) {
        fad.setFadFra(fadFra);
        fad.setFadType(fadType);
        fad.setFillNr(fillNr);
        fad.setFadNr(fadNr);
        fad.setFadStørrelse(fadStørrelse);
    }

    /**
     * Returner fade gemt i Storage
     * @return en List<Fad> fade
     */
    public static List<Fad> getFade() {
        return storage.getFade();
    }

    //---------------------------------------------------------------------

    /**
     * Opret et nyt Lager
     * Pre: rækker, hylder og pladsHylde er > 0
     */
    public static Lager opretLager(String adresse, String navn, int rækker, int hylder, int pladsHylde) {
        Lager l = new Lager(adresse, navn);
        opretRække(l, rækker, hylder, pladsHylde);
        storage.storeLager(l);
        return l;
    }

    /**
     * Update Lager.
     */
    public static void updateLager(Lager lager, String adresse, String navn) {
        lager.setAdresse(adresse);
        lager.setNavn(navn);
    }


    public static List<Lager> getLagere() {
        return storage.getLagere();
    }

    //---------------------------------------------------------------------

    /**
     * Opret en ny Hylde
     */
    public static void opretHylde(int maxKapacitet, int antal, Række række) {
        for (int i = 0; i < antal; i++) {
            Hylde h = new Hylde(i + 1, maxKapacitet);
            række.addHylde(h);
        }
    }

    /**
     * Tilføjer et fad til en hylde på et lager
     */
    public static void addFadTilHylde(Fad fad, int hylde, int række, Lager lager) {
        lager.getRækker().get(række - 1).getHylder().get(hylde - 1).addFad(fad);

    }
    //---------------------------------------------------------------------

    /**
     * Opret ny Række
     */
    public static List<Række> opretRække(Lager lager, int rækker, int hylder, int pladsHylde) {
        for (int i = 0; i < rækker; i++) {
            Række r = new Række(i + 1, lager);
            opretHylde(pladsHylde, hylder, r);
            lager.addRække(r);
        }
        return lager.getRækker();
    }
    //---------------------------------------------------------------------

    /**
     * Opret en nyt Whisky ud fra en liste af fad. Fadene bliver "tømt" fuldkommen,
     * det vil sige, at deres Påfyldning bliver fjernet, og deres nuværeneVolumen bliver
     * sat til 0.
     * Pre: De valgte fade har påfyldninger på, ellers bliver der knyttet ligegyldige fade til whiskyen.
     * @param fade - de fade, der skal tømmes til whisky
     */
    public static Whisky opretWhisky(List<Fad> fade, double vandVolumen) {
        Whisky w = new Whisky(fade, vandVolumen);
        storage.storeWhisky(w);
        for (Fad f : fade) {
            f.updatenuværendeMængdeLiter(0);
            f.setPåfyldning(null);
            f.setFillNr(f.getFillNr() + 1);
        }
        return w;
    }

    /**
     * Returnerer en liste af whiskyer gemt i Storage
     * @return en List<Whisky> whiskies
     */
    public static List<Whisky> getWhisker() {
        return storage.getWhiskyer();
    }

    //---------------------------------------------------------------------
    /**
     * Opret en nyt Påfyldning
     */
    public static Påfyldning opretPåfyldning(List<DestillatTilPåfyldning> destillater, Fad fad, LocalDate startDato,
                                             String medarbejder) {
        double volumen = 0;
        for (DestillatTilPåfyldning destillatTilPåfyldning : destillater) {
            volumen += destillatTilPåfyldning.getMængdeLiter();
        }
        for (DestillatTilPåfyldning destillatTilPåfyldning : destillater){
            if (destillatTilPåfyldning.getDestillat().getSlutDato().isAfter(startDato)){
                throw new IllegalArgumentException("Start dato er før destillatet er færdigt");
            }
        }

        if (volumen > fad.getFadStørrelse()) {
            throw new IllegalArgumentException("Påfyldning for stor til fad");
        }  else {
            Påfyldning p = new Påfyldning(destillater, startDato, medarbejder);
            fad.setPåfyldning(p);
            fad.updatenuværendeMængdeLiter(volumen);
            for (DestillatTilPåfyldning  destillat : destillater){
                Destillat d = destillat.getDestillat();
                d.setMængdeLiter(d.getMængdeLiter() - destillat.getMængdeLiter());
            }
            return p;
        }
    }

    /**
     * Opret er DestillatTilPåfyldning
     */

    public static DestillatTilPåfyldning opretDestillatTilPåfyldning(Destillat destillat, double mængdeLiter) {
        if ((destillat.getMængdeLiter() - mængdeLiter) < 0) {
            throw new IllegalArgumentException("Mængden af væske til påfyldning er større end mængden der er tilbage af destillatet");
        }
        DestillatTilPåfyldning d = new DestillatTilPåfyldning(destillat, mængdeLiter, destillat.getAlkoholProcent());
        return d;
    }

    //---------------------------------------------------------------------

    /**
     * Finder lokation på fad
     * Pre: fad oprettet
     */
    public static String findLokationPåFad(Fad fad) {
        List<Fad> Fade = new ArrayList<>();
        for (Lager lager : storage.getLagere()) {
            for (Række række : lager.getRækker()) {
                for (Hylde hylde : række.getHylder()) {
                    if (hylde.getFade().contains(fad)) {
                        return ", Række: " + række.getRækkeNr() +
                                            ", Hylde: " + hylde.getHyldeNr();
                    }
                }
            }
        }
        return "lokation";
    }

        //---------------------------------------------------------------------

    /**
     * Finder alle fade der har en alder på 3 år+
     * @return en liste af Fade > 3 år
     */
    public static List<Fad> treAarGammel() {
        List<Fad> TreAarGammel = new ArrayList<>();
        for (Fad f : Controller.getFade()) {
            try {
                if (f.getPåfyldning().getStartDato().plusYears(3).isBefore(LocalDate.now())) {
                    TreAarGammel.add(f);
                }
            } catch(NullPointerException ex){

            }

        }
        return TreAarGammel;
    }
        //---------------------------------------------------------------------

    /**
     * Opdaterer alkoholprocenten på en Påfyldning
     */
    public static void opdaterAlkoholProcent(double alkoholProcent, Påfyldning påfyldning){
        påfyldning.setAlkoholProcent(alkoholProcent);
    }

        //---------------------------------------------------------------------

    /**
     * Opdaterer den nuværendeMængdeLiter på et fad
     * @param fad
     * @param mængde
     */
    public static void  opdaterMængdeIFad(Fad fad, double mængde){
        if (fad.getPåfyldning() == null){
            throw new IllegalArgumentException("Der er ikke en påfyldning på dette fad");
        } else {
            fad.updatenuværendeMængdeLiter(mængde);
        }
    }
}
