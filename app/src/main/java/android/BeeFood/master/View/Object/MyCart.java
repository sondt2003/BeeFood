package android.BeeFood.master.View.Object;

public class MyCart {
    int id;
    int avt;
    String name;
    int coutItem;
    double khoangCach;
    double tongGia;

    public MyCart(int avt, String name, int coutItem, double khoangCach, double tongGia) {
        this.avt = avt;
        this.name = name;
        this.coutItem = coutItem;
        this.khoangCach = khoangCach;
        this.tongGia = tongGia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvt() {
        return avt;
    }

    public void setAvt(int avt) {
        this.avt = avt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoutItem() {
        return coutItem;
    }

    public void setCoutItem(int coutItem) {
        this.coutItem = coutItem;
    }

    public double getKhoangCach() {
        return khoangCach;
    }

    public void setKhoangCach(double khoangCach) {
        this.khoangCach = khoangCach;
    }

    public double getTongGia() {
        return tongGia;
    }

    public void setTongGia(double tongGia) {
        this.tongGia = tongGia;
    }
}
