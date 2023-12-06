
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class ProgrammePrincipal {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Fichier introuvable");
            return;
        }
        String entreeCSV = args[0];
        String sortieCSV = args[1];

        try {
            ArrayList<String> data = Entree.extraireDonnees(entreeCSV);
            ArrayList<Intervention> evenements = Entree.convertirEnIntervention(data);
            Sortie.sauvegarderListeDesInterventionsDansSortieCSV(evenements, sortieCSV);
            System.out.println("Exécution réussie. Les résultats sont enregistrés dans le fichier '" + sortieCSV + "'.");

        } catch (FileNotFoundException e) {
            System.err.println("Erreur: Fichier introuvable - " + e.getMessage());
            e.printStackTrace();
        }catch (IOException e) {
            System.err.println("Erreur d'entrée/sortie - " + e.getMessage());
            e.printStackTrace();
        }


    }

    public static void executerProgramme(Entree entree, Sortie sortie, String entreeCSV, String sortieCSV) {
        try {
            ArrayList<String> data = entree.extraireDonnees(entreeCSV);
            ArrayList<Intervention> evenements = entree.convertirEnIntervention(data);
            sortie.sauvegarderListeDesInterventionsDansSortieCSV(evenements, sortieCSV);
            System.out.println("Exécution réussie. Les résultats sont enregistrés dans le fichier '" + sortieCSV + "'.");
        } catch (FileNotFoundException e) {
            System.err.println("Erreur: Fichier introuvable - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erreur d'entrée/sortie - " + e.getMessage());
        }
    }

}
