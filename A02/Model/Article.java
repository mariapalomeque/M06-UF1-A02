package Model;

public class Article {
    private String nom;
    private float quantitat;
    private String unitat;
    private float preu;

    public Article(String nom, float quantitat, String unitat, float preu) {
        this.nom = nom;
        this.quantitat = quantitat;
        this.unitat = unitat;
        this.preu = preu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(float quantitat) {
        this.quantitat = quantitat;
    }

    public String getUnitat() {
        return unitat;
    }

    public void setUnitat(String unitat) {
        this.unitat = unitat;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    @Override
    public String toString() {
        return "Article{" +
                "nom='" + nom + '\'' +
                ", quantitat=" + quantitat +
                ", unitat='" + unitat + '\'' +
                ", preu=" + preu +
                '}';
    }
}
