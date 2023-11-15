
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;


public class SortieTest {
@Test
//On verifie si le trie par arrondissement se fait correctement
    void TesterTri(){
    ArrayList<Intervention>interventions=new ArrayList<>();
    ArrayList<Intervention>interventions2=new ArrayList<>();
    Intervention intervention1=new Intervention("2023-09-01", "20:41", "Parc Camille", "Ahuntsic-Cartierville", "Vente de drogues");
    Intervention intervention2=new Intervention("2023-08-26","23:11","Parc Brook","Pierrefonds-Roxboro","Vente de drogues");
    Intervention intervention3=new Intervention("2023-09-12","13:11","Parc Carignan","Lachine","Bagarre");
    interventions.add(intervention1);
    interventions.add(intervention3);
    interventions.add(intervention2);
    interventions2.add(intervention1);
    interventions2.add(intervention3);
    interventions2.add(intervention2);
    interventions2=Sortie.trierArrondissement(interventions2);
    assertEquals(interventions,interventions2);
}
@Test
//ici on teste l'entree d'un fichier vide
    void TestfichierVide () throws FileNotFoundException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        try {
            Entree.convertirEnIntervention(Entree.extraireDonnees("TestFichierVide.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String actualOutput = outputStream.toString().trim();
        System.setOut(System.out);
        // Compare la sortie avec les attentes
        assertEquals("le fichier est vide", actualOutput);
      }
}
