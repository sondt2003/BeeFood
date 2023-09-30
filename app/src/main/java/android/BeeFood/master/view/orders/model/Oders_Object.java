package android.BeeFood.master.view.orders.model;

public class Oders_Object {
    private int avt,soluong,status;
    private String name;
    private double khoang_Cach,giaTien;

    public Oders_Object(int avt, int soluong, int status, String name, double khoang_cach, double giaTien) {
        this.avt = avt;
        this.soluong = soluong;
        this.status = status;
        this.name = name;
        khoang_Cach = khoang_cach;
        this.giaTien = giaTien;
    }

    public Oders_Object() {
    }

    public int getAvt() {
        return avt;
    }

    public void setAvt(int avt) {
        this.avt = avt;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getKhoang_Cach() {
        return khoang_Cach;
    }

    public void setKhoang_Cach(double khoang_Cach) {
        this.khoang_Cach = khoang_Cach;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }
}
