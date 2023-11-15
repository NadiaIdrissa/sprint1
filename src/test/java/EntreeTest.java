
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;


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
    void TestDescriptionImcomplete() {
        ArrayList<String> donnees = new ArrayList<>();


        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorStream));
        Throwable exception = assertThrows(RuntimeException.class, () -> Entree.convertirEnIntervention(Entree.extraireDonnees("DescriptionImcomplete.csv")));
        String actualErrorOutput = errorStream.toString().trim();
        System.setErr(System.err);
        // Vérifie que la sortie d'erreur n'est pas vide
        assert errorStream.toString().trim().length() > 0;

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
        assertEquals("Erreur : Intervetion incorrecte", actualOutput);

    }


    @Test
    void TesterMauvaisArrondissement(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        try {
            Entree.convertirEnIntervention(Entree.extraireDonnees("MauvaisArromndissement.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String actualOutput = outputStream.toString().trim();
        System.setOut(System.out);
        // Compare la sortie avec les attentes
        assertEquals("Erreur : Arrondissement incorrect", actualOutput);

    }
}






