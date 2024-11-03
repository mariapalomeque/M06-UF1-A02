package App;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Model.Article;

public class UtilReadFitxer {

        public void FormatCSV(String folder, String fileName) {
        String filInputName = folder + fileName + ".csv";

        try (BufferedReader bwfilInp = new BufferedReader(new FileReader(filInputName))) {

            String linea = "";
            String[] contingut;

            while ((linea = bwfilInp.readLine()) != null){

                contingut = linea.split(";", 0);
                int idEncarrec  = contingut[0];
                String nomCli  = contingut[1];
                String telCli  = contingut[2];
                String dataEncarrec  = contingut[3];
                float preuTotal  = contingut[4];

                ArrayList<Article> articles = new ArrayList<>();

                int j = 3;

                while (j < contingut.length) {
                    Article a1 = new Article();
                    a1.setNomArticle(contingut[j]);
                    j++;
                    a1.setnombreUnitats(Float.parseFloat(contingut[j]));
                    j++;
                    a1.settipusUnitat(contingut[j]);
                    articles.add(a1);
                    j++;
                }

                FormatEncarrec(idEncarrec, nomCli, telCli, dataEncarrec, preuTotal, articles);
            } 

        } catch (FileNotFoundException fn) {
                System.out.println ("No es troba el fitxer");         
        } catch (IOException io) {
        System.out.println ("Error d'E/S"); 
        }
    }

    public void FormatBinari(String folder, String fileName) {

        String filInputName = folder + fileName + ".dat";
        ArrayList<Article> articles = new ArrayList<>();

        try (DataInputStream diStr1 = new DataInputStream(new FileInputStream(filInputName))) {


            int idEncarrec  = diStr1.readUTF();
            String nomCli  = diStr1.readUTF();
            String telCli  = diStr1.readUTF();
            String dataEncarrec  = diStr1.readUTF();
            float preuTotal = diStr1.readFloat();

            try{

                while (diStr1.available()>0) {
                    String nomArticle = diStr1.readUTF();
                    float quantitat = diStr1.readFloat();
                    String unitat = diStr1.readUTF();
                     float preu  = diStr1.readFloat();;
                    Article art = new Article(nomArticle,quantitat,unitat,preu);
                    articles.add(art);   
   

                }

                FormatEncarrec(idEncarrec, nomCli, telCli, dataEncarrec, preuTotal, articles);

            } catch (EOFException e) {
                System.out.println("Final de fitxer");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void FormatEncarrec(String nomCli, String telCli, String dataEncarrec, ArrayList<Article> articles) {
        System.out.printf(String.format("%n"));
        System.out.println("DETALL DE L'ENCARREC");
        System.out.println("====================================================");
        System.out.printf(String.format("%n"));
        System.out.println("Id encarrec: " + idEncarrec);
        System.out.println("Nom del client: " + nomCli);
        System.out.println("Telefon del client: " + telCli);
        System.out.println("Data de l'encarrec: " + dataEncarrec);
        System.out.println("Preu de l'encarrec: " + preuTotal);
        System.out.printf(String.format("%n"));
        System.out.printf(String.format("%-15s %-10s %-15s%n", "Quantitat","Unitats","Article"));
        System.out.printf(String.valueOf("=").repeat(15)+" " +String.valueOf("=").repeat(10)+" "+String.valueOf("=").repeat(15));
        for (Article article:articles) {
            System.out.printf(String.format("%n"));
            System.out.printf(String.format("%-15s %-10s %-15s%n",article.getnombreUnitats(),article.gettipusUnitat(),article.getNomArticle()));
        }
    }

}