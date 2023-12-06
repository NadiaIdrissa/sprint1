
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void testExtraireDonneesFichierVide() {
        try {
            ArrayList<String> result = Entree.extraireDonnees("TestFichierVide.csv");
            assertTrue(result.isEmpty());
        } catch (FileNotFoundException e) {
            fail("Erreur inattendue : " + e.getMessage());
        }
    }



    /*@Test
    void testExtraireDonneesFormatIncorrect() throws IOException {
        // Créer un fichier temporaire avec une ligne incorrecte
        File fichierIncorrect = new File("FichierFormatIncorrect.csv");
        try (PrintWriter writer = new PrintWriter(fichierIncorrect)) {
            // Ajouter une ligne incorrecte (par exemple, moins de champs que prévu)
            writer.println("2023-09-01,20:41,Parc Camille,Ahuntsic-Cartierville");
        }catch (FileNotFoundException e) {
            fail("Erreur inattendue : " + e.getMessage());
        }

        // Effectuer le test
        // Effectuer le test
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            Entree.extraireDonnees(fichierIncorrect.getAbsolutePath());
        });
        // Vérifier le message d'erreur
        assertEquals("Le format du fichier '" + fichierIncorrect.getAbsolutePath() + "' à la ligne 1 est incorrect.", exception.getMessage());
    }*/


    @Test
    void testExtraireDonneesCasStandard() throws IOException {
        // Créez un fichier temporaire avec des données correctes
        File entreeFile = new File("Entree.csv");
        try (FileWriter writer = new FileWriter(entreeFile)) {
            // Ajoutez des lignes de données correctes
            writer.write("2023-09-01,20:41,Parc Camille,Ahuntsic-Cartierville,Vente de drogues\n");
            writer.write("2023-09-02,15:30,Parc Jean,Outremont,Cambriolage\n");
            // Ajoutez autant de lignes que nécessaire pour votre cas de test
        }

        // Appelez la méthode que vous souhaitez tester
        ArrayList<String> result = Entree.extraireDonnees(entreeFile.getPath());

        // Ajoutez des assertions en fonction de votre logique
        assertFalse(result.isEmpty());
        assertEquals(2, result.size()); // Vérifiez le nombre d'interventions extraites
        assertEquals("2023-09-01,20:41,Parc Camille,Ahuntsic-Cartierville,Vente de drogues", result.get(0));
        assertEquals("2023-09-02,15:30,Parc Jean,Outremont,Cambriolage", result.get(1));
    }

}










