/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_2310010119.ui;
import pbo_2310010119.dao.GolonganDAO;
import pbo_2310010119.model.Golongan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ThinkPad
 */
public class GolonganFrame extends JFrame {
        private JTextField tfNama, tfKet, tfId;
    private JTable table;
    private DefaultTableModel model;
    private GolonganDAO dao = new GolonganDAO();

    public GolonganFrame() {
        setTitle("Data Golongan Jemaat");
        setSize(500,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
        loadData();
    }

    private void initUI() {
        JPanel form = new JPanel(new GridLayout(3,2,5,5));
        form.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        tfId = new JTextField(); tfId.setEditable(false);
        tfNama = new JTextField();
        tfKet = new JTextField();

        form.add(new JLabel("ID Golongan:")); form.add(tfId);
        form.add(new JLabel("Nama Golongan:")); form.add(tfNama);
        form.add(new JLabel("Keterangan:")); form.add(tfKet);

        JPanel tombol = new JPanel();
        JButton btnAdd = new JButton("Tambah");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Hapus");
        JButton btnClear = new JButton("Clear");
        tombol.add(btnAdd); tombol.add(btnEdit);
        tombol.add(btnDelete); tombol.add(btnClear);

        model = new DefaultTableModel(new String[]{"ID", "Nama Golongan", "Keterangan"}, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);

        add(form, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        add(tombol, BorderLayout.SOUTH);

        // event tombol
        btnAdd.addActionListener(e -> simpanData());
        btnEdit.addActionListener(e -> ubahData());
        btnDelete.addActionListener(e -> hapusData());
        btnClear.addActionListener(e -> clearForm());
        table.getSelectionModel().addListSelectionListener(e -> isiForm());
    }

    private void loadData() {
        try {
            model.setRowCount(0);
            List<Golongan> list = dao.getAll();
            for (Golongan g : list) {
                model.addRow(new Object[]{g.getIdGolongan(), g.getNamaGolongan(), g.getKeterangan()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error DB: " + ex.getMessage());
        }
    }

    private void simpanData() {
        try {
            dao.insert(new Golongan(tfNama.getText(), tfKet.getText()));
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
            loadData(); clearForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal simpan: " + ex.getMessage());
        }
    }

    private void ubahData() {
        if (tfId.getText().isEmpty()) return;
        try {
            dao.update(new Golongan(
                    Integer.parseInt(tfId.getText()),
                    tfNama.getText(),
                    tfKet.getText()));
            JOptionPane.showMessageDialog(this, "Data berhasil diubah!");
            loadData(); clearForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal ubah: " + ex.getMessage());
        }
    }

    private void hapusData() {
        if (tfId.getText().isEmpty()) return;
        try {
            dao.delete(Integer.parseInt(tfId.getText()));
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            loadData(); clearForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal hapus: " + ex.getMessage());
        }
    }

    private void clearForm() {
        tfId.setText("");
        tfNama.setText("");
        tfKet.setText("");
        table.clearSelection();
    }

    private void isiForm() {
        int row = table.getSelectedRow();
        if (row != -1) {
            tfId.setText(model.getValueAt(row, 0).toString());
            tfNama.setText(model.getValueAt(row, 1).toString());
            tfKet.setText(model.getValueAt(row, 2).toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GolonganFrame().setVisible(true));
    }

}
