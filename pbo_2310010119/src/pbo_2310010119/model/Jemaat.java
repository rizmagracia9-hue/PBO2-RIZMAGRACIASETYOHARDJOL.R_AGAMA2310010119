/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_2310010119.model;

/**
 *
 * @author ThinkPad
 */
public class Jemaat {
     private int id;
    private String nama;
    private String alamat;
    private String noKk;
    private double penghasilan;

    public Jemaat() {}

    public Jemaat(int id, String nama, String alamat, String noKk, double penghasilan) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.noKk = noKk;
        this.penghasilan = penghasilan;
    }

    public Jemaat(String nama, String alamat, String noKk, double penghasilan) {
        this.nama = nama;
        this.alamat = alamat;
        this.noKk = noKk;
        this.penghasilan = penghasilan;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public String getNoKk() { return noKk; }
    public void setNoKk(String noKk) { this.noKk = noKk; }
    public double getPenghasilan() { return penghasilan; }
    public void setPenghasilan(double penghasilan) { this.penghasilan = penghasilan; }

}
