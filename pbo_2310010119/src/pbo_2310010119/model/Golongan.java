/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_2310010119.model;

/**
 *
 * @author ThinkPad
 */
public class Golongan {
    private int idGolongan;
    private String namaGolongan;
    private String keterangan;

    public Golongan() {}

    public Golongan(int idGolongan, String namaGolongan, String keterangan) {
        this.idGolongan = idGolongan;
        this.namaGolongan = namaGolongan;
        this.keterangan = keterangan;
    }

    public Golongan(String namaGolongan, String keterangan) {
        this.namaGolongan = namaGolongan;
        this.keterangan = keterangan;
    }

    public int getIdGolongan() {
        return idGolongan;
    }

    public void setIdGolongan(int idGolongan) {
        this.idGolongan = idGolongan;
    }

    public String getNamaGolongan() {
        return namaGolongan;
    }

    public void setNamaGolongan(String namaGolongan) {
        this.namaGolongan = namaGolongan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
