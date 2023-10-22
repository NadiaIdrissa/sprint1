import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Sortie {

    public static void sauvegarderListeDesInterventionsDansSortieCSV(ArrayList<Intervention> interventions, String sortieCSV) {
        ArrayList<String> arrondissementsTraites = new ArrayList<>();
        interventions = trierArrondissement(interventions);
        try (FileWriter writer = new FileWriter(sortieCSV)) {
            writer.write("Arrondissement,Nombre d'interventions\n");
            for (Intervention event : interventions) {
                String arrondissement = event.getArrondissement();
                // Vérifier si l'arrondissement a déjà été traité

                if (!arrondissementsTraites.contains(arrondissement)) {
                    int nbIntervention = compterArrondissement(interventions, arrondissement);
                    writer.write(arrondissement + "," + nbIntervention + "\n");
                }
                // Ajouter les arrondissements déjà traités
                arrondissementsTraites.add(arrondissement);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Intervention> trierArrondissement(ArrayList<Intervention>interventions) {
        Collections.sort(interventions, new Comparator<Intervention>() {
            @Override
            public int compare(Intervention o1, Intervention o2) {
                return o1.getArrondissement().compareTo(o2.getArrondissement());
            }

        });
        return interventions;
    }





    /**
     * Cette methode va compter le nombre d'arrondissement dans la liste des interventions
     *
     *param ArrayList<Intervention>, String arrondissement
     * @return le nombre d'arrondissement
     */
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

