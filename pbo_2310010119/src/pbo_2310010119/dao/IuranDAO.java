/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_2310010119.dao;
import pbo_2310010119.db.DBConnection;
import pbo_2310010119.model.Iuran;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThinkPad
 */
public class IuranDAO {
    public void insert(Iuran i) throws SQLException {
        String sql = "INSERT INTO iuran (nama_iuran, jumlah, keterangan) VALUES (?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, i.getNamaIuran());
            ps.setDouble(2, i.getJumlah());
            ps.setString(3, i.getKeterangan());
            ps.executeUpdate();
        }
    }

    public void update(Iuran i) throws SQLException {
        String sql = "UPDATE iuran SET nama_iuran=?, jumlah=?, keterangan=? WHERE id_iuran=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, i.getNamaIuran());
            ps.setDouble(2, i.getJumlah());
            ps.setString(3, i.getKeterangan());
            ps.setInt(4, i.getIdIuran());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM iuran WHERE id_iuran=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Iuran> getAll() throws SQLException {
        List<Iuran> list = new ArrayList<>();
        String sql = "SELECT * FROM iuran";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Iuran(
                    rs.getInt("id_iuran"),
                    rs.getString("nama_iuran"),
                    rs.getDouble("jumlah"),
                    rs.getString("keterangan")
                ));
            }
        }
        return list;
    }
}
