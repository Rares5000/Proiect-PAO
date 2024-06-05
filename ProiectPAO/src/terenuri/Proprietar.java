package terenuri;

public class Proprietar {
    public int id;
    public String nume;
    public int varsta;
    public String numarTelefon;
    public String email;

    Proprietar() {
        this.id = 0;
        this.nume = "";
        this.varsta = 0;
        this.numarTelefon = "";
        this.email = "";
    }

    public Proprietar(int id, String nume, int varsta, String numarTelefon, String email) {
        this.id = id;
        this.nume = nume;
        this.varsta = varsta;
        this.numarTelefon = numarTelefon;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public String getEmail() {
        return email;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return nume + ", " + varsta + ", " + numarTelefon + ", " + email;
    }
}
