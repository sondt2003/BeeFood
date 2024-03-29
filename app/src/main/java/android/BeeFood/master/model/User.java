package android.BeeFood.master.model;

public class User {
    UserChiTiet userChiTiet;
    private String email, name, level;

    public User() {
    }

    public User(String email, String name, String level, UserChiTiet userChiTiet) {
        this.email = email;
        this.name = name;
        this.level = level;
        this.userChiTiet = userChiTiet;
    }

    public UserChiTiet getUserChiTiet() {
        return userChiTiet;
    }

    public void setUserChiTiet(UserChiTiet userChiTiet) {
        this.userChiTiet = userChiTiet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
