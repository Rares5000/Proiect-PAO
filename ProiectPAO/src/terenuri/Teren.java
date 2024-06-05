package terenuri;

import java.util.ArrayList;
import java.util.List;

public class Teren {
    int id;
    protected int idProprietar;
    protected String nume;
    protected String tipTeren;
    protected static int nrTerenuri;
    protected static int medieTerenuri;
    protected int pret;
    protected int dimensiuneLungime;
    protected int dimensiuneLatime;
    protected int capacitateMaxima;
    protected String adresa;
    protected List<String> regulament;

    static {
        nrTerenuri = 0;
        medieTerenuri = 0;
    }
    public Teren() {
        this.id = 0;
        this.idProprietar = -1;
        this.nume = "";
        this.tipTeren = "";
        this.pret = 0;
        this.dimensiuneLatime = 0;
        this.dimensiuneLungime = 0;
        this.capacitateMaxima = 0;
        this.regulament = new ArrayList<>();
        this.adresa = "";
        nrTerenuri++;
    }
    Teren(int id, int idProprietar, String nume, int pret, int dimensiuneLatime, int dimensiuneLungime, int capacitateMaxima,
          List<String> regulament, String adresa) {
        this.id = id;
        this.idProprietar = idProprietar;
        this.nume = nume;
        this.tipTeren = "";
        this.pret = pret;
        this.dimensiuneLatime = dimensiuneLatime;
        this.dimensiuneLungime = dimensiuneLungime;
        this.capacitateMaxima = capacitateMaxima;
        this.regulament = regulament;
        this.adresa = adresa;
        nrTerenuri++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProprietar() {
        return idProprietar;
    }

    public void setIdProprietar(int idProprietar) {
        this.idProprietar = idProprietar;
    }

    public static void setMedieTerenuri(int medieTerenuri) {
        Teren.medieTerenuri = medieTerenuri;
    }

    public static void setNrTerenuri(int nrTerenuri) {
        Teren.nrTerenuri = nrTerenuri;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public int getDimensiuneLatime() {
        return dimensiuneLatime;
    }

    public void setDimensiuneLatime(int dimensiuneLatime) {
        this.dimensiuneLatime = dimensiuneLatime;
    }

    public int getDimensiuneLungime() {
        return dimensiuneLungime;
    }

    public static int getMedieTerenuri() {
        return medieTerenuri;
    }

    public void setDimensiuneLungime(int dimensiuneLungime) {
        this.dimensiuneLungime = dimensiuneLungime;
    }

    public int getCapacitateMaxima() {
        return capacitateMaxima;
    }

    public void setCapacitateMaxima(int capacitateMaxima) {
        this.capacitateMaxima = capacitateMaxima;
    }

    public static int getNrTerenuri() {
        return nrTerenuri;
    }

    public List<String> getRegulament() {
        return regulament;
    }

    public void setRegulament(List<String> regulament) {
        this.regulament = regulament;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTipTeren() {
        return tipTeren;
    }

    public void setTipTeren(String tipTeren) {
        this.tipTeren = tipTeren;
    }

    @Override
    public String toString() {
        return nume + "," + tipTeren + "," + pret + "," + dimensiuneLatime + "," +
                dimensiuneLungime + "," + capacitateMaxima + "," + adresa + "," + regulament.toString();
    }

}
