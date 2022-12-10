package android.BeeFood.master.model;

public class BuyFood {
    String idBuyFood,idFood,emailUser,emailFood,amountofood,priceOderFood,khoangcach,status,priceShipper;

    public BuyFood() {
    }

    public BuyFood(String idFood, String emailUser, String emailFood, String amountofood, String priceOderFood, String khoangcach, String status) {
        this.idFood = idFood;
        this.emailUser = emailUser;
        this.emailFood = emailFood;
        this.amountofood = amountofood;
        this.priceOderFood = priceOderFood;
        this.khoangcach = khoangcach;
        this.status = status;
    }

    public BuyFood(String idBuyFood, String idFood, String emailUser, String emailFood, String amountofood, String priceOderFood, String khoangcach, String status) {
        this.idBuyFood = idBuyFood;
        this.idFood = idFood;
        this.emailUser = emailUser;
        this.emailFood = emailFood;
        this.amountofood = amountofood;
        this.priceOderFood = priceOderFood;
        this.khoangcach = khoangcach;
        this.status = status;
    }

    public BuyFood(String idBuyFood, String idFood, String emailUser, String emailFood, String amountofood, String priceOderFood, String khoangcach, String status, String priceShipper) {
        this.idBuyFood = idBuyFood;
        this.idFood = idFood;
        this.emailUser = emailUser;
        this.emailFood = emailFood;
        this.amountofood = amountofood;
        this.priceOderFood = priceOderFood;
        this.khoangcach = khoangcach;
        this.status = status;
        this.priceShipper = priceShipper;
    }

    public String getPriceShipper() {
        return priceShipper;
    }

    public void setPriceShipper(String priceShipper) {
        this.priceShipper = priceShipper;
    }

    public String getIdBuyFood() {
        return idBuyFood;
    }

    public void setIdBuyFood(String idBuyFood) {
        this.idBuyFood = idBuyFood;
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

    public String getEmailFood() {
        return emailFood;
    }

    public void setEmailFood(String emailFood) {
        this.emailFood = emailFood;
    }

    public String getAmountofood() {
        return amountofood;
    }

    public void setAmountofood(String amountofood) {
        this.amountofood = amountofood;
    }

    public String getPriceOderFood() {
        return priceOderFood;
    }

    public void setPriceOderFood(String priceOderFood) {
        this.priceOderFood = priceOderFood;
    }

    public String getKhoangcach() {
        return khoangcach;
    }

    public void setKhoangcach(String khoangcach) {
        this.khoangcach = khoangcach;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
