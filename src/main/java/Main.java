import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String entreeCSV, sortieCSV;

        System.out.println("fichier d'entrée (avec extension .csv) : ");
        entreeCSV = scanner.nextLine();

        // verifier si le fichier a une extension .csv sinon fin du programme
        if (!entreeCSV.endsWith(".csv")) {
            System.out.println("Fichier introuvable - Fin du programme");
            return;
        }

        do {
            System.out.println("Fichier de sortie (avec extension .csv) : ");
            sortieCSV = scanner.nextLine();
        } while (!sortieCSV.endsWith(".csv"));

        scanner.close();

        try{

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
