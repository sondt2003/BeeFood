package android.BeeFood.master.model;

public class khuyenMai {
    String id,soTienGiam,Min,Max,soLuong;

    public khuyenMai(String id, String soTienGiam, String min, String max, String soLuong) {
        this.id = id;
        this.soTienGiam = soTienGiam;
        Min = min;
        Max = max;
        this.soLuong = soLuong;
    }


    public khuyenMai(String soTienGiam, String min, String max, String soLuong) {
        this.soTienGiam = soTienGiam;
        Min = min;
        Max = max;
        this.soLuong = soLuong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSoTienGiam() {
        return soTienGiam;
    }

    public void setSoTienGiam(String soTienGiam) {
        this.soTienGiam = soTienGiam;
    }

    public String getMin() {
        return Min;
    }

    public void setMin(String min) {
        Min = min;
    }

    public String getMax() {
        return Max;
    }

    public void setMax(String max) {
        Max = max;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }
}
