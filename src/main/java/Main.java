import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Entree.Main <fichier_entree.csv> <fichier_sortie.csv>");
        } else {
            Scanner scanner = new Scanner(System.in);
            String entreeCSV = args[0];
            String sortieCSV = args[1];
            List<String> listArrondissement = Intervention.chargementArrondissementJSON();
            System.out.println("fichier d'entrée (avec extension .csv) : ");
            entreeCSV = scanner.nextLine();
            if (!entreeCSV.endsWith(".csv")) {
                System.out.println("Fichier introuvable - Fin du programme");
            } else {
                do {
                    System.out.println("Fichier de sortie (avec extension .csv) : ");
                    sortieCSV = scanner.nextLine();
                } while(!sortieCSV.endsWith(".csv"));

                scanner.close();

                try {
                    ArrayList<String> data = Entree.extraireDonnees(entreeCSV);
                    ArrayList<Intervention> evenements = Entree.convertirEnIntervention(data);
                    Sortie.sauvegarderListeDesInterventionsDansSortieCSV(evenements, sortieCSV);
                    System.out.println("Exécution réussie. Les résultats sont enregistrés dans le fichier '" + sortieCSV + "'.");
                } catch (FileNotFoundException var7) {
                    System.err.println("Erreur: Fichier introuvable - " + var7.getMessage());
                }

            }
        }
    }
}


