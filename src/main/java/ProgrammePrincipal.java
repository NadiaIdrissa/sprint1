
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class ProgrammePrincipal {
public static boolean eng=false;
    public static void main(String[] args) {
        String engOpt="--english";

        if (args.length==3 && args[0].equals( engOpt)) {
            eng=true;
            String entreeCSV = args[1];
            String sortieCSV = args[2];
            Locale.setDefault(Locale.ENGLISH);
            if (args.length != 3) {
                System.out.println("\n file not found");
                return;
            }


            try {
                ArrayList<String> data = Entree.extraireDonnees(entreeCSV);
                ArrayList<Intervention> evenements = Entree.convertirEnIntervention(data);
                Sortie.sauvegarderListeDesInterventionsDansSortieCSV(evenements, sortieCSV);
                System.out.println("\n Successful execution. The results are saved to the file  " + sortieCSV + ".");

            } catch (FileNotFoundException e) {
                System.err.println( "\n Error: File not found - " + e.getMessage() +" \n ~ \n the first parameter should be  --english the second " +
                        " the path/to/your/inputfile and the 3rd the path/to/your/outputfile \n ~" );
                e.printStackTrace();
            }catch (IOException e) {
                System.err.println("\n Input/output error - " + e.getMessage());
                e.printStackTrace();
            }
        }
       else if (args.length == 2 ){


        String entreeCSV = args[0];
        String sortieCSV = args[1];

        try {
            ArrayList<String> data = Entree.extraireDonnees(entreeCSV);
            ArrayList<Intervention> evenements = Entree.convertirEnIntervention(data);
            Sortie.sauvegarderListeDesInterventionsDansSortieCSV(evenements, sortieCSV);
            System.out.println(" \n Exécution réussie. Les résultats sont enregistrés dans le fichier '" + sortieCSV + ".");

        } catch (FileNotFoundException e) {
            System.err.println(" \n Erreur: Fichier introuvable - " + e.getMessage());
            e.printStackTrace();
        }catch (IOException e) {
            System.err.println("\n Erreur d'entrée/sortie - " + e.getMessage());
            e.printStackTrace();
        }
    }
  else{
            System.out.println("\n Fichier introuvable");
            return;
        }

}}
