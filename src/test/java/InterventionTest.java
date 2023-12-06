import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.io.BufferedReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class InterventionTest {
    @Test
    void testValiderArrondissementExiste() throws IOException {

        BufferedReader mockBufferedReader = Mockito.mock(BufferedReader.class);
        Intervention intervention = new Intervention();
        intervention.setBufferedReaderArrondissement(mockBufferedReader);

        when(mockBufferedReader.readLine()).thenReturn("Ahuntsic-Cartierville", "Plateau-Mont-Royal");

        assertTrue(Intervention.validerArrondissement("Ahuntsic-Cartierville"));
    }

    @Test
    void testValiderArrondissementExistePas() throws IOException {

        BufferedReader mockBufferedReader = Mockito.mock(BufferedReader.class);
        Intervention intervention = new Intervention();
        intervention.setBufferedReaderArrondissement(mockBufferedReader);

        when(mockBufferedReader.readLine()).thenReturn("Ahuntsic-Cartierville", "Plateau-Mont-Royal");

        assertFalse(Intervention.validerArrondissement("Rosemont-La Petite-Patrie"));
    }

    @Test
    void testValiderDescriptionExiste() throws IOException {

        BufferedReader mockBufferedReader = Mockito.mock(BufferedReader.class);
        Intervention intervention = new Intervention();
        intervention.setBufferedReaderDescription(mockBufferedReader);

        when(mockBufferedReader.readLine()).thenReturn("Vente de drogues", "Vol");

        assertTrue(Intervention.validerDescription("Vente de drogues"));
    }

    @Test
    void testValiderDescriptionExistePas() throws IOException {

        BufferedReader mockBufferedReader = Mockito.mock(BufferedReader.class);
        Intervention intervention = new Intervention();
        intervention.setBufferedReaderDescription(mockBufferedReader);

        when(mockBufferedReader.readLine()).thenReturn("Vente de drogues", "Vol");

        assertFalse(Intervention.validerDescription("Homicide"));
    }
}


