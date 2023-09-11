package zenika.solid.ocp;

import java.util.List;

public class CalculateurAires {
    public double sommeAires(List<Figure> figures) {
        var aire = 0d;
        for(Figure figure : figures) {
            if(figure instanceof Rectangle) {
                var rectangle = (Rectangle)figure;
                aire += (rectangle.largeur * rectangle.longueur);
            } else if(figure instanceof Triangle) {
                var triangle = (Triangle)figure;
                aire += (double)(triangle.base * triangle.hauteur) / 2;
            } else if(figure instanceof Cercle) {
                var cercle = (Cercle)figure;
                aire += Math.PI * Math.pow(cercle.rayon, 2);
            }
        }
        return aire;
    }
}
