package android.BeeFood.master.View.Object;

public class Loai_food {
    int id;
    int Avt;
    String name;

    public Loai_food(int avt, String name) {
        Avt = avt;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvt() {
        return Avt;
    }

    public void setAvt(int avt) {
        Avt = avt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
