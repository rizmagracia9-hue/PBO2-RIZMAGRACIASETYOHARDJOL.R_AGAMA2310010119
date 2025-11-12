/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_2310010119.ui;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author ThinkPad
 */
public class MainMenuFrame extends JFrame {
    public MainMenuFrame() {
        setTitle("Menu Utama - Aplikasi Gereja");
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(5,1,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));

        JLabel lblTitle = new JLabel("MENU UTAMA", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JButton btnGolongan = new JButton("Data Golongan");
        JButton btnJemaat = new JButton("Data Jemaat");
        JButton btnIuran = new JButton("Data Iuran");
        JButton btnPembayaran = new JButton("Data Pembayaran");
        JButton btnLogout = new JButton("Logout");

        panel.add(lblTitle);
        panel.add(btnGolongan);
        panel.add(btnJemaat);
        panel.add(btnIuran);
        panel.add(btnPembayaran);

        JPanel bawah = new JPanel();
        bawah.add(btnLogout);

        add(panel, BorderLayout.CENTER);
        add(bawah, BorderLayout.SOUTH);

        btnGolongan.addActionListener(e -> new GolonganFrame().setVisible(true));
        btnJemaat.addActionListener(e -> new JemaatFrame().setVisible(true));
        btnIuran.addActionListener(e -> new IuranFrame().setVisible(true));
        btnPembayaran.addActionListener(e -> new PembayaranFrame().setVisible(true));

        btnLogout.addActionListener(e -> {
            dispose(); 
            new LoginFrame().setVisible(true); 
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenuFrame().setVisible(true));
    }
}
