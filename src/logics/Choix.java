package logics;

public class Choix {
    private String texte;
    private int cibleId;

    // Le constructeur
    public Choix(String texte, int cibleId) {
        this.texte = texte;
        this.cibleId = cibleId;
    }

    // Les Getters
    public String getTexte() {
        return this.texte;
    }

    public int getCibleId() {
        return this.cibleId;
    }
}