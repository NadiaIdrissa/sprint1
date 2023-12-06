
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    interventions2.add(intervention2);
    interventions2.add(intervention3);
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
      @Test
    void TesterLaSauvegarde() throws IOException {
          ArrayList<Intervention>interventions=new ArrayList<>();
          Intervention intervention1=new Intervention("2023-09-01", "20:41", "Parc Camille", "Ahuntsic-Cartierville", "Vente de drogues");
          Intervention intervention2=new Intervention("2023-08-26","23:11","Parc Brook","Pierrefonds-Roxboro","Vente de drogues");
          Intervention intervention3=new Intervention("2023-09-12","13:11","Parc Carignan","Lachine","Bagarre");
          interventions.add(intervention1);
          interventions.add(intervention2);
          interventions.add(intervention3);
    Sortie.sauvegarderListeDesInterventionsDansSortieCSV(interventions,"SortieFichiertest.csv");
          String resultatVoulu = "Arrondissement,Nombre d'interventions, Nombre de parcs " + "\n" + "Ahuntsic-Cartierville,1,1"
                  +"\n"+ "Lachine,1,1"+"\n"+"Pierrefonds-Roxboro,1,1"+"\n";
          String resultatActuel= Files.readString(Path.of("SortieFichiertest.csv"));
          assertEquals(resultatVoulu,resultatActuel);
      }

    @Test
    void testSauvegarderListeDesInterventionsDansSortieCSV() throws IOException {
        // Créez un mock pour FileWriter afin de ne pas écrire réellement dans un fichier
        FileWriter mockFileWriter = mock(FileWriter.class);

        // Créez une instance de Sortie et définissez le mockFileWriter
        Sortie sortie = new Sortie();
        sortie.setFileWriter(mockFileWriter);

        // Créez une liste d'interventions pour le test
        ArrayList<Intervention> interventions = new ArrayList<>();

        Intervention intervention1 = new Intervention("2023-09-01", "20:41", "Parc Camille", "Ahuntsic-Cartierville", "Vente de drogues");
        Intervention intervention2 = new Intervention("2023-08-26", "23:11", "Parc Brook", "Pierrefonds-Roxboro", "Vente de drogues");
        interventions.add(intervention1);
        interventions.add(intervention2);

        // Configurez le comportement du mockFileWriter
        doNothing().when(mockFileWriter).write(anyString());

        // Appelez la méthode que vous souhaitez tester
        sortie.sauvegarderListeDesInterventionsDansSortieCSV(interventions, "TestFichierSortie.csv");

        // Vérifiez que la méthode write a été appelée avec les bonnes lignes
        verify(mockFileWriter, times(1)).write("Ahuntsic-Cartierville,1,1\n");
        verify(mockFileWriter, times(1)).write("Pierrefonds-Roxboro,1,1\n");


        // Assurez-vous de fermer le mockFileWriter pour éviter des fuites de ressources
        mockFileWriter.close();
    }

}
