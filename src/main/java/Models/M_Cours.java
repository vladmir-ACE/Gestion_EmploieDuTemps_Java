package Models;

public class M_Cours {
    private  Integer id;
    private String matiere;
    private  String enseignant;
    private  String classe;
    private  String year;

    public M_Cours(Integer id,String matiere, String enseignant, String classe, String year) {
        this.id=id;
        this.matiere = matiere;
        this.enseignant = enseignant;
        this.classe = classe;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(String enseignant) {
        this.enseignant = enseignant;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
