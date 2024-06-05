package terenuri.interfaces;

import terenuri.InchiriereTeren;
import java.util.List;

public interface InchiriereTerenService {
    void inchiriazaTeren(InchiriereTeren inchiriere);
    List<InchiriereTeren> getAllInchirieri();
    InchiriereTeren getInchiriereById(int id);
    void updateInchiriere(int id, InchiriereTeren inchiriere);
    void deleteInchiriere(int id);
}
