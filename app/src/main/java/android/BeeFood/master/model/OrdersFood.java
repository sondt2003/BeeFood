package android.BeeFood.master.model;

public class OrdersFood {
    String IDOderFood,IDFood,EmailUser,PriceOderFood,KhoangCach,soLuong,AddressFood,AddressUser;

    public OrdersFood(String IDFood, String emailUser, String priceOderFood, String khoangCach, String soLuong, String addressFood, String addressUser) {
        this.IDFood = IDFood;
        EmailUser = emailUser;
        PriceOderFood = priceOderFood;
        KhoangCach = khoangCach;
        this.soLuong = soLuong;
        AddressFood = addressFood;
        AddressUser = addressUser;
    }

    public OrdersFood() {
    }

    public String getIDOderFood() {
        return IDOderFood;
    }

    public void setIDOderFood(String IDOderFood) {
        this.IDOderFood = IDOderFood;
    }

    public String getIDFood() {
        return IDFood;
    }

    public void setIDFood(String IDFood) {
        this.IDFood = IDFood;
    }

    public String getEmailUser() {
        return EmailUser;
    }

    public void setEmailUser(String emailUser) {
        EmailUser = emailUser;
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
