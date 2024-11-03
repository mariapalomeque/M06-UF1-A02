package App;

import Model.Article;
import Model.Encarrec;

import java.io.*;
import java.util.ArrayList;

public class UtilWriteFitxer {

    public void TextMultiLinea(String nomCli, String telCli, String dataEncarrec, ArrayList<Article> articles, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Client: " + nomCli);
            writer.newLine();
            writer.write("Telèfon: " + telCli);
            writer.newLine();
            writer.write("Data: " + dataEncarrec);
            writer.newLine();
            writer.write("Articles:");
            writer.newLine();

            for (Article article : articles) {
                writer.write(article.toString());
                writer.newLine();
            }

            System.out.println("Fitxer escrit amb èxit.");
        } catch (IOException e) {
            System.err.println("Error escrivint el fitxer: " + e.getMessage());
        }
    }

    public void escriureEncarrrecAleatori(ArrayList<Encarrec> encarrecs, String fileName) {
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            for (Encarrec enc : encarrecs) {
                raf.writeInt(enc.getId());
                raf.writeUTF(enc.getNomCli());
                raf.writeUTF(enc.getTelCli());
                raf.writeUTF(enc.getDataEncarrec());
                raf.writeFloat(enc.getPreuTotal());
                
                for (Article article : enc.getArticles()) {
                    raf.writeUTF(article.getNom());
                    raf.writeFloat(article.getQuantitat());
                    raf.writeUTF(article.getUnitat());
                    raf.writeFloat(article.getPreu());
                }
                raf.writeUTF("/"); 
            }
        } catch (IOException e) {
            System.err.println("Error guardant encàrrecs: " + e.getMessage());
        }
    }


}
