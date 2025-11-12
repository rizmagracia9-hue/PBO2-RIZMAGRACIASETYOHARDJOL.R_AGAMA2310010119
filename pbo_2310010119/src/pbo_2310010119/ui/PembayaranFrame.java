/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_2310010119.ui;
import pbo_2310010119.dao.PembayaranDAO;
import pbo_2310010119.db.DBConnection;
import pbo_2310010119.model.Pembayaran;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.List;
/**
 *
 * @author ThinkPad
 */
public class PembayaranFrame extends JFrame {
    private JTextField tfId, tfTanggal, tfJumlah;
    private JComboBox<String> cbJemaat, cbIuran;
    private JTable table;
    private DefaultTableModel model;
    private PembayaranDAO dao = new PembayaranDAO();

    public PembayaranFrame() {
        setTitle("Data Pembayaran Iuran Jemaat");
        setSize(650,450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
        loadCombo();
        loadData();
    }

    private void initUI() {
        JPanel form = new JPanel(new GridLayout(5,2,5,5));
        form.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        tfId = new JTextField(); tfId.setEditable(false);
        cbJemaat = new JComboBox<>();
        cbIuran = new JComboBox<>();
        tfTanggal = new JTextField();
        tfJumlah = new JTextField();

        form.add(new JLabel("ID Pembayaran:")); form.add(tfId);
        form.add(new JLabel("Jemaat:")); form.add(cbJemaat);
        form.add(new JLabel("Iuran:")); form.add(cbIuran);
        form.add(new JLabel("Tanggal (YYYY-MM-DD):")); form.add(tfTanggal);
        form.add(new JLabel("Jumlah:")); form.add(tfJumlah);

        JPanel tombol = new JPanel();
        JButton btnAdd = new JButton("Tambah");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Hapus");
        JButton btnClear = new JButton("Clear");
        tombol.add(btnAdd); tombol.add(btnEdit);
        tombol.add(btnDelete); tombol.add(btnClear);

        model = new DefaultTableModel(new String[]{"ID", "Jemaat", "Iuran", "Tanggal", "Jumlah"}, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);

        add(form, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        add(tombol, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> simpan());
        btnEdit.addActionListener(e -> ubah());
        btnDelete.addActionListener(e -> hapus());
        btnClear.addActionListener(e -> clear());
        table.getSelectionModel().addListSelectionListener(e -> isiForm());
    }

    private void loadCombo() {
        try (Connection c = DBConnection.getConnection()) {
            cbJemaat.removeAllItems();
            cbIuran.removeAllItems();

            ResultSet rs1 = c.createStatement().executeQuery("SELECT id_jemaat, nama FROM jemaat");
            while (rs1.next()) {
                cbJemaat.addItem(rs1.getInt("id_jemaat") + " - " + rs1.getString("nama"));
            }

            ResultSet rs2 = c.createStatement().executeQuery("SELECT id_iuran, nama_iuran FROM iuran");
            while (rs2.next()) {
                cbIuran.addItem(rs2.getInt("id_iuran") + " - " + rs2.getString("nama_iuran"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal load combo: " + ex.getMessage());
        }
    }

    private int getSelectedId(JComboBox<String> cb) {
        if (cb.getSelectedItem() == null) return 0;
        return Integer.parseInt(cb.getSelectedItem().toString().split(" - ")[0]);
    }

    private void loadData() {
        try {
            model.setRowCount(0);
            List<Pembayaran> list = dao.getAll();
            for (Pembayaran p : list) {
                model.addRow(new Object[]{
                        p.getIdPembayaran(),
                        p.getIdJemaat(),
                        p.getIdIuran(),
                        p.getTanggal(),
                        p.getJumlah()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error DB: " + ex.getMessage());
        }
    }

    private void simpan() {
        try {
            int idJemaat = getSelectedId(cbJemaat);
            int idIuran = getSelectedId(cbIuran);
            dao.insert(new Pembayaran(
                    idJemaat, idIuran, tfTanggal.getText(), Double.parseDouble(tfJumlah.getText())
            ));
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
            loadData(); clear();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal simpan: " + ex.getMessage());
        }
    }

    private void ubah() {
        if (tfId.getText().isEmpty()) return;
        try {
            int idJemaat = getSelectedId(cbJemaat);
            int idIuran = getSelectedId(cbIuran);
            dao.update(new Pembayaran(
                    Integer.parseInt(tfId.getText()),
                    idJemaat,
                    idIuran,
                    tfTanggal.getText(),
                    Double.parseDouble(tfJumlah.getText())
            ));
            JOptionPane.showMessageDialog(this, "Data berhasil diubah!");
            loadData(); clear();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal ubah: " + ex.getMessage());
        }
    }

    private void hapus() {
        if (tfId.getText().isEmpty()) return;
        try {
            dao.delete(Integer.parseInt(tfId.getText()));
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            loadData(); clear();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal hapus: " + ex.getMessage());
        }
    }

    private void clear() {
        tfId.setText("");
        tfTanggal.setText("");
        tfJumlah.setText("");
        table.clearSelection();
        cbJemaat.setSelectedIndex(-1);
        cbIuran.setSelectedIndex(-1);
    }

    private void isiForm() {
        int row = table.getSelectedRow();
        if (row != -1) {
            tfId.setText(model.getValueAt(row, 0).toString());
            tfTanggal.setText(model.getValueAt(row, 3).toString());
            tfJumlah.setText(model.getValueAt(row, 4).toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PembayaranFrame().setVisible(true));
    }
}
