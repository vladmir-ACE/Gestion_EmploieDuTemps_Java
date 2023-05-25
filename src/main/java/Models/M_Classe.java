package Models;

public class M_Classe {

    private  int id;

    private  String intituler;
    private String code;

    public M_Classe(int id, String intituler, String code) {
        this.id = id;
        this.intituler = intituler;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntituler() {
        return intituler;
    }

    public void setIntituler(String intituler) {
        this.intituler = intituler;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
