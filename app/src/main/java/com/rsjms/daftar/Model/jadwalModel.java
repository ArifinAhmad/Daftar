package com.rsjms.daftar.Model;

public class jadwalModel {
    public String nmDokter;
    public String nmPoli;
    public String jamMulai;
    public String jamSelesai;

    public  jadwalModel(){}
    public jadwalModel(String nmDokter, String nmPoli, String jamMulai, String jamSelesai){
        this.nmDokter = nmDokter;
        this.nmPoli = nmPoli;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;

    }



    public String getNmDokter() {
        return nmDokter;
    }

    public void setNmDokter(String nmDokter) {
        this.nmDokter = nmDokter;
    }

    public String getNmPoli() {
        return nmPoli;
    }

    public void setNmPoli(String nmPoli) {
        this.nmPoli = nmPoli;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }



}

