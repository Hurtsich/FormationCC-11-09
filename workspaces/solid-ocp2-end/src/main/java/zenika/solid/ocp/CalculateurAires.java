package zenika.solid.ocp;

import java.util.List;

public class CalculateurAires {
    public double sommeAires(List<Figure> figures) {
        return figures.stream().mapToDouble(Figure::aire).sum();
    }
}
