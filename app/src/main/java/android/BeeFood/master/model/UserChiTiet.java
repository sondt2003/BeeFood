package android.BeeFood.master.model;

public class UserChiTiet {
    private String email,fullname,gender,date,url,address,pin;

    public UserChiTiet(String email, String fullname, String gender, String date, String url, String address, String pin) {
        this.email = email;
        this.fullname = fullname;
        this.gender = gender;
        this.date = date;
        this.url = url;
        this.address = address;
        this.pin = pin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setId_User(String id_User) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
