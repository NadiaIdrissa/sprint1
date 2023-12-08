import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.After;
import org.junit.Before;
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

import static java.nio.file.Files.deleteIfExists;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFonctionnel {
    @Test
    void testExecutionReussie() throws IOException {
        // Créer un fichier d'entrée CSV simulé
        Path entreeCsvPath = Files.createTempFile("executionReussiTest", ".csv");
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
        assertEquals(2, lines.size());
        assertEquals("Arrondissement,Nombre d'interventions, Nombre de parcs ", lines.get(0));
        assertEquals("Ahuntsic-Cartierville,1,1", lines.get(1));

        // Nettoyer les fichiers temporaires
        Files.deleteIfExists(sortieCsvPath);
    }
/*
    @Test
    public void testExcutionAnglaisReussie() throws IOException {
        // Créer un fichier d'entrée CSV et de sortie CSV
        String entreeCSVAnglais = "entreeTestAnglaisReussi.csv";

        List<String> donnee  = new ArrayList<>();
        donnee.add("2023-09-02,15:30,Parc Jean,Outremont,Vente de drogues");


        Path fichierTempAnglais = Files.createTempFile("fichierAnglaisReussi ",".csv");
        Files.write(fichierTempAnglais,donnee);

        ProgrammePrincipal.main(new String[]{"--english", entreeCSVAnglais, fichierTempAnglais.toString()});
        // verifie les messages afficher dans la console
        PrintStream affichageConsole = System.out;

        System.setOut(new PrintStream(affichageConsole));
        assertEquals("\n Successful execution. The results are saved to the file  fichierAnglaisReussi.csv.","one");
        List<String> sortieEnAnglaisReussi= Files.readAllLines(Paths.get(fichierTempAnglais.toUri()));
        List<String> fichierEnAnglaisAttendu = new ArrayList<>();
        fichierEnAnglaisAttendu.add("Number of districts,Number of interventions, Number of parks ");
        fichierEnAnglaisAttendu.add("Ahuntsic-Cartierville,1,1");

        assertEquals(fichierEnAnglaisAttendu.get(0),sortieEnAnglaisReussi.get(0));
        //assertEquals(fichierEnAnglaisAttendu.get(1),sortieEnAnglaisReussi.get(1));

        Files.deleteIfExists(fichierTempAnglais);

    }

    @Test
    public void testFichierIntrouvable() {

        String messageAttendu = " \n Erreur: Fichier introuvable - fichierErronne.csv (The system cannot find the file specified)";

        ByteArrayOutputStream sortie = new ByteArrayOutputStream();
        ByteArrayOutputStream erreurSortie = new ByteArrayOutputStream();
        PrintStream sortieOriginal = System.out;
        PrintStream errConsoleOriginal = System.err;

        //Lancement du programme
        try {
            ProgrammePrincipal.main(new String[]{"fichierErronne.csv", "sortieTest.csv"});
            System.setOut(new PrintStream(sortie));
            System.setErr(new PrintStream(erreurSortie));
            String affichageConsole = sortie.toString();
            // capture du message d'erreur
            String affichageErreurConsole = erreurSortie.toString();

            assertEquals(messageAttendu, affichageConsole.trim());
            assertEquals(errConsoleOriginal, affichageErreurConsole.trim());
        }finally {

            System.setOut(sortieOriginal);
            System.setErr(errConsoleOriginal);
        }
    }
    */
}
