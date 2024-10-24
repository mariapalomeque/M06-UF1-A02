package Model;
import java.util.ArrayList;

public class Encarrec {

    int idEncarrec;
    String nomCli;
    String telCli;
    String dataEncarrec;
    float preuTotal;
    ArrayList<Article> articles;

    
    public Encarrec(int idEncarrec, ifString nomCli, String telCli, String dataEncarrec, float preuTotal, ArrayList<Article> articles) {
        this.idEncarre = idEncarre;
        this.nomCli = nomCli;
        this.telCli = telCli;
        this.dataEncarrec = dataEncarrec;
        this.preuTotal = preuTotal;
        this.articles = articles;
    }

    public String getidEncarrec() {
        return idEncarrec;
    }


    public void setidEncarrec(String idEncarrec) {
        this.idEncarrec = idEncarrec;
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

    public String getpreuTotal() {
        return preuTotal;
    }

    public void setpreuTotal(String preuTotalc) {
        this.dataEncarrec = preuTotal;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }


    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    


    @Override
    public String toString() {
        return "Encarrec [idEncarrec=" + idEncarrec + nomCli=" + nomCli + ", telCli=" + telCli + ", dataEncarrec=" + dataEncarrec + ", preuTotal=" + preuTotal + ", articles="
                + articles + "]";
    }

    public String toCSV() {
        StringBuilder cliDet = new StringBuilder();
        cliDet.append(idEncarrec).append(";")
              .append(nomCli).append(";")
              .append(telCli).append(";")
              .append(dataEncarrec).append(";");
              .append(preuTotal).append(";")
    
        // Usar StringBuilder para añadir cada artículo
        articles.forEach(article -> cliDet.append(article.toCSV()));
    
        return cliDet.toString();
    }

}