package android.BeeFood.master.model;

public class DanhGia {
    String idDanhGia,email,idFood,numberStar,comment,time;

    public DanhGia(String idDanhGia, String email, String idFood, String numberStar, String comment, String time) {
        this.idDanhGia = idDanhGia;
        this.email = email;
        this.idFood = idFood;
        this.numberStar = numberStar;
        this.comment = comment;
        this.time = time;
    }

    public String getIdDanhGia() {
        return idDanhGia;
    }

    public void setIdDanhGia(String idDanhGia) {
        this.idDanhGia = idDanhGia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    public String getNumberStar() {
        return numberStar;
    }

    public void setNumberStar(String numberStar) {
        this.numberStar = numberStar;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
