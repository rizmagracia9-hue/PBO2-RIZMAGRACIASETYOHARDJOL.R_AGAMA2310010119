/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_2310010119.dao;
import pbo_2310010119.db.DBConnection;
import pbo_2310010119.model.Jemaat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ThinkPad
 */
public class JemaatDAO {
    public void insert(Jemaat j) throws SQLException {
        String sql = "INSERT INTO jemaat (nama, alamat, no_kartu_keluarga, penghasilan) VALUES (?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, j.getNama());
            ps.setString(2, j.getAlamat());
            ps.setString(3, j.getNoKk());
            ps.setDouble(4, j.getPenghasilan());
            ps.executeUpdate();
        }
    }

    public void update(Jemaat j) throws SQLException {
        String sql = "UPDATE jemaat SET nama=?, alamat=?, no_kartu_keluarga=?, penghasilan=? WHERE id_jemaat=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, j.getNama());
            ps.setString(2, j.getAlamat());
            ps.setString(3, j.getNoKk());
            ps.setDouble(4, j.getPenghasilan());
            ps.setInt(5, j.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM jemaat WHERE id_jemaat=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Jemaat> getAll() throws SQLException {
        List<Jemaat> list = new ArrayList<>();
        String sql = "SELECT * FROM jemaat";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Jemaat j = new Jemaat(
                    rs.getInt("id_jemaat"),
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("no_kartu_keluarga"),
                    rs.getDouble("penghasilan")
                );
                list.add(j);
            }
        }
        return list;
    }

    public Jemaat getById(int id) throws SQLException {
        String sql = "SELECT * FROM jemaat WHERE id_jemaat=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Jemaat(
                        rs.getInt("id_jemaat"),
                        rs.getString("nama"),
                        rs.getString("alamat"),
                        rs.getString("no_kartu_keluarga"),
                        rs.getDouble("penghasilan")
                    );
                }
            }
        }
        return null;
    }
}
