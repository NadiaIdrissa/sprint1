import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class Sortie {


    /**
     * Genere un fichier contenant la liste des arrondissements  triés en ordre alphabétique,
     * avec le nombre d'interventions policières qui y ont eu lieu
     *
     * @param interventions la liste de toutes les interventions
     * @param sortieCSV le nom du fichier de sortie
     */
    public static void sauvegarderListeDesInterventionsDansSortieCSV(ArrayList<Intervention> interventions, String sortieCSV) {
        ArrayList<String> arrondissementsTraites = new ArrayList<>();
        interventions = trierArrondissement(interventions);

        try (FileWriter writer = new FileWriter(sortieCSV)) {
            writer.write("Arrondissement,Nombre d'interventions, Nombre de parcs \n");

            for (Intervention event : interventions) {
                String arrondissement = event.getArrondissement();

                // Vérifier si l'arrondissement a déjà été traité
                if (!arrondissementsTraites.contains(arrondissement)) {
                    int nbIntervention = compterArrondissement(interventions, arrondissement);
                    int nbParc = compterParc(interventions, arrondissement);

                    writer.write(arrondissement + "," + nbIntervention + "," + nbParc + "\n");
                }
                // Ajouter les arrondissements déjà traités
                arrondissementsTraites.add(arrondissement);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Trie les arrondissements par ordre alphabetique
     *
     * @param interventions la liste de toutes les  interventions
     * @return interventions la liste triee des interventions en fonctions de
     * l'arrondissemnt
     */
    protected static ArrayList<Intervention> trierArrondissement(ArrayList<Intervention> interventions) {
        Collections.sort(interventions, new Comparator<Intervention>() {
            @Override
            public int compare(Intervention o1, Intervention o2) {
                return o1.getArrondissement().compareTo(o2.getArrondissement());
            }

        });
        return interventions;
    }


    /**
     *  Cette methode va compter le nombre d'interventions dans chaque arrondissement
     *
     * @param interventions la liste des interventions
     * @param arrondissement
     *
     * @return nbrArrondissemnt le nombre d'interventions par arrondissemnt
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

    /**
     * Cette methode compte le nombre de parcs contenus  dans chaque  arrondissement
     * @param interventions
     * @param arrondissement
     * @return le nombre de parcs dans chacun des arrondissement
     */

    private static int compterParc (ArrayList<Intervention> interventions, String arrondissement){
        List<String> parcs = new ArrayList<>();

        for (Intervention intervention : interventions){
            if(intervention.getArrondissement().equals(arrondissement)) {
                String nomDuParc = intervention.getParc();

                //verifier si le parc n'est pas dans la liste, l'ajouter
                if (!parcs.contains(nomDuParc)) {
                    parcs.add(nomDuParc);
                }
            }

        }
        return parcs.size();
    }


    public void setFileWriter(FileWriter mockFileWriter) {
    }
}

