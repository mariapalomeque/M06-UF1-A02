package App;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Model.Article;

public class UtilReadFitxer {

    public void FormatCSV(String folder, String fileName) {
        String filInputName = folder + fileName + ".csv";

        try (BufferedReader bwfilInp = new BufferedReader(new FileReader(filInputName))) {
            String linea;
            while ((linea = bwfilInp.readLine()) != null) {
                String[] contingut = linea.split(";");
                int idEncarrec = Integer.parseInt(contingut[0]);
                String nomCli = contingut[1];
                String telCli = contingut[2];
                String dataEncarrec = contingut[3];
                float preuTotal = Float.parseFloat(contingut[4]);

                ArrayList<Article> articles = new ArrayList<>();
                int j = 5;

                while (j < contingut.length) {
                    Article a1 = new Article(
                        contingut[j++],                 
                        Float.parseFloat(contingut[j++]), 
                        contingut[j++],                 
                        0.0f                          
                    );
                    articles.add(a1);
                }
                FormatEncarrec(idEncarrec, nomCli, telCli, dataEncarrec, preuTotal, articles);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FormatBinari(String folder, String fileName) {
        String filInputName = folder + fileName + ".dat";

        try (DataInputStream bwfilInp = new DataInputStream(new FileInputStream(filInputName))) {
            while (true) {
                int idEncarrec = bwfilInp.readInt();
                String nomCli = bwfilInp.readUTF();
                String telCli = bwfilInp.readUTF();
                String dataEncarrec = bwfilInp.readUTF();
                float preuTotal = bwfilInp.readFloat();

                ArrayList<Article> articles = new ArrayList<>(); 
                int numArticles = bwfilInp.readInt();
                for (int i = 0; i < numArticles; i++) {
                    Article a1 = new Article(
                        bwfilInp.readUTF(), 
                        bwfilInp.readFloat(), 
                        bwfilInp.readUTF(), 
                        0.0f 
                    );
                    articles.add(a1);
                }
                FormatEncarrec(idEncarrec, nomCli, telCli, dataEncarrec, preuTotal, articles);
            }
        } catch (EOFException eofe) {
            System.out.println("Fi del fitxer binari");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void FormatEncarrec(int idEncarrec, String nomCli, String telCli, String dataEncarrec, float preuTotal, ArrayList<Article> articles) {
        System.out.println("Dades de l'encàrrec");
        System.out.println("======================");
        System.out.println("Identificador: " + idEncarrec);
        System.out.println("Client: " + nomCli);
        System.out.println("Telèfon: " + telCli);
        System.out.println("Data preparació: " + dataEncarrec);
        System.out.println("Preu total: " + preuTotal + "€");

        System.out.println("Articles:");
        for (Article a : articles) {
            System.out.println(a);
        }
    }
}
