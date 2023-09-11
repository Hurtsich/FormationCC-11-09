package zenika.solid.ocp;

public class Triangle implements Figure {
    public final int base;
    public final int hauteur;
    public final int cote2;
    public final int cote3;

    public Triangle(int base, int hauteur, int cote2, int cote3) {
        this.base = base;
        this.hauteur = hauteur;
        this.cote2 = cote2;
        this.cote3 = cote3;
    }
}