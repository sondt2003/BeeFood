package android.BeeFood.master.model;

public class Food {
    private String id,Name,price,address,phoneNumber,email,idloai,tenloai,url,describle;

    public Food() {
    }

    public Food(String name, String price, String address, String phoneNumber, String email, String tenloai, String url, String describle) {
        Name = name;
        this.price = price;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.tenloai = tenloai;
        this.url = url;
        this.describle = describle;
    }

    public Food(String name, String price, String address, String phoneNumber, String email, String idloai, String tenloai, String url, String describle) {
        Name = name;
        this.price = price;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.idloai = idloai;
        this.tenloai = tenloai;
        this.url = url;
        this.describle = describle;
    }

    public Food(String id, String name, String price, String address, String phoneNumber, String email, String idloai, String tenloai, String url, String describle) {
        this.id = id;
        Name = name;
        this.price = price;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.idloai = idloai;
        this.tenloai = tenloai;
        this.url = url;
        this.describle = describle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdloai() {
        return idloai;
    }

    public void setIdloai(String idloai) {
        this.idloai = idloai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle;
    }
}
