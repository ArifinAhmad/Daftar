package com.rsjms.daftar.Model;

public class RiwayatModel {
    public String Mtgl_registrasi;
    public String Mnorawat;
    public String MnmPoli;
    public String MnmDokter;
    public String Mnstatus;
    public  String Mcarabayar;
    public String Mnoantrian;

  public RiwayatModel(String Mtgl_registrasi,String Mnorawat,String MnmPoli, String MnmDokter, String Mnstatus,String Mcarabayar, String Mnoantrian ) {
      this.Mtgl_registrasi = Mtgl_registrasi;
      this.Mnorawat = Mnorawat;
      this.MnmPoli = MnmPoli;
      this.MnmDokter = MnmDokter;
      this.Mnstatus = Mnstatus;
      this.Mcarabayar = Mcarabayar;
      this.Mnoantrian = Mnoantrian;

  }

    public String getMtgl_registrasi() {
        return Mtgl_registrasi;
    }

    public void setMtgl_registrasi(String mtgl_registrasi) {
        Mtgl_registrasi = mtgl_registrasi;
    }

    public String getMnorawat() {
        return Mnorawat;
    }

    public void setMnorawat(String mnorawat) {
        Mnorawat = mnorawat;
    }

    public String getMnmPoli() {
        return MnmPoli;
    }

    public void setMnmPoli(String mnmPoli) {
        MnmPoli = mnmPoli;
    }

    public String getMnmDokter() {
        return MnmDokter;
    }

    public void setMnmDokter(String mnmDokter) {
        MnmDokter = mnmDokter;
    }

    public String getMnstatus() {
        return Mnstatus;
    }

    public void setMnstatus(String mnstatus) {
        Mnstatus = mnstatus;
    }

    public String getMcarabayar() {
        return Mcarabayar;
    }

    public void setMcarabayar(String mcarabayar) {
        Mcarabayar = mcarabayar;
    }

    public String getMnoantrian() {
        return Mnoantrian;
    }

    public void setMnoantrian(String mnoantrian) {
        Mnoantrian = mnoantrian;
    }
}
