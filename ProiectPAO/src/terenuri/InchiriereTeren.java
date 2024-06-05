package terenuri;
import java.time.LocalDateTime;

public class InchiriereTeren {
    int id;
    int idClient;
    int idProprietar;
    int idTeren;
    LocalDateTime dataInceput;
    LocalDateTime dataSfarsit;
    int pret;

    public InchiriereTeren(int id, int idClient, int idProprietar, int idTeren, LocalDateTime dataInceput, LocalDateTime dataSfarsit, int pret) {
        this.id = id;
        this.idClient = idClient;
        this.idProprietar = idProprietar;
        this.idTeren = idTeren;
        this.dataInceput = dataInceput;
        this.dataSfarsit = dataSfarsit;
        this.pret = pret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getIdClient() {
        return idClient;
    }

    public int getIdProprietar() {
        return idProprietar;
    }

    public int getIdTeren() {
        return idTeren;
    }

    public LocalDateTime getDataInceput() {
        return dataInceput;
    }

    public LocalDateTime getDataSfarsit() {
        return dataSfarsit;
    }

    public int getPret() {
        return pret;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setIdProprietar(int idProprietar) {
        this.idProprietar = idProprietar;
    }

    public void setIdTeren(int idTeren) {
        this.idTeren = idTeren;
    }

    public void setDataInceput(LocalDateTime dataInceput) {
        this.dataInceput = dataInceput;
    }

    public void setDataSfarsit(LocalDateTime dataSfarsit) {
        this.dataSfarsit = dataSfarsit;
    }

    public void setPret(int pret) {
        this.pret = pret;

    }
}

