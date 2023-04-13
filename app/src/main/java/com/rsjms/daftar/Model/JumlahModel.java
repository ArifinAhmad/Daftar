package com.rsjms.daftar.Model;

public class JumlahModel {
    public String nMPoli;
    public String nJumlah;


    public JumlahModel(String nMPoli, String nJumlah){
        this.nMPoli = nMPoli;
        this.nJumlah = nJumlah;

    }

    public String getnMPoli() {
        return nMPoli;
    }

    public void setnMPoli(String nMPoli) {
        this.nMPoli = nMPoli;
    }

    public String getnJumlah() {
        return nJumlah;
    }

    public void setnJumlah(String nJumlah) {
        this.nJumlah = nJumlah;
    }
}

