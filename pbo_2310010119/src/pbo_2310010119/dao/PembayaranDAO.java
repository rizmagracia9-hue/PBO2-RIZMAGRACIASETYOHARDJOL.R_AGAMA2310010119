/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_2310010119.dao;
import pbo_2310010119.db.DBConnection;
import pbo_2310010119.model.Pembayaran;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ThinkPad
 */
public class PembayaranDAO {
    public void insert(Pembayaran p) throws SQLException {
        String sql = "INSERT INTO pembayaran (id_jemaat, id_iuran, tanggal, jumlah) VALUES (?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getIdJemaat());
            ps.setInt(2, p.getIdIuran());
            ps.setString(3, p.getTanggal());
            ps.setDouble(4, p.getJumlah());
            ps.executeUpdate();
        }
    }

    public void update(Pembayaran p) throws SQLException {
        String sql = "UPDATE pembayaran SET id_jemaat=?, id_iuran=?, tanggal=?, jumlah=? WHERE id_pembayaran=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getIdJemaat());
            ps.setInt(2, p.getIdIuran());
            ps.setString(3, p.getTanggal());
            ps.setDouble(4, p.getJumlah());
            ps.setInt(5, p.getIdPembayaran());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM pembayaran WHERE id_pembayaran=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Pembayaran> getAll() throws SQLException {
        List<Pembayaran> list = new ArrayList<>();
        String sql = "SELECT * FROM pembayaran";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Pembayaran(
                    rs.getInt("id_pembayaran"),
                    rs.getInt("id_jemaat"),
                    rs.getInt("id_iuran"),
                    rs.getString("tanggal"),
                    rs.getDouble("jumlah")
                ));
            }
        }
        return list;
    }
}
