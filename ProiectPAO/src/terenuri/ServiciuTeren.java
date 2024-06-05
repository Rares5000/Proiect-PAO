package terenuri;

import java.util.*;

public class ServiciuTeren {
    Set<Teren> terenuri = new TreeSet<>(Comparator.comparingInt(Teren::getPret));

    public List<Teren> getTerenuriTip(String tipTeren) {
        List<Teren> terenuriTip = new ArrayList<>();

        for (Teren teren : terenuri) {
            if (teren.getTipTeren().equals(tipTeren)) {
                terenuriTip.add(teren);
            }
        }

        return terenuriTip;
    }

    public List<Teren> getTopTrei(String tipTeren) {
        int cnt = 0;
        List<Teren> topTrei = new ArrayList<>();
        for (Teren teren : terenuri) {
            if (teren.getTipTeren().equals(tipTeren)) {
                cnt++;
                topTrei.add(teren);
            }
        }
        if (cnt < 3) {
            System.out.println("Nu sunt 3 terenuri de tip " + tipTeren);
        }
        else {
            System.out.println("Am gasit 3 terenuri de tip " + tipTeren);
        }
        return topTrei;
    }

    public int getMediaTerenurilorTip(String tipTeren) {
        int mediaPreturilor, nrTerenuriTip;
        mediaPreturilor = nrTerenuriTip = 0;
        for(Teren teren : terenuri) {
            if(teren.getTipTeren().equals(tipTeren)) {
                mediaPreturilor += teren.getPret();
                nrTerenuriTip++;
            }
        }

        mediaPreturilor /= nrTerenuriTip;

        System.out.println("Media terenurilor de tip " + tipTeren + " este " + mediaPreturilor);
        return mediaPreturilor;
    }

    public void evalueazaTeren(Teren teren) {
        String tipTeren = teren.getTipTeren();
        int medie = getMediaTerenurilorTip(tipTeren);

        if(teren.getPret() < medie) {
            System.out.println("Terenul este la un pret mai mic fata de celelalte");
        }
        else if(teren.getPret() > medie) {
            System.out.println("Terenul este la un pret mai mare fata de celelalte");
        }
        else System.out.println("Terenul este la pretul pietei");
    }

    public Teren terenMaximDimensiune(String tipTeren) {
        int dimensiuneMaximaLungime, dimensiuneMaximaLatime;
        Teren terenMaxim = new Teren();
        dimensiuneMaximaLungime = dimensiuneMaximaLatime = 0;
        for(Teren teren : terenuri) {
            if(teren.getTipTeren().equals(tipTeren)) {
                if(teren.getDimensiuneLungime() > dimensiuneMaximaLungime) {
                    dimensiuneMaximaLungime = teren.getDimensiuneLungime();
                    dimensiuneMaximaLatime = teren.getDimensiuneLatime();
                    terenMaxim = teren;
                }
                else
                    if(teren.getDimensiuneLungime() == dimensiuneMaximaLungime) {
                        if(teren.getDimensiuneLatime() > dimensiuneMaximaLatime)
                        {
                            terenMaxim = teren;
                            dimensiuneMaximaLatime = teren.getDimensiuneLatime();
                        }
                    }
            }
        }
        return terenMaxim;
    }

    public List<Teren> terenAdresa(String adresa) {
        List<Teren> terenuriAdresa = new ArrayList<>();
        for(Teren teren : terenuri) {
            if(teren.getAdresa().contains(adresa)) {
                terenuriAdresa.add(teren);
            }
        }
        return terenuriAdresa;
    }

    public List<Teren> terenInInterval(int stangaInterval, int dreaptaInterval) {
        List<Teren> terenuriInterval = new ArrayList<>();
        for(Teren teren : terenuri) {
            if(stangaInterval <= teren.getPret() &&  teren.getPret() <= dreaptaInterval) {
                terenuriInterval.add(teren);
            }
        }
        return terenuriInterval;
    }

    public void verificaRegulamentTeren(Teren teren) {
        List<String> regulamentTeren = teren.getRegulament();
        if(regulamentTeren.size() >= 2) {
            System.out.println("Terenul are regulament stufos, citeste-l");
        }
        else {
            System.out.println("Terenul are regulament permisiv");
        }
    }

}
