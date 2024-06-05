package terenuri;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class ServiciuInchiriere {
    private Set<InchiriereTeren> rezervari = new TreeSet<>((r1, r2) -> r1.getDataInceput().compareTo(r2.getDataInceput()));

    public void inchiriazaTeren(int id, Client client, Proprietar proprietar, Teren teren, LocalDateTime dataInceput, LocalDateTime dataSfarsit) {
        if (verificaDisponibilitateTeren(teren, dataInceput, dataSfarsit)) {
            int pret = (dataSfarsit.getHour() - dataInceput.getHour()) * teren.pret;
            InchiriereTeren rezervare = new InchiriereTeren(id, client.id, proprietar.id, teren.id, dataInceput, dataSfarsit, pret);
            rezervari.add(rezervare);
            System.out.println("Inchirierea terenului in intervalul a fost facuta cu succes");
            return;
        }
        System.out.println("Inchirierea terenului in intervalul a esuat. Intervalul se suprapune cu alte inchirieri");
        return;
    }

    private boolean verificaDisponibilitateTeren(Teren teren, LocalDateTime dataInceput, LocalDateTime dataSfarsit) {
        for (InchiriereTeren rezervare : rezervari) {
            if (rezervare.getIdTeren() == teren.getId() && areSuprapunere(rezervare.getDataInceput(), rezervare.getDataSfarsit(), dataInceput, dataSfarsit)) {
                return false;
            }
        }
        return true;
    }

    private boolean areSuprapunere(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        if(end1.isEqual(start2) || start1.isEqual(end2)) {
            return false;
        }
        return !start1.isAfter(end2) && !start2.isAfter(end1);
    }

    public Set<InchiriereTeren> getRezervari() {
        return rezervari;
    }

    
}
