/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_2310010119.ui;
import pbo_2310010119.db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
/**
 *
 * @author ThinkPad
 */
public class LoginFrame extends JFrame {
     private JTextField tfUser;
    private JPasswordField pfPass;

    public LoginFrame() {
        setTitle("Login Admin");
        setSize(380,220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
    }

    private void init() {
        JPanel p = new JPanel(new GridLayout(4,2,5,5));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        p.add(new JLabel("Username:"));
        tfUser = new JTextField(); 
        p.add(tfUser);

        p.add(new JLabel("Password:"));
        pfPass = new JPasswordField(); 
        p.add(pfPass);

        JButton btnLogin = new JButton("Login");
        JButton btnGanti = new JButton("Ganti Username/Password");

        p.add(btnLogin);
        p.add(btnGanti);
        add(p);

        btnLogin.addActionListener(e -> loginAction());

        btnGanti.addActionListener(e -> showChangeAccountDialog());
    }
    private void loginAction() {
        String u = tfUser.getText();
        String pws = new String(pfPass.getPassword());
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM user WHERE username=? AND password=?")) {
            ps.setString(1, u);
            ps.setString(2, pws);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
    JOptionPane.showMessageDialog(this, "Login berhasil!");
    SwingUtilities.invokeLater(() -> new MainMenuFrame().setVisible(true));
    dispose();
} else {
    JOptionPane.showMessageDialog(this, "Login gagal! Username atau password salah.");
}

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error DB: " + ex.getMessage());
        }
    }

    private void showChangeAccountDialog() {
        JTextField tfOldUser = new JTextField();
        JPasswordField pfOldPass = new JPasswordField();
        JTextField tfNewUser = new JTextField();
        JPasswordField pfNewPass = new JPasswordField();

        JPanel panel = new JPanel(new GridLayout(4,2,5,5));
        panel.add(new JLabel("Username Lama:"));
        panel.add(tfOldUser);
        panel.add(new JLabel("Password Lama:"));
        panel.add(pfOldPass);
        panel.add(new JLabel("Username Baru:"));
        panel.add(tfNewUser);
        panel.add(new JLabel("Password Baru:"));
        panel.add(pfNewPass);

        int result = JOptionPane.showConfirmDialog(
            this, panel, "Ganti Username & Password",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String oldUser = tfOldUser.getText().trim();
            String oldPass = new String(pfOldPass.getPassword());
            String newUser = tfNewUser.getText().trim();
            String newPass = new String(pfNewPass.getPassword());

            if (oldUser.isEmpty() || oldPass.isEmpty() || newUser.isEmpty() || newPass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua kolom wajib diisi!");
                return;
            }

            try (Connection c = DBConnection.getConnection()) {
                PreparedStatement psCheck = c.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
                psCheck.setString(1, oldUser);
                psCheck.setString(2, oldPass);
                ResultSet rs = psCheck.executeQuery();

                if (rs.next()) {
                    PreparedStatement psUpdate = c.prepareStatement(
                        "UPDATE user SET username=?, password=? WHERE username=? AND password=?");
                    psUpdate.setString(1, newUser);
                    psUpdate.setString(2, newPass);
                    psUpdate.setString(3, oldUser);
                    psUpdate.setString(4, oldPass);
                    int rows = psUpdate.executeUpdate();
                    if (rows > 0)
                        JOptionPane.showMessageDialog(this, "Username dan password berhasil diubah!");
                    else
                        JOptionPane.showMessageDialog(this, "Gagal memperbarui data!");
                } else {
                    JOptionPane.showMessageDialog(this, "Username atau password lama salah!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error DB: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
