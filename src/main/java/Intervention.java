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
    public Intervention( String date,String heure,String parc,String arrondissement,String description){
        this.date=date;
        this.heure=heure;
        this.parc=parc;
        this.arrondissement=arrondissement;
        this.description=description;

    }

}
