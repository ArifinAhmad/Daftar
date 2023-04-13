package com.rsjms.daftar.Model;

public class KamarModel {
    public String nmKamar;
    public String nJumlah;
    public String nIsi;
    public String nKosong;


public KamarModel(String nmKamar, String nJumlah,String nIsi,String nKosong){
    this.nmKamar = nmKamar;
    this.nJumlah = nJumlah;
    this.nIsi = nIsi;
    this.nKosong = nKosong;
}

    public String getNmKamar() {
        return nmKamar;
    }

    public void setNmKamar(String nmKamar) {
        this.nmKamar = nmKamar;
    }

    public String getnJumlah() {
        return nJumlah;
    }

    public void setnJumlah(String nJumlah) {
        this.nJumlah = nJumlah;
    }

    public String getnIsi() {
        return nIsi;
    }

    public void setnIsi(String nIsi) {
        this.nIsi = nIsi;
    }

    public String getnKosong() {
        return nKosong;
    }

    public void setnKosong(String nKosong) {
        this.nKosong = nKosong;
    }
}
