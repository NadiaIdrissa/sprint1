public class TestFonctionnel {
    /*@Test
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

        exit.expectSystemExitWithStatus(0);

        // Exécuter le programme principal
        ProgrammePrincipal.main(new String[]{entreeCsvPath.toString(), sortieCsvPath.toString()});

        // Vérifier les résultats dans le fichier de sortie
        List<String> lines = Files.readAllLines(sortieCsvPath);
        assertEquals(1, lines.size());
        assertEquals("Ahuntsic-Cartierville,0,0", lines.get(0));

        // Nettoyer les fichiers temporaires
        Files.deleteIfExists(entreeCsvPath);
        Files.deleteIfExists(sortieCsvPath);
    }

    @Test
    public void testFichierIntrouvable() {
        exit.expectSystemExitWithStatus(0);

        // Rediriger la sortie standard pour capturer les messages
        System.out.println("Redirection de la sortie standard");
        System.setOut(new PrintStream(new ByteArrayOutputStream()));

        // Exécuter le programme principal avec un fichier introuvable
        ProgrammePrincipal.main(new String[]{"fichierInexistant.csv", "sortieTest.csv"});
    }*/
}
