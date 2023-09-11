package zenika.solid.ocp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FigureTest {
    @Test
    public void calculationTest() {
        List<Figure> figures = new ArrayList<Figure>();
        figures.add(new Rectangle(8, 6));
        figures.add(new Triangle(5,3,3,4));
        // TODO : ajouter une figure de type cercle de rayon 4

        assertEquals(55.5, new CalculateurAires().sommeAires(figures), 1);
        assertEquals(40, new CalculateurPerimetres().sommePerimetres(figures), 1);

    }
}
