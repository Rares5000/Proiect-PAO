package terenuri.interfaces;

import terenuri.Teren;
import java.util.List;

public interface TerenService {
    void createTeren(Teren teren);
    List<Teren> getAllTerenuri();
    Teren getTerenByNume(String nume);
    void updateTeren(String nume, Teren teren);
    void deleteTeren(String nume);
}
