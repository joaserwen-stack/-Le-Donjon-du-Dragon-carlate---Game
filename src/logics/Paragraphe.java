package logics;

public class Paragraphe {
    private int id;
    private String texte;
    private TypeFin type;

    public Paragraphe(int id, String texte, TypeFin type) {
        this.id = id;
        this.texte = texte;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public String getTexte() {
        return this.texte;
    }

    public TypeFin getType() {
        return this.type;
    }
}