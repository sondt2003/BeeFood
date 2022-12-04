package android.BeeFood.master.model;

public class loaiFood {
    private  String idloaifood,url,nameloai;

    public loaiFood(String idloaifood, String url, String nameloai) {
        this.idloaifood = idloaifood;
        this.url = url;
        this.nameloai = nameloai;
    }

    public loaiFood(String url, String nameloai) {
        this.url = url;
        this.nameloai = nameloai;
    }

    public String getIdloaifood() {
        return idloaifood;
    }

    public void setIdloaifood(String idloaifood) {
        this.idloaifood = idloaifood;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNameloai() {
        return nameloai;
    }

    public void setNameloai(String nameloai) {
        this.nameloai = nameloai;
    }
}
