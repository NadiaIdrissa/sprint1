import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TestAvecMockObject {
    @SuppressWarnings("unchecked")
    @Test
    void testValidationArrondissementEtDescription() throws IOException {

        BufferedReader mockBufferedReader = mock(BufferedReader.class);
        Intervention intervention = new Intervention();

        // Définir les attentes pour la validation de la description
        when(mockBufferedReader.readLine()).thenReturn("Vente de drogues", "Vol");

        // Définir les attentes pour la validation de l'arrondissement
        when(mockBufferedReader.readLine()).thenReturn("Ahuntsic-Cartierville", "Plateau-Mont-Royal");

        // Tester la validation de la description
        assertTrue(Intervention.validerDescription("Vente de drogues"));
        assertFalse(Intervention.validerDescription("Homicide"));

        // Tester la validation de l'arrondissement
        assertTrue(Intervention.validerArrondissement("Ahuntsic-Cartierville"));
        assertFalse(Intervention.validerArrondissement("Rosemont-La Petite-Patrie"));


    }

    @Test
    void testExtraireDonnees() throws IOException {
        // Créer des données simulées
        ArrayList<String> donnees = new ArrayList<>();
        donnees.add("2023-09-01,20:41,Parc Camille,Ahuntsic-Cartierville,Vente de drogues");
        donnees.add("2023-08-26,23:11,Parc Brook,Pierrefonds-Roxboro,Vente de drogues");

        BufferedReader mockBufferedReader = mock(BufferedReader.class);

        // Configurer le comportement du mockBufferedReader pour simuler la lecture du fichier CSV
        when(mockBufferedReader.readLine()).thenReturn(String.join("\n", donnees), null);

        // Appeler la méthode à tester
        ArrayList<String> result = Entree.extraireDonnees("Entree.csv");

        // Vérifier le résultat
        assertEquals(2, result.size());


    }

}

