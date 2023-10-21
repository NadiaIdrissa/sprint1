import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Sortie {

    public static void main(String[] args) {

        // Définir le nom du fichier csv à lire
        String interventionCsv = "Intervention.csv";

        try{
            ArrayList<String> data = CsvParser.extraireDonnees(interventionCsv);
            ArrayList <Intervention> evenements = CsvParser.convertirEnIntervention(data);

            calculerNbIntervention(evenements, interventionCsv);

        }catch (FileNotFoundException e){
            // En cas de fichier introuvable, affichez l'erreur
            e.printStackTrace();

        }catch (IOException e){
            // En cas d'erreur d'entrée/sortie affichez l'erreur lors de la lecture
            e.printStackTrace();
        }


    }

    private static void calculerNbIntervention( ArrayList<Intervention> interventions,String interventionCsv) {
        int nbIntervention = 0;

        for (Intervention event : interventions ) {

            nbIntervention++;
            System.out.println("Arrondissement,Nombre d'interventions \n"+
                    event.getArrondissement() + ", " + nbIntervention);
        }

    }
}

