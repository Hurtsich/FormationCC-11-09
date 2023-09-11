package zenika.solid.ocp;

public class Rectangle implements Figure {
    public final int longueur;
    public final int largeur;

    public Rectangle(int longueur, int largeur) {
        this.longueur = longueur;
        this.largeur = largeur;
    }

    @Override
    public double aire() {
        return (largeur * longueur);
    }

    @Override
    public double perimetre() {
        return (2 * (largeur+longueur));
    }
}
