package terenuri;

import java.util.List;

public class TerenFotbal extends Teren {
    public TerenFotbal(int id, int idProprietar, String nume, int pret, int dimensiuneLatime, int dimensiuneLungime, int capacitateMaxima,
                       List<String> regulament, String adresa) {
        this.id = id;
        this.idProprietar = idProprietar;
        this.nume = nume;
        this.tipTeren = "Fotbal";
        this.pret = pret;
        this.dimensiuneLatime = dimensiuneLatime;
        this.dimensiuneLungime = dimensiuneLungime;
        this.capacitateMaxima = capacitateMaxima;
        this.regulament = regulament;
        this.adresa = adresa;
        nrTerenuri++;
        medieTerenuri += pret;
    }
}
