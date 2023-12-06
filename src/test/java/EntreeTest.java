
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class EntreeTest {
    @Test
    void TestListerDonnees() {
        ArrayList<String> donnees;
        ArrayList<String> donnees2 = new ArrayList<>();
        try {
            donnees = Entree.extraireDonnees("FichierTest.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        donnees2.add("2023-09-01,20:41,Parc Camille,Ahuntsic-Cartierville,Vente de drogues");
        donnees2.add("2023-09-03,21:13,Parc Camille,Ahuntsic-Cartierville,Vente de drogues");
        donnees2.add("2023-08-26,23:11,Parc Brook,Pierrefonds-Roxboro,Vente de drogues");

        assertEquals(donnees, donnees2);
    }

    @Test
    void TestseparerDonneesPourUneInformation() {

        String s = "2023-09-01,20:41,Parc Camille,Ahuntsic-Cartierville,Vente de drogues";
        String[] information = Entree.separerDonneePourUneIntervention(s);
        String[] information2 = {"2023-09-01", "20:41", "Parc Camille", "Ahuntsic-Cartierville", "Vente de drogues"};
        assertArrayEquals(information2, information);
    }


    @Test
    void TesterMauvaiseIntervention() throws FileNotFoundException {


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        try {
            Entree.convertirEnIntervention(Entree.extraireDonnees("MauvaiseIntervention.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String actualOutput = outputStream.toString().trim();
        System.setOut(System.out);
        // Compare la sortie avec les attentes
        assertEquals("Erreur : Intervention incorrecte", actualOutput);

    }


    @Test
    void TesterMauvaisArrondissement() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        try {
            Entree.convertirEnIntervention(Entree.extraireDonnees("MauvaisArrondissement.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String actualOutput = outputStream.toString().trim();
        System.setOut(System.out);
        // Compare la sortie avec les attentes
        assertEquals("Erreur : Arrondissement incorrect", actualOutput);

    }
    @Test
    void testExtraireDonneesAvecMock() throws IOException {
        // Créez un mock pour BufferedReader
        BufferedReader mockBufferedReader = mock(BufferedReader.class);

        // Créez une instance de la classe Entree en lui injectant le mock
        Entree entree = new Entree(mockBufferedReader);

        // Configurez le comportement de mockBufferedReader
        when(mockBufferedReader.readLine())
                .thenReturn("2023-09-01,20:41,Parc Camille,Ahuntsic-Cartierville,Vente de drogues")
                .thenReturn(null);  // Returning null to indicate end of file

        // Appelez la méthode que vous souhaitez tester
        List<String> result = entree.extraireDonnees("Entree.csv");

        // Vérifiez le comportement attendu
        verify(mockBufferedReader, atLeastOnce()).readLine();

        // Ajoutez des assertions pour vérifier le résultat
        assertEquals(1, result.size()); // Assurez-vous que la liste résultante a la bonne taille
        assertEquals("2023-09-01,20:41,Parc Camille,Ahuntsic-Cartierville,Vente de drogues", result.get(0));
    }


}






