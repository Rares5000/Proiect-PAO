package terenuri.implementation;

import terenuri.Proprietar;
import terenuri.DatabaseConnection;
import terenuri.interfaces.ProprietarService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProprietarServiceImpl implements ProprietarService {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }

    @Override
    public void createProprietar(Proprietar proprietar) {
        String sql = "INSERT INTO proprietar (id, nume, varsta, numar_telefon, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, proprietar.getId());
            stmt.setString(2, proprietar.getNume());
            stmt.setInt(3, proprietar.getVarsta());
            stmt.setString(4, proprietar.getNumarTelefon());
            stmt.setString(5, proprietar.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Proprietar> getAllProprietari() {
        List<Proprietar> proprietari = new ArrayList<>();
        String sql = "SELECT id, nume, varsta, numar_telefon, email FROM proprietar";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Proprietar proprietar = new Proprietar(rs.getInt("id"), rs.getString("nume"), rs.getInt("varsta"), rs.getString("numar_telefon"), rs.getString("email"));
                proprietari.add(proprietar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proprietari;
    }

    @Override
    public Proprietar getProprietarByNume(String nume) {
        Proprietar proprietar = null;
        String sql = "SELECT id, nume, varsta, numar_telefon, email FROM proprietar WHERE nume = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nume);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    proprietar = new Proprietar(rs.getInt("id"), rs.getString("nume"), rs.getInt("varsta"), rs.getString("numar_telefon"), rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proprietar;
    }

    @Override
    public void updateProprietar(String nume, Proprietar proprietar) {
        String sql = "UPDATE proprietar SET nume = ?, varsta = ?, numar_telefon = ?, email = ? WHERE nume = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, proprietar.getNume());
            stmt.setInt(2, proprietar.getVarsta());
            stmt.setString(3, proprietar.getNumarTelefon());
            stmt.setString(4, proprietar.getEmail());
            stmt.setString(5, nume);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProprietar(String nume) {
        String sql = "DELETE FROM proprietar WHERE nume = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nume);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
