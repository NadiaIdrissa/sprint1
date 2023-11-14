import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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

        // en cas d'erreur va afficher le numero de la ligne en question
        int numeroLigne = 1;

        while (scanner.hasNext()) {
            String donnee = scanner.nextLine();

            //verifie si la ligne contient les 5 elements
            if(donnee.split(",").length != 5){
                //affiche l'erreur et arret du programme
                System.out.println("Erreur dans le fichier '" + nomFichier + "' à la ligne " + numeroLigne + " : Le nombre de champs est incorrect.");
                System.exit(1);
            }

            donnees.add(donnee);
            numeroLigne++;
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
        List<String> listeArrondissement = Intervention.chargementArrondissementJSON();
        List<String> listeDescription = Intervention.chargementDescriptionJSON();

        for (int i = 1; i < donnees.size(); i++){
            String [] information = separerDonneePourUneIntervention(donnees.get(i));
            String description = information[4]; // l'arrondissement est à l'indice 4

            // verification de l'arrondissement et de la description avant la creation d'une intervention
            if(Intervention.validerDescription(description)) {
                String arrondissement = information[3]; // l'arrondissement est à l'indice 3
                Intervention intervention = new Intervention(information[0], information[1], information[2], arrondissement, description);
                interventions.add(intervention);
            }else {
                System.out.println("Erreur : Arrondissement n'est pas là ");
            }
        }
        return interventions;
    }

    public static class Main {
        public static void main(String[] args) {
            // feedback sprint 1 - args doivent etre en parametre 5/10
            // correction
            if(args.length != 2){
                System.out.println("Usage: java Entree.Main <fichier_entree.csv> <fichier_sortie.csv>");
                return;
            }

            Scanner scanner = new Scanner(System.in);
            String entreeCSV = args[0];
            String sortieCSV = args[1];

            List<String> listArrondissement = Intervention.chargementArrondissementJSON();

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

                ArrayList<String> data = extraireDonnees(entreeCSV);
                ArrayList <Intervention> evenements = convertirEnIntervention(data);

                Sortie.sauvegarderListeDesInterventionsDansSortieCSV(evenements, sortieCSV);

                // Afficher un message de réussite
                // feedback
                System.out.println("Exécution réussie. Les résultats sont enregistrés dans le fichier '" + sortieCSV + "'.");

            }catch (FileNotFoundException e){
                // En cas de fichier introuvable, affichez l'erreur
                System.err.println("Erreur: Fichier introuvable - " + e.getMessage());

            }

        }
    }
}