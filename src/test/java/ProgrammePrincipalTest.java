/*import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import java.util.List;

public class ProgrammePrincipalTest {

    @Test
    void testExecutionReussie() throws IOException {
        Entree mockEntree = mock(Entree.class);
        Sortie mockSortie = mock(Sortie.class);

        when(mockEntree.extraireDonnees(anyString())).thenReturn(new ArrayList<>());
        when(mockEntree.convertirEnIntervention(any())).thenReturn(new ArrayList<>());

        ProgrammePrincipal.executerProgramme(mockEntree, mockSortie, "Entree.csv", "SortieFichiertest.csv");

        verify(mockEntree).extraireDonnees("Entree.csv");
        verify(mockEntree).convertirEnIntervention(any());
        verify(mockSortie).sauvegarderListeDesInterventionsDansSortieCSV(any(ArrayList.class), eq("SortieFichiertest.csv"));
    }

    @Test
    void testFichierIntrouvable() throws FileNotFoundException {
        // Créez des mocks pour les dépendances
        Entree mockEntree = mock(Entree.class);
        Sortie mockSortie = mock(Sortie.class);

        // Configurez le comportement des mocks selon vos besoins
        doThrow(new FileNotFoundException("Fichier introuvable")).when(mockEntree).extraireDonnees(anyString());

        // Appelez la méthode principale avec des mocks
        ProgrammePrincipal.executerProgramme(mockEntree, mockSortie, "Entree.csv", "SortieFichiertest.csv");

        // Vérifiez que le message d'erreur approprié a été affiché
        //verify(mockSortie, never()).sauvegarderListeDesInterventionsDansSortieCSV((ArrayList<Intervention>) Mockito.any(List.class), anyString());
        verify(mockSortie, never()).sauvegarderListeDesInterventionsDansSortieCSV(any(), anyString());
        //verify(mockSortie, never()).sauvegarderListeDesInterventionsDansSortieCSV(anyList(), anyString());
        verify(System.out).println("Fichier introuvable");
    }

    @Test
    void testErreurEntreeSortie() throws IOException {
        // Créez des mocks pour les dépendances
        Entree mockEntree = mock(Entree.class);
        Sortie mockSortie = mock(Sortie.class);

        // Configurez le comportement des mocks selon vos besoins
        doThrow(new IOException("Erreur d'entrée/sortie")).when(mockEntree).extraireDonnees(anyString());

        // Appelez la méthode principale avec des mocks
        ProgrammePrincipal.executerProgramme(mockEntree, mockSortie, "Entree.csv", "SortieFichiertest.csv");

        // Vérifiez que le message d'erreur approprié a été affiché
        verify(mockSortie, never()).sauvegarderListeDesInterventionsDansSortieCSV(any(), anyString());
        //verify(mockSortie, never()).sauvegarderListeDesInterventionsDansSortieCSV((ArrayList<Intervention>) any(List.class), anyString());
        //verify(mockSortie, never()).sauvegarderListeDesInterventionsDansSortieCSV(ArgumentMatchers.<ArrayList<Intervention>>anyList(), anyString());

        verify(System.err).println("Erreur d'entrée/sortie - Erreur d'entrée/sortie");
    }
}*/
