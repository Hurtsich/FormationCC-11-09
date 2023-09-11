package zenika.solid.ocp;

import java.util.List;

public class CalculateurPerimetres {
    public double sommePerimetres(List<Figure> figures) {
        return figures.stream().mapToDouble(Figure::perimetre).sum();
    }
}
