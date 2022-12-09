package android.BeeFood.master.model;

public class OderFood {
    String idOderFood,idFood,emailFood,priceOderFood,amountoffood,khoangcach;

    public OderFood(String idOderFood, String idFood, String emailFood, String priceOderFood, String amountoffood, String khoangcach) {
        this.idOderFood = idOderFood;
        this.idFood = idFood;
        this.emailFood = emailFood;
        this.priceOderFood = priceOderFood;
        this.amountoffood = amountoffood;
        this.khoangcach = khoangcach;
    }

    public OderFood(String idFood, String emailFood, String priceOderFood, String amountoffood, String khoangcach) {
        this.idFood = idFood;
        this.emailFood = emailFood;
        this.priceOderFood = priceOderFood;
        this.amountoffood = amountoffood;
        this.khoangcach = khoangcach;
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

    public String getEmailFood() {
        return emailFood;
    }

    public void setEmailFood(String emailFood) {
        this.emailFood = emailFood;
    }

    public String getPriceOderFood() {
        return priceOderFood;
    }

    public void setPriceOderFood(String priceOderFood) {
        this.priceOderFood = priceOderFood;
    }

    public String getAmountoffood() {
        return amountoffood;
    }

    public void setAmountoffood(String amountoffood) {
        this.amountoffood = amountoffood;
    }

    public String getKhoangcach() {
        return khoangcach;
    }

    public void setKhoangcach(String khoangcach) {
        this.khoangcach = khoangcach;
    }
}
