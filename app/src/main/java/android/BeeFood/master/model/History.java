package android.BeeFood.master.model;

public class History {
    String idUser,idFood,dayMonthYear,status,idDanhGia;

    public History(String idUser, String idFood, String dayMonthYear, String status, String idDanhGia) {
        this.idUser = idUser;
        this.idFood = idFood;
        this.dayMonthYear = dayMonthYear;
        this.status = status;
        this.idDanhGia = idDanhGia;
    }

    public String getIdUser() {
        return idUser;
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
