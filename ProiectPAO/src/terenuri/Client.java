package terenuri;

public class Client {
    public int id;
    public String nume;
    public int varsta;
    public String numarTelefon;

    Client() {
        this.id = 0;
        this.nume = "";
        this.varsta = 0;
        this.numarTelefon = "";
    }

    public Client(int id, String nume, Integer varsta, String numarTelefon) {
        this.id = id;
        this.nume = nume;
        this.varsta = varsta;
        this.numarTelefon = numarTelefon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public String getNume() {
        return nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return nume + ", " + varsta + ", " + numarTelefon;
    }
}