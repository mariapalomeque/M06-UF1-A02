package App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import Model.Article;
import Model.Encarrec;

public class Aplicació {

    private static ArrayList<Encarrec> encarrecs = new ArrayList<>();
    private static int nextId = 1; 

    public static void main(String[] args) {
        System.out.println("GESTIO D'ENCARRECS");
        System.out.println("======================");
        
        MainMenu();
    }

    public static void MainMenu() {
        boolean seguir = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (seguir) {
            System.out.println("Opcions disponibles");
            System.out.println("");
            System.out.println("1. Afegir nous encàrrecs");
            System.out.println("2. Modificar encàrrec");
            System.out.println("3. Mostrar per pantalla els encàrrecs");
            System.out.println("4. Guardar encàrrec en fitxer");
            System.out.println("5. Sortir");
            System.out.println("");
            System.out.print("Quina opció tries?: ");

            try {
                String opcio = reader.readLine();

                switch (opcio) {
                    case "1":
                        AfegirDadesEncarrec(reader);
                        break;

                    case "2":
                        ModificarEncarrec(reader);
                        break;

                    case "3":
                        MostrarEncarrec(reader);
                        break;

                    case "4":
                        GuardarEncarrecFitxer(reader);
                        break;

                    case "5":
                        GuardarEncarrrecAleatori();
                        seguir = false; 
                        System.out.println("Que tinguis un bon dia!");
                        break;

                    default:
                        System.out.println("Opció no vàlida");
                }

                if (seguir) {
                    System.out.println("Vols fer cap altra acció? Indicar S en cas afirmatiu");
                    String continuar = reader.readLine();
                    if (!continuar.matches("[Ss]")) {
                        seguir = false; 
                        System.out.println("Que tinguis un bon dia!");
                    }
                }

            } catch (IOException e) {
                System.err.println("Error de entrada/salida: " + e.getMessage());
            }
        }
    }

    public static void AfegirDadesEncarrec(BufferedReader reader) throws IOException {
        System.out.println("Introdueix les dades de l'encarrec: ");
        System.out.print("Nom del client: ");
        String nomCli = reader.readLine();
        System.out.print("Telèfon del client: ");
        String telCli = reader.readLine();
        System.out.print("Data de l'encarrec: ");
        String dataEncarrec = reader.readLine();

        ArrayList<Article> articles = new ArrayList<>();
        boolean afegirArticle = true;

        while (afegirArticle) {
            System.out.println("Introdueix les dades de l'article: ");
            System.out.print("Nom de l'article: ");
            String nomArticle = reader.readLine();
            System.out.print("Quantitat: ");
            float quantitat = Float.parseFloat(reader.readLine());
            System.out.print("Unitat: ");
            String unitat = reader.readLine();
            System.out.print("Preu: ");
            float preu = Float.parseFloat(reader.readLine());

            articles.add(new Article(nomArticle, quantitat, unitat, preu));

            System.out.print("Vols afegir un altre article? (S/N): ");
            String resposta = reader.readLine();
            if (!resposta.equalsIgnoreCase("S")) {
                afegirArticle = false;
            }
        }

        Encarrec enc = new Encarrec(nextId++, nomCli, telCli, dataEncarrec, articles);
        encarrecs.add(enc);
        System.out.println("Encarrec afegit amb èxit.");
    }

    public static void ModificarEncarrec(BufferedReader reader) throws IOException {
       
    }

    public static void MostrarEncarrec(BufferedReader reader) throws IOException {
        System.out.println("Llista d'encarrec:");
        for (Encarrec enc : encarrecs) {
            System.out.println(enc);
        }
    }

    public static void GuardarEncarrecFitxer(BufferedReader reader) throws IOException {
        System.out.print("Introdueix el nom del fitxer per guardar: ");
        String fileName = reader.readLine();
        UtilWriteFitxer util = new UtilWriteFitxer();
        for (Encarrec enc : encarrecs) {
            util.TextMultiLinea(enc.getNomCli(), enc.getTelCli(), enc.getDataEncarrec(), enc.getArticles(), fileName);
        }
        System.out.println("Encarrec(s) guardat(s) en fitxer.");
    }

    public static void GuardarEncarrrecAleatori() {
        UtilWriteFitxer util = new UtilWriteFitxer();
        util.escriureEncarrrecAleatori(encarrecs, "encarrecs.dat");
        System.out.println("Encàrrecs guardats aleatòriament en el fitxer.");
    }
}
