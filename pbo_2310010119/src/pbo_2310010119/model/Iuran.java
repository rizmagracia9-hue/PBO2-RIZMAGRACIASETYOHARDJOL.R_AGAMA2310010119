/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_2310010119.model;

/**
 *
 * @author ThinkPad
 */
public class Iuran {
    private int idIuran;
    private String namaIuran;
    private double jumlah;
    private String keterangan;

    public Iuran() {}

    public Iuran(int idIuran, String namaIuran, double jumlah, String keterangan) {
        this.idIuran = idIuran;
        this.namaIuran = namaIuran;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
    }

    public Iuran(String namaIuran, double jumlah, String keterangan) {
        this.namaIuran = namaIuran;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
    }

    public int getIdIuran() {
        return idIuran;
    }

    public void setIdIuran(int idIuran) {
        this.idIuran = idIuran;
    }

    public String getNamaIuran() {
        return namaIuran;
    }

    public void setNamaIuran(String namaIuran) {
        this.namaIuran = namaIuran;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
