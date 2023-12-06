import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe intervention modelise les interventions policieres. Chaque intervention policiere peut etre
 * identifiee par une date,une heure,une description,le parc et l'arrondissement ou s est passe celle-ci.
 */
public class Intervention {
    private String date;
    private String heure;
    private String description;
    private String parc;
    private String arrondissement;



    public String getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public String getParc() {
        return parc;
    }

    public String getArrondissement() {
        return arrondissement;
    }

    public String getDescription() {
        return description;
    }
    
    public Intervention(){

    }
    public Intervention( String date,String heure,String parc,String arrondissement,String description){
        this.date=date;
        this.heure=heure;
        this.parc=parc;
        this.arrondissement=arrondissement;
        this.description=description;

    }

    public static boolean validerArrondissement(String arrondissement){
        List<String> listeArrondissement = chargementArrondissementJSON();
        return listeArrondissement.contains(arrondissement);
    }

    /**
     * Cette methode retourne
     * @param description
     * @return vrai si la liste des types d'interventions contient la l'intevertion(la description)
     */
    public static boolean validerDescription(String description){
        List<String> listeDescription = chargementDescriptionJSON();

        // si la liste description n'est pas charger
        if(listeDescription == null){
            chargementDescriptionJSON();
        }
        return listeDescription.contains(description);
    }
    /**
     * Cette methode lit la liste des interventions dans le fichier JSON inteverntions.json
     * et en extraire les donnees contenues
     *
     * @return la liste des differents types d'interventions
     */
    public static List<String> chargementDescriptionJSON(){

        List<String> listeDescription = new ArrayList<>();
        try(FileReader lectureFichierJSON = new FileReader("json/interventions.json")) {
            JsonArray descriptionJSON = JsonParser.parseReader(lectureFichierJSON).getAsJsonObject().getAsJsonArray("intervention_policiere");

            //ajouter arrondissements dans la liste en string
            for (int i = 0; i < descriptionJSON.size(); i++) {
                listeDescription.add(descriptionJSON.get(i).getAsString());
            }
        }catch (IOException e){
            e.printStackTrace(); // gestion et trace des exceptions

        }
        return listeDescription;
    }

    /**
     * Cette methode lit la liste des arrondissements dans le fichiers JSON et en extraire les donnees
     * contenues
     *
     * @return la liste des arrondissements
     */
    public static List<String> chargementArrondissementJSON(){
        List<String> listeArrondissement = new ArrayList<>();

        try(FileReader lectureFichierJSON = new FileReader("json/arrondissements.json")) {
            JsonArray arrondissementsJSON = JsonParser.parseReader(lectureFichierJSON).getAsJsonObject().getAsJsonArray("arrondissements");

            //ajouter arrondissements dans la liste en string
            for (int i = 0; i < arrondissementsJSON.size(); i++) {
                listeArrondissement.add(arrondissementsJSON.get(i).getAsString());
            }
        }catch (IOException e){
            e.printStackTrace(); // gestion et trace des exceptions

        }
        return listeArrondissement;
    }

}
