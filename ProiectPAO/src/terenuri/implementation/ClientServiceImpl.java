package terenuri.implementation;

import terenuri.Client;
import terenuri.DatabaseConnection;
import terenuri.interfaces.ClientService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private Connection getConnection() throws Exception {
        return DatabaseConnection.getConnection();
    }

    @Override
    public void createClient(Client client) {
        String sql = "INSERT INTO client (id, nume, varsta, numar_telefon) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, client.getId());
            stmt.setString(2, client.getNume());
            stmt.setInt(3, client.getVarsta());
            stmt.setString(4, client.getNumarTelefon());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT id, nume, varsta, numar_telefon FROM client";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Client client = new Client(rs.getInt("id"), rs.getString("nume"), rs.getInt("varsta"), rs.getString("numar_telefon"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clients;
    }

    @Override
    public Client getClientByName(String nume) {
        Client client = null;
        String sql = "SELECT id, nume, varsta, numar_telefon FROM client WHERE nume = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nume);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    client = new Client(rs.getInt("id"), rs.getString("nume"), rs.getInt("varsta"), rs.getString("numar_telefon"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    @Override
    public void updateClient(String nume, Client client) {
        String sql = "UPDATE client SET nume = ?, varsta = ?, numar_telefon = ? WHERE nume = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getNume());
            stmt.setInt(2, client.getVarsta());
            stmt.setString(3, client.getNumarTelefon());
            stmt.setString(4, nume);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteClient(String nume) {
        String sql = "DELETE FROM client WHERE nume = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nume);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}