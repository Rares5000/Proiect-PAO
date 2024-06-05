package terenuri.implementation;

import terenuri.Client;
import terenuri.InchiriereTeren;
import terenuri.DatabaseConnection;
import terenuri.Teren;
import terenuri.interfaces.InchiriereTerenService;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class InchiriereTerenServiceImpl implements InchiriereTerenService {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }

    @Override
    public void inchiriazaTeren(InchiriereTeren inchiriere) {
        if (verificaDisponibilitateTeren(inchiriere.getIdTeren(), inchiriere.getDataInceput(), inchiriere.getDataSfarsit())) {
            String sql = "INSERT INTO inchiriereteren (id, id_client, id_proprietar, id_teren, data_inceput, data_sfarsit, pret) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, inchiriere.getId());
                stmt.setInt(2, inchiriere.getIdClient());
                stmt.setInt(3, inchiriere.getIdProprietar());
                stmt.setInt(4, inchiriere.getIdTeren());
                stmt.setTimestamp(5, Timestamp.valueOf(inchiriere.getDataInceput()));
                stmt.setTimestamp(6, Timestamp.valueOf(inchiriere.getDataSfarsit()));
                stmt.setInt(7, inchiriere.getPret());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<InchiriereTeren> getAllInchirieri() {
        List<InchiriereTeren> inchirieri = new ArrayList<>();
        String sql = "SELECT id, id_client, id_proprietar, id_teren, data_inceput, data_sfarsit, pret FROM inchiriereteren";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                InchiriereTeren inchiriere = new InchiriereTeren(rs.getInt("id"), rs.getInt("id_client"), rs.getInt("id_proprietar"), rs.getInt("id_teren"), rs.getTimestamp("data_inceput").toLocalDateTime(), rs.getTimestamp("data_sfarsit").toLocalDateTime(), rs.getInt("pret"));
                inchirieri.add(inchiriere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return inchirieri;
    }

    @Override
    public InchiriereTeren getInchiriereById(int id) {
        InchiriereTeren inchiriere = null;
        String sql = "SELECT id, id_client, id_proprietar, id_teren, data_inceput, data_sfarsit, pret FROM inchiriereteren WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    inchiriere = new InchiriereTeren(rs.getInt("id"), rs.getInt("id_client"), rs.getInt("id_proprietar"), rs.getInt("id_teren"), rs.getTimestamp("data_inceput").toLocalDateTime(), rs.getTimestamp("data_sfarsit").toLocalDateTime(), rs.getInt("pret"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return inchiriere;
    }

    @Override
    public void updateInchiriere(int id, InchiriereTeren inchiriere) {
        String sql = "UPDATE inchiriereteren SET id = ?, id_client = ?, id_proprietar = ?, id_teren = ?, data_inceput = ?, data_sfarsit = ?, pret = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setInt(2, inchiriere.getIdClient());
            stmt.setInt(3, inchiriere.getIdProprietar());
            stmt.setInt(4, inchiriere.getIdTeren());
            stmt.setTimestamp(5, Timestamp.valueOf(inchiriere.getDataInceput()));
            stmt.setTimestamp(6, Timestamp.valueOf(inchiriere.getDataSfarsit()));
            stmt.setInt(7, inchiriere.getPret());
            stmt.setInt(8, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteInchiriere(int id) {
        String sql = "DELETE FROM inchiriereteren WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean verificaDisponibilitateTeren(int idTeren, LocalDateTime dataInceput, LocalDateTime dataSfarsit) {
        String sql = "SELECT data_inceput, data_sfarsit FROM inchiriereteren WHERE id_teren = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTeren);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LocalDateTime existingDataInceput = rs.getTimestamp("data_inceput").toLocalDateTime();
                    LocalDateTime existingDataSfarsit = rs.getTimestamp("data_sfarsit").toLocalDateTime();
                    if (areSuprapunere(existingDataInceput, existingDataSfarsit, dataInceput, dataSfarsit)) {
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    private boolean areSuprapunere(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        if(end1.isEqual(start2) || start1.isEqual(end2)) {
            return false;
        }
        return !start1.isAfter(end2) && !start2.isAfter(end1);
    }
}
