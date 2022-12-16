package android.BeeFood.master.model;

public class History {
    String idUser,email,idFood,dayMonthYear,status,idDanhGia;

    public History(String email, String idFood, String dayMonthYear, String status, String idDanhGia) {
        this.email = email;
        this.idFood = idFood;
        this.dayMonthYear = dayMonthYear;
        this.status = status;
        this.idDanhGia = idDanhGia;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    public String getDayMonthYear() {
        return dayMonthYear;
    }

    public void setDayMonthYear(String dayMonthYear) {
        this.dayMonthYear = dayMonthYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdDanhGia() {
        return idDanhGia;
    }

    public void setIdDanhGia(String idDanhGia) {
        this.idDanhGia = idDanhGia;
    }
}
