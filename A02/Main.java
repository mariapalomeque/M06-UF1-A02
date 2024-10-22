import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        boolean sortir = false;
        int opcio;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!sortir) {
                System.out.println("-- MENU --");
                System.out.println("1. Generar un nou encàrrec.");
                System.out.println("2. Mostrar un encàrrec.");
                System.out.println("3. Sortir.");

                try {
                    System.out.println("Inserta un numero que correspongui al menu: ");
                    opcio = Integer.parseInt(reader.readLine());

                    switch (opcio) {
                        case 1:
                            generarNouEncarrec(reader);
                            break;
                        case 2:
                            mostrarEncarrec(reader);
                            break;
                        case 3:
                            sortir = true;
                            break;
                        default:
                            System.out.println("Les opcions són entre 1 i 3.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Escull una opció vàlida del menú.");
                }
            }
            System.out.println("Sistema finalitzat...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generarNouEncarrec(BufferedReader reader) {
        try {
            System.out.println("Per generar un nou encàrrec necessitarem les següents dades:");

            System.out.println("Nom del client: ");
            String nom = reader.readLine();

            String telefon = "";
            boolean telefonValid = false;

            while (!telefonValid) {
                System.out.println("Telèfon del client: ");
                telefon = reader.readLine();

                if (telefon.matches("\\d{9}")) {
                    telefonValid = true;
                } else {
                    System.out.println("El número de telèfon no és vàlid. Ha de tenir exactament 9 dígits.");
                }
            }

            String data = "";
            String regex = "^\\d{2}/\\d{2}/\\d{4}$";
            boolean dataValida = false;
            while (!dataValida) {
                System.out.println("Introdueix la data de la comanda (DD/MM/AAAA): ");
                data = reader.readLine();
                if (Pattern.matches(regex, data)) {
                    dataValida = true;
                } else {
                    System.out.println("Format de data incorrecte. Si us plau, introdueix la data en format DD/MM/AAAA.");
                }
            }

            String nomFitxer = nom + "_" + data.replace("/", "-");

            boolean afegirArticle = true;
            ArrayList<Article> articles = new ArrayList<>();

            while (afegirArticle) {
                System.out.println("Introdueix el nom de l'article: ");
                String nomArticle = reader.readLine();

                double quantitat = 0;
                boolean quantitatValida = false;

                while (!quantitatValida) {
                    System.out.println("Introdueix la quantitat: ");
                    try {
                        quantitat = Double.parseDouble(reader.readLine());
                        if (quantitat > 0) {
                            quantitatValida = true;
                        } else {
                            System.out.println("La quantitat no pot ser un número negatiu.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Valor no vàlid. Introdueix un número (decimals amb '.').");
                    }
                }

                System.out.println("Introdueix la unitat de mesura del producte: ");
                String unitat = reader.readLine();

                articles.add(new Article(quantitat, unitat, nomArticle));

                String resposta;
                boolean respostaValida = false;

                while (!respostaValida) {
                    System.out.print("Vols afegir més articles?");
                    resposta = reader.readLine();

                    if (resposta.equalsIgnoreCase("si")) {
                        afegirArticle = true;
                        respostaValida = true;
                    } else if (resposta.equalsIgnoreCase("no")) {
                        afegirArticle = false;
                        respostaValida = true;
                    } else {
                        System.out.println("No és una resposta correcta, escriu 'si' o 'no'.");
                    }
                }
            }

            // Crear fitxer de text
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFitxer + ".txt"))) {
                writer.write("Nom del client: " + nom + "\n");
                writer.write("Telèfon del client: " + telefon + "\n");
                writer.write("Data de l'encàrrec: " + data + "\n");
                writer.write("Quantitat\tUnitats\tArticle\n");
                writer.write("=============== ======== ===========\n");

                for (Article article : articles) {
                    writer.write(String.format("%-15s %-10s %-15s\n", article.quantitatA, article.unitatA, article.nomA));
                }

                System.out.println("Encàrrec creat i desat en el fitxer '" + nomFitxer + ".txt' amb èxit!");

            } catch (IOException e) {
                System.out.println("Hi ha hagut un problema a l'hora de crear el fitxer.");
                e.printStackTrace();
            }

            // Crear fitxer CSV
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFitxer + ".csv"))) {
                writer.write(nom + ";" + telefon + ";" + data + ";");

                for (Article article : articles) {
                    writer.write(article.nomA + ";" + article.quantitatA + ";" + article.unitatA + ";");
                }

                System.out.println("Encàrrec creat i desat en el fitxer '" + nomFitxer + ".csv' amb èxit!");

            } catch (IOException e) {
                System.out.println("Hi ha hagut un problema a l'hora de crear el fitxer.");
                e.printStackTrace();
            }

            // Crear fitxer binari
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(nomFitxer + ".dat"))) {
                dos.writeUTF(nom);
                dos.writeUTF(telefon);
                dos.writeUTF(data);

                for (Article article : articles) {
                    dos.writeUTF(article.nomA);
                    dos.writeDouble(article.quantitatA);
                    dos.writeUTF(article.unitatA);
                }

                System.out.println("Encàrrec creat i desat en el fitxer binari '" + nomFitxer + ".dat' amb èxit!");

            } catch (IOException e) {
                System.out.println("Hi ha hagut un problema a l'hora de crear el fitxer binari.");
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("Error de lectura.");
            e.printStackTrace();
        }
    }

    // Menu mostrar fitxers
    public static void mostrarEncarrec(BufferedReader reader) {
        try {
            System.out.println("Seleccioneu el tipus de fitxer a obrir:");
            System.out.println("1. Fitxer CSV");
            System.out.println("2. Fitxer Binari");

            int opcioFitxer = Integer.parseInt(reader.readLine());
            System.out.print("Introdueix la ruta del fitxer: ");
            String ruta = reader.readLine();

            switch (opcioFitxer) {
                case 1:
                    mostrarCSV(ruta);
                    break;
                case 2:
                    mostrarBinari(ruta);
                    break;
                default:
                    System.out.println("Opció no vàlida.");
            }
        } catch (IOException e) {
            System.out.println("Error en llegir la resposta.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Opció no vàlida.");
        }
    }

    //MOSTRAR CSV
    public static void mostrarCSV(String ruta) {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(ruta))) {
            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] parts = line.split(";");
                System.out.println("Nom del client: " + parts[0]);
                System.out.println("Telèfon del client: " + parts[1]);
                System.out.println("Data de l'encàrrec: " + parts[2]);
                System.out.println("Quantitat\tUnitats\tArticle");
                System.out.println("=============== ======== ===========");

                for (int i = 3; i < parts.length; i += 3) {
                    System.out.printf("%-15s %-10s %-15s\n", parts[i + 1], parts[i], parts[i - 1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Hi ha hagut un problema a l'hora d'obrir el fitxer CSV.");
            e.printStackTrace();
        }
    }

    //MOSTRAR BINARI
    public static void mostrarBinari(String ruta) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(ruta))) {
            String nom = dis.readUTF();
            String telefon = dis.readUTF();
            String data = dis.readUTF();

            System.out.println("Nom del client: " + nom);
            System.out.println("Telèfon del client: " + telefon);
            System.out.println("Data de l'encàrrec: " + data);
            System.out.println("Quantitat\tUnitats\tArticle");
            System.out.println("=============== ======== ===========");

            while (dis.available() > 0) {
                String nomArticle = dis.readUTF();
                double quantitat = dis.readDouble();
                String unitat = dis.readUTF();

                System.out.printf("%-15s %-10s %-15s\n", quantitat, unitat, nomArticle);
            }
        } catch (IOException e) {
            System.out.println("Hi ha hagut un problema a l'hora d'obrir el fitxer binari.");
            e.printStackTrace();
        }
    }
}

class Article {
    double quantitatA;
    String unitatA;
    String nomA;

    public Article(double quantitatA, String unitatA, String nomA) {
        this.quantitatA = quantitatA;
        this.unitatA = unitatA;
        this.nomA = nomA;
    }
}
