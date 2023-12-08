
import org.junit.Assert;
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
    void TesterMauvaiseInterventionETArrondissement() throws FileNotFoundException {


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(outputStream));
        try {
            Entree.convertirEnIntervention(Entree.extraireDonnees("MauvaiseIntervention.csv"));
            Entree.convertirEnIntervention(Entree.extraireDonnees("MauvaisArrondissement.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String actualOutput = outputStream.toString().trim();

        // Restaurer la sortie standard et d'erreur standard
        System.setOut(System.out);
        System.setErr(System.err);

        // Compare la sortie avec les attentes
        assertTrue(actualOutput.contains("Erreur : Intervention incorrecte"));
        assertTrue(actualOutput.contains("Erreur : Arrondissement incorrect"));

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


    @Test
    void testConvertirEnIntervention() {
        // Créer des données simulées
        ArrayList<String> donnees = new ArrayList<>();
        donnees.add("2023-09-01,20:41,Parc Camille,Ahuntsic-Cartierville,Vente de drogues");
        donnees.add("2023-08-26,23:11,Parc Brook,Pierrefonds-Roxboro,Vente de drogues");

        // Appeler la méthode à tester
        ArrayList<Intervention> interventions = Entree.convertirEnIntervention(donnees);

        // Vérifier le résultat
        Assert.assertEquals(2, interventions.size());
    }


    @Test
    void testExtraireDonneesFormatIncorrect() {
        // Créer un fichier temporaire avec une ligne incorrecte
        File fichierIncorrect = new File("FichierFormatIncorrect.csv");
        try (PrintWriter writer = new PrintWriter(fichierIncorrect)) {
            // Ajouter une ligne incorrecte (par exemple, moins de champs que prévu)
            writer.println("2023-09-01,20:41,Parc Camille,Ahuntsic-Cartierville");
        } catch (IOException e) {
            fail("Erreur inattendue : " + e.getMessage());
        }

        // Effectuer le test
        try {
            Entree.extraireDonnees(fichierIncorrect.getAbsolutePath());
        } catch (Exception e) {
            // Vérifier le message d'erreur
            assertEquals("Le format du fichier '" + fichierIncorrect.getAbsolutePath() + "' à la ligne 1 est incorrect.", e.getMessage());
        }
    }




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
