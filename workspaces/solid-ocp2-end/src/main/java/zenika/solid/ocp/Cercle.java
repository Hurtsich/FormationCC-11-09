package zenika.solid.ocp;

public class Cercle implements Figure {
    public int rayon;

    public Cercle(int rayon) {
        this.rayon = rayon;
    }

    @Override
    public double aire() {
        return Math.PI * Math.pow(rayon, 2);
    }

    @Override
    public double perimetre() {
        return 2 * Math.PI * rayon;
    }
}
