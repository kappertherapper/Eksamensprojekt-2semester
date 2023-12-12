package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Whisky implements Serializable {
    private String whiskyType;
    private double antalFlasker;
    private double alkoholProcent;
    private double vandVolumen;
    //private LocalDate lagringsdDato;
    private LocalDate tapningsDato;
    private List<Destillat> destillater = new ArrayList<>();
    private List<Fad> fade = new ArrayList<>();

    public Whisky(List<Fad> fade, double vandVolumen) {
        this.vandVolumen = vandVolumen;
        if (fade.size() > 1) {
            this.whiskyType = "Single Malt";
        } else {
            this.whiskyType = "Single Cask";
        }
        this.fade.addAll(fade);
        for (Fad f : this.fade) {
            for (DestillatTilPåfyldning dtp : f.getPåfyldning().getDestillat()) {
                destillater.add(dtp.getDestillat());
            }
        }
        this.tapningsDato = LocalDate.now();
        updateAntalFlasker();
        updateAlkoholProcent();
    }

    //---------------------------------------------------------------------

    public void updateAntalFlasker() {
        double sum = vandVolumen;
        for (Fad f : fade) {
            sum += f.getNuværendeMængdeLiter();
        }
        this.antalFlasker = sum /0.7;
    }

    //---------------------------------------------------------------------

    /**
     * Opdater alkohol procent på et fad
     */
    private void updateAlkoholProcent() {
        double alkoholVolume = 0;
        double volume = vandVolumen;
        for (Fad f : fade) {
            alkoholVolume += (f.getNuværendeMængdeLiter() * (f.getPåfyldning().getAlkoholProcent() / 100));
            volume += f.getNuværendeMængdeLiter();
        }
        this.alkoholProcent = (alkoholVolume/volume)* 100;
    }

    //---------------------------------------------------------------------

    @Override
    public String toString() {
        String malt = "";
        for (Destillat d : destillater) {
            if (!malt.contains(d.getMaltBatch())) {
                malt += d.getMaltBatch() + " ";
            }
        }
        return "Type: " + this.whiskyType + "\nMalt: " + malt + String.format("\nAntal flasker: %.2f" ,this.antalFlasker);
    }

    //---------------------------------------------------------------------

    /**
     * Denne metode tager information fra alle fad og destillater tilknyttet til den pågældene whisky
     * og sætter dem sammmen i en enkelt String, der repræsenterer den etikette, der bliver printet på flasken.
     * @return etikette - en String med relevant information fra fadene og destillaterne
     */
    public String toEtikette() {
        String etikette = "";

        // Fad info
        String fadeFra = "Fade fra: ";
        String fadeType = "Fade Typer: ";
        for (Fad f : fade) {
            if (!fadeFra.contains(f.getFadFra())) {
                fadeFra += f.getFadFra() + ", ";
            }
            if (!fadeType.contains(f.getFadType())) {
                fadeType += f.getFadType() + ", ";
            }
        }
        etikette += fadeFra + "\n" + fadeType + "\n";

        // Destillat info
        String kornsort = "Kornsorte anvendt: ";
        String medarbejder = "Hvem har lavet whiskyen: ";
        String rygemateriale = "Rygemateriale anvendt: ";
        String maltBatch = "Malt anvendt: ";
        for (Destillat d : destillater) {
            if (!kornsort.contains(d.getKornsort())) {
                kornsort += d.getKornsort() + ", ";
            }
            if (!medarbejder.contains(d.getMedarbejder())) {
                medarbejder += d.getMedarbejder() + ", ";
            }
            if (!rygemateriale.contains(d.getRygeMateriale())) {
                rygemateriale += d.getRygeMateriale() + ", ";
            }
            if (!maltBatch.contains(d.getMaltBatch())) {
                maltBatch += d.getMaltBatch() + ", ";
            }
        }
        String alkoholprocent = String.format("Alkoholprocent: %.2f", this.alkoholProcent);
        etikette += kornsort + "\n" + medarbejder + "\n" + rygemateriale + "\n" + maltBatch + "\n" + "Tapningsdato: " +
                this.tapningsDato + "\n" + alkoholprocent + "%";

        // Vand
        if (vandVolumen == 0) {
            etikette += "\nDenne whisky er cask strength.";
        } else {
            etikette += "\nDer er blevet anvendt " + vandVolumen + " liter vand fra en dal,\n" +
                    "der findes under destilleriet.";
        }
        return etikette;
    }
}
