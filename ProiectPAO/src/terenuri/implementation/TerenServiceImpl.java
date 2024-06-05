package terenuri.implementation;

import terenuri.Teren;
import terenuri.DatabaseConnection;
import terenuri.interfaces.TerenService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TerenServiceImpl implements TerenService {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }

    @Override
    public void createTeren(Teren teren) {
        String sql = "INSERT INTO teren (id, proprietar_id, nume, tip_teren, pret, dimensiune_latime, dimensiune_lungime, capacitate_maxima, adresa, regulament) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teren.getId());
            stmt.setInt(2, teren.getIdProprietar());
            stmt.setString(3, teren.getNume());
            stmt.setString(4, teren.getTipTeren());
            stmt.setInt(5, teren.getPret());
            stmt.setInt(6, teren.getDimensiuneLatime());
            stmt.setInt(7, teren.getDimensiuneLungime());
            stmt.setInt(8, teren.getCapacitateMaxima());
            stmt.setString(9, teren.getAdresa());
            stmt.setArray(10, conn.createArrayOf("varchar", teren.getRegulament().toArray()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Teren> getAllTerenuri() {
        List<Teren> terenuri = new ArrayList<>();
        String sql = "SELECT id, proprietar_id, nume, tip_teren, pret, dimensiune_latime, dimensiune_lungime, capacitate_maxima, adresa, regulament FROM teren";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Teren teren = new Teren();
                teren.setId(rs.getInt("id"));
                teren.setIdProprietar(rs.getInt("proprietar_id"));
                teren.setNume(rs.getString("nume"));
                teren.setTipTeren(rs.getString("tip_teren"));
                teren.setPret(rs.getInt("pret"));
                teren.setDimensiuneLatime(rs.getInt("dimensiune_latime"));
                teren.setDimensiuneLungime(rs.getInt("dimensiune_lungime"));
                teren.setCapacitateMaxima(rs.getInt("capacitate_maxima"));
                teren.setAdresa(rs.getString("adresa"));
                Array regulamentArray = rs.getArray("regulament");
                if (regulamentArray != null) {
                    String[] regulament = (String[]) regulamentArray.getArray();
                    for (String reg : regulament) {
                        teren.getRegulament().add(reg);
                    }
                }
                terenuri.add(teren);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return terenuri;
    }

    @Override
    public Teren getTerenByNume(String nume) {
        Teren teren = null;
        String sql = "SELECT id, proprietar_id, nume, tip_teren, pret, dimensiune_latime, dimensiune_lungime, capacitate_maxima, adresa, regulament FROM teren WHERE nume = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nume);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    teren = new Teren();
                    teren.setId(rs.getInt("id"));
                    teren.setIdProprietar(rs.getInt("proprietar_id"));
                    teren.setNume(rs.getString("nume"));
                    teren.setTipTeren(rs.getString("tip_teren"));
                    teren.setPret(rs.getInt("pret"));
                    teren.setDimensiuneLatime(rs.getInt("dimensiune_latime"));
                    teren.setDimensiuneLungime(rs.getInt("dimensiune_lungime"));
                    teren.setCapacitateMaxima(rs.getInt("capacitate_maxima"));
                    teren.setAdresa(rs.getString("adresa"));
                    Array regulamentArray = rs.getArray("regulament");
                    if (regulamentArray != null) {
                        String[] regulament = (String[]) regulamentArray.getArray();
                        for (String reg : regulament) {
                            teren.getRegulament().add(reg);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teren;
    }

    @Override
    public void updateTeren(String nume, Teren teren) {
        String sql = "UPDATE teren SET nume = ?, proprietar_id = ?, tip_teren = ?, pret = ?, dimensiune_latime = ?, dimensiune_lungime = ?, capacitate_maxima = ?, adresa = ?, regulament = ? WHERE nume = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, teren.getNume());
            stmt.setInt(2, teren.getIdProprietar());
            stmt.setString(3, teren.getTipTeren());
            stmt.setInt(4, teren.getPret());
            stmt.setInt(5, teren.getDimensiuneLatime());
            stmt.setInt(6, teren.getDimensiuneLungime());
            stmt.setInt(7, teren.getCapacitateMaxima());
            stmt.setString(8, teren.getAdresa());
            stmt.setArray(9, conn.createArrayOf("varchar", teren.getRegulament().toArray()));
            stmt.setString(10, nume);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTeren(String nume) {
        String sql = "DELETE FROM teren WHERE nume = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nume);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
