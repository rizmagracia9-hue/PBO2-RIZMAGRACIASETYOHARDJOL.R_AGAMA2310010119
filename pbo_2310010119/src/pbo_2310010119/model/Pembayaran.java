/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_2310010119.model;

/**
 *
 * @author ThinkPad
 */
public class Pembayaran {
    private int idPembayaran;
    private int idJemaat;
    private int idIuran;
    private String tanggal;
    private double jumlah;

    public Pembayaran() {}

    public Pembayaran(int idPembayaran, int idJemaat, int idIuran, String tanggal, double jumlah) {
        this.idPembayaran = idPembayaran;
        this.idJemaat = idJemaat;
        this.idIuran = idIuran;
        this.tanggal = tanggal;
        this.jumlah = jumlah;
    }

    public Pembayaran(int idJemaat, int idIuran, String tanggal, double jumlah) {
        this.idJemaat = idJemaat;
        this.idIuran = idIuran;
        this.tanggal = tanggal;
        this.jumlah = jumlah;
    }

    public int getIdPembayaran() { return idPembayaran; }
    public void setIdPembayaran(int idPembayaran) { this.idPembayaran = idPembayaran; }
    public int getIdJemaat() { return idJemaat; }
    public void setIdJemaat(int idJemaat) { this.idJemaat = idJemaat; }
    public int getIdIuran() { return idIuran; }
    public void setIdIuran(int idIuran) { this.idIuran = idIuran; }
    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    public double getJumlah() { return jumlah; }
    public void setJumlah(double jumlah) { this.jumlah = jumlah; }
}
