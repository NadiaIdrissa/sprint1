import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String entreeCSV, sortieCSV;
        do {
            System.out.println("fichier d'entrée (avec extension .csv) : ");
            entreeCSV = scanner.nextLine();
        } while (!entreeCSV.endsWith(".csv"));

        do {
            System.out.println("fichier de sortie (avec extension .csv) : ");
            sortieCSV = scanner.nextLine();
        } while (!sortieCSV.endsWith(".csv"));

        scanner.close();

        try{

            // verifier si les 2 entrees ont une extension .csven
            if (!entreeCSV.endsWith(".csv") || !sortieCSV.endsWith(".csv")) {
                System.out.println("Les fichiers doivent avoir l'extension .csv");
                return;
            }

            ArrayList<String> data = Entree.extraireDonnees(entreeCSV);
            ArrayList <Intervention> evenements = Entree.convertirEnIntervention(data);

            Sortie.sauvegarderListeDesInterventionsDansSortieCSV(evenements, sortieCSV);

        }catch (FileNotFoundException e){
            // En cas de fichier introuvable, affichez l'erreur
            e.printStackTrace();

        }catch (IOException e){
            // En cas d'erreur d'entrée/sortie affichez l'erreur lors de la lecture
            e.printStackTrace();
        }


    }
}
