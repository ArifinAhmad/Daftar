package com.rsjms.daftar.Model;

public class DaftarModel {
    public String tglregister;
    public String kdpoli;
    public String kddokter;
    public String kdpj;
    public String nomorrm;

public DaftarModel(String tglregister, String kdpoli,String kddokter, String kdpj,String nomorrm){
    this.tglregister = tglregister;
    this.kdpoli = kdpoli;
    this.kddokter = kddokter;
    this.kdpj = kdpj;
    this.nomorrm = nomorrm;
}

    public String getTglregister() {
        return tglregister;
    }

    public void setTglregister(String tglregister) {
        this.tglregister = tglregister;
    }

    public String getKdpoli() {
        return kdpoli;
    }

    public void setKdpoli(String kdpoli) {
        this.kdpoli = kdpoli;
    }

    public String getKddokter() {
        return kddokter;
    }

    public void setKddokter(String kddokter) {
        this.kddokter = kddokter;
    }

    public String getKdpj() {
        return kdpj;
    }

    public void setKdpj(String kdpj) {
        this.kdpj = kdpj;
    }

    public String getNomorrm() {
        return nomorrm;
    }

    public void setNomorrm(String nomorrm) {
        this.nomorrm = nomorrm;
    }
}
