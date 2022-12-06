package android.BeeFood.master.model;

public class OderFood {
    String idOderFood,idFood,emailUser,PriceOderFood,KhoangCach,soLuong,AddressFood,AddressUser;

    public OderFood(String idFood, String emailUser, String priceOderFood, String khoangCach, String soLuong, String addressFood, String addressUser) {
        this.idFood = idFood;
        this.emailUser = emailUser;
        PriceOderFood = priceOderFood;
        KhoangCach = khoangCach;
        this.soLuong = soLuong;
        AddressFood = addressFood;
        AddressUser = addressUser;
    }

    public String getIdOderFood() {
        return idOderFood;
    }

    public void setIdOderFood(String idOderFood) {
        this.idOderFood = idOderFood;
    }

    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPriceOderFood() {
        return PriceOderFood;
    }

    public void setPriceOderFood(String priceOderFood) {
        PriceOderFood = priceOderFood;
    }

    public String getKhoangCach() {
        return KhoangCach;
    }

    public void setKhoangCach(String khoangCach) {
        KhoangCach = khoangCach;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getAddressFood() {
        return AddressFood;
    }

    public void setAddressFood(String addressFood) {
        AddressFood = addressFood;
    }

    public String getAddressUser() {
        return AddressUser;
    }

    public void setAddressUser(String addressUser) {
        AddressUser = addressUser;
    }
}
