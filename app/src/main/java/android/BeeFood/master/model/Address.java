package android.BeeFood.master.model;

public class Address {
    private String email,longaddress,lataddress,status;

    public Address(String email, String longaddress, String lataddress, String status) {
        this.email = email;
        this.longaddress = longaddress;
        this.lataddress = lataddress;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLongaddress() {
        return longaddress;
    }

    public void setLongaddress(String longaddress) {
        this.longaddress = longaddress;
    }

    public String getLataddress() {
        return lataddress;
    }

    public void setLataddress(String lataddress) {
        this.lataddress = lataddress;
    }
}
