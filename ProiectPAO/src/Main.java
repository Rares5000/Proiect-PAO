import terenuri.*;
import terenuri.implementation.ClientServiceImpl;
import terenuri.implementation.InchiriereTerenServiceImpl;
import terenuri.implementation.ProprietarServiceImpl;
import terenuri.implementation.TerenServiceImpl;
import terenuri.interfaces.ClientService;
import terenuri.interfaces.InchiriereTerenService;
import terenuri.interfaces.ProprietarService;
import terenuri.interfaces.TerenService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Declaram serviciile
        ClientService clientService = new ClientServiceImpl();
        ProprietarService proprietarService = new ProprietarServiceImpl();
        TerenService terenService = new TerenServiceImpl();
        InchiriereTerenService inchiriereTerenService = new InchiriereTerenServiceImpl();

        List<Teren> terenuriFotbal = new ArrayList<>();
        Set<Teren> terenCapacitate = new TreeSet<>((teren1, teren2) -> Integer.compare(teren1.getCapacitateMaxima(), teren2.getCapacitateMaxima()));

        //Declaram proprietarii
        Proprietar proprietar1 = new Proprietar(1, "Baciu Rares", 20, "0744321020", "baciu.rares25@gmail.com");
        Proprietar proprietar2 = new Proprietar(2, "Voicu Eduard", 25, "0742124529", "eduard.voicu@gmail.com");
        Proprietar proprietar3 = new Proprietar(3, "Iacob Victor", 31, "0742731421", "victor.iacob@gmail.com");

        //Declaram clientii
        Client client1 = new Client(1, "Stefanescu Mihai", 18, "0741892319");
        Client client2 = new Client(2, "Gabroveanu Razvan", 25, "0749512891");
        Client client3 = new Client(3, "Wellcode Alex", 22, "0741583910");
        Client client4 = new Client(4, "Vulpoi Gabriel", 19, "0741223280");
        Client client5 = new Client(5, "Cristiano Ronaldo", 26, "0749301023");

        ///Punem clientii in baza de date
        clientService.createClient(client1);
        clientService.createClient(client2);
        clientService.createClient(client3);
        clientService.createClient(client4);

        //Punem proprietarii in baza de date
        proprietarService.createProprietar(proprietar1);
        proprietarService.createProprietar(proprietar2);
        proprietarService.createProprietar(proprietar3);

        //Stergem 1 client
        clientService.deleteClient("Wellcode Alex");

        //Facem update la 1 client
        clientService.updateClient("Stefanescu Mihai", client5);

        //Declaram primul teren
        List<String> regulamentTerenBaschet = new ArrayList<>();
        regulamentTerenBaschet.add("Regula 1. Nu aruncati nimic pe jos");
        regulamentTerenBaschet.add("Regula 2. Fiti punctuali");
        String adresaTeren = "Strada Bradului, Nr 2";
        String numeTeren = "Teren Unirii";
        TerenBaschet terenBaschet1 = new TerenBaschet(1, 1, numeTeren, 2500, 90, 100, 15, regulamentTerenBaschet, adresaTeren);

        //Punem primul teren in baza de date
        terenService.createTeren(terenBaschet1);

        LocalDateTime oraInceput = LocalDateTime.of(2024, 3, 26, 11, 0);
        LocalDateTime oraFinal = LocalDateTime.of(2024, 3, 26, 13, 0);

        //Punem prima inchiriere in baza de date
        InchiriereTeren inchiriere1 = new InchiriereTeren(1, 1, 1, 1, oraInceput, oraFinal, 5000);
        inchiriereTerenService.inchiriazaTeren(inchiriere1);

        oraInceput = LocalDateTime.of(2024, 3, 26, 13, 0);
        oraFinal = LocalDateTime.of(2024, 3, 26, 14, 0);

        inchiriere1.setId(2);
        inchiriere1.setDataInceput(oraInceput);
        inchiriere1.setDataSfarsit(oraFinal);

        //Incercare de a inchiria a 2 a oara un teren (nu merge pentru ca se suprapun orele)
        inchiriereTerenService.inchiriazaTeren(inchiriere1);

        oraInceput = LocalDateTime.of(2024, 3, 26, 14, 0);
        oraFinal = LocalDateTime.of(2024, 3, 26, 16, 0);

        inchiriere1.setId(3);
        inchiriere1.setDataInceput(oraInceput);
        inchiriere1.setDataSfarsit(oraFinal);

        //Punem a 2 a inchiriere in baza de date
        inchiriereTerenService.inchiriazaTeren(inchiriere1);

        oraInceput = LocalDateTime.of(2024, 3, 26, 9, 0);
        oraFinal = LocalDateTime.of(2024, 3, 26, 10, 0);

        inchiriere1.setId(4);
        inchiriere1.setDataInceput(oraInceput);
        inchiriere1.setDataSfarsit(oraFinal);

        //Punem a 3 a inchiriere in baza de date
        inchiriereTerenService.inchiriazaTeren(inchiriere1);

        //Declaram al 2 lea teren
        List<String> regulamentTerenFotbal = new ArrayList<>();
        regulamentTerenFotbal.add("Regula 1. Nu injura");
        adresaTeren = "Strada Ghencea, Nr. 10";
        numeTeren = "Teren Ghencea";
        TerenFotbal terenFotbal1 = new TerenFotbal(2, 2, numeTeren, 2500, 100, 120, 10,
                regulamentTerenFotbal, adresaTeren);

        //Punem al 2 lea teren in baza de date
        terenService.createTeren(terenFotbal1);

        //Facem un get pentru a lua toate rezervarile
        List<InchiriereTeren> rezervari = new ArrayList<>();
        rezervari = inchiriereTerenService.getAllInchirieri();
        for(InchiriereTeren rezervare : rezervari) {
            System.out.println("Rezervarea are urmatoarele date :");
            System.out.println("Id-ul rezervarii: " + rezervare.getId());
            System.out.println("Clientul care a rezervat este: " + rezervare.getIdClient());
            System.out.println("Proprietarul terenului este: " + rezervare.getIdProprietar());
            System.out.println("Terenul este: " + rezervare.getIdTeren());
            System.out.println("Intervalul rezervarii este [" + rezervare.getDataInceput() + ", " + rezervare.getDataSfarsit() + "]");
            System.out.println("Pretul rezervarii este: " + rezervare.getPret());
            System.out.println();
        }

        //Facem un get pentru a lua toti clientii
        List<Client> clienti = new ArrayList<>();
        clienti = clientService.getAllClients();
        for(Client client : clienti) {
            System.out.println("Clientul are urmatoarele date :");
            System.out.println("Id-ul clientului: " + client.getId());
            System.out.println("Numele clientului este: " + client.getNume());
            System.out.println("Varsta clientului este: " + client.getVarsta());
            System.out.println("Numarul de telefon a clientului este: " + client.getNumarTelefon());
            System.out.println();
        }

        //Facem un get pentru a lua toti proprietarii
        List<Proprietar> proprietari = new ArrayList<>();
        proprietari = proprietarService.getAllProprietari();
        for(Proprietar proprietar : proprietari) {
            System.out.println("Proprietarul are urmatoarele date :");
            System.out.println("Id-ul proprietarului: " + proprietar.getId());
            System.out.println("Numele proprietarului este: " + proprietar.getNume());
            System.out.println("Varsta proprietarului este: " + proprietar.getVarsta());
            System.out.println("Numarul de telefon a proprietarului este: " + proprietar.getNumarTelefon());
            System.out.println("Emailul proprietarului este: " + proprietar.getEmail());
            System.out.println();
        }

        //Facem un get pentru a lua toate terenurile
        List<Teren> terenuri = new ArrayList<>();
        terenuri = terenService.getAllTerenuri();
        for(Teren teren : terenuri) {
            System.out.println("Rezervarea are urmatoarele date :");
            System.out.println("Id-ul terenului este: " + teren.getId());
            System.out.println("Id-ul proprietarului este: " + teren.getIdProprietar());
            System.out.println("Denumirea terenului: " + teren.getNume());
            System.out.println("Tipul terenului este: " + teren.getTipTeren());
            System.out.println("Pretul terenului este: " + teren.getPret());
            System.out.println("Latimea terenului este: " + teren.getDimensiuneLatime());
            System.out.println("Lungimea terenului este: " + teren.getDimensiuneLungime());
            System.out.println("Capacitatea maxima a terenului este: " + teren.getCapacitateMaxima());
            System.out.println("Adresa terenului este: " + teren.getAdresa());
            System.out.println("Regulamentul terenului este: " + teren.getRegulament());
            System.out.println();
        }
    }
}