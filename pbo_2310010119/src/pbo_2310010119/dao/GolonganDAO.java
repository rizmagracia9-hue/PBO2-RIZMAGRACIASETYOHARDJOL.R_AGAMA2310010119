/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_2310010119.dao;
import pbo_2310010119.db.DBConnection;
import pbo_2310010119.model.Golongan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ThinkPad
 */
public class GolonganDAO {
    public void insert(Golongan g) throws SQLException {
        String sql = "INSERT INTO golongan (nama_golongan, keterangan) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, g.getNamaGolongan());
            ps.setString(2, g.getKeterangan());
            ps.executeUpdate();
        }
    }

    public void update(Golongan g) throws SQLException {
        String sql = "UPDATE golongan SET nama_golongan=?, keterangan=? WHERE id_golongan=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, g.getNamaGolongan());
            ps.setString(2, g.getKeterangan());
            ps.setInt(3, g.getIdGolongan());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM golongan WHERE id_golongan=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Golongan> getAll() throws SQLException {
        List<Golongan> list = new ArrayList<>();
        String sql = "SELECT * FROM golongan";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Golongan(
                    rs.getInt("id_golongan"),
                    rs.getString("nama_golongan"),
                    rs.getString("keterangan")
                ));
            }
        }
        return list;
    }
}
