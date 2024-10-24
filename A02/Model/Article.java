package Model;
import java.io.Serializable;

public class Article implements Serializable {

    String nomArticle;
    float nombreUnitats;
    String tipusUnitat;
    float preuArticle;

    public Article () {
    
    }

    public Article(String nomArticle, float nombreUnitats, String tipusUnitat, float preuArticle) {

        ValidatenomArticle(nomArticle);
        ValidateUnitats(nombreUnitats);
        ValidatetipusUnitat(tipusUnitat);
        ValidatepreuArticle(preuArticle);



        this.nomArticle = nomArticle;
        this.nombreUnitats = nombreUnitats;
        this.tipusUnitat = tipusUnitat;
        this.preuArticle = preuArticle;
        

    }

    public float getnombreUnitats() {
        return nombreUnitats;
    
    }
    public void setnombreUnitats(float nombreUnitats) {

        ValidateUnitats(nombreUnitats);

        this.nombreUnitats = nombreUnitats;
    }

    public String gettipusUnitat() {
        return tipusUnitat;
    }

    public void settipusUnitat(String tipusUnitat) {

        ValidatetipusUnitat(tipusUnitat);

        this.tipusUnitat = tipusUnitat;
    }

    public String getNomArticle() {
        return nomArticle;
    }
    
    public void setNomArticle(String nomArticle) {

        ValidatenomArticle(nomArticle);
        
        this.nomArticle = nomArticle;
    }

     public String getPreuArticle() {
        return preuArticle;
    }
    
    public void setPreuArticle(String preuArticle) {

        ValidatepreuArticle(preuArticle);
        
        this.preuArticle = preuArticle; 
    }

    @Override
    public String toString() { 
        return "Article [article=" + nomArticle + ", nombreUnitats=" + nombreUnitats + ", tipusUnitat=" + tipusUnitat  + ", preuArticle=" + preuArticle
                + "]";
    }

    public String toCSV() {
        return nomArticle + ";" + nombreUnitats + ";" + tipusUnitat + ";" + preuArticle + ";" ;
    }

    private void ValidateUnitats(float nombreUnitats) {
        if (nombreUnitats < 0) {
            throw new ArithmeticException("Quantitat no vàlida");
        }
    }

    private void ValidatetipusUnitat(String tipusUnitat) {
        if ((tipusUnitat == null) || !(tipusUnitat.matches("^[a-zA-Z]+$")))  {
            throw new IllegalArgumentException("Unitats no vàlides");
        }
    }

    private void ValidatenomArticle(String nomArticle) {
        if ((nomArticle == null) || nomArticle.matches("-?\\d+"))  {
            throw new IllegalArgumentException("Nom d'article no vàlid");
        }
    }
    
}