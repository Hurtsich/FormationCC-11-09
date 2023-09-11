package zenika.solid.ocp;

import java.util.List;

public class CalculateurPerimetres {
    public double sommePerimetres(List<Figure> figures) {
        var perimetre = 0d;
        for(Figure figure : figures) {
            if(figure instanceof Rectangle) {
                var rectangle = (Rectangle)figure;
                perimetre += (rectangle.largeur + rectangle.longueur) * 2;
            } else if(figure instanceof Triangle) {
                var triangle = (Triangle)figure;
                perimetre += (triangle.base + triangle.cote2 + triangle.cote3);
            }
        }
        return perimetre;
    }
}
