/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_2310010119.ui;
import pbo_2310010119.dao.IuranDAO;
import pbo_2310010119.model.Iuran;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author ThinkPad
 */
public class IuranFrame extends JFrame {
     private JTextField tfId, tfNama, tfJumlah, tfKet;
    private JTable table;
    private DefaultTableModel model;
    private IuranDAO dao = new IuranDAO();

    public IuranFrame() {
        setTitle("Data Iuran Gereja");
        setSize(550,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
        loadData();
    }

    private void initUI() {
        JPanel form = new JPanel(new GridLayout(4,2,5,5));
        form.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        tfId = new JTextField(); tfId.setEditable(false);
        tfNama = new JTextField();
        tfJumlah = new JTextField();
        tfKet = new JTextField();

        form.add(new JLabel("ID Iuran:")); form.add(tfId);
        form.add(new JLabel("Nama Iuran:")); form.add(tfNama);
        form.add(new JLabel("Jumlah:")); form.add(tfJumlah);
        form.add(new JLabel("Keterangan:")); form.add(tfKet);

        JPanel tombol = new JPanel();
        JButton btnAdd = new JButton("Tambah");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Hapus");
        JButton btnClear = new JButton("Clear");
        tombol.add(btnAdd); tombol.add(btnEdit);
        tombol.add(btnDelete); tombol.add(btnClear);

        model = new DefaultTableModel(new String[]{"ID", "Nama Iuran", "Jumlah", "Keterangan"}, 0);
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

    private void loadData() {
        try {
            model.setRowCount(0);
            List<Iuran> list = dao.getAll();
            for (Iuran i : list) {
                model.addRow(new Object[]{i.getIdIuran(), i.getNamaIuran(), i.getJumlah(), i.getKeterangan()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error DB: " + ex.getMessage());
        }
    }

    private void simpan() {
        try {
            double jumlah = Double.parseDouble(tfJumlah.getText());
            dao.insert(new Iuran(tfNama.getText(), jumlah, tfKet.getText()));
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
            loadData(); clear();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal simpan: " + ex.getMessage());
        }
    }

    private void ubah() {
        if (tfId.getText().isEmpty()) return;
        try {
            double jumlah = Double.parseDouble(tfJumlah.getText());
            dao.update(new Iuran(
                    Integer.parseInt(tfId.getText()),
                    tfNama.getText(),
                    jumlah,
                    tfKet.getText()
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
        tfNama.setText("");
        tfJumlah.setText("");
        tfKet.setText("");
        table.clearSelection();
    }

    private void isiForm() {
        int row = table.getSelectedRow();
        if (row != -1) {
            tfId.setText(model.getValueAt(row, 0).toString());
            tfNama.setText(model.getValueAt(row, 1).toString());
            tfJumlah.setText(model.getValueAt(row, 2).toString());
            tfKet.setText(model.getValueAt(row, 3).toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IuranFrame().setVisible(true));
    }
}
