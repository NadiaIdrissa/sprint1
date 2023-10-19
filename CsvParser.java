import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Cette classe sert a aller extraire les donnees contenues dans une fichiers CSV  afin d'en modeliser les
 * interventions survenues policieres survenues dans les parcs de Montreal
 */
public class CsvParser {
    /**
     * Cette methode va creer une liste qui va extraire les donnees contenues dans le fichier CSV
     *
     * @param nomFichier
     * @return Une liste de toutes les interventions parvenues et enregistrees dans le fichier CSV
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
     * @param donnees
     * @param index
     * return le tableau contenant toutes le information pour une intervention
     *
     */
    public static String [] extraireDonneePourUneIntervention(String donnees, int index) {
        String [] information = donnees.split(",");
        return information;
    }
    /**
     * Cette methode va creer une liste qui va contenir toutes les interventions contenues dans le fichier CSV
     * @param donnees
     * @return la liste des interventions
     */
    public static ArrayList<Intervention> convertirEnIntervention(ArrayList<String> donnees){
        ArrayList<Intervention> interventions = new ArrayList<>();
        for (int i = 1; i < donnees.size(); i++){
            String [] information = extraireDonneePourUneIntervention(donnees.get(i), i);

            Intervention intervention = new Intervention(information[0],information[1],information[2],information[3],information[4]);
            interventions.add(intervention);
        }
        return interventions;
    }
}

