import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFonctionnel {
    @Test
    void testExecutionReussie() throws IOException {
        // Créer un fichier d'entrée CSV simulé
        Path entreeCsvPath = Files.createTempFile("entreeTest", ".csv");
        FileWriter entreeCsvWriter = new FileWriter(entreeCsvPath.toFile());
        entreeCsvWriter.write("2023-09-01,20:41,Parc Camille,Ahuntsic-Cartierville,Vente de drogues\n");
        entreeCsvWriter.close();

        // Créer un fichier de sortie CSV simulé
        Path sortieCsvPath = Files.createTempFile("sortieTest", ".csv");

        // Rediriger la sortie standard pour capturer les messages
        System.out.println("Redirection de la sortie standard");
        System.setOut(new PrintStream(new ByteArrayOutputStream()));

        //exit.expectSystemExitWithStatus(0);

        // Exécuter le programme principal
        ProgrammePrincipal.main(new String[]{entreeCsvPath.toString(), sortieCsvPath.toString()});

        // Vérifier les résultats dans le fichier de sortie
        List<String> lines = Files.readAllLines(sortieCsvPath);
        assertEquals(1, lines.size());
        assertEquals("Ahuntsic-Cartierville,1,1", lines.get(0));

        // Nettoyer les fichiers temporaires
        Files.deleteIfExists(entreeCsvPath);
        Files.deleteIfExists(sortieCsvPath);
    }

    @Test
    public void testExcutionAnglaisReussie() throws IOException {
        // Créer un fichier d'entrée CSV et de sortie CSV
        String entreeCSVAnglais = "entreeTestAnglaisReussi.csv";

        List<String> donnee  = new ArrayList<>();
        donnee.add("2023-09-02,15:30,Parc Jean,Outremont,Vente de drogues");


        Path fichierTempAnglais = Files.createTempFile("fichierAnglaisReussi ",".csv");
        Files.write(fichierTempAnglais,donnee);

        ProgrammePrincipal.main(new String[]{"--english", entreeCSVAnglais.toString(), fichierTempAnglais.toString()});
        // verifie les messages afficher dans la console
        PrintStream affichageConsole = System.out;

        System.setOut(new PrintStream(affichageConsole));
        assertEquals("\n Successful execution. The results are saved to the file  fichierAnglaisReussi.csv.","one");
        List<String> sortieEnAnglaisReussi= Files.readAllLines(Paths.get(fichierTempAnglais.toUri()));
        List<String> fichierEnAnglaisAttendu = new ArrayList<>();
        fichierEnAnglaisAttendu.add("Number of districts,Number of interventions, Number of parks ");
        fichierEnAnglaisAttendu.add("Ahuntsic-Cartierville,1,1");

        assertEquals(fichierEnAnglaisAttendu.get(0),sortieEnAnglaisReussi.get(0));
        assertEquals(fichierEnAnglaisAttendu.get(1),sortieEnAnglaisReussi.get(1));

        Files.deleteIfExists(fichierTempAnglais);

    }

    @Test
    public void testFichierIntrouvable() {

        String messageAttendu = " \n Erreur: Fichier introuvable - ";
        String fichierErronne = "fichierErronne.csv";
        ByteArrayOutputStream sortie = new ByteArrayOutputStream();
        ByteArrayOutputStream erreurSortie = new ByteArrayOutputStream();
        PrintStream sortieOriginal = System.out;
        PrintStream errConsoleOriginal = System.err;
        System.setOut(new PrintStream(sortie));
        System.setErr(new PrintStream(erreurSortie));


        //Lancement du programme
        ProgrammePrincipal.main(new String[]{fichierErronne, "sortieTest.csv"});

        String affichageConsole = sortie.toString().trim();
        String affichageErreurConsole= erreurSortie.toString().trim();

        assertEquals(messageAttendu, affichageConsole);
        assertEquals("Expected error message", affichageErreurConsole);


        System.setOut(sortieOriginal);
        System.setErr(errConsoleOriginal);
    }
}
