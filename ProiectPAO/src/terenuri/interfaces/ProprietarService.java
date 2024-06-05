package terenuri.interfaces;

import terenuri.Proprietar;
import java.util.List;

public interface ProprietarService {
    void createProprietar(Proprietar proprietar);
    List<Proprietar> getAllProprietari();
    Proprietar getProprietarByNume(String nume);
    void updateProprietar(String nume, Proprietar proprietar);
    void deleteProprietar(String nume);
}
