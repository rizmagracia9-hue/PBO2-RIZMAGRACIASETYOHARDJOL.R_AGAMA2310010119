/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_2310010119.ui;
import pbo_2310010119.dao.JemaatDAO;
import pbo_2310010119.model.Jemaat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author ThinkPad
 */
public class JemaatFrame extends JFrame{
     private JTextField tfNama, tfAlamat, tfNoKk, tfPenghasilan;
    private JTable table;
    private DefaultTableModel tableModel;
    private JemaatDAO dao = new JemaatDAO();
    private int selectedId = -1;

    public JemaatFrame() {
        setTitle("Data Jemaat");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        loadTable();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(5,2,5,5));
        form.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        form.add(new JLabel("Nama:"));
        tfNama = new JTextField(); form.add(tfNama);
        form.add(new JLabel("Alamat:"));
        tfAlamat = new JTextField(); form.add(tfAlamat);
        form.add(new JLabel("No. Kartu Keluarga:"));
        tfNoKk = new JTextField(); form.add(tfNoKk);
        form.add(new JLabel("Penghasilan:"));
        tfPenghasilan = new JTextField(); form.add(tfPenghasilan);

        JButton btnSave = new JButton("Simpan");
        JButton btnUpdate = new JButton("Ubah");
        JButton btnDelete = new JButton("Hapus");
        JButton btnClear = new JButton("Clear");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPanel.add(btnSave); btnPanel.add(btnUpdate); btnPanel.add(btnDelete); btnPanel.add(btnClear);

        JPanel north = new JPanel(new BorderLayout());
        north.add(form, BorderLayout.CENTER);
        north.add(btnPanel, BorderLayout.SOUTH);
        panel.add(north, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID","Nama","Alamat","No KK","Penghasilan"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        panel.add(scroll, BorderLayout.CENTER);

        add(panel);

        btnSave.addActionListener(e -> saveAction());
        btnUpdate.addActionListener(e -> updateAction());
        btnDelete.addActionListener(e -> deleteAction());
        btnClear.addActionListener(e -> clearForm());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if (r >= 0) {
                    selectedId = Integer.parseInt(tableModel.getValueAt(r,0).toString());
                    tfNama.setText(tableModel.getValueAt(r,1).toString());
                    tfAlamat.setText(tableModel.getValueAt(r,2).toString());
                    tfNoKk.setText(tableModel.getValueAt(r,3).toString());
                    tfPenghasilan.setText(tableModel.getValueAt(r,4).toString());
                }
            }
        });
    }

    private void loadTable() {
        try {
            List<Jemaat> list = dao.getAll();
            tableModel.setRowCount(0);
            for (Jemaat j : list) {
                tableModel.addRow(new Object[]{
                    j.getId(), j.getNama(), j.getAlamat(), j.getNoKk(), j.getPenghasilan()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error load data: "+ex.getMessage());
        }
    }

    private void saveAction() {
        try {
            String nama = tfNama.getText().trim();
            String alamat = tfAlamat.getText().trim();
            String nokk = tfNoKk.getText().trim();
            double peng = Double.parseDouble(tfPenghasilan.getText().trim());

            Jemaat j = new Jemaat(nama, alamat, nokk, peng);
            dao.insert(j);
            clearForm();
            loadTable();
            JOptionPane.showMessageDialog(this, "Data tersimpan.");
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Penghasilan harus angka.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: "+ex.getMessage());
        }
    }

    private void updateAction() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan diubah.");
            return;
        }
        try {
            String nama = tfNama.getText().trim();
            String alamat = tfAlamat.getText().trim();
            String nokk = tfNoKk.getText().trim();
            double peng = Double.parseDouble(tfPenghasilan.getText().trim());

            Jemaat j = new Jemaat(selectedId, nama, alamat, nokk, peng);
            dao.update(j);
            clearForm();
            loadTable();
            JOptionPane.showMessageDialog(this, "Data diubah.");
            selectedId = -1;
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Penghasilan harus angka.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: "+ex.getMessage());
        }
    }

    private void deleteAction() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan dihapus.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                dao.delete(selectedId);
                loadTable();
                clearForm();
                selectedId = -1;
                JOptionPane.showMessageDialog(this, "Data dihapus.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: "+ex.getMessage());
            }
        }
    }

    private void clearForm() {
        tfNama.setText("");
        tfAlamat.setText("");
        tfNoKk.setText("");
        tfPenghasilan.setText("");
        table.clearSelection();
        selectedId = -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JemaatFrame().setVisible(true);
        });
    }
}
