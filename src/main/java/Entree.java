import java.io.*;
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

        if(!scanner.hasNext()){
            if(ProgrammePrincipal.eng){
                System.err.println("the file is empty");
            }else{
                System.err.println("le fichier est vide");
            }
            return donnees; // Retourne une liste vide si le fichier est vide

        }


        // en cas d'erreur va afficher le numero de la ligne en question
        int numeroLigne = 1;

        //  lire la première ligne du fichier
        donnees.add(scanner.nextLine());

        while (scanner.hasNext()) {
            String donnee = scanner.nextLine();

            //verifie si la ligne contient les 5 elements
            if(donnee.split(",").length != 5){
                //affiche l'erreur et arret du programme
                if(ProgrammePrincipal.eng){
                    System.err.println("Error in the file " + nomFichier + " at the line " + numeroLigne + " : The number of fields is incorrect.");
                }else{
                    System.err.println("Erreur dans le fichier " + nomFichier + " à la ligne " + numeroLigne + " : Le nombre de champs est incorrect.");
                }
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
        int ligne=1;
        for (int i = 1; i < donnees.size(); i++){

            String [] information = separerDonneePourUneIntervention(donnees.get(i));
            String description = information[4]; // la description est à l'indice 4
            String arrondissement = information[3]; // l'arrondissement est à l'indice 3
            // verification de l'arrondissement et de la description avant la creation d'une intervention
            if(!Intervention.validerDescription(description)) {
                if(ProgrammePrincipal.eng){

                    System.err.println("Error: Incorrect intervention at the ligne "+ligne);
                }
                else{System.err.println("Erreur : Intervention incorrecte a la ligne "+ ligne);}

            }else if(!Intervention.validerArrondissement(arrondissement)) {
                if(ProgrammePrincipal.eng){
                    System.err.println("Error: Incorrect district at the ligne "+ligne);
                }
               else{ System.err.println("Erreur : Arrondissement incorrect a la ligne " + ligne);}

            }
            else  {

                Intervention intervention = new Intervention(information[0], information[1], information[2], arrondissement, description);
                interventions.add(intervention);

            }ligne++;

        }
        return interventions;
    }

}