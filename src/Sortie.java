import net.sf.json.JSONArray;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Sortie {

    public static void main(String[] args) {

        // Définir le nom du fichier csv à lire
        String interventionCsv = "InterventionData.csv";

        try{
            ArrayList<String> data = entree.extraireDonnees(interventionCsv);
            ArrayList <Intervention> evenements = entree.convertirEnIntervention(data);

            calculerNbIntervention(evenements);

        }catch (FileNotFoundException e){
            // En cas de fichier introuvable, affichez l'erreur
            e.printStackTrace();

        }catch (IOException e){
            // En cas d'erreur d'entrée/sortie affichez l'erreur lors de la lecture
            e.printStackTrace();
        }


    }

    private static void calculerNbIntervention(ArrayList<Intervention> interventions) {
        int nbIntervention = 0;
        ArrayList<String> arrondissementTraites = new ArrayList<>();

        System.out.println("\nArrondissement,Nombre d'interventions \n");

        for (Intervention event : interventions) {
            String arrondissement = event.getArrondissement();

            // verifier si l'arrondissement a déjà été traité
            if (!arrondissementTraites.contains(arrondissement)){
                nbIntervention = compterArrondissement(interventions, arrondissement);
                    System.out.println(arrondissement + ", " + nbIntervention);
                }

                //ajouter les arrondissements déjà traités
                arrondissementTraites.add(arrondissement);
            }


    }
    private static int compterArrondissement(ArrayList<Intervention> interventions, String arrondissement) {
        int nbArrondissement = 0;
        for (Intervention evenement : interventions) {
            if (evenement.getArrondissement().equals(arrondissement)) {
                nbArrondissement++;
            }
        }
        return nbArrondissement;
    }


}

