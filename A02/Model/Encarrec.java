package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Encarrec implements Serializable {
    private int id;
    private String nomCli;
    private String telCli;
    private String dataEncarrec;
    private ArrayList<Article> articles;
    private float preuTotal;

    public Encarrec(int id, String nomCli, String telCli, String dataEncarrec, ArrayList<Article> articles) {
        this.id = id;
        this.nomCli = nomCli;
        this.telCli = telCli;
        this.dataEncarrec = dataEncarrec;
        this.articles = articles;
        this.preuTotal = calcularPreuTotal();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCli() {
        return nomCli;
    }

    public void setNomCli(String nomCli) {
        this.nomCli = nomCli;
    }

    public String getTelCli() {
        return telCli;
    }

    public void setTelCli(String telCli) {
        this.telCli = telCli;
    }

    public String getDataEncarrec() {
        return dataEncarrec;
    }

    public void setDataEncarrec(String dataEncarrec) {
        this.dataEncarrec = dataEncarrec;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
        this.preuTotal = calcularPreuTotal(); 
    }

    public float getPreuTotal() {
        return preuTotal;
    }

    private float calcularPreuTotal() {
        float total = 0;
        for (Article article : articles) {
            total += article.getPreu() * article.getQuantitat();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Encarrec{" +
                "id=" + id +
                ", nomCli='" + nomCli + '\'' +
                ", telCli='" + telCli + '\'' +
                ", dataEncarrec='" + dataEncarrec + '\'' +
                ", preuTotal=" + preuTotal +
                ", articles=" + articles +
                '}';
    }
}
