package terenuri.interfaces;

import terenuri.Client;

import java.util.List;

public interface ClientService {
    void createClient(Client client);
    List<Client> getAllClients();
    Client getClientByName(String nume);
    void updateClient(String nume, Client client);
    void deleteClient(String nume);
}