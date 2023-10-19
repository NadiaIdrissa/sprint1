public class Intervention {
    private String date;
    private String heure;
    private String description;
    private String parc;
    private String arrondissement;
    private int nbrIntervention;


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


    public int getNbrIntervention() {
        return nbrIntervention;
    }
    public Intervention( String date,String heure,String parc,String arrondissement,String description,int nbrIntervention){
        this.date=date;
        this.heure=heure;
        this.parc=parc;
        this.arrondissement=arrondissement;
        this.description=description;
        this.nbrIntervention=nbrIntervention;
    }

}
