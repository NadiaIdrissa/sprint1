import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFonctionnel {
    @Test
    void testExecutionReussie() throws IOException {
        // Créer un fichier d'entrée CSV simulé
        Path entreeCsvPath = Files.createTempFile("executionReussiTest", ".csv");
        FileWriter entreeCsvWriter = new FileWriter(entreeCsvPath.toFile());
        entreeCsvWriter.write("Date,Heure,Parc,Arrondissement,Description\n");
        entreeCsvWriter.write("2023-09-01,20:41,Parc Camille,Ahuntsic-Cartierville,Vente de drogues\n");
        entreeCsvWriter.close();

        // Créer un fichier de sortie CSV simulé
        Path sortieCsvPath = Files.createTempFile("sortieTest", ".csv");

        // Rediriger la sortie standard pour capturer les messages
        System.out.println("Redirection de la sortie standard");
        System.setOut(new PrintStream(new ByteArrayOutputStream()));

        // Exécuter le programme principal
        ProgrammePrincipal.main(new String[]{entreeCsvPath.toString(), sortieCsvPath.toString()});

        // Vérifier les résultats dans le fichier de sortie
        List<String> lines = Files.readAllLines(sortieCsvPath);

        assertEquals(2, lines.size());
        assertEquals("Arrondissement,Nombre d'interventions, Nombre de parcs ", lines.get(0));
        assertEquals("Ahuntsic-Cartierville,1,1", lines.get(1));

        // Nettoyer les fichiers temporaires
        Files.deleteIfExists(sortieCsvPath);
    }

    @Test
    public void testExcutionAnglaisReussie() throws IOException {
        // Créer un fichier d'entrée CSV et de sortie CSV
        Path  entreeCSVAnglais = Files.createTempFile("executionReussiTest", ".csv");
        FileWriter entreeCsvWriter = new FileWriter( entreeCSVAnglais.toFile());
        entreeCsvWriter.write("2023-09-01,20:41,Parc Camille,Outremont,Vente de drogues\n");
        entreeCsvWriter.close();


        List<String> donnee  = new ArrayList<>();
        donnee.add("2023-09-02,15:30,Parc Jean,Outremont,Vente de drogues");


        Path fichierTempAnglais = Files.createTempFile("fichierAnglaisReussi ",".csv");
        Files.write(fichierTempAnglais,donnee);
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        ProgrammePrincipal.main(new String[]{"--english", entreeCSVAnglais.toString(), fichierTempAnglais.toString()});


        // verifie les messages afficher dans la console

        List<String> sortieEnAnglaisReussi= Files.readAllLines(fichierTempAnglais);
        // formatage du fichier Anglais
        List<String> fichierEnAnglaisAttendu = new ArrayList<>();
        fichierEnAnglaisAttendu.add("Number of districts,Number of interventions, Number of parks ");
        fichierEnAnglaisAttendu.add("Outremont,1,1");

        // test la grandeur et le contenu du fichier
        assertEquals(2, fichierEnAnglaisAttendu.size());
        assertEquals(fichierEnAnglaisAttendu.get(0),sortieEnAnglaisReussi.get(0));
        assertEquals(fichierEnAnglaisAttendu.get(1),sortieEnAnglaisReussi.get(1));

        // fermeture du fichier temporaire
        Files.deleteIfExists(fichierTempAnglais);

    }

}
