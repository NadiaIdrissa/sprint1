import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Cette classe sert a aller extraire les donnees contenues dans un fichier CSV  afin d'en modeliser les
 * interventions  policieres survenues dans les parcs de Montreal
 */
public class Entree {
    /**
     * Cette methode va creer une liste qui va extraire les donnees contenues dans le fichier CSV par intervention
     *
     * @param nomFichier le nom du fichiercsv existant à extraire
     * @return Une liste de toutes les interventions parvenues et enregistrees dans le fichier CSV
     *
     */
    public static ArrayList<String> extraireDonnees(String nomFichier) throws FileNotFoundException {
        ArrayList<String> donnees = new ArrayList<>();

        File fichierCsv = new File(nomFichier);
        Scanner scanner = new Scanner(fichierCsv);

        while (scanner.hasNext()) {
            String donnee = scanner.nextLine();
            donnees.add(donnee);
        }
        return donnees;
    }
    /**
     * Cette methode va separer les donnees date, heure, parc, arrondissement et description pour une intervention
     * @param donnees les donnees extraites du fichier csv
     * return le tableau contenant toutes les informations pour l'intervention selectionnee(celle donnee en parametre)
     */
    public static String [] separerDonneePourUneIntervention(String donnees) {
        String [] information = donnees.split(",");
        return information;
    }
    /**
     * Cette methode va creer une liste qui va contenir toutes les interventions contenues dans le fichier CSV
     *
     * @param donnees les donnees extraites du fichier csv
     * @return la liste des interventions qui etaient dans le fichier CSV
     */
    public static ArrayList<Intervention> convertirEnIntervention(ArrayList<String> donnees){
        ArrayList<Intervention> interventions = new ArrayList<>();
        for (int i = 1; i < donnees.size(); i++){
            String [] information = separerDonneePourUneIntervention(donnees.get(i));
            Intervention intervention = new Intervention(information[0],information[1],information[2],information[3],information[4]);
            interventions.add( intervention);
        }
        return interventions;
    }
}